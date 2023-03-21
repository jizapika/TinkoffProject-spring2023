import parsers.AbstractParser;
import parsers.GithubLinkParser;
import parsers.LinkParser;
import parsers.StackoverflowLinkParser;

public class ChainCreater {
    public static LinkParser createChain() {
        AbstractParser gh = new GithubLinkParser();
        AbstractParser so = new StackoverflowLinkParser();
        gh.setNext(so);
        return gh;
    }
}
