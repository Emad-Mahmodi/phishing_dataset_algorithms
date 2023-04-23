package CANTINNA;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Cantinna_Main {

public static void main(String[] args) {
	String URL="http://www.tortasfactory.com.mx/secure.paypal.com-support-assistance-update-informations/";
	
	String s = "comsupportassistanceupdateinformations";//com-support-assistance-update-informations
	Pattern p = Pattern.compile("[^a-zA-Z0-9]");
	Pattern p2 = Pattern.compile("[_]");

	Matcher m = p.matcher(s);
	Matcher m2 = p2.matcher(s);

	boolean xx = m.find();
	boolean x2 = m2.find();

	if (!xx==true)
	  System.out.println("The string \"" + s + "\" contains alphanumerical characters.");
	
	int F1 = Embedded_domain(URL);
		
	}
public static int Embedded_domain(String URL) {
	    int is_Embedded_domain = 0;
	    URL = URL.substring(URL.indexOf("//")+2 , URL.length());
	    URL = URL.substring(URL.indexOf("/")+1 , URL.length());
	    int Num_backslash = countOccurrences("/", URL);
	    
	    for (int i = 0; i <= Num_backslash ; i++) {
			URL = URL.substring(0, URL.indexOf("/")+1);
			Check_Embbed_domain(URL);
			URL = URL.substring( URL.indexOf("/") , URL.length());

		}
		return is_Embedded_domain;
}	

public static int Check_Embbed_domain(String URL) {
    int is_Embedded_domain = 0;
    Pattern p = Pattern.compile("[^a-zA-Z0-9]");
    Pattern p2 = Pattern.compile("[-]");
    Pattern p3 = Pattern.compile("[_]");
    
    URL = URL.substring(0 , URL.length());
    int Num_backslash = countOccurrences("/", URL);
    
    for (int i = 0; i < Num_backslash ; i++) {
		URL = URL.substring(0, URL.indexOf("/"));
		int num_dot = 0;
		num_dot = countOccurrences(".", URL);
		if(num_dot == 0 || num_dot<2 ){
			continue;
		}else{
			String[] word_dots=URL.split("\\.");
			for ( String word_dot : word_dots) {
				Matcher m = p.matcher(word_dot);
				Matcher m2 = p2.matcher(word_dot);
				Matcher m3 = p3.matcher(word_dot);
				
				boolean x = m.find();
				boolean x2 = m2.find();
				boolean x3 = m3.find();
				if(word_dot.length()>=2 && (!x==true /*|| x2==true*/ || x3==true) ){
					continue;
				}else {
					return 0;
				}
			  }
		}
		
		URL = URL.substring( URL.indexOf("/") , URL.length());

	}
	return is_Embedded_domain;
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
