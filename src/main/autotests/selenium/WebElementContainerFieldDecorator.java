package selenium;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.*;
import java.util.List;
import java.util.stream.Collectors;

public class WebElementContainerFieldDecorator extends DefaultFieldDecorator {
    private final WebDriver webDriver;

    public WebElementContainerFieldDecorator(SearchContext searchContext, WebDriver webDriver) {
        super(new DefaultElementLocatorFactory(searchContext));
        this.webDriver = webDriver;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Object complexWebElement = getComplexWebElement(loader, field);
        if (complexWebElement != null) {
            return complexWebElement;
        } else {
            return super.decorate(loader, field);
        }
    }

    @SuppressWarnings("unchecked")
    private Object getComplexWebElement(ClassLoader loader, Field field) {
        Class<?> fieldClass = field.getType();
        ElementLocator locator = factory.createLocator(field);
        if (locator != null) {
            if (List.class.isAssignableFrom(fieldClass)) {
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    Class<?> listElementClass = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
                    if (WebElementContainer.class.isAssignableFrom(listElementClass)) {
                        return createList(loader, locator, (Class<WebElementContainer>) listElementClass);
                    }
                }
            } else if (WebElementContainer.class.isAssignableFrom(fieldClass)) {
                return createElement(loader, locator, (Class<WebElementContainer>) fieldClass);
            }
        }
        return null;
    }

    private WebElementContainer createElement(ClassLoader loader, ElementLocator locator, Class<WebElementContainer> clazz) {
        WebElement webElement = proxyForLocator(loader, locator);
        try {
            return newComplexWebElementInstance(clazz, webElement);
        } catch (Exception e) {
            throw new RuntimeException("Can't create complex web element", e);
        }
    }

    @SuppressWarnings("unchecked")
    private List<WebElementContainer> createList(ClassLoader loader, ElementLocator locator, Class<WebElementContainer> clazz) {
        return (List) Proxy.newProxyInstance(loader, new Class[]{List.class}, (proxy, method, args) -> {
            List<WebElement> webElements = locator.findElements();
            List<WebElementContainer> webElementContainers =
                    webElements.stream().map(we -> newComplexWebElementInstance(clazz, we)).collect(Collectors.toList());
            try {
                return method.invoke(webElementContainers, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
    }

    private WebElementContainer newComplexWebElementInstance(Class<WebElementContainer> clazz, WebElement webElement) {
        try {
            WebElementContainer webElementContainer =
                    clazz.getConstructor(WebDriver.class, WebElement.class).newInstance(webDriver, webElement);
            webElementContainer.init();
            return webElementContainer;
        } catch (Exception e) {
            throw new RuntimeException("Can't create complex web element for class " + clazz, e);
        }
    }

}
