package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.common.WebDriverUtil;
import com.dennislin.github.qe.framework.ImageLocator;
import com.dennislin.github.qe.framework.VisualWebDriver;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LiveDemo {
  @Test
  public void testLiveDemo() throws IOException {
    final File testDirectory = new File("/Users/dennislin/Desktop/TestImages");
    final File[] imageFiles = testDirectory.listFiles();

    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://localhost:8080");

    for (File imageFile : imageFiles) {
      if (imageFile.isDirectory()) {
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

    webDriver.close();
  }
}
