package com.hudl.utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.nio.file.Paths;
import java.util.logging.Logger;

public class AllureUtils {

    private static final Logger logger = Logger.getLogger(AllureUtils.class.getName());

    /**
     * Attach screenshot to allure report upon failure
     *
     * @param page
     */
    public static void attachScreenshot(Page page) {
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshot.png")));
        Allure.addAttachment("Screenshot on Failure", "image/png", new java.io.ByteArrayInputStream(screenshot), ".png");
        logger.info("Screenshot attached to allure report");
    }

}
