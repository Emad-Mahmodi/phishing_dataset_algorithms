package PhishWho;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSearch {
	public static ArrayList<String> MySearch(String S , int number) throws Exception {
	String google = "http://www.google.com/search?q=";
	String search = S;
	String charset = "UTF-8";
	String userAgent = "ExampleBot 1.0 (+http://example.com/bot)"; // Change this to your company's name and bot homepage!

//	Elements links = Jsoup.connect(google + (URLEncoder.encode(search, charset))+"&num="+number).userAgent(userAgent).get().select(".g>.r>a");
	ArrayList<String> UrlList;
	UrlList = new ArrayList<String>();

	try {

		Elements links = Jsoup.connect(google + (URLEncoder.encode(search, charset))).userAgent(userAgent).get().select(".g>.r>a");
		int count=0;
		for (Element link : links) {
		    String title = link.text();
		    String url = link.absUrl("href"); // Google returns URLs in format "http://www.google.com/url?q=<url>&sa=U&ei=<someKey>".
		    url = URLDecoder.decode(url.substring(url.indexOf('=') + 1, url.indexOf('&')), "UTF-8");
		    UrlList.add(url);
		    if (!url.startsWith("http")) {
		        continue; // Ads/news/etc.
		    }
count++;
//	    System.out.println(count +"  "+url +"   ===>  "+ "Title: " + title );
//	    System.out.println("URL: " + url);
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	System.out.println(UrlList);
	return UrlList;
	}
}
