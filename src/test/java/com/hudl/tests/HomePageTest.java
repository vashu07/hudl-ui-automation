
package com.hudl.tests;

import com.hudl.pages.AboutPage;
import com.hudl.pages.HomePage;
import com.hudl.pages.LoginPage;
import com.hudl.utils.ScrollUtils;
import com.hudl.utils.WaitUtils;
import com.microsoft.playwright.Locator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.hudl.constants.AboutPageConstants.TIMELINE;
import static com.hudl.constants.AboutPageConstants.TIMELINE_SELECTOR;
import static com.hudl.constants.ErrorMessageConstants.EXPECTED_ELEMENT_NOT_VISIBLE_MESSAGE;
import static com.hudl.constants.HomePageConstants.SEARCH_ITEM;
import static com.hudl.constants.HomePageConstants.SEARCH_RESULTS;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class HomePageTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    AboutPage aboutPage;

    @BeforeMethod(alwaysRun = true)
    public void loginAndNavigateToHomePage() {
        this.loginPage = new LoginPage(page);
        this.homePage = new HomePage(page);
        this.aboutPage = new AboutPage(page);
        loginPage.login(regionManager.getLoginUrl(), regionManager.getUserName(), regionManager.getPassword());
        assertThat(page).hasURL(regionManager.getHomePageUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void logout() {
        loginPage.logout();
    }

    /**
     * Verify home page displays all core elements
     *
     */
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

    /**
     * Verify search functionality
     * Search for a text
     * click on the first item in the results
     * verify whether the page is redirected to About-Page
     * Logout
     */
    @Test(description = "verify search functionality", priority = 2)
    public void verifySearchTextBox() {
        homePage.searchForAnItemAndClick(SEARCH_ITEM);
        homePage.getSearch().first().click();
        WaitUtils.waitForElementVisible(page, TIMELINE_SELECTOR, 5000);
        assertTrue(aboutPage.isAboutVisible() || aboutPage.isTimeLineVisible(), EXPECTED_ELEMENT_NOT_VISIBLE_MESSAGE);
    }


    /**
     * Login -> Navigate to home page
     * Search a text in search text box
     * Scroll down and click on any result that is visible
     * verify whether the page is redirected to About-Page
     */
    @Test(description = "verify if search dropdown is scrollable", priority = 2)
    public void verifySearchIsScrollable() {
        homePage.searchFor(SEARCH_ITEM);
        Locator searchResults = ScrollUtils.infiniteScrollInContainer(page, SEARCH_RESULTS, 200, 4, 1000);
        searchResults.first().click();
        WaitUtils.waitForElementVisible(page, TIMELINE_SELECTOR, 5000);
        assertTrue(aboutPage.isAboutVisible() || aboutPage.isTimeLineVisible(),EXPECTED_ELEMENT_NOT_VISIBLE_MESSAGE);

    }

}
