package com.dennislin.github.qe.common;

import com.dennislin.github.qe.framework.AugmentedWebDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.Map;


public class SampleApp {
    public static final void main(final String[] args) throws Exception {
        RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultChrome();
        AugmentedWebDriver webDriver = new AugmentedWebDriver(plainWebDriver);
        webDriver.get("http://automationpractice.com/index.php");

        System.out.println("**** WebDriver session ID: " + webDriver.getSessionId());

        //  Dismiss active alert
        webDriver.executeScript("alert('Hello, World!');");
        if (webDriver.switchTo().alert() != null) {
            Alert alert = webDriver.switchTo().alert();
            alert.dismiss();
        }

        //  Take a screenshot
        webDriver.saveScreenshotAsPNG("test.png");

        //  Save the source.
        webDriver.savePageSourceToFile("test.html");

        List<WebElement> containersProduct = webDriver.findElements(By.cssSelector("div.product-container"));
        Map<String, String> mapCSSAttributes = webDriver.getCSSAttributes(containersProduct.get(0));
        mapCSSAttributes.entrySet().forEach((entry) -> {
            System.out.println(String.format("    Found %s=%s", entry.getKey(), entry.getValue()));
        });

        List<WebElement> containersProductImage = webDriver.findElements(By.cssSelector("div.product-image-container"));
        System.out.println("**** DEBUG - num product image containers: " + containersProductImage.size());

        List<WebElement> elementsDisplayGrid = webDriver.findElements(By.cssSelector("[display=\"grid\"]"));
        System.out.println("**** DEBUG - num display grids: " + elementsDisplayGrid.size());

        List<WebElement> elementsDisplayGridInline = webDriver.findElements(By.cssSelector("[display=\"inline-grid\"]"));
        System.out.println("**** DEBUG - num display inline-grids: " + elementsDisplayGridInline.size());

        Thread.sleep(2000);

        webDriver.saveScreenshotAsPNG("test2.png");

        webDriver.close();
    }
}