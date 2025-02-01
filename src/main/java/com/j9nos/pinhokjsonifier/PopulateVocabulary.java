package com.j9nos.pinhokjsonifier;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public final class PopulateVocabulary {
    private static final String BASE_URL = "https://www.pinhok.com";
    private static final String LEARN_URL = BASE_URL + "/learn-";

    private PopulateVocabulary() {
    }


    public static void of(final Language language) {
        Scraper.scrape(driver -> {
            driver.get(LEARN_URL + language.name());
            Scraper.wait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(language.xref())))
                    .forEach(element -> {
                        Scraper.scroll(driver, element);
                        final String topicUrl = BASE_URL + element.getDomAttribute("href");
                        final String topicName = topicUrl.split("/")[topicUrl.split("/").length - 1]
                                .replaceAll("/", "");
                        final Topic topic = Topic.newFrom(topicName, topicUrl);
                        System.out.println("Found: " + topic);
                        language.topics().add(topic);
                    });

            language.topics().forEach(topic -> {
                driver.get(topic.url());
                Scraper.wait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(Topic.XREF)))
                        .forEach(element -> {
                            Scraper.scroll(driver, element);
                            final String from = element.findElement(By.className("kbfrom"))
                                    .findElement(By.tagName("span")).getText();
                            final String to = element.findElement(By.className("kbto"))
                                    .findElement(By.tagName("span")).getText();
                            final WordPair wordPair = new WordPair(from, to);
                            System.out.println("Found: " + wordPair);
                            topic.words().add(wordPair);
                        });
            });
        });
    }


}
