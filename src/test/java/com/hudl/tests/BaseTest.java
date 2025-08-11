package com.hudl.tests;

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

    protected static Playwright playwright;
    protected static Browser browser;
    protected static RegionManagerWto regionManager;
    protected BrowserContext context;
    protected Page page;
    public static String grid = System.getenv("grid") != null ? System.getenv("grid") : System.getProperty("grid");

    @Parameters({"browserName"})
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(@Optional("chrome") String browserName) throws IOException {
        logger.info("--- Test Suite Starting ---");
        regionManager = buildRegionConfig();
        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "webkit":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "chromium":
            default:
//                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                browser = playwright.chromium().launch();
                break;
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            AllureUtils.attachScreenshot(page);
        }
        if (context != null) context.close();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}
