package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button extends WebElementWrapper {
    public Button(WebElement webElement, WebDriver webDriver) {
        super(webElement, webDriver);
    }
}
