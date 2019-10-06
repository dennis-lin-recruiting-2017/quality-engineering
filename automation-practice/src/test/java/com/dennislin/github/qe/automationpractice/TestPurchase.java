package com.dennislin.github.qe.automationpractice;

import com.dennislin.github.qe.framework.VisualWebDriver;
import com.dennislin.github.qe.framework.WebDriverUtil;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class TestPurchase {
  @Test
  public void testPurchase() {
    final RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    final VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    webDriver.get("http://www.automationpractice.com");

    
  }
}
