package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.framework.ImageLocator;
import com.dennislin.github.qe.framework.VisualWebDriver;
import com.dennislin.github.qe.framework.WebDriverUtil;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class BejeweledClone {
  private static final Logger LOGGER = LoggerFactory.getLogger(BejeweledClone.class);

  @Test
  public void testScreenshot(final ITestContext testContext)
      throws IOException, InterruptedException, URISyntaxException {
    final RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    final VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080/ciborgarmy.testWebapps/match3/");

    Thread.sleep(2000);
    webDriver.saveScreenshotAsPNG("bejeweled.png");

    final float SIMILARITY = 0.90f;
    final ImageLocator[] imageLocators = new ImageLocator[] {
        new ImageLocator("bejeweled/red.png", SIMILARITY),
        new ImageLocator("bejeweled/yellow.png", SIMILARITY),
        new ImageLocator("bejeweled/green.png", SIMILARITY),
        new ImageLocator("bejeweled/blue.png", SIMILARITY),
        new ImageLocator("bejeweled/purple.png", SIMILARITY),
        new ImageLocator("bejeweled/white.png", SIMILARITY)
    };

    /*
    final List<List<Rectangle>> results = webDriver.getExtensionForVision().findMultipleImagesOnScreen(imageLocators);
    for (final List<Rectangle> matches : results) {
      System.out.println("**** Image locator found");
      for (final Rectangle match : matches) {
        System.out.println(String.format("    (%d, %d) - width=%d, height=%d", match.x, match.y, match.width, match.height));
      }
    }
    //*/

    //*
    for (int counter = 0; counter < imageLocators.length; counter++) {
      final ImageLocator imageLocator = imageLocators[counter];
      LOGGER.info("**** Image locator: " + imageLocator.getResourceLocation());
      final List<Rectangle> matches = webDriver.getExtensionForVision().findImageOnScreen(imageLocator);
      LOGGER.info("Num matches: " + matches.size());
      for (final Rectangle match : matches) {
        LOGGER.info(String.format("    (%d, %d) - width=%d, height=%d", match.x, match.y, match.width, match.height));
      }
    }
    //*/
  }
}
