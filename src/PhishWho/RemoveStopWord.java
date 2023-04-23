package PhishWho;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveStopWord {

	public static String Remove(String S) {
		// instead of the ".....", add all your stopwords, separated by "|"
	    // "\\b" is to account for word boundaries, i.e. not replace "his" in "this"
	    // the "\\s?" is to suppress optional trailing white space
//	    Pattern p = Pattern.compile("\\b(I|and|phone|this|its.....)\\b\\s?");//[but, be, with, such, then, for, no, will, not, are, and, their, if, this, on, into, a, or, there, in, that, they, was, is, it, an, the, as, at, these, by, to, of]
	   
	    Pattern p = Pattern.compile("\\b(I|but|be|with|such|then|for|so|'s|no|will|not|are|and|their|if|this|on|into|a|or|there|in|that|they|was|is|it|an|the|as|at|these|by|to|of|its|a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z)\\b\\s?");
	    Matcher m = p.matcher(S);
	    String s = m.replaceAll("");
	    System.out.println(s);
	    return s;
	}

}
