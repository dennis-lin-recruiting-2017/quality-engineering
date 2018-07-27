package com.dennislin.github.qe.automationpractice.pages;

import com.dennislin.github.qe.framework.AugmentedWebDriver;
import com.dennislin.github.qe.framework.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage extends BasePageObject {
    public LandingPage(final AugmentedWebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getProductImageContainer() {
        return getWebDriver().findElement(By.cssSelector("div.product-image-container"));
    }

    public List<WebElement> getProductImages() {
        return getWebDriver().findElements(By.cssSelector("div.product-image-container"));
    }
}
