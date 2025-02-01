package com.j9nos.pinhokjsonifier;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public record Language(String name, List<Topic> topics, @JsonIgnore String xref) {
    private static final String XREF_TEMPLATE = "//a[starts-with(@href, '/kb/%s')]";

    public static Language newFrom(final String name) {
        final List<String> options = options();
        if (!options.contains(name)) {
            throw new IllegalArgumentException("Your language options are: " + options);
        }
        return new Language(name, new ArrayList<>(), String.format(XREF_TEMPLATE, name));
    }

    public static List<String> options() {
        final List<String> options = new ArrayList<>();
        Scraper.scrape(driver -> {
            driver.get("https://www.pinhok.com");
            Scraper.wait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//a[starts-with(@href, '/learn-')]"))
            ).forEach(element -> {
                Scraper.scroll(driver, element);
                final String url = element.getDomAttribute("href");
                if (null == url || url.trim().isEmpty() || !url.contains("/learn-")) {
                    return;
                }
                options.add(url.replaceAll("/learn-", "").replaceAll("/", ""));
            });
        });
        return options;
    }


}