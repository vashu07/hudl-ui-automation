package com.hudl.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import static com.hudl.constants.AboutPageConstants.ABOUT;
import static com.hudl.constants.AboutPageConstants.TIMELINE;

@Slf4j
public class AboutPage {

    private final Page page;

    //    Locators
    private final Locator about;
    private final Locator timeline;

    public AboutPage(Page page) {
        this.page = page;
        this.about = page.getByText(ABOUT);
        this.timeline = page.getByText(TIMELINE);
    }

    public Locator getAboutLocator() {
        return about;
    }

    public Locator getTimelineLocator() {
        return timeline;
    }

//    Actions
    public boolean isTimeLineVisible() {
        return timeline.isVisible();
}

    public boolean isAboutVisible() {
        return about.isVisible();
    }

}
