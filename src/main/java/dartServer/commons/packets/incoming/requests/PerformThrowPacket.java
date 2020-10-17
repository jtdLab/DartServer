package dartServer.commons.packets.incoming.requests;

import dartServer.commons.packets.incoming.RequestPacket;
import dartServer.gameengine.model.Throw;

import javax.validation.constraints.NotNull;

/**
 * Packet for a client to request performing a throw.
 */
public class PerformThrowPacket implements RequestPacket {

    // TODO add throw validation annotation

    @NotNull
    private Throw t;

    public PerformThrowPacket(Throw t) {
        this.t = t;
    }

    public Throw getT() {
        return t;
    }

    @Override
    public String toString() {
        return "PerformThrow{" +
                "t='" + toString() +'\'' +
                "}";
    }

}