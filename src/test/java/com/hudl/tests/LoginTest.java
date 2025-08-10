
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

    @Test(description = "verify if user can successfully login with valid credentials", groups = {"smoke"})
    public void verifySuccessfulLogin() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(regionManager.getUserName());
        loginPage.enterPassword(regionManager.getPassword());
        assertThat(page).hasURL(regionManager.getHomePageUrl());
        Thread.onSpinWait();
        loginPage.logout();
    }

    @Test(description = "verify the error message displayed for an invalid username")
    public void verifyLoginForInvalidUserName() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(invalidUserNameAndPassword);
        assertTrue(loginPage.getInvalidUserName(), INVALID_USERNAME_VALIDATION_MESSAGE);
    }

    @Test(description = "verify the error message displayed for an invalid password")
    public void verifyLoginForInvalidPassword() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(regionManager.getUserName());
        loginPage.enterPassword(invalidUserNameAndPassword);
        assertTrue(loginPage.getInvalidPassword(), INVALID_PASSWORD_VALIDATION_MESSAGE);
    }

    @Test(description = "verify if show password is clickable")
    public void verifyIfShowPasswordIsClickable() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(regionManager.getUserName());
        loginPage.enterPassword(invalidUserNameAndPassword);
        assertThat(loginPage.getPassWordInPutSelector()).hasAttribute("type", "password");
        loginPage.getShowPassword().click();
        assertThat(loginPage.getPassWordInPutSelector()).hasAttribute("type", "text");
    }

    @Test(description = "verify retry analyzer for failing scenario",retryAnalyzer = com.hudl.utils.RetryAnalyzer.class)
    public void verifyRetryAnalyzer() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate(regionManager.getLoginUrl());
        loginPage.enterUserName(regionManager.getUserName());
        loginPage.enterPassword(invalidUserNameAndPassword);
        assertFalse(loginPage.getInvalidPassword(), INVALID_PASSWORD_VALIDATION_MESSAGE);
    }
}
