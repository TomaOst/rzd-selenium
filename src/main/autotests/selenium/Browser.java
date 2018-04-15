package selenium;

import org.openqa.selenium.WebDriver;
import util.Config;

import java.util.concurrent.TimeUnit;

public class Browser {
    private WebDriver driver;

    public Browser(Config config) throws Exception {
        driver = new WebDriverFactory().getDriver(config);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(config.getWebsiteUrl());
    }

    public WebDriver getWebDriver() {
        return driver;
    }

}
