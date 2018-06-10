package net.crawl.simplewebcrawler.locator;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JsoupCssSelectLocator extends GenericLocator {
    public Optional<Elements> find(String resourcePath, String cssQuery) {
        return readDocument(resourcePath).map(doc -> getSelect(cssQuery, doc));
    }

    private Elements getSelect(String cssQuery, Document doc) {
        return doc.select(cssQuery);
    }
}