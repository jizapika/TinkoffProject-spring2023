package parsers;

import answers.LinkParserAnswer;

public interface LinkParser {
    LinkParser next = null;
    LinkParserAnswer parse(String link);
}