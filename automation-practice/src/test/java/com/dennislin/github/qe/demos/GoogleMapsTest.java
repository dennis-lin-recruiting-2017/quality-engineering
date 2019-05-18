package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.framework.WebDriverUtil;
import com.dennislin.github.qe.framework.ImageLocator;
import com.dennislin.github.qe.framework.VisualWebDriver;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GoogleMapsTest {
  public enum Locations {
    INFINITE_LOOP("https://www.google.com/maps/place/1+Infinite+Loop,+Cupertino,+CA+95014/@37.3336747,-122.0360695,16.57z/data=!4m5!3m4!1s0x808fb5b7176a341d:0x4ae040c5bfc59fcd!8m2!3d37.3318737!4d-122.0302472"),
    APPLE_PARK("https://www.google.com/maps/place/Apple+Park+Visitor+Center/@37.333909,-122.0101452,17z/data=!4m5!3m4!1s0x808fb590b4b4985b:0x4569138892ad41a1!8m2!3d37.3327062!4d-122.0053065");

    private String url;

    Locations(final String url) {
      this.url = url;
    }

    public String getUrl() {
      return url;
    }
  }

  private static final String[] images = {
    "vision/findImageOnScreen/googleMaps/InfiniteLoop.png",
    "vision/findImageOnScreen/googleMaps/ApplePark.png",
    "vision/findImageOnScreen/googleMaps/Chipotle.png",
    "vision/findImageOnScreen/googleMaps/Safeway.png"
  };

  @Test
  public void testGoogleMaps() throws IOException {
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get(Locations.INFINITE_LOOP.getUrl());

    for (final String imageResource : images) {
      final ImageLocator imageLocator = new ImageLocator(imageResource);
      final List<Rectangle> listMatches = webDriver.getExtensionForVision().findImageOnScreen(imageLocator);

      if (listMatches.size() > 0) {
        for (int counter = 0; counter < listMatches.size(); counter++) {
          System.out.println(String.format("**** Image locator '%s' has %d matches", imageResource, listMatches.size()));
          for (Rectangle region : listMatches) {
            System.out.println(String.format("    Found match at (%d, %d) with width=%d and height=%d", region.x, region.y, region.width, region.height));
          }
        }
      } else {
        System.out.println(String.format("**** Image locator '%s' has no matches", imageResource));
      }
    }
  }
}
