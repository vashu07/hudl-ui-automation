package com.hudl.tests;

import com.hudl.wtos.RegionManagerWto;
import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import lombok.Getter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.nio.file.Paths;

import static com.hudl.builders.RegionManagerBuilder.buildRegionConfig;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    @Getter
    protected Page page;
    protected RegionManagerWto regionManager = new RegionManagerWto();
    public static String grid = System.getenv("grid") != null ? System.getenv("grid") : System.getProperty("grid");

    @BeforeMethod
    public void setUp() throws IOException {
        regionManager = buildRegionConfig();
        playwright = Playwright.create();
//        browser = playwright.chromium().launch();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshot.png")));
            Allure.addAttachment("Screenshot on Failure", "image/png", new java.io.ByteArrayInputStream(screenshot), ".png");
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
