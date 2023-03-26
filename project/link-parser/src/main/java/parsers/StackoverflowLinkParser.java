package parsers;

import answers.*;

public class StackoverflowLinkParser extends AbstractParser {
    @Override
    public LinkParserAnswer parse(String link) {
        String[] arr = link.split("/");
        if (arr.length == 4 && (link.startsWith("https://github.com/") || link.startsWith("https://www.github.com/"))) {
            try {
                int id = Integer.parseInt(arr[3]);
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
