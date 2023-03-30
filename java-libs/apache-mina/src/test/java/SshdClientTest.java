import static org.assertj.core.api.Assertions.assertThat;

import org.apache.sshd.common.io.nio2.Nio2ServiceFactoryFactory;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.shell.ProcessShellCommandFactory;
import org.apache.sshd.server.shell.ProcessShellFactory;
import org.junit.jupiter.api.Test;

import peter.lee.client.SshdClient;
import peter.lee.server.LoggingPasswordAuthenticator;

class SshdClientTest {
    SshdClient client = new SshdClient();

    @Test
    void getFileList() throws Exception {
        try (SshServer sshServer = SshServer.setUpDefaultServer()) {
            sshServer.setKeyPairProvider(new SimpleGeneratorHostKeyProvider());
            sshServer.setPasswordAuthenticator(new LoggingPasswordAuthenticator());
            sshServer.setCommandFactory(new ProcessShellCommandFactory());
            sshServer.setShellFactory(new ProcessShellFactory("/bin/sh -i -l", "/bin/sh", "-i", "-l"));
            sshServer.setIoServiceFactoryFactory(new Nio2ServiceFactoryFactory());
            sshServer.start();
            final String files = client.getFileList("localhost", sshServer.getPort(), "thachle", "password", 100);
            assertThat(files).isNotEmpty()
                             .contains("build.gradle", "README.md");
        }
    }
}
