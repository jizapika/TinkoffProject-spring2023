package ru.tinkoff.edu.java.linkparser.parsers;

import ru.tinkoff.edu.java.linkparser.answers.*;

public class GithubLinkParser extends AbstractParser {
    @Override
    public LinkParserAnswer parse(String link) {
        String copyLink = new String(link);
        if (copyLink.endsWith("/"))
            copyLink = copyLink.substring(0, link.length() - 1);
        String[] arr = copyLink.split("/");
        if (!copyLink.endsWith("/") && arr.length == 5 && copyLink.startsWith("https://github.com/")) {
            return new GithubLinkParserAnswer(arr[3], arr[4]);
        } else {
            return getNext() != null ? getNext().parse(link) : null;
        }
    }
}