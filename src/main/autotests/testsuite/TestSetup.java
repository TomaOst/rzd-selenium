package testsuite;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import selenium.Browser;
import util.Config;

public class TestSetup {
    protected WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        Config config = Config.load();
        webDriver = new Browser(config).getWebDriver();
    }

    @After
    public void after() {
        webDriver.quit();
    }
}
