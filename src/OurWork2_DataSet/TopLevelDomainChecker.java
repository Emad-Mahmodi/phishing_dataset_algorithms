package OurWork2_DataSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TopLevelDomainChecker  {
    private Set<String> exceptions;
    private Set<String> suffixes;

    public void setPublicSuffixes(Collection<String> suffixes) {
        this.suffixes = new HashSet<String>(suffixes);
    }
    public void setExceptions(Collection<String> exceptions) {
        this.exceptions = new HashSet<String>(exceptions);
    }

    /**
     * Checks if the domain is a TLD.
     * @param domain
     * @return
     */
    public boolean isTLD(String domain) {
        if (domain.startsWith(".")) 
            domain = domain.substring(1);

        // An exception rule takes priority over any other matching rule.
        // Exceptions are ones that are not a TLD, but would match a pattern rule
        // e.g. bl.uk is not a TLD, but the rule *.uk means it is. Hence there is an exception rule
        // stating that bl.uk is not a TLD. 
        if (this.exceptions != null && this.exceptions.contains(domain)) 
            return false;


        if (this.suffixes == null) 
            return false;

        if (this.suffixes.contains(domain)) 
            return true;

        // Try patterns. ie *.jp means that boo.jp is a TLD
        int nextdot = domain.indexOf('.');
        if (nextdot == -1)
            return false;
        domain = "*" + domain.substring(nextdot);
        if (this.suffixes.contains(domain)) 
            return true;

        return false;
    }


    public String extractSLD(String domain)
    {
        String last = domain;
        boolean anySLD = false;
        do
        {
            if (isTLD(domain))
            {
                if (anySLD)
                    return domain;
                else
                    return "";
            }
            anySLD = true;
            last = domain;
            int nextDot = domain.indexOf(".");
            if (nextDot == -1)
                return "";
            domain = domain.substring(nextDot+1);
        } while (domain.length() > 0);
        return domain;
    }
}