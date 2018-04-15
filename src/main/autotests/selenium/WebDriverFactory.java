package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.Config;

public class WebDriverFactory {
    public WebDriver getDriver(Config config) throws Exception {
        switch (config.getBrowserType()) {
            case CHROME:
                return createChrome(config);
            case FIREFOX:
                return createFirefox();
            default:
                throw new Exception("Incorrect browser");
        }
    }

    private static WebDriver createChrome(Config config) {
        System.setProperty("webdriver.chrome.driver", config.getChromeDriverPath());
        return new ChromeDriver();
    }

    private static WebDriver createFirefox() {
        return new FirefoxDriver();
    }
}
