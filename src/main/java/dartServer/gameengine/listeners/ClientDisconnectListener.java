package dartServer.gameengine.listeners;

import dartServer.api.services.IsOnlineService;
import dartServer.gameengine.GameEngine;
import dartServer.gameengine.lobby.Lobby;
import dartServer.gameengine.lobby.User;
import dartServer.networking.events.ClientDisconnectEvent;
import dartServer.networking.events.Event;
import dartServer.networking.events.NetworkEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener class for the event of a socket connection being closed by client or server.
 */
public class ClientDisconnectListener implements NetworkEventListener {

    static final Logger logger = LoggerFactory.getLogger(ClientDisconnectListener.class);

    /**
     * Event called on a disconnect event. Will be called regardless of who closed the connection.
     * If the user is an active player and loses the connection client-side, the match will remain.
     * If the server closes the connection to an active player (kick), the match is ended and the other player awarded the victory.
     *
     * @param event
     */
    @Event
    public void onDisconnect(ClientDisconnectEvent event) {
        User user = GameEngine.getUser(event.getClient().getAddress());

        if (user == null) {
            logger.trace(event.getClient().getAddress() + " disconnected");
            return;
        }

        if (user.isPlaying()) {
            if (event.getClient().isKicked()) {
                GameEngine.removeUser(user);
                logger.info(user.getUsername() + " got kicked");
            } else {
                //user.setClient(null); // remove client because disconnected
                Lobby lobby = GameEngine.getLobbyByUser(user);
                // TODO if game is canceld and then user disconnects error appears
                if(user.equals(lobby.getOwner())) {
                    GameEngine.removeLobby(lobby);
                    // TODO cancel game
                    // TODO send messages to other players to show them they game is over
                }
                GameEngine.removeUser(user);
                logger.info(user.getUsername() + " left");
            }
        } else {
            GameEngine.removeUser(user);
            logger.info(user.getUsername() + " left");
        }

        IsOnlineService.updateIsOnline(user.getUid(), false);
    }

}
