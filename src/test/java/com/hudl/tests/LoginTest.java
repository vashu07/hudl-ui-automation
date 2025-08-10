
package com.hudl.tests;

import com.hudl.pages.LoginPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import static com.hudl.constants.ErrorMessageConstants.INVALID_PASSWORD_VALIDATION_MESSAGE;
import static com.hudl.constants.ErrorMessageConstants.INVALID_USERNAME_VALIDATION_MESSAGE;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private static final String invalidUserNameAndPassword = RandomStringUtils.randomAlphabetic(8);

    @Test
    public void verifySuccessfulLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(regionManager.getUserName());
        loginPage.enterPassword(regionManager.getPassword());
        assertThat(page).hasURL(regionManager.getHomePageUrl());
        Thread.onSpinWait();
        loginPage.logout();
    }

    @Test
    public void verifyLoginForInvalidUserName() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(invalidUserNameAndPassword);
        assertTrue(loginPage.getInvalidUserName(), INVALID_USERNAME_VALIDATION_MESSAGE);
    }

    @Test()
    public void verifyLoginForInvalidPassword() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(regionManager.getUserName());
        loginPage.enterPassword(invalidUserNameAndPassword);
        assertTrue(loginPage.getInvalidPassword(), INVALID_PASSWORD_VALIDATION_MESSAGE);
    }

    @Test
    public void verifyIfShowPasswordIsClickable() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(regionManager.getUserName());
        loginPage.enterPassword(invalidUserNameAndPassword);
        assertThat(page.locator(loginPage.passwordLocator)).hasAttribute("type", "password");
        loginPage.getShowPassword().click();
        assertThat(page.locator(loginPage.passwordLocator)).hasAttribute("type", "text");
    }

    @Test(retryAnalyzer = com.hudl.utils.RetryAnalyzer.class)
    public void verifyRetryAnalyzer() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(regionManager.getUserName());
        loginPage.enterPassword(invalidUserNameAndPassword);
        assertFalse(loginPage.getInvalidPassword(), INVALID_PASSWORD_VALIDATION_MESSAGE);
    }
}
