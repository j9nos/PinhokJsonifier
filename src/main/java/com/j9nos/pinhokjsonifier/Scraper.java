package com.j9nos.pinhokjsonifier;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

final class Scraper {
    private static final Duration ALLOWED_WAITING_FOR_ELEMENTS = Duration.ofSeconds(5);
    private static final ChromeOptions CHROME_OPTIONS = new ChromeOptions();

    static {
        CHROME_OPTIONS.addArguments("--headless=new");
    }

    private Scraper() {
    }

    static void scrape(final Action action) {
        final WebDriver driver = new ChromeDriver(CHROME_OPTIONS);
        try {
            action.execute(driver);
        } finally {
            driver.quit();
        }
    }

    static void scroll(final WebDriver driver, final WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    static WebDriverWait wait(final WebDriver driver) {
        return new WebDriverWait(driver, ALLOWED_WAITING_FOR_ELEMENTS);
    }


    @FunctionalInterface
    interface Action {
        void execute(WebDriver driver);
    }


}
