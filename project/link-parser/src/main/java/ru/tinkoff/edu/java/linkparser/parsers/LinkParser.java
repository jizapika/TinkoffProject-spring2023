package ru.tinkoff.edu.java.linkparser.parsers;

import ru.tinkoff.edu.java.linkparser.answers.LinkParserAnswer;

public interface LinkParser {
    LinkParserAnswer parse(String link);
}