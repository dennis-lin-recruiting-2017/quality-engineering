package com.dennislin.github.qe.framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.io.IOException;

public final class AugmentedWebDriver extends AugmentedWebDriverImpl implements JavascriptExecutor {
    public AugmentedWebDriver(RemoteWebDriver driver) {
        super(driver);
    }

    @Override
    public Object executeScript(String s, Object... objects) {
        return super.executeScript(s, objects);
    }

    @Override
    public Object executeAsyncScript(String s, Object... objects) {
        return super.executeAsyncScript(s, objects);
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
}
