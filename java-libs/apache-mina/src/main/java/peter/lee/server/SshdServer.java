package peter.lee.server;

import org.apache.sshd.common.io.nio2.Nio2ServiceFactoryFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.shell.InteractiveProcessShellFactory;
import org.apache.sshd.server.shell.ProcessShellCommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;

public final class SshdServer {
    private static final Logger logger = LoggerFactory.getLogger(SshdServer.class);
    private static final String BIND_ADDRESS = "localhost";
    private static final int SSHD_PORT = 2224;
    public static void main(String[] args) {
        logger.info("Welcome to SSHD Server ");
        start();
    }

    public static void start() {
        try (var sshServer = SshServer.setUpDefaultServer()) {
            sshServer.setHost(BIND_ADDRESS);
            sshServer.setPort(SSHD_PORT);
            sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
            sshServer.setPasswordAuthenticator(new LoggingPasswordAuthenticator());
            sshServer.setCommandFactory(new ProcessShellCommandFactory());
            sshServer.setShellFactory(new InteractiveProcessShellFactory());
            sshServer.setIoServiceFactoryFactory(new Nio2ServiceFactoryFactory());
            sshServer.start();

            logger.info("Server started with port {}", sshServer.getPort());
            final var scanner = new Scanner(System.in);
            var quit = false;
            while (!quit) {
                while (scanner.hasNextLine()) {
                    final var command = scanner.nextLine();
                    if ("q".equals(command) || "quit".equals(command)) {
                        logger.info("Server exiting ...");
                        sshServer.stop();
                        quit = true;
                    }
                }
            }
            logger.info("Server exited");
        } catch (IOException e) {
            logger.error("Server start fail ", e);
            System.exit(1);
        }
    }

    private SshdServer() {
    }
}
