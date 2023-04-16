package ru.tinkoff.edu.java.linkparser.parsers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @ValueSource(strings = {
            "https://stackoverfow.com/questions/40796756/assertall-vs-multiple-assertions-in-junit5",
            "https://stackoverflow.com/questions/40796756/assertall-vs-multiple-assertions-in-junit5//"
    })
    void parse_receivedUncorrectedLinks_returnNull(String notCorrectLink) {
        // when
        LinkParserAnswer withoutLetter = parser.parse(notCorrectLink);
        // then
        Assertions.assertNull(withoutLetter);
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