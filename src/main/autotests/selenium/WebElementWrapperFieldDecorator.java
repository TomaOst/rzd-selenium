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

public class WebElementWrapperFieldDecorator extends DefaultFieldDecorator {
    private final WebDriver webDriver;

    public WebElementWrapperFieldDecorator(SearchContext searchContext, WebDriver webDriver) {
        super(new DefaultElementLocatorFactory(searchContext));
        this.webDriver = webDriver;
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        Object complexWebElement = getWebElementWrapper(loader, field);
        if (complexWebElement != null) {
            return complexWebElement;
        } else {
            return super.decorate(loader, field);
        }
    }

    @SuppressWarnings("unchecked")
    private Object getWebElementWrapper(ClassLoader loader, Field field) {
        Class<?> fieldClass = field.getType();
        ElementLocator locator = factory.createLocator(field);
        if (locator != null) {
            if (List.class.isAssignableFrom(fieldClass)) {
                Type genericType = field.getGenericType();
                if (genericType instanceof ParameterizedType) {
                    Class<?> listElementClass = (Class<?>) ((ParameterizedType) genericType).getActualTypeArguments()[0];
                    if (WebElementWrapper.class.isAssignableFrom(listElementClass)) {
                        return createList(loader, locator, (Class<WebElementWrapper>) listElementClass);
                    }
                }
            } else if (WebElementWrapper.class.isAssignableFrom(fieldClass)) {
                return createElement(loader, locator, (Class<WebElementWrapper>) fieldClass);
            }
        }
        return null;
    }

    private WebElementWrapper createElement(ClassLoader loader, ElementLocator locator, Class<WebElementWrapper> clazz) {
        WebElement webElement = proxyForLocator(loader, locator);
        try {
            return newWebElementWrapperInstance(clazz, webElement);
        } catch (Exception e) {
            throw new RuntimeException("Can't create complex web element", e);
        }
    }

    @SuppressWarnings("unchecked")
    private List<WebElementWrapper> createList(ClassLoader loader, ElementLocator locator, Class<WebElementWrapper> clazz) {
        return (List) Proxy.newProxyInstance(loader, new Class[]{List.class}, (proxy, method, args) -> {
            List<WebElement> webElements = locator.findElements();
            List<WebElementWrapper> WebElementWrappers =
                    webElements.stream().map(we -> newWebElementWrapperInstance(clazz, we)).collect(Collectors.toList());
            try {
                return method.invoke(WebElementWrappers, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        });
    }

    private WebElementWrapper newWebElementWrapperInstance(Class<WebElementWrapper> clazz, WebElement webElement) {
        try {
            WebElementWrapper webElementWrapper =
                    clazz.getConstructor(WebElement.class, WebDriver.class).newInstance(webElement, webDriver);
            webElementWrapper.init();
            return webElementWrapper;
        } catch (Exception e) {
            throw new RuntimeException("Can't create complex web element for class " + clazz, e);
        }
    }

}
