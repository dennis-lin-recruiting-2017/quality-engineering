package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.framework.VisualWebDriver;
import com.dennislin.github.qe.framework.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class CaptureSeleniumServerResponses {
  @Test
  public void test() throws Exception {
    //RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultChromeUI();
    RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
    VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
    System.out.println("**** Webdriver Session ID: " + webDriver.getSessionId());
    webDriver.get("http://www.google.com");

    final WebElement webElement = webDriver.findElement(By.cssSelector("input[name='quit']"));
    webElement.sendKeys("testing");
  }


}
