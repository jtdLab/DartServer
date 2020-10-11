package dartServer.networking.artefacts;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import dartServer.networking.artefacts.responses.AuthResponse;
import dartServer.networking.artefacts.responses.GameSnapshot;

public class ContainerEncoder {

    private static final Gson gson = new Gson();

    public static String encode(Payload payload) {
        try {
            Container container;

            if (payload instanceof AuthResponse) {
                container = new Container("authResponse", payload);
            } else if(payload instanceof GameSnapshot) {
                container = new Container("gameSnapshot", payload);
            } else {
                container = null;
            }
            // TODO

            if (container != null) {
                return gson.toJson(container, Container.class);
            }

        } catch (JsonParseException e) {
            // TODO invalid json received add log
        }

        return null;
    }
}
