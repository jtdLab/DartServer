package dartServer.gameengine.listeners;

import dartServer.api.services.AuthService;
import dartServer.api.services.IsOnlineService;
import dartServer.commons.packets.incoming.requests.AuthRequestPacket;
import dartServer.commons.packets.outgoing.unicasts.AuthResponsePacket;
import dartServer.gameengine.GameEngine;
import dartServer.gameengine.lobby.User;
import dartServer.networking.events.Event;
import dartServer.networking.events.NetworkEventListener;
import dartServer.networking.events.PacketReceiveEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener class for the event of a new authRequest by a client.
 */
public class AuthenticationListener implements NetworkEventListener {

    static final Logger logger = LoggerFactory.getLogger(AuthenticationListener.class);

    /**
     * Debug event.
     *
     * @param event the event fired on authRequest by a client
     */
    @Event
    public void onAuth(PacketReceiveEvent<AuthRequestPacket> event) {
        AuthRequestPacket authRequest = event.getPacket();

        boolean authenticated = AuthService.authenticate(authRequest);

        if (authenticated) {
            User user = GameEngine.createUser(authRequest.getUid(), authRequest.getUsername(), event.getClient());
            if (user != null) {
                user.sendMessage(new AuthResponsePacket(true));
                IsOnlineService.updateIsOnline(user.getUid(), true);
                logger.info(authRequest.getUsername() + " joined");
            } else {
                event.getClient().sendPacket(new AuthResponsePacket(false));
            }
        } else {
            event.getClient().sendPacket(new AuthResponsePacket(false));
        }
    }

}
