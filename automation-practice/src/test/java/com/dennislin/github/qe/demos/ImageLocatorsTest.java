package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.common.WebDriverUtil;
import com.dennislin.github.qe.framework.ImageLocator;
import com.dennislin.github.qe.framework.VisualWebDriver;
import graphql.Assert;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ImageLocatorsTest {
  @Test
  public void testImageLocatorDragAndDrop() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");
    //webDriver.get("https://html5demos.com/drag/");

    System.out.println("Webdriver sessionID:" + webDriver.getSessionId());

    final ImageLocator imageLocator = new ImageLocator("vision/findImageOnScreen/dragDrop/tomcat.png");
    List<Rectangle> listImageMatches = webDriver.getExtensionForVision().findImageOnScreen(imageLocator);
    listImageMatches.forEach((match) -> {
      final Rectangle matchedRegion = (Rectangle) match;
      System.out.println(String.format("    (x, y) = (%d, %d), width=%d, height=%d", matchedRegion.x, matchedRegion.y, matchedRegion.width, matchedRegion.height));
    });

    webDriver.close();
    Assert.assertNotEmpty(listImageMatches, "Should have matched at least 1 region");
  }
}
