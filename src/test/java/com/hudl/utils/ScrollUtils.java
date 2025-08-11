package com.hudl.utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ScrollUtils {


    /** Scroll a specific element into view */
    public static void scrollToElement(Page page, String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }
    /**
     * Infinite scroll inside a scrollable container until no new content loads.
     * @param page Playwright Page instance
     * @param containerSelector selector of the scrollable container element
     * @param scrollStep pixels to scroll each time (e.g., 500)
     * @param maxScrolls max number of scroll attempts to avoid infinite loops
     * @param waitMs wait time in milliseconds after each scroll
     * TODO: Make this better
     */
    public static Locator infiniteScrollInContainer(Page page, String containerSelector, int scrollStep, int maxScrolls, int waitMs) {
        Locator container = page.locator(containerSelector);

        for (int i = 0; i <= maxScrolls; i++) {
            container.evaluate("el => { el.scrollTop = el.scrollTop + " + scrollStep + "; }");
            page.waitForTimeout(waitMs);
        }
        return container;
    }
}
