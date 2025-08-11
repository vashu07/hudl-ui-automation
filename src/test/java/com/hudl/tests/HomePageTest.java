
package com.hudl.tests;

import com.hudl.pages.HomePage;
import com.hudl.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.hudl.constants.HomePageConstants.SEARCH_TERM;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePageTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndNavigateToHomePage() {
        this.loginPage = new LoginPage(page);
        this.homePage = new HomePage(page);
        loginPage.login(regionManager.getLoginUrl(), regionManager.getUserName(), regionManager.getPassword());
        assertThat(page).hasURL(regionManager.getHomePageUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void logout() {
        loginPage.logout();
    }

    @Test(description = "verify home page displays all core elements", groups = {"smoke"}, priority = 1)
    public void homePageShouldDisplayAllCoreElements() {
        assertThat(homePage.getNewCastleJetsLocator()).isVisible();
        assertThat(homePage.getLibraryLocator()).isVisible();
        assertThat(homePage.getReportsLocator()).isVisible();
        assertThat(homePage.getTeamsLocator()).isVisible();
        assertThat(homePage.getHighlightsLocator()).isVisible();
        assertThat(homePage.getRecruitingLocator()).isVisible();
        assertThat(homePage.getFeedLocator()).isVisible();
        assertThat(homePage.getFeaturedLocator()).isVisible();
        assertThat(homePage.getYourTeamsLocator()).isVisible();
        assertThat(homePage.getSuggestionsLocator()).isVisible();
        assertThat(homePage.getMoreSuggestionsLocator()).isVisible();
    }

    @Test(description = "verify search functionality", priority = 2)
    public void verifySearchTextBox() {
        homePage.searchFor(SEARCH_TERM);
    }

}
