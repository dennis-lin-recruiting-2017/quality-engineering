package com.dennislin.github.qe.framework;

public abstract class BasePageObject {
    private final VisualWebDriver objWebDriver;

    public BasePageObject(final VisualWebDriver webDriver) {
        objWebDriver = webDriver;
    }

    @SuppressWarnings("unused")
    public BasePageObject() {
        throw new RuntimeException("Should not use default constructor");
    }

    public VisualWebDriver getWebDriver() {
        return objWebDriver;
    }
}
