package ru.tinkoff.edu.java.linkparser.parsers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.linkparser.ParsersChainBuilder;
import ru.tinkoff.edu.java.linkparser.answers.GithubLinkParserAnswer;
import ru.tinkoff.edu.java.linkparser.answers.LinkParserAnswer;
import ru.tinkoff.edu.java.linkparser.answers.StackoverflowLinkParserAnswer;

import static org.junit.jupiter.api.Assertions.assertNull;

class AbstractParserTest {
    private final AbstractParser parser;

    public AbstractParserTest() {
        // given
        this.parser = ParsersChainBuilder.createDefaultChain();
    }

    @Test
    void parse_receivedUncorrectedLinks_returnNull() {
        // when
        LinkParserAnswer withoutLetter = parser.parse("https://stackoverfow.com/questions/40796756/assertall-vs-multiple-assertions-in-junit5");
        LinkParserAnswer withAnExtraSlash = parser.parse("https://stackoverflow.com/questions/40796756/assertall-vs-multiple-assertions-in-junit5//");
        // then
        Assertions.assertAll(
                () -> assertNull(withoutLetter),
                () -> assertNull(withAnExtraSlash));
    }

    @Test
    void parse_receivedCorrectedGithubLink_returnCorrectAnswer() {
        // when
        LinkParserAnswer answer = parser.parse("https://github.com/sanyarnd/tinkoff-java-course-2022/");
        // then
        Assertions.assertEquals(answer,
                new GithubLinkParserAnswer("sanyarnd", "tinkoff-java-course-2022"));
    }

    @Test
    void parse_receivedCorrectedStackoverflowLink_returnCorrectAnswer() {
        // when
        LinkParserAnswer answer = parser.parse("https://stackoverflow.com/questions/40796756/assertall-vs-multiple-assertions-in-junit5");
        // then
        Assertions.assertEquals(answer,
                new StackoverflowLinkParserAnswer(40796756L));
    }
}