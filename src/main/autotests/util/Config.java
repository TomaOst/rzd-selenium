package util;

import selenium.BrowserType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties properties;

    public static Config load() throws IOException {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties properties = new Properties();
            properties.load(input);
            return new Config(properties);
        }
    }

    public Config(Properties properties) {
        this.properties = properties;
    }

    public BrowserType getBrowserType() {
        return BrowserType.valueOf(properties.getProperty("browserType"));
    }

    public String getChromeDriverPath() {
        return properties.getProperty("chromeDriverPath");
    }

    public String getGeckoDriverPath(){
        return properties.getProperty("geckoDriverPath");
    }

    public String getWebsiteUrl() {
        return properties.getProperty("websiteUrl");
    }
}
