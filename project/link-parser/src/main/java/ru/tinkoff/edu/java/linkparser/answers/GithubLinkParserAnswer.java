package ru.tinkoff.edu.java.linkparser.answers;

public record GithubLinkParserAnswer(String username, String repo) implements LinkParserAnswer {
}