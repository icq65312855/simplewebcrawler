# simplewebcrawler

Basic algorithm:
1. Find the element by the given id and get all its attributes.
2. For another page, get a list of all the elements where at least one attribute has the same value as in the original.
3. Select the element with the largest number of matches.

Class packages:
Locator:
  The classes for parsing and searching elements by id or CSS query.
Tools:
  CssQueryMaker - for making ccs query based on the attributes.
  ElementSelector - for searching target element in another page.

You can find jar file in /jar folder.
To execute: java -jar ./jar/simplewebcrawler.jar ./samples/original.htm ./samples/other_fourth.htm 
Also, you can pass third argument (element id). In this case, the search will be performed for the specified id.
There must be at least two arguments!

Output for four cases:
Page First -  Target element path: html > body > div > div > div[row] > div[col-lg-8] > div[panel, panel-default] > div[panel-body] > a[btn, btn-success]
Page Second - Target element path: html > body > div > div > div[row] > div[col-lg-8] > div[panel, panel-default] > div[panel-body] > div[some-container] > a[btn, test-link-ok]
Page Third -  Target element path: html > body > div > div > div[row] > div[col-lg-8] > div[panel, panel-default] > div[panel-footer] > a[btn, btn-success]
Page Fourth - Target element path: html > body > div > div > div[row] > div[col-lg-8] > div[panel, panel-default] > div[panel-footer] > a[btn, btn-success]
