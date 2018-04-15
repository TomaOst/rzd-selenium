package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class WebElementContainer {
    private final static int WAIT_TIME_OUT = 30;

    private WebDriver webDriver;
    private WebElement webElement;

    public WebElementContainer(WebDriver webDriver, WebElement webElement) {
        this.webDriver = webDriver;
        this.webElement = webElement;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void init() {
        SearchContext searchContext = webElement != null ? webElement : webDriver;
        PageFactory.initElements(new WebElementContainerFieldDecorator(searchContext, webDriver), this);
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

    protected void waitForElementContainerVisible(WebElementContainer container) {
        waitForElementVisible(container.getWebElement());
    }

    protected void waitForElementContainersVisible(List<? extends WebElementContainer> containers) {
        List<WebElement> elements =
                containers.stream().map(WebElementContainer::getWebElement).collect(Collectors.toList());
        waitForElementsVisible(elements);
    }

    protected void waitForElementClickable(WebElement element) {
        webDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForDisappear(By locator) {
        webDriverWait().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    private WebDriverWait webDriverWait() {
        return new WebDriverWait(webDriver, WAIT_TIME_OUT);
    }
}
