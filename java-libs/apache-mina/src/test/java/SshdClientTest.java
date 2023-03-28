import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import peter.lee.client.SshdClient;

class SshdClientTest {
    SshdClient client = new SshdClient();

    @Test
    void getFileList() throws Exception {
        final String files = client.getFileList("localhost", 2224, "thachle", "password", 100);
        assertThat(files).isNotEmpty()
                         .contains("build.gradle", "settings.gradle");
    }
}
