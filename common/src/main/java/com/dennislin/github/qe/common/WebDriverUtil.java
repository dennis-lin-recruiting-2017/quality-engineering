package com.dennislin.github.qe.common;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Toolkit class used to construct WebDriver instances used in UI automation.
 */
public final class WebDriverUtil {
    private static final Logger LOGGER = Logger.getLogger(WebDriverUtil.class.getName());
    private static URL urlSeleniumServer;

    static {
        try {
            urlSeleniumServer = new URL("http://testUser:testPassword@localhost:9180/smiley-http-proxy-servlet/webdriver/wd/hub");
        } catch (MalformedURLException exception) {
            LOGGER.severe("Unable to initialize connection to Selenium Server: " + exception.getMessage());
            System.exit(0);
        }
    };

    private WebDriverUtil() {
        throw new RuntimeException("Should not instantiate this class.");
    }

    private static URL getSeleniumServerURL() {
        return urlSeleniumServer;
    }

    public static RemoteWebDriver getDefaultFirefox() {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();

        return new RemoteWebDriver(getSeleniumServerURL(), capabilities);
    }

    /**
     * @return a WebDriver instance that references a headless Chrome browser
     */
    public static RemoteWebDriver getDefaultChrome() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return new RemoteWebDriver(getSeleniumServerURL(), capabilities);
    }

    public static RemoteWebDriver getDefaultSafari() {
        throw new RuntimeException("Not implmented yet.");
    }

    public static RemoteWebDriver getWebDriverWithPropertiesFile(final String resourceLocation) {
        throw new RuntimeException("Not implmented yet.");
    }
}
