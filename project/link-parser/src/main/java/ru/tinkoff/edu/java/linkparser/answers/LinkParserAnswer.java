package ru.tinkoff.edu.java.linkparser.answers;

public sealed interface LinkParserAnswer permits GithubLinkParserAnswer, StackoverflowLinkParserAnswer {
}