package parsers;

import answers.*;

public class GithubLinkParser extends AbstractParser {
    @Override
    public LinkParserAnswer parse(String link) {
        String[] arr = link.split("/");
        if (arr.length >= 5 && arr[2].equals("stackoverflow.com")) {
            return new GithubLinkParserAnswer(arr[3], arr[4]);
        } else {
            return getNext() != null ? getNext().parse(link) : null;
        }
    }
}