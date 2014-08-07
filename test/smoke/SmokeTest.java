package smoke;

import TestHelpers.BaseTestServer;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SmokeTest extends BaseTestServer {

    @Test
    public void verificaSeOTituloEIgualAePractice() {
        testBrowser.goTo(URL_ENVIRONMENT);
        assertThat(testBrowser.$("title").getTexts().get(0)).isEqualTo("e-Practice");
    }
}