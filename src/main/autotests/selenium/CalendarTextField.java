package selenium;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalendarTextField extends TextField {
    public CalendarTextField(WebElement webElement, WebDriver webDriver) {
        super(webElement, webDriver);
    }

    @Override
    public void clear() {
        while (getAttribute("value").length() > 0) {
            sendKeys(Keys.BACK_SPACE);
        }
    }
}
