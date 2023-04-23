package ParsingWithSoup_xxx;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ParseTitle {
	public static String out;
	  public String title(String s) throws IOException {
		try {
			  Document doc;
//			  doc = Jsoup.connect("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/#identifier").get();
//			  String html = "<div><p>Lorem ipsum.</p>";
			  doc = Jsoup.parse(new File(s), "ISO-8859-1");
			  
			  String title="";
			  String content="";
			  String BodyTitle="";
			  
			  title= doc.title();

			  Element body = doc.body();
	    	  content = body.children().text();
	    	  BodyTitle=new StringBuilder().append(title).append(" ").append(content).toString();

//			  System.out.println("Title : " + title);  
//			  System.out.println("body : " + tagname);
//			  System.out.println( BodyTitle);

			  out=title;
			  
//			  System.out.println("body : " + body.text());
		} catch (Exception e) {
			out="";
		}

		  
		  return out;
		  }
	  
}

