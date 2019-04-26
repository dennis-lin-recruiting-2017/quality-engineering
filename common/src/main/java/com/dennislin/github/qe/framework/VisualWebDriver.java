package com.dennislin.github.qe.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.io.IOException;

public final class VisualWebDriver extends BaseVisualWebDriver implements JavascriptExecutor {
    public VisualWebDriver(RemoteWebDriver driver) {
        super(driver);
    }

    public void savePageSourceToFile(final String fileLocation) throws IOException {
        FileOutputStream outfile = new FileOutputStream(fileLocation);
        outfile.write(getPageSource().getBytes());
        outfile.close();
    }

    public void saveScreenshotAsPNG(final String fileLocation) throws IOException {
        byte[] dataScreenshot = getScreenshotAs(OutputType.BYTES);
        FileOutputStream outfile = new FileOutputStream(fileLocation);
        outfile.write(dataScreenshot);
        outfile.close();
    }

    public Point getCenterOfWebElement(final WebElement webElement) {
        final Rectangle rectangle = webElement.getRect();

        return new Point(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);
    }
}
