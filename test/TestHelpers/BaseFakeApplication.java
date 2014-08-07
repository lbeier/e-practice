package TestHelpers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import play.test.FakeApplication;
import static play.test.Helpers.*;

public abstract class BaseFakeApplication {

    private static FakeApplication fakeApplication;

    @BeforeClass
    public static void startFakeApplication() {
        fakeApplication = fakeApplication();
        start(fakeApplication);
    }

    @AfterClass
    public static void shutdownFakeApplication() {
        stop(fakeApplication);
    }
}