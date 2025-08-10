package com.hudl.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.hudl.constants.HomePageConstants.*;

public class HomePage {

    private final Page page;

//    Locators
    private final Locator searchInput;
    private final Locator newcastleJetsLink;
    private final Locator libraryLink;
    private final Locator reportsLink;
    private final Locator teamText;
    private final Locator highlightsLink;
    private final Locator recruitingText;
    private final Locator feedText;
    private final Locator featuredText;
    private final Locator yourTeamsText;
    private final Locator suggestionsText;
    private final Locator moreSuggestionsButton;
    private final Locator searchItem;

    public HomePage(Page page) {
        this.page = page;

        this.searchInput = page.getByPlaceholder(SEARCH_PLACEHOLDER);
        this.newcastleJetsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(NEWCASTLE_JETS_LINK_TEXT));
        this.libraryLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(LIBRARY_LINK_TEXT));
        this.reportsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(REPORTS_LINK_TEXT));
        this.teamText = page.getByText(TEAM_TEXT, new Page.GetByTextOptions().setExact(true));
        this.highlightsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(HIGHLIGHTS_LINK_TEXT));
        this.recruitingText = page.getByText(RECRUITING_TEXT);
        this.feedText = page.getByText(FEED_TEXT);
        this.featuredText = page.getByText(FEATURED_TEXT);
        this.yourTeamsText = page.getByText(YOUR_TEAMS_TEXT, new Page.GetByTextOptions().setExact(true));
        this.suggestionsText = page.getByText(SUGGESTIONS_TEXT, new Page.GetByTextOptions().setExact(true));
        this.moreSuggestionsButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(MORE_SUGGESTIONS_BUTTON_TEXT));
        this.searchItem = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(SEARCH_ITEM));
    }


    public void searchFor(String searchTerm) {
        searchInput.click();
        searchInput.fill(searchTerm);
        searchItem.click();
    }

    public Locator getNewCastleJetsLocator() {
        return newcastleJetsLink;
    }

    public Locator getLibraryLocator() {
        return libraryLink;
    }

    public Locator getReportsLocator() {
        return reportsLink;
    }

    public Locator getTeamsLocator() {
        return teamText;
    }

    public Locator getHighlightsLocator() {
        return highlightsLink;
    }

    public Locator getRecruitingLocator() {
        return recruitingText;
    }

    public Locator getFeedLocator() {
        return feedText;
    }

    public Locator getFeaturedLocator() {
        return featuredText;
    }

    public Locator getYourTeamsLocator() {
        return yourTeamsText;
    }

    public Locator getSuggestionsLocator() {
        return suggestionsText;
    }

    public Locator getMoreSuggestionsLocator() {
        return moreSuggestionsButton;
    }

}
