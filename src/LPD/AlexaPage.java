package LPD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class AlexaPage {
    private static final String URL = "http://www.alexa.com/topsites";


    public static ArrayList<String> main() throws IOException {
        Document doc = Jsoup.connect(URL).get();


        ArrayList<String> topList=new ArrayList<String>();
/*        Elements divs = doc.select("li");
        Elements els = doc.getElementsByClass("site-listing");
        for(Element div : els){
        	 href = div.select("a").first().attr("href");
        	 topList.add(href);
        System.out.println(href);
        }*/
        String href="";

//   --------------------------------
        String myURL="";
        for(int i=1;i<20;i++){//top 500 website in alexa must be in the 20 page
        myURL="http://www.alexa.com/topsites/global;"+i;
        Document doc2 = Jsoup.connect(myURL).get();
        Elements els2 = doc2.getElementsByClass("site-listing");

        for(Element div : els2){
       	 href = div.select("a").first().attr("href");
       	 topList.add(href);
         System.out.println(href);
         }
       	 System.out.println("----------------------");
        }

        System.out.println(topList);
        return topList;
    }
        
    
    
    
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }


}
