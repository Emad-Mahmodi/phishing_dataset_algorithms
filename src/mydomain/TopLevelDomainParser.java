package mydomain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Parses the list from <a href="http://publicsuffix.org/">publicsuffix.org
 * Copied from http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/httpclient/src/main/java/org/apache/http/impl/cookie/PublicSuffixListParser.java
 */
public class TopLevelDomainParser {
    private static final int MAX_LINE_LEN = 256;
    private final TopLevelDomainChecker filter;

    public TopLevelDomainParser(TopLevelDomainChecker filter) {
        this.filter = filter;
    }
    public void parse(Reader list) throws IOException {
        Collection<String> rules = new ArrayList();
        Collection<String> exceptions = new ArrayList();
        BufferedReader r = new BufferedReader(list);
        StringBuilder sb = new StringBuilder(256);
        boolean more = true;
        while (more) {
            more = readLine(r, sb);
            String line = sb.toString();
            if (line.length() == 0) continue;
            if (line.startsWith("//")) continue; //entire lines can also be commented using //
            if (line.startsWith(".")) line = line.substring(1); // A leading dot is optional
            // An exclamation mark (!) at the start of a rule marks an exception to a previous wildcard rule
            boolean isException = line.startsWith("!"); 
            if (isException) line = line.substring(1);

            if (isException) {
                exceptions.add(line);
            } else {
                rules.add(line);
            }
        }

        filter.setPublicSuffixes(rules);
        filter.setExceptions(exceptions);
    }
    private boolean readLine(Reader r, StringBuilder sb) throws IOException {
        sb.setLength(0);
        int b;
        boolean hitWhitespace = false;
        while ((b = r.read()) != -1) {
            char c = (char) b;
            if (c == '\n') break;
            // Each line is only read up to the first whitespace
            if (Character.isWhitespace(c)) hitWhitespace = true;
            if (!hitWhitespace) sb.append(c);
            if (sb.length() > MAX_LINE_LEN) throw new IOException("Line too long"); // prevent excess memory usage
        }
        return (b != -1);
    }
}
