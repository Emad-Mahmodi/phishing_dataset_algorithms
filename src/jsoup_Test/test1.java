package jsoup_Test;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class test1 {

	public static void main(String[] args) throws IOException {
		try {
			/*Document doc = null;
			doc = Jsoup.connect("https://www.netflix.com").get();
			System.out.println(doc);*/
			
			String url = "https://www.google.com";
			Document doc=Jsoup.connect(url).get();
			Response response = Jsoup.connect(url).followRedirects(true).execute();
			String s=response.url().toString();
			System.out.println(s);

			doc = Jsoup.connect(s).get();
			Elements TitleAttrs = doc.select("body");
			System.out.println(TitleAttrs.text());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
