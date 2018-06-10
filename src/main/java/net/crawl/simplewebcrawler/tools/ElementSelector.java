package net.crawl.simplewebcrawler.tools;

import net.crawl.simplewebcrawler.locator.JsoupCssSelectLocator;
import net.crawl.simplewebcrawler.locator.JsoupFindByIdLocator;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ElementSelector {

    private static Logger LOGGER = LoggerFactory.getLogger(ElementSelector.class);

    @Autowired
    private JsoupFindByIdLocator idLocator;

    @Autowired
    private JsoupCssSelectLocator cssLocator;

    @Autowired
    private CssQueryMaker cssQueryMaker;

    @Value("${default_id}")
    private String target;

    public void setTarget(String target) {
        this.target = target;
    }

    static public Element select(Element original, Elements elements) {
        TreeMap<Long, Element> map = new TreeMap<>();

        for (Element item : elements) {
            long matches = item.attributes().asList().stream()
                    .filter(at -> at.getValue().equals(original.attributes().get(at.getKey())))
                    .count();

            if (matches > 0) {
                map.put(matches, item);
            }
        }

        return map.lastEntry().getValue();
    }

    public void printPath(Element foundHtmlItem) {
        Elements parents = foundHtmlItem.parents();
        Collections.reverse(parents);
        parents.add(foundHtmlItem);
        String path = parents.stream().map(this::getElementName).collect(Collectors.joining(" > "));
        LOGGER.info("Target element path: {}", path);
    }

    public Optional<Element> find(String resourcePathOriginal, String resourcePathOther) {
        Optional<Element> original = idLocator.find(resourcePathOriginal, target);
        String cssQuerry = cssQueryMaker.makeQuery(original);
        Optional<Elements> matchingElements = cssLocator.find(resourcePathOther, cssQuerry);
        return Optional.of(select(original.get(), matchingElements.get()));
    }

    private String getElementName(Element element) {
        return element.tagName() + (element.classNames().isEmpty() ? "" : element.classNames());
    }
}