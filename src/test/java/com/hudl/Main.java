//package com.hudl;
//
//import com.microsoft.playwright.*;
//
//public class Main {
//    public static void main(String[] args) {
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch();
//            Page page = browser.newPage();
//            page.navigate("https://playwright.dev");
//            System.out.println(page.title());
//        }
//    }
//
//
//}