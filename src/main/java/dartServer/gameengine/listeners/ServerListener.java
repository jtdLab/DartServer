package dartServer.gameengine.listeners;

import dartServer.commons.packets.incoming.requests.CreateGamePacket;
import dartServer.commons.packets.incoming.requests.JoinGamePacket;
import dartServer.gameengine.GameEngine;
import dartServer.gameengine.lobby.User;
import dartServer.networking.events.Event;
import dartServer.networking.events.NetworkEventListener;
import dartServer.networking.events.PacketReceiveEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Listener class for server events.
 */
public class ServerListener implements NetworkEventListener {

    static final Logger logger = LogManager.getLogger(ServerListener.class);

    /**
     * @param event the event fired on createGame by a client
     */
    @Event
    public void onCreateGame(PacketReceiveEvent<CreateGamePacket> event) {
        logger.warn("onCreateGame");
    }

    /**
     * @param event the event fired on joinGame by a client
     */
    @Event
    public void onJoinGame(PacketReceiveEvent<JoinGamePacket> event) {
        logger.warn("onJoin");
        /*

        User user = GameEngine.getUserByName(event.getPacket().getUserName());
        if (user != null) {
            // A user with that name already exists
            // We need to drop the old connection
            logger.info("User " + user.getName() + " connected from a new client!");
            user.getClient().sendPacket(new PrivateDebugPacket("You get kicked because you joined from another connection."));
            user.getClient().disconnect();
            user.setClient(event.getClient());
            GameEngine.updateUserAddress(event.getClient().getAddress(), user);
            GameEngine.broadcastToLobby(user.getLobbyName(), new LoginGreetingPacket(user.getName()));
            // send ReconnectPacket
            user.sendMessage(GameEngine.getLobbyByName(user.getLobbyName()).getReconnectPacket());
        } else {
            if (GameEngine.isLobby(event.getPacket().getLobby())) {
                user = new User(event.getClient(), event.getPacket().getUserName(), event.getPacket().getLobby(), event.getPacket().getPassword(), event.getPacket().isArtificialIntelligence());
                logger.info("New user " + user.getName() + " joined the game!");
                logger.info("Valid lobby name, add user to lobby.");
                GameEngine.addUser(event.getClient().getAddress(), user);
                // TODO GameEngine.broadcastToLobby(event.getPacket().getLobby(), new LoginGreetingPacket(user.getName()));
                user.sendMessage(new JoinGamePacket("Welcome to lobby " + event.getPacket().getLobby()));
            } else {
                logger.warn("User wanted to join unknown lobby!");
                event.setRejected(true, "Login was denied because the lobby does not exist.");
            }
        }

         */
    }

}