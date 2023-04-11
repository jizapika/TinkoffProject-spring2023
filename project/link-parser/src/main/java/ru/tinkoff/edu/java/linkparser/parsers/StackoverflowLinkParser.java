package ru.tinkoff.edu.java.linkparser.parsers;

import ru.tinkoff.edu.java.linkparser.answers.*;

public class StackoverflowLinkParser extends AbstractParser {
    @Override
    public LinkParserAnswer parse(String link) {
        String copyLink = new String(link);
        if (copyLink.endsWith("/"))
            copyLink = copyLink.substring(0, link.length() - 1);
        String[] arr = copyLink.split("/");
        if (!copyLink.endsWith("/") && arr.length == 6 && copyLink.startsWith("https://stackoverflow.com/questions/")) {
            try {
                long id = Long.parseLong(arr[4]);
                return new StackoverflowLinkParserAnswer(id);
            } catch (NumberFormatException e) {
                return parseInNext(link);
            }
        } else {
            return parseInNext(link);
        }
    }

    private LinkParserAnswer parseInNext(String link) {
        return getNext() != null ? getNext().parse(link) : null;
    }
}
