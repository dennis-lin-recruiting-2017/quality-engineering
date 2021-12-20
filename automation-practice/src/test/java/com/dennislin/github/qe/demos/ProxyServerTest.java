package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.framework.WebDriverUtil;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarLog;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import javax.json.Json;
import javax.json.JsonObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class ProxyServerTest {
  private static final Logger LOG = LoggerFactory.getLogger(ProxyServerTest.class);
  private static final BrowserMobProxy proxyServer;
  static {
    proxyServer = new BrowserMobProxyServer();
    proxyServer.setTrustAllServers(true);
    proxyServer.start();
  }

  public Proxy getSeleniumProxy() {
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxyServer);
    try {
      final String hostIp = Inet4Address.getLocalHost().getHostAddress();
      final String strProxyServer = String.format("%s:%d", hostIp, proxyServer.getPort());
      System.out.println("*** Proxy server; " + strProxyServer);
      seleniumProxy.setHttpProxy(strProxyServer);
      seleniumProxy.setSslProxy(strProxyServer);
      proxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
    } catch (UnknownHostException e) {
      e.printStackTrace();
    }

    return seleniumProxy;
  }

  @Test
  public void testRunGoogleSearch() throws Exception {
    final DesiredCapabilities capabilities = new DesiredCapabilities();
    final Proxy seleniumProxy = getSeleniumProxy();

    capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
    capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

    //final WebDriver driver = new RemoteWebDriver(WebDriverUtil.getSeleniumServerURL(), new FirefoxOptions(capabilities));
    final WebDriver driver = WebDriverUtil.getDefaultFirefox();
    driver.get("https://www.google.com");
    final WebElement webElement = driver.findElement(By.cssSelector("input[name='q']"));
    for (char c : "Test Selenium ".toCharArray()) {
      proxyServer.newHar("test01");
      webElement.sendKeys("" + c);
      Thread.sleep(250);
      recordHar("test01");
    }

    for (char c : " Java".toCharArray()) {
      proxyServer.newHar("test02");
      webElement.sendKeys("" + c);
      Thread.sleep(250);
      recordHar("test02");
    }

    Thread.sleep(5000);
    driver.close();
  }

  private static int counterHarEvents = 0;

  private static final void recordHar(final String name) throws IOException {
    final Har har = proxyServer.getHar();
    final HarLog harLog = har.getLog();
    System.out.println("**** Num proxy server entries: " + harLog.getEntries().size());
    counterHarEvents += harLog.getEntries().size();
    for (final HarEntry harEntry : harLog.getEntries()) {
      System.out.println("**** Proxy entry: " + harEntry.toString());
    }
    //*/
    /*
    final File fileHar = new File(String.format("/Users/dennislin/%s.har", name));
    har.writeTo(fileHar);
    //*/
    final StringWriter writer = new StringWriter();
    har.writeTo(writer);

    byte[] data = writer.toString().getBytes();
    System.out.println("Data length: " + data.length);
    final InputStream inputStream = new ByteArrayInputStream(data);
    final JsonObject jsonHar = Json.createReader(inputStream).readObject();
    //final JsonObject test01 = jsonHar.getJsonObject("log").getJsonObject("entries"));
    System.out.println(jsonHar.get("log"));

    System.out.println("Num Har entries: " + counterHarEvents);
  }
}
