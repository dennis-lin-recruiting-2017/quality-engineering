package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.framework.WebDriverUtil;
import com.dennislin.github.qe.framework.VisualWebDriver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DragAndDrop {
  @Test
  public void test() throws Exception {
    //RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultChromeUI();
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    System.out.println("**** Webdriver Session ID: " + webDriver.getSessionId());
    webDriver.get("http://localhost:8080/ciborgarmy.testWebapps/html5drag/");

    List<Long> listTimestamps = new ArrayList<>();

    listTimestamps.add(System.currentTimeMillis());
    RemoteWebElement elementBin = (RemoteWebElement) webDriver.findElement(By.cssSelector("#bin"));
    Dimension testSize = elementBin.getSize();
    String testAttribute = elementBin.getAttribute("id");
    Rectangle testRectangle = elementBin.getRect();


    listTimestamps.add(System.currentTimeMillis());
    RemoteWebElement elementDraggable1 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#one"));
    List<WebElement> listElementDraggable1 = webDriver.findElements(By.cssSelector("#one"));
    listTimestamps.add(System.currentTimeMillis());
    RemoteWebElement elementDraggable2 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#two"));
    listTimestamps.add(System.currentTimeMillis());
    RemoteWebElement elementDraggable3 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#three"));
    listTimestamps.add(System.currentTimeMillis());
    RemoteWebElement elementDraggable4 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#four"));
    listTimestamps.add(System.currentTimeMillis());
    RemoteWebElement elementDraggable5 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#five"));
    listTimestamps.add(System.currentTimeMillis());
    Rectangle rect = elementDraggable1.getRect();


    Document htmlDocument = Jsoup.parse(webDriver.getPageSource());
    List<Long> listTimestamps02 = new ArrayList<>();
    listTimestamps02.add(System.currentTimeMillis());
    Elements elements00 = htmlDocument.select("#bin");
    elements00.forEach((element) -> {
      System.out.println("**** Bin element");
      element.attributes().forEach((attribute) -> {
        System.out.println(String.format("    %s = %s", attribute.getKey(), attribute.getValue()));
      });
    });
    listTimestamps02.add(System.currentTimeMillis());
    Elements elements01 = htmlDocument.select("#one");
    listTimestamps02.add(System.currentTimeMillis());
    Elements elements02 = htmlDocument.select("#two");
    listTimestamps02.add(System.currentTimeMillis());
    Elements elements03 = htmlDocument.select("#three");
    listTimestamps02.add(System.currentTimeMillis());
    Elements elements04 = htmlDocument.select("#four");
    listTimestamps02.add(System.currentTimeMillis());
    Elements elements05 = htmlDocument.select("#five");
    listTimestamps02.add(System.currentTimeMillis());
    System.out.println("**** WebDriver");
    for (int counter = 0; counter < listTimestamps.size() - 1; counter++) {
      long time1 = listTimestamps.get(counter);
      long time2 = listTimestamps.get(counter + 1);
      System.out.println(String.format("**** %d: %d", counter, (time2 - time1)));
    }
    System.out.println("**** JSoup");
    System.out.println("num elements - 0: " + elements00.size());
    System.out.println("num elements - 1: " + elements01.size());
    System.out.println("num elements - 2: " + elements02.size());
    System.out.println("num elements - 3: " + elements03.size());
    System.out.println("num elements - 4: " + elements04.size());
    System.out.println("num elements - 5: " + elements05.size());
    for (int counter = 0; counter < listTimestamps02.size() - 1; counter++) {
      long time1 = listTimestamps02.get(counter);
      long time2 = listTimestamps02.get(counter + 1);
      System.out.println(String.format("**** %d: %d", counter, (time2 - time1)));
    }

    testExtension(webDriver, elementBin, elementDraggable1, elementDraggable2, elementDraggable3, elementDraggable4, elementDraggable5);

    webDriver.close();
  }

  private void testExtension(final VisualWebDriver webDriver,
                             final RemoteWebElement webElementBin,
                             final RemoteWebElement webElementDraggable1,
                             final RemoteWebElement webElementDraggable2,
                             final RemoteWebElement webElementDraggable3,
                             final RemoteWebElement webElementDraggable4,
                             final RemoteWebElement webElementDraggable5) throws Exception {
    File source = webDriver.getScreenshotAs(OutputType.FILE);
    Thread.sleep(1000);
    webDriver.dragAndDrop(webElementDraggable1, webElementBin);
    Thread.sleep(1000);
    webDriver.dragAndDrop(webElementDraggable2, webElementBin);
    Thread.sleep(1000);
    webDriver.dragAndDrop(webElementDraggable3, webElementBin);
    Thread.sleep(1000);
    webDriver.dragAndDrop(webElementDraggable4, webElementBin);
    Thread.sleep(1000);
    webDriver.dragAndDrop(webElementDraggable5, webElementBin);
  }

  @Test
  public void testBrowserScreenshotSpeed() throws IOException {
    //RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultChromeBrowser();
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.manage().window().maximize();
    //webDriver.get("https://www.cnn.com");
    webDriver.get("http://localhost:8080");

    final int NUM_SCREENSHOTS = 10;
    long totalTime = 0;
    for (int counter = 0; counter < NUM_SCREENSHOTS; counter++) {
      final long timestampStart = System.currentTimeMillis();
      webDriver.takeScreenshot();
      final long timestampEnd = System.currentTimeMillis();
      long timeElapsed = timestampEnd - timestampStart;
      totalTime += timeElapsed; System.out.println(String.format("**** Screenshot attempt #%d: %d ms", counter, timeElapsed));
    }

    System.out.println(String.format("Total time of %d for %d screenshots", totalTime, NUM_SCREENSHOTS));
    webDriver.close();
  }

  @Test
  public void testGetBrowserDimensions() {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.manage().window().maximize();
    webDriver.get("http://localhost:8080");

    final int NUM_ATTEMPTS = 10;
    long totalTime = 0;
    for (int counter = 0; counter < NUM_ATTEMPTS; counter++) {
      final long timestampStart = System.currentTimeMillis();
      webDriver.manage().window().getSize();
      webDriver.manage().window().getPosition();
      final long timestampEnd = System.currentTimeMillis();
      long timeElapsed = timestampEnd - timestampStart;
      totalTime += timeElapsed;
      System.out.println(String.format("**** Get browser dimensions attempt #%d: %d ms", counter, timeElapsed));
    }

    System.out.println(String.format("Total time of %d for %d screenshots", totalTime, NUM_ATTEMPTS));
    webDriver.close();
  }
}

