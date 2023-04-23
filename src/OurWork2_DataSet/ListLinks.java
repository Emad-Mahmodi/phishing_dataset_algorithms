package OurWork2_DataSet;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Example program to list links from a URL.
 */

public class ListLinks {
	public static ArrayList<String> AllLinks = new ArrayList<String>();
	public static ArrayList<String> AllWord = new ArrayList<String>();

	public static void main(String url) throws IOException {

		Document doc = Jsoup.connect(url).get();
		String text = doc.body().text();
		AllWord = CheckWord(getWords(text));

		Elements links = doc.select("a[href]");
		Elements media = doc.select("[src]");
		Elements imports = doc.select("link[href]");

		print("\nMedia: (%d)", media.size());
		for (Element src : media) {
			if (src.tagName().equals("img")) {
				print(" * %s: <%s> %sx%s (%s)", src.tagName(), src.attr("abs:src"), src.attr("width"),
						src.attr("height"), trim(src.attr("alt"), 20));
				AllLinks.add(src.attr("abs:src"));
			} else
				print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
			if (src.attr("abs:src") == null 
					|| src.attr("abs:src").isEmpty()
					||countOccurrences("//", src.attr("abs:src"))>1) {
				continue;
			} else {
				AllLinks.add(src.attr("abs:src"));
			}

		}

		print("\nImports: (%d)", imports.size());
		for (Element link : imports) {
			print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
			if (link.attr("abs:href") == null 
					|| link.attr("abs:href").isEmpty()
					||countOccurrences("//", link.attr("abs:href"))>1) {
				continue;
			} else {
				AllLinks.add(link.attr("abs:href"));
			}
		}

		print("\nLinks: (%d)", links.size());
		for (Element link : links) {
			print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
			if (link.attr("abs:href") == null 
					|| link.attr("abs:href").isEmpty()
					||countOccurrences("//", link.attr("abs:href"))>1) {
				continue;
			} else {
				AllLinks.add(link.attr("abs:href"));
			}
		}
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}

	public static ArrayList<String> CheckWord(List<String> AllWord) {
		ArrayList<String> Word_filter = new ArrayList<String>();
		// ----------------------------convert array list into string
		// ------------
		String listString = "";
		for (String s : AllWord) {
			listString += s + " ";
		}

		Word_filter.clear();
		Word_filter = getWords(deDup(listString));

		return Word_filter;
	}

	public static String deDup(String s) {
		return new LinkedHashSet<String>(Arrays.asList(s.split(" "))).toString();
	}

	public static ArrayList<String> getWords(String text) {
		ArrayList<String> words = new ArrayList<String>();
		BreakIterator breakIterator = BreakIterator.getWordInstance();
		breakIterator.setText(text);
		int lastIndex = breakIterator.first();
		while (BreakIterator.DONE != lastIndex) {
			int firstIndex = lastIndex;
			lastIndex = breakIterator.next();
			if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
				words.add(text.substring(firstIndex, lastIndex));
			}
		}

		return words;
	}

	public static ArrayList<String> Word_that_host_plus_SLD(ArrayList<String> Words) {
		for (int i = 0; i < Words.size(); i++) {
			if (Words.get(i).contains(".")) {
				String[] s2 = Words.get(i).split(Pattern.quote("."));// String[]
																		// s2 =
																		// Words.get(i).split("\\.");
				for (int i2 = 0; i2 < s2.length; i2++) {
					Words.add(s2[i2]);
				}
			}
		}
		return Words;
	}
	public static int countOccurrences(String find, String string)
	{
			  int count = 0;
			  int indexOf = 0;
			
			  while (indexOf > -1)
			  {
			    indexOf = string.indexOf(find, indexOf + 1);
			    if (indexOf > -1)
			      count++;
			  }
			
			  return count;
	}

}