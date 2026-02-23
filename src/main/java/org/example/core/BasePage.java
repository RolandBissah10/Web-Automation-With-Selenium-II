package org.example.core;
import org.example.helper.ElementHelper;

public abstract class BasePage {

    // All pages share the same ElementHelper for driver and actions
    protected ElementHelper elementHelper;

    public BasePage(ElementHelper elementHelper) {
        this.elementHelper = elementHelper;
    }
}