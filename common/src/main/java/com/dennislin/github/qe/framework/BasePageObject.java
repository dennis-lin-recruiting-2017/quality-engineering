package com.dennislin.github.qe.framework;

public abstract class BasePageObject {
    private final AugmentedWebDriver objWebDriver;

    public BasePageObject(final AugmentedWebDriver webDriver) {
        objWebDriver = webDriver;
    }

    @SuppressWarnings("unused")
    public BasePageObject() {
        throw new RuntimeException("Should not use default constructor");
    }

    public AugmentedWebDriver getWebDriver() {
        return objWebDriver;
    }
}
