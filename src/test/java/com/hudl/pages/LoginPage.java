package com.hudl.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {
    private final Page page;

    public String userNameLocator = "#username";
    public String continueLocator = "//button[@name='action']";
    public String passwordLocator = "//input[@id='password']";
    public String submitLocator = "button[type='submit']";
    public String userProfileLocator = "//div[@class='hui-globalusermenu']";


    public LoginPage(Page page) {
        this.page = page;
    }

    public void navigate(String url) {
        page.navigate(url);
    }

    public void enterUserName(String username) {
        page.locator(userNameLocator).fill(username);
        page.locator(continueLocator).click();
    }

    public void enterPassword(String password) {
        page.locator(passwordLocator).fill(password);
        page.locator(submitLocator).click();
    }

    public void login(String url, String username, String password) {
        navigate(url);
        enterUserName(username);
        enterPassword(password);
    }

    public void logout() {
        page.locator(userProfileLocator).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Log Out")).click();
    }

    public boolean getInvalidUserName() {
        return page.getByText("Enter a valid email.").isVisible();
    }

    public boolean getInvalidPassword() {
        return page.getByText("Your email or password is incorrect. Try again.").isVisible();
    }

    public Locator getShowPassword() {
        return page.getByLabel("Show password");
    }

}
