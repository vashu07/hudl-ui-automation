package com.hudl.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.logging.Logger;

import static com.hudl.constants.ErrorMessageConstants.INVALID_PASSWORD_ERROR_MESSAGE;
import static com.hudl.constants.ErrorMessageConstants.INVALID_USERNAME_ERROR_MESSAGE;
import static com.hudl.constants.HomePageConstants.FEED_SELECTOR;
import static com.hudl.constants.HomePageConstants.NEWCASTLE_JETS_LINK_TEXT;
import static com.hudl.constants.LoginPageConstants.*;

public class LoginPage {
    private static final Logger logger = Logger.getLogger(LoginPage.class.getName());
    private final Page page;

    //    Locators
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator continueButton;
    private final Locator loginButton;
    private final Locator userProfileMenu;
    private final Locator logoutLink;
    private final Locator invalidUserNameError;
    private final Locator invalidPasswordError;
    private final Locator showPasswordButton;

    public LoginPage(Page page) {
        this.page = page;

        this.emailInput = page.locator(USERNAME_INPUT_SELECTOR);
        this.passwordInput = page.locator(PASSWORD_INPUT_SELECTOR);
        this.continueButton = page.locator(CONTINUE_BUTTON_NAME);
        this.loginButton = page.locator(SUBMIT_BUTTON_SELECTOR);
        this.userProfileMenu = page.locator(USER_PROFILE_MENU_SELECTOR);
        this.logoutLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(LOG_OUT));
        this.invalidUserNameError = page.getByText(INVALID_USERNAME_ERROR_MESSAGE);
        this.invalidPasswordError = page.getByText(INVALID_PASSWORD_ERROR_MESSAGE);
        this.showPasswordButton = page.getByLabel(SHOW_PASSWORD_BUTTON);
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void waitForPageToLoad() {
        logger.info("Waiting for page to load");
        page.waitForSelector(FEED_SELECTOR, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        logger.info("Page loaded successfully");
    }

    public void enterUserName(String username) {
        emailInput.fill(username);
        continueButton.click();
    }

    public void enterPassword(String password) {
        passwordInput.fill(password);
        loginButton.click();
    }

    public Locator getPassWordInPutSelector() {
        return passwordInput;
    }

    public void login(String url, String username, String password) {
        navigate(url);
        enterUserName(username);
        enterPassword(password);
        waitForPageToLoad();
        logger.info("Logged in successfully");
    }

    public void logout() {
        userProfileMenu.click();
        logoutLink.click();
    }

    public boolean getInvalidUserName() {
        return invalidUserNameError.isVisible();
    }

    public boolean getInvalidPassword() {
        return invalidPasswordError.isVisible();
    }

    public Locator getShowPassword() {
        return showPasswordButton;
    }

}
