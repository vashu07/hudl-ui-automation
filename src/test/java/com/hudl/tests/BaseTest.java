package com.hudl.tests;

import com.hudl.builders.RegionManagerBuilder;
import com.hudl.utils.AllureUtils;
import com.hudl.wtos.RegionManagerWto;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.logging.Logger;

import static com.hudl.builders.RegionManagerBuilder.buildRegionConfig;

public class BaseTest {

    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected RegionManagerWto regionManager = new RegionManagerWto();
    public static String grid = System.getenv("grid") != null ? System.getenv("grid") : System.getProperty("grid");

//    TODO: Move this to BeforeSuite and AfterSuite
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
//                browser = playwright.chromium().launch();
                break;
        }
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            AllureUtils.attachScreenshot(page);
        }
        if (context != null) context.close();

        if (browser != null) browser.close();

        if (playwright != null) playwright.close();

    }
}
