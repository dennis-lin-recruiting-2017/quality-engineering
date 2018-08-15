package com.dennislin.github.qe.common;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
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
        return new RemoteWebDriver(getSeleniumServerURL(), new FirefoxOptions());
    }

    /**
     * @return a WebDriver instance that references a headless Chrome browser
     */
    public static RemoteWebDriver getDefaultChrome() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");

        return new RemoteWebDriver(getSeleniumServerURL(), chromeOptions);
    }

    public static RemoteWebDriver getDefaultChromeUI() {
        ChromeOptions chromeOptions = new ChromeOptions();

        return new RemoteWebDriver(getSeleniumServerURL(), chromeOptions);
    }

    public static RemoteWebDriver getDefaultSafari() {
        throw new RuntimeException("Not implmented yet.");
    }

    public static RemoteWebDriver getWebDriverWithPropertiesFile(final String resourceLocation) {
        throw new RuntimeException("Not implmented yet.");
    }
}
