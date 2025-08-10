package com.hudl.tests;

import com.hudl.wtos.RegionManagerWto;
import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Paths;

import static com.hudl.builders.RegionManagerBuilder.buildRegionConfig;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected RegionManagerWto regionManager = new RegionManagerWto();
    public static String grid = System.getenv("grid") != null ? System.getenv("grid") : System.getProperty("grid");

    @Parameters({"browserName"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserName) throws IOException {
        regionManager = buildRegionConfig();
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
                break;
            case "chromium":
            default:
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100));
                break;
        }
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("target/screenshot.png")));
            Allure.addAttachment("Screenshot on Failure", "image/png", new java.io.ByteArrayInputStream(screenshot), ".png");
        }
        if (context != null) context.close();

        if (browser != null) browser.close();

        if (playwright != null) playwright.close();

    }
}
