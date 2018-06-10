package net.crawl.simplewebcrawler.locator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class JsoupFindByIdLocator extends GenericLocator {
    public Optional<Element> find(String resourcePath, String cssQuery) {
        return readDocument(resourcePath).map(doc -> getSelect(cssQuery, doc));
    }

    private Element getSelect(String targetElementId, Document doc) {
        return doc.getElementById(targetElementId);
    }
}