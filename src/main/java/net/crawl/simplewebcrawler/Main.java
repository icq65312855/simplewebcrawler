package net.crawl.simplewebcrawler;

import net.crawl.simplewebcrawler.tools.ElementSelector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
    private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if (args.length >= 2) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

            String resourcePathOriginal = args[0];//"./samples/original.htm";
            String resourcePathOther = args[1];//"./samples/other_first.htm";

            ElementSelector crawler = context.getBean(ElementSelector.class);
            if (args.length > 2) {
                crawler.setTarget(args[2]);
            }
            crawler.find(resourcePathOriginal, resourcePathOther).ifPresent(crawler::printPath);
        } else {
            LOGGER.error("It's necessary 2 arguments!!!");
        }
    }
}