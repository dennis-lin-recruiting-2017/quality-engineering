package com.dennislin.github.qe.demos;

import com.dennislin.github.qe.common.WebDriverUtil;
import com.dennislin.github.qe.framework.VisualWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;

import java.awt.*;

public class DragAndDrop {
    @Test
    public void test() throws Exception {
        //RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultChromeUI();
        RemoteWebDriver plainWebDriver = WebDriverUtil.getDefaultFirefox();
        VisualWebDriver webDriver = new VisualWebDriver(plainWebDriver);
        webDriver.get("https://html5demos.com/drag/");

        RemoteWebElement elementBin = (RemoteWebElement) webDriver.findElement(By.cssSelector("#bin"));
        RemoteWebElement elementDraggable1 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#one"));
        RemoteWebElement elementDraggable2 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#two"));
        RemoteWebElement elementDraggable3 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#three"));
        RemoteWebElement elementDraggable4 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#four"));
        RemoteWebElement elementDraggable5 = (RemoteWebElement) webDriver.findElement(By.cssSelector("#five"));

        testExtension(webDriver, elementBin, elementDraggable1, elementDraggable2, elementDraggable3, elementDraggable4, elementDraggable5);
    }

    private void testExtension(final VisualWebDriver webDriver,
                               final RemoteWebElement webElementBin,
                               final RemoteWebElement webElementDraggable1,
                               final RemoteWebElement webElementDraggable2,
                               final RemoteWebElement webElementDraggable3,
                               final RemoteWebElement webElementDraggable4,
                               final RemoteWebElement webElementDraggable5) throws Exception {
        webDriver.dragAndDrop(webElementDraggable1, webElementBin);
        webDriver.dragAndDrop(webElementDraggable2, webElementBin);
        webDriver.dragAndDrop(webElementDraggable3, webElementBin);
        webDriver.dragAndDrop(webElementDraggable4, webElementBin);
        webDriver.dragAndDrop(webElementDraggable5, webElementBin);
    }
}
