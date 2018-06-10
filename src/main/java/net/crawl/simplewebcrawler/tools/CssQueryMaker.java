package net.crawl.simplewebcrawler.tools;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CssQueryMaker {

    public String makeQuery(Optional<Element> element) {
        Optional<String> s = element.map(item -> item.attributes().asList().stream().
                map(attr -> "[" + attr.getKey() + "=\"" + attr.getValue() + "\"]").collect(Collectors.joining(", ")));
        return s.orElse("");
    }
}