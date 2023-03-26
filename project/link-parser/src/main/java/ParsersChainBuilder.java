import parsers.AbstractParser;
import parsers.GithubLinkParser;
import parsers.LinkParser;
import parsers.StackoverflowLinkParser;

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
