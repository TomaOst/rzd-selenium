package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class WebElementContainer extends WebElementWrapper {
    private final static int WAIT_TIME_OUT = 30;

    public WebElementContainer(WebElement webElement, WebDriver webDriver) {
        super(webElement, webDriver);
    }

    @Override
    public void init() {
        SearchContext searchContext = getWebElement() != null ? getWebElement() : getWebDriver();
        PageFactory.initElements(new WebElementWrapperFieldDecorator(searchContext, getWebDriver()), this);
    }

    public void waitForLoad() {
        webDriverWait().until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    protected void waitForElementVisible(WebElement element) {
        webDriverWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementsVisible(List<WebElement> elements) {
        webDriverWait().until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected void waitForElementClickable(WebElement element) {
        webDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForDisappear(By locator) {
        webDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private WebDriverWait webDriverWait() {
        return new WebDriverWait(getWebDriver(), WAIT_TIME_OUT);
    }
}
