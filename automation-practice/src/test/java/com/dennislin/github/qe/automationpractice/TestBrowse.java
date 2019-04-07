package com.dennislin.github.qe.automationpractice;

import com.dennislin.github.qe.automationpractice.pages.LandingPage;
import com.dennislin.github.qe.common.WebDriverUtil;
import com.dennislin.github.qe.framework.VisualWebDriver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestBrowse {
    @Test
    public void testRetrieveElementImages() throws IOException {
        final RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
        final VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
        webDriver.get("http://www.automationpractice.com");

        final List<WebElement> listWebElements = webDriver.findElementsByCssSelector("div.product-image-container");
        System.out.println("*** Num products found: " + listWebElements.size());

        webDriver.close();
    }

    @Test
    public void testBrowseLandingPage() throws IOException {
        RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
        VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
        webDriver.get("http://www.automationpractice.com");

        List<WebElement> listWebElements = webDriver.findElements(By.xpath("//*"));
        System.out.println("**** Num elements: " + listWebElements.size());

        FileOutputStream outfile = new FileOutputStream("./dump.out");
            outfile.write(webDriver.getPageSource().getBytes());
        outfile.close();

        long timestampStart01 = System.currentTimeMillis();
        Document htmlDocument = Jsoup.parse(webDriver.getPageSource());
        Elements listElements = htmlDocument.body().getAllElements();
        long timestampEnd01 = System.currentTimeMillis();

        System.out.println("**** Num elements: " + listElements.size());
        System.out.println("**** Elapsed time (JSoup): " + (timestampEnd01 - timestampStart01));
        outfile = new FileOutputStream("./elements.out");
        for (Iterator<Element> it = listElements.iterator(); it.hasNext(); ) {
            Element element = it.next();
            outfile.write(element.tagName().getBytes());
            if (element.parent() != null) {
                outfile.write(" - ".getBytes());
                outfile.write(element.parent().tagName().getBytes());
            }
            outfile.write("\n".getBytes());
            element.attributes();
        }

        outfile = new FileOutputStream("./page.out");
        for (final WebElement webElement : listWebElements) {
            outfile.write(webElement.getTagName().getBytes());
            outfile.write("\n".getBytes());
        }
        outfile.close();

        webDriver.get("file:///Users/dennislin/workspace/github_public/ciborgarmy-selenium/temp/dump.out");
        long timestampStart02 = System.currentTimeMillis();
        listWebElements = webDriver.findElements(By.xpath("//*"));
        long timestampEnd02 = System.currentTimeMillis();
        System.out.println("**** Elapsed time (WebDriver): " + (timestampEnd02 - timestampStart02));
        System.out.println("**** Num elements 2: " + listWebElements.size());

        LandingPage landingPage = new LandingPage(webDriver);
        WebElement containersProduct = landingPage.getProductImageContainer();
        Map<String, String> mapCSSAttributes = webDriver.getCSSAttributes(containersProduct);
        mapCSSAttributes.entrySet().forEach((entry) -> {
            System.out.println(String.format("    Found %s=%s", entry.getKey(), entry.getValue()));
        });

        List<WebElement> containersProductImage = landingPage.getProductImages();
        System.out.println("**** DEBUG - num product image containers: " + containersProductImage.size());

        //htmlDocument.body().  div.product-image-container

        final Dimension viewportDimension = webDriver.manage().window().getSize();
        System.out.println(String.format("**** Browser viewport size: (width=%d, height=%d)",
            viewportDimension.width, viewportDimension.height));
        webDriver.saveScreenshotAsPNG("test2.png");

        webDriver.close();
    }
}
