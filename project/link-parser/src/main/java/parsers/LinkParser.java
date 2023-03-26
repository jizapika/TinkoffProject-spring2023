package parsers;

import answers.LinkParserAnswer;

public interface LinkParser {
    LinkParserAnswer parse(String link);
}