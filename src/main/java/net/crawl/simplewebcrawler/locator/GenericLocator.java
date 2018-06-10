package net.crawl.simplewebcrawler.locator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public abstract class GenericLocator {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericLocator.class);

    @Value("${charset_name}")
    protected String charset;

    public Optional<Document> readDocument(String path) {
        try {
            return Optional.of(Jsoup.parse(
                    new File(path),
                    charset));
        } catch (IOException e) {
            LOGGER.error("Error reading [{}] file", path, e);
            return Optional.empty();
        }
    }
}