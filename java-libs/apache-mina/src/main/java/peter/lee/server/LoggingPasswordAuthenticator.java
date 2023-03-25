package peter.lee.server;

import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.session.ServerSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingPasswordAuthenticator implements PasswordAuthenticator {
    private static final Logger logger = LoggerFactory.getLogger(LoggingPasswordAuthenticator.class);

    @Override
    public boolean authenticate(String username, String password, ServerSession session) {
        logger.info("Login by user {} with password {} \n", username, password);
        return true;
    }
}
