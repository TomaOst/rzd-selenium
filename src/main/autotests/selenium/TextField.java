package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextField extends WebElementWrapper {
    public TextField(WebElement webElement, WebDriver webDriver) {
        super(webElement, webDriver);
    }

    public void fill(String text) {
        clear();
        sendKeys(text);
        blur();
    }
}
