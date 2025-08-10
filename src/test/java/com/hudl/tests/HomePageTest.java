
package com.hudl.tests;

import com.hudl.pages.HomePage;
import com.hudl.pages.LoginPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePageTest extends BaseTest {

    LoginPage loginPage;
    HomePage landingPage;

    public void setupTest() {
        loginPage = new LoginPage(page);
        landingPage = new HomePage(page);
        loginPage.login(regionManager.getLoginUrl(), regionManager.getUserName(), regionManager.getPassword());
        assertThat(page).hasURL(regionManager.getHomePageUrl());
    }

    @Test
    public void searchTest() {
        setupTest();
        landingPage.clickOnSearch();
        loginPage.logout();
    }

    @Test
    public void verifyAllElementsInLoginPage() {
        setupTest();
        page.waitForTimeout(10000);
        assertThat(landingPage.newCastleJetsLocator()).isVisible();
        assertThat(landingPage.getLibraryLocator()).isVisible();
        assertThat(landingPage.getReportsLocator()).isVisible();
        assertThat(landingPage.getTeamsLocator()).isVisible();
        assertThat(landingPage.getHighlightsLocator()).isVisible();
        assertThat(landingPage.getRecruitingLocator()).isVisible();
        assertThat(landingPage.getFeedLocator()).isVisible();
        assertThat(landingPage.getFeaturedLocator()).isVisible();
        assertThat(landingPage.getYourTeamsLocator()).isVisible();
        assertThat(landingPage.getSuggestionsLocator()).isVisible();
        assertThat(landingPage.getMoreSuggestionsLocator()).isVisible();
        loginPage.logout();
    }
}
