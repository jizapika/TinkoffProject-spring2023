package ru.tinkoff.edu.java.linkparser;

import ru.tinkoff.edu.java.linkparser.parsers.AbstractParser;
import ru.tinkoff.edu.java.linkparser.parsers.GithubLinkParser;
import ru.tinkoff.edu.java.linkparser.parsers.StackoverflowLinkParser;

public class ParsersChainBuilder {
    private AbstractParser first;
    private AbstractParser last;

    public ParsersChainBuilder(AbstractParser first) {
        this.first = first;
    }

    public void append(AbstractParser next) {
        last.setNext(next);
        last = next;
    }

    public AbstractParser build() {
        return first;
    }

    public static AbstractParser createDefaultChain() {
        AbstractParser gh = new GithubLinkParser();
        AbstractParser so = new StackoverflowLinkParser();
        gh.setNext(so);
        return gh;
    }
}
