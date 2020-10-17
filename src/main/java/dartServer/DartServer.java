package dartServer;


import dartServer.networking.WebSocketServer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.apache.logging.log4j.spi.StandardLevel;


// {"payloadType":"authRequest","payload":{"username":"mrjosch","password":"sanoj050499"},"timestamp":"2020-10-17 03:38:16.44"}

/**
 * This is the main class to start the Dart server from the command line
 * Commandline Options are implemented with picocli
 * <p>
 * See https://picocli.info/
 */
@CommandLine.Command(name = "DartServer", version = "1.0")
class DartServer implements Runnable {

    private static final Logger logger = LogManager.getLogger(DartServer.class);

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Help")
    private static boolean help;

    @CommandLine.Option(names = {"-p", "--port"}, description = "Server Port (default=4488)")
    private static int port = 9000;

    @CommandLine.Option(names = {"-v", "--verbosity"}, description = "Verbosity (0=off, 100=fatal, ..., 600=trace)")
    private static int verbosity = 400;

    /**
     * Start the server by parsing the commandline options
     *
     * @param args Run arguments
     */
    public static void main(String... args) {
        System.out.println(
                " _____             _    _____                          \n" +
                        "|  __ \\           | |  / ____|                         \n" +
                        "| |  | | __ _ _ __| |_| (___   ___ _ ____   _____ _ __ \n" +
                        "| |  | |/ _` | '__| __|\\___ \\ / _ \\ '__\\ \\ / / _ \\ '__|\n" +
                        "| |__| | (_| | |  | |_ ____) |  __/ |   \\ V /  __/ |   \n" +
                        "|_____/ \\__,_|_|   \\__|_____/ \\___|_|    \\_/ \\___|_|   \n" +
                        "                                                       "
        );

        CommandLine.run(new DartServer(), System.out);
    }

    /**
     * Initialize the server
     */
    public void run() {
        // Print the specified command line options

        System.out.println();

        System.out.println("Starting with options:");
        System.out.println("> Server port: " + port);
        Level level = Level.getLevel(StandardLevel.getStandardLevel(verbosity).name());
        System.out.println("> Logging verbosity: " + level.name());
        Configurator.setLevel(System.getProperty("log4j.logger"), level);

        System.out.println();

        logger.info("Initializing game engine...");
        // TODO GameEngine.init(matchConfig);

        try {
            logger.info("Starting websocket server...");
            new WebSocketServer(port).run();
        } catch (Exception e) {
            logger.warn(e);
        }
    }
}