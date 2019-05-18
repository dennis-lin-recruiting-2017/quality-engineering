package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.framework.WebDriverUtil;
import com.dennislin.github.qe.framework.ImageFileUtil;
import com.dennislin.github.qe.framework.ImageLocator;
import com.dennislin.github.qe.framework.VisualWebDriver;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LiveDemo {
  @Test
  public void captureChromeScreenshot() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultChromeBrowser();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");

    final BufferedImage screenshot = webDriver.takeScreenshot();
    webDriver.saveScreenshotAsPNG("screenshotChrome.png");
  }

  @Test
  public void captureFirefoxScreenshot() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");

    final BufferedImage screenshot = webDriver.takeScreenshot();
    webDriver.saveScreenshotAsPNG("screenshotFirefox.png");
  }

  @Test
  public void testLiveDemo() throws Exception {
    final File testDirectory = new File("/Users/dennislin/Desktop/TestImages");
    final File[] imageFiles = testDirectory.listFiles();

    //RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultChromeBrowser();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");
    final SessionId sessionId = webDriver.getSessionId();
    System.out.println("**** Session ID: " + sessionId.toString());
    for (File imageFile : imageFiles) {
      if (imageFile.isDirectory() || (imageFile.getName().indexOf(".png") < 0) || imageFile.getName().equals("clickMe.png")) {
        continue;
      }

      final ImageLocator imageLocator = new ImageLocator(imageFile.getAbsolutePath());
      List<Rectangle> listMatches = webDriver.getExtensionForVision().findImageOnScreen(imageLocator);
      if (listMatches.size() > 0) {
        System.out.println(String.format("**** Num matches found for file=%s: %d", imageFile.getName(), listMatches.size()));
        for (final Rectangle rectangle : listMatches) {
          System.out.println(String.format("    Found matched region: (%d, %d), (width=%d, height=%d)",
              rectangle.x, rectangle.y, rectangle.width, rectangle.height));
        }
      } else {
        System.out.println("**** No matches found for file: " + imageFile.getName());
      }
    }

    for (File imageFile : imageFiles) {
      if (imageFile.getName().equals("clickMe.png")) {
        final ImageLocator imageLocator = new ImageLocator(imageFile.getAbsolutePath());

        webDriver.getExtensionForVision().clickOnImage(imageLocator);

        Thread.sleep(5000);

        break;
      }
    }

    webDriver.close();
  }

  @Test
  public void testGoogleMaps() throws Exception {
    final File testDirectory = new File("/Users/dennislin/Desktop/GoogleMaps");
    final File[] imageFiles = testDirectory.listFiles();

    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("https://www.google.com/maps/place/1+Infinite+Loop,+Cupertino,+CA+95014/@37.3336747,-122.0360695,16z/data=!4m5!3m4!1s0x808fb5b7176a341d:0x4ae040c5bfc59fcd!8m2!3d37.3318737!4d-122.0302472");

    for (File imageFile : imageFiles) {
      if (imageFile.isDirectory() || (imageFile.getName().indexOf(".png") < 0) || imageFile.getName().equals("clickMe.png")) {
        continue;
      }

      final ImageLocator imageLocator = new ImageLocator(imageFile.getAbsolutePath());
      List<Rectangle> listMatches = webDriver.getExtensionForVision().findImageOnScreen(imageLocator);
      if (listMatches.size() > 0) {
        System.out.println(String.format("**** Num matches found for file=%s: %d", imageFile.getName(), listMatches.size()));
        for (final Rectangle rectangle : listMatches) {
          System.out.println(String.format("    Found matched region: (%d, %d), (width=%d, height=%d)",
              rectangle.x, rectangle.y, rectangle.width, rectangle.height));
        }
      } else {
        System.out.println("**** No matches found for file: " + imageFile.getName());
      }
    }

    for (File imageFile : imageFiles) {
      if (imageFile.getName().equals("clickMe.png")) {
        final ImageLocator imageLocator = new ImageLocator(imageFile.getAbsolutePath());

        webDriver.getExtensionForVision().clickOnImage(imageLocator);

        Thread.sleep(5000);

        break;
      }
    }

    webDriver.close();
  }


  @Test
  public void testClickOnImageThatExists() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");

    final ImageLocator imageLocator = new ImageLocator("vision/clickOnImage/securityConsiderationsHowTo.png");
    Assert.assertTrue(webDriver.getExtensionForVision().clickOnImage(imageLocator));

    final BufferedImage screenshot = webDriver.takeScreenshot();
    ImageFileUtil.saveBufferedImageToFile(screenshot, "screenshotClickOnImage.png");

    webDriver.close();
  }
}
