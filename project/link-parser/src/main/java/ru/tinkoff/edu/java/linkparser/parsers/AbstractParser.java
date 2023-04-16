package ru.tinkoff.edu.java.linkparser.parsers;

public abstract class AbstractParser implements LinkParser {
    protected LinkParser next;

    public void setNext(LinkParser next) {
        this.next = next;
    }

    public LinkParser getNext() {
        return next;
    }
}
