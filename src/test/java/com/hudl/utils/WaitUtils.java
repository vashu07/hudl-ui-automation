package com.hudl.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.logging.Logger;

public class WaitUtils {

    private static final Logger logger = Logger.getLogger(WaitUtils.class.getName());

    /**
     * Waits for a specific element to be hidden or detached from the DOM, using a custom timeout.
     * This is useful for waiting for loading spinners or overlays to disappear.
     *
     * @param page      The Playwright Page object.
     * @param selector  The CSS selector for the target element.
     * @param timeoutMs The maximum time to wait in milliseconds.
     */
    public static void waitForElementHidden(Page page, String selector, double timeoutMs) {
        logger.info("Waiting for hidden elements with timeout: " + timeoutMs + "ms");
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(timeoutMs));
    }

    /**
     * Waits for a specific element to be visible in the DOM, using a custom timeout.
     *
     * @param page      The Playwright Page object.
     * @param selector  The CSS selector for the target element.
     * @param timeoutMs The maximum time to wait in milliseconds.
     */
    public static void waitForElementVisible(Page page, String selector, double timeoutMs) {
        logger.info("Waiting for visible elements with timeout: " + timeoutMs + "ms");
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(timeoutMs));
    }

    /**
     * Waits for a specific element to be hidden or detached from the DOM, using the page's default timeout.
     *
     * @param page     The Playwright Page object.
     * @param selector The CSS selector for the target element.
     */
    public static void waitForElementHidden(Page page, String selector) {
        logger.info("Waiting for hidden elements with default timeout");
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.HIDDEN));
    }
}
