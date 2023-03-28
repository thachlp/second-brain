package peter.lee.client;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.channel.Channel;

public class SshdClient {
    public String getFileList(String host, int port, String username,
                              String password, long defaultTimeout) throws Exception {
        final SshClient client = SshClient.setUpDefaultClient();
        client.start();
        try (ClientSession session = client.connect(username, host, port)
                                           .verify(defaultTimeout, TimeUnit.SECONDS)
                                           .getSession()) {
            session.addPasswordIdentity(password);
            session.auth()
                   .verify(defaultTimeout, TimeUnit.SECONDS);
            try (ByteArrayOutputStream responseStream =
                         new ByteArrayOutputStream();
                 ByteArrayOutputStream errorStream =
                         new ByteArrayOutputStream();
                 ClientChannel channel =
                         session.createChannel(Channel.CHANNEL_SHELL)){
                channel.setOut(responseStream);
                channel.setErr(errorStream);
                try {
                    channel.open()
                           .verify(defaultTimeout, TimeUnit.SECONDS);
                    try (OutputStream pipedIn = channel.getInvertedIn()) {
                        pipedIn.write("ls\n".getBytes());
                        pipedIn.flush();
                    }
                    channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED),
                                    TimeUnit.SECONDS.toMillis(defaultTimeout));
                    return responseStream.toString();
                } finally {
                    channel.close(false);
                }
            }
        } finally {
            client.stop();
        }
    }
}
