package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.common.WebDriverUtil;
import com.dennislin.github.qe.framework.ImageLocator;
import com.dennislin.github.qe.framework.VisualWebDriver;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ImageLocatorsTest {
  @Test
  public void testImageLocatorDragAndDrop() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080/ciborgarmy.testWebapps/html5drag/");
    //webDriver.get("https://html5demos.com/drag/");

    System.out.println("Webdriver sessionID:" + webDriver.getSessionId());

    final ImageLocator imageLocator = new ImageLocator("vision/findImageOnScreen/dragDrop/tomcat.png");
    List<Rectangle> listImageMatches = webDriver.getExtensionForVision().findImageOnScreen(imageLocator);
    listImageMatches.forEach((match) -> {
      final Rectangle matchedRegion = (Rectangle) match;
      System.out.println(String.format("    (x, y) = (%d, %d), width=%d, height=%d", matchedRegion.x, matchedRegion.y, matchedRegion.width, matchedRegion.height));
    });

    webDriver.close();
    Assert.assertTrue(listImageMatches.size() > 0, "Should have matched at least 1 region");
  }

  @Test
  public void testMultipleImageLocatorsAllExist() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");

    System.out.println("Webdriver sessionID:" + webDriver.getSessionId());

    final ImageLocator imageLocator01 = new ImageLocator("vision/findImageOnScreen/dragDrop/tomcat.png");
    final ImageLocator imageLocator02 = new ImageLocator("vision/findImageOnScreen/dragDrop/tomcat.png");
    List<List<Rectangle>> listImageMatches = webDriver.getExtensionForVision().findMultipleImagesOnScreen(imageLocator01, imageLocator02);

    webDriver.close();
    Assert.assertEquals(listImageMatches.size(), 2);
    Assert.assertEquals(listImageMatches.get(0).size(), 1);
    Assert.assertEquals(listImageMatches.get(1).size(), 1);
  }

  @Test
  public void testMultipleImageLocatorsNoneExist() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");

    System.out.println("Webdriver sessionID:" + webDriver.getSessionId());

    final ImageLocator imageLocator01 = new ImageLocator("vision/findImageOnScreen/dragDrop/item4.png");
    final ImageLocator imageLocator02 = new ImageLocator("vision/findImageOnScreen/dragDrop/item4.png");
    List<List<Rectangle>> listImageMatches = webDriver.getExtensionForVision().findMultipleImagesOnScreen(imageLocator01, imageLocator02);

    webDriver.close();
    Assert.assertEquals(listImageMatches.size(), 2);
    Assert.assertEquals(listImageMatches.get(0).size(), 0);
    Assert.assertEquals(listImageMatches.get(1).size(), 0);
  }

  @Test
  public void testMultipleImageLocatorsSomeExist() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");

    System.out.println("Webdriver sessionID:" + webDriver.getSessionId());

    final ImageLocator imageLocator01 = new ImageLocator("vision/findImageOnScreen/dragDrop/item4.png");
    final ImageLocator imageLocator02 = new ImageLocator("vision/findImageOnScreen/dragDrop/tomcat.png");
    List<List<Rectangle>> listImageMatches = webDriver.getExtensionForVision().findMultipleImagesOnScreen(imageLocator01, imageLocator02);

    webDriver.close();
    Assert.assertEquals(listImageMatches.size(), 2);
    Assert.assertEquals(listImageMatches.get(0).size(), 0);
    Assert.assertEquals(listImageMatches.get(1).size(), 1);
  }

  @Test
  public void testClickOnImageThatExists() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");

    final ImageLocator imageLocator = new ImageLocator("vision/findImageOnScreen/dragDrop/tomcat.png");
    webDriver.getExtensionForVision().clickOnImage(imageLocator);

    webDriver.close();
  }
}
