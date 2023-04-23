package ParsingWithSoup_xxx;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import netPC.*;

public class contentWithTag {
	public static String out;

	  public String coTag(String s) throws IOException {
		try {
				  Document doc;
				  doc = Jsoup.connect(s).get();
				  out=doc.toString();
	    }catch (Exception e) {
	    	ReadNetworkFile ne=new ReadNetworkFile();
	    	out=ne.ContentWeb(s);
		}
		  return out;
	}
	  
}
