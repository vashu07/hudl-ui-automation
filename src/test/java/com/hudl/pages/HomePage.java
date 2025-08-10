package com.hudl.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class HomePage {

    private final Page page;

    //    Locators
    public String userNameLocator = "#username";

    public HomePage(Page page) {
        this.page = page;
    }

    public void clickOnSearch() {
        page.getByPlaceholder("Search for athletes, teams,").click();
        page.getByPlaceholder("Search for athletes, teams,").fill("test");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("tt test test soccer Hudl")).click();
    }

    public Locator newCastleJetsLocator() {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Newcastle Jets FC"));
    }

    public Locator getLibraryLocator() {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Library"));
    }

    public Locator getReportsLocator() {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Reports"));
    }

    public Locator getTeamsLocator() {
        return page.getByText("Team", new Page.GetByTextOptions().setExact(true));
    }

    public Locator getHighlightsLocator() {
        return page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Highlights"));
    }

    public Locator getRecruitingLocator() {
        return page.getByText("Recruiting");
    }

    public Locator getFeedLocator() {
        return page.getByText("Feed");
    }

    public Locator getFeaturedLocator() {
        return page.getByText("Featured");
    }

    public Locator getYourTeamsLocator() {
        return page.getByText("Your Teams", new Page.GetByTextOptions().setExact(true));
    }

    public Locator getSuggestionsLocator() {
        return page.getByText("Suggestions", new Page.GetByTextOptions().setExact(true));
    }

    public Locator getMoreSuggestionsLocator() {
        return page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("More Suggestions"));
    }

}
