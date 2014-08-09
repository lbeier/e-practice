package TestHelpers;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import play.Play;
import play.test.Helpers;
import play.test.TestBrowser;
import play.test.TestServer;
import static play.test.Helpers.HTMLUNIT;

public abstract class BaseTestServer extends BaseFakeApplication {

    protected static TestBrowser testBrowser;
    protected static TestServer testServer;
    protected static final String URL_ENVIRONMENT = Play.application().configuration().getString("application.baseUrl");

    @BeforeClass
    public static void setup() throws Exception {
        testServer = Helpers.testServer(3333);
        testBrowser = new TestBrowser(HTMLUNIT, URL_ENVIRONMENT);
        testServer.start();
        testBrowser.goTo(URL_ENVIRONMENT);
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(testServer);
    }
}