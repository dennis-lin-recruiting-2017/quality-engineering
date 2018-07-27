package com.dennislin.github.qe.automationpractice;

import com.dennislin.github.qe.automationpractice.pages.LandingPage;
import com.dennislin.github.qe.common.WebDriverUtil;
import com.dennislin.github.qe.framework.AugmentedWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestBrowse {
    @Test
    public void testBrowseLandingPage() throws IOException {
        RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
        AugmentedWebDriver webDriver = new AugmentedWebDriver(plainWebDriver);
        webDriver.get("http://www.automationpractice.com");

        LandingPage landingPage = new LandingPage(webDriver);
        WebElement containersProduct = landingPage.getProductImageContainer();
        Map<String, String> mapCSSAttributes = webDriver.getCSSAttributes(containersProduct);
        mapCSSAttributes.entrySet().forEach((entry) -> {
            System.out.println(String.format("    Found %s=%s", entry.getKey(), entry.getValue()));
        });

        List<WebElement> containersProductImage = landingPage.getProductImages();
        System.out.println("**** DEBUG - num product image containers: " + containersProductImage.size());

        webDriver.saveScreenshotAsPNG("test2.png");

        webDriver.close();
    }
}
