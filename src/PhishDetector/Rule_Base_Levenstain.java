package PhishDetector;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import PhishWho.DomainName;
import mydomain.TopLevelDomainChecker;
import mydomain.TopLevelDomainParser;

import java.io.*;

public class Rule_Base_Levenstain {
	public static ArrayList<String> src = new ArrayList<String>();
	public static ArrayList<String> href = new ArrayList<String>();
	public static ArrayList<String> Script = new ArrayList<String>();
	public static ArrayList<String> Css = new ArrayList<String>();
	
public static void main(String[] args) throws IOException, URISyntaxException {
		String URL = "https://www.google.com/search?q=Page+resource+access+protocol+by+selenium+java&oq=Page+resource+access+protocol+by+selenium+java&aqs=chrome..69i57j69i64.8934j0j4&sourceid=chrome&ie=UTF-8#q=javascript+file+in+website&tbm=vid&*";
		URL aURL = new URL(URL);
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x2\\libs\\selenium\\geckodriver.exe");
	    WebDriver driver = new FirefoxDriver();
 	    driver.get(URL);
 	    Set_Resource(driver);
 	    double[] F14_F15_F16_F17 = new double[4];
 	    String domain_name=domain(driver);
 	    
 	    
		int F1_IPAddress = IP_address(URL);
		int F2_SSLcertificate = SSL_certificate(aURL.getProtocol()); //SSL_certificate2(URL) 		
		int F3_N_dot = countOccurrences(".", URL);  //URL.split("\\.",-1).length-1;
		int F4_host = aURL.getHost().length();
		int F5_path = aURL.getPath().length();
		int F6_file = aURL.getFile().length();
		
		double F10_LD = Levenshtein_distance_Normalize(domain_name, href);
		double F11_LD = Levenshtein_distance_Normalize(domain_name, Script);
		double F12_LD = Levenshtein_distance_Normalize(domain_name, Css);
		double F13_LD = Levenshtein_distance_Normalize(domain_name, src);

		F14_F15_F16_F17 = Page_resource_access_protocol_feature_set();
	}
	
public static int IP_address(String URL) {
		    int is_IP_Address = 0;
		    URL = URL.substring(URL.indexOf("//")+2 , URL.length());
		    URL = URL.substring(0 , URL.indexOf("/"));
		    URL = URL.replace(".", "");
		    if (URL.matches("^-?\\d+$")) {
		    	is_IP_Address = 1;
		    }

		    return is_IP_Address;
}	


public static int SSL_certificate(String URL) {
	    int SSL_certificate = 0;
	    if(URL.toLowerCase().equals("https")){
	    	SSL_certificate = 1;
	    }
	    return SSL_certificate;
}

public static int SSL_certificate2(String URL) {
	    int SSL_certificate = 0;
	    URL = URL.substring( 0 , URL.indexOf("//"));
	    if(URL.toLowerCase().contains("https")){
	    	SSL_certificate = 1;
	    }
	    return SSL_certificate;
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

public static void Set_Resource( WebDriver driver) {
	  //---------------------------------------------------------------
	  List<WebElement> href_links = driver.findElements(By.xpath("//a"));
	  List<WebElement> src_links = driver.findElements(By.xpath("//img"));
	  List<WebElement> Script_links = driver.findElements(By.xpath("//script"));
	  List<WebElement> Css_links = driver.findElements(By.xpath("//link"));
	  //--------  href , src , Script(JavaScript files) , Link(style sheet files)   -------------------------

	    for (WebElement href_link : href_links)
	    {
	        href.add(href_link.getAttribute("href"));
	    }
	    
	    for (WebElement src_link : src_links)
	    {
	        src.add(src_link.getAttribute("src"));
	    }
	    
	    for (WebElement Script_link : Script_links)
	    {
	    	Script.add(Script_link.getAttribute("src"));
	    }
	    
	    for (WebElement Css_link : Css_links)
	    {
	    	Css.add(Css_link.getAttribute("href"));
	    }

}

public static double[] Page_resource_access_protocol_feature_set() {
	  double[] URLs = new double[4];

	    int a=0;
	    for(String zz : href ){
	    	try {
				if(zz.substring(0 , zz.indexOf("//")).contains("https")){
					a++;
				}
			} catch (Exception e) {
				continue;
			}
	    }
	    if(a==0){
	    	URLs[0]=-1;
	    }else{
	    	URLs[0]=(double)a/href.size();
	    }
	    //------------------------------------------------------------
	    int b=0;
	    for(String zz : src ){
	    	try {
				if(zz.substring(0 , zz.indexOf("//")).contains("https")){
					b++;
				}
			} catch (Exception e) {
			     continue;
			}
	    }
	    if(b==0){
	    	URLs[1]=-1;
	    }else{
	    	URLs[1]=(double)b/src.size();
	    }
	    //-------------------------------------------------------------
	    int c=0;
	    for(String zz : Script ){
	    	try {
				if(zz.substring(0 , zz.indexOf("//")).contains("https")){
					c++;
				}
			} catch (Exception e) {
				continue;
			}
	    }
	    if(c==0){
	    	URLs[2]=-1;
	    }else{
	    	URLs[2]=(double)c/Script.size();
	    }
	    //-------------------------------------------------------------
	    int d=0;
	    for(String zz : Css ){
	    	try {
				if(zz.substring(0 , zz.indexOf("//")).contains("https")){
					d++;
				}
			} catch (Exception e) {
				continue;
			}
	    }
	    if(d==0){
	    	URLs[3]=-1;
	    }else{
	    	URLs[3]=(double)d/Css.size();
	    }

	   // ------------------------------------------------------------------		  
	  return URLs;
}
public static String domain( WebDriver driver) throws IOException, URISyntaxException{
	    String TargetURL = driver.getCurrentUrl();
	    FileReader fr = new FileReader("public_suffix_list.dat.txt");
	    TopLevelDomainChecker checker = new TopLevelDomainChecker();
	    TopLevelDomainParser parser = new TopLevelDomainParser(checker);
	    parser.parse(fr);
	  //------------------ SLD Target-----------------
	    DomainName dm5=new DomainName();
		String MainDomain2=dm5.MyName(TargetURL);
		String sld2;
		sld2 = ".".concat(checker.extractSLD(MainDomain2));
	    String URLSLD="";
		URLSLD="";
		URLSLD=MainDomain2.substring(0,MainDomain2.indexOf(sld2));
	    if(URLSLD.contains("."))
	    	URLSLD=MainDomain2.substring(URLSLD.lastIndexOf(".")+1,URLSLD.length());
	    
	    return URLSLD;
}

public static ArrayList<String> SLDs( ArrayList<String> Inpute) throws IOException, URISyntaxException{
    String TargetURL = "";
    ArrayList<String> SLD_List = new ArrayList<String>();
    for (int i = 0; i < Inpute.size(); i++) {
		 TargetURL = Inpute.get(i);
	
	    try {
			FileReader fr = new FileReader("public_suffix_list.dat.txt");
			TopLevelDomainChecker checker = new TopLevelDomainChecker();
			TopLevelDomainParser parser = new TopLevelDomainParser(checker);
			parser.parse(fr);
	  //------------------ SLD Target-----------------
			DomainName dm5=new DomainName();
			String MainDomain2=dm5.MyName(TargetURL);
			String sld2;
			sld2 = ".".concat(checker.extractSLD(MainDomain2));
			String URLSLD="";
			URLSLD="";
			URLSLD=MainDomain2.substring(0,MainDomain2.indexOf(sld2));
			if(URLSLD.contains("."))
				URLSLD=MainDomain2.substring(URLSLD.lastIndexOf(".")+1,URLSLD.length());
			SLD_List.add(URLSLD);
		} catch (Exception e) {
			continue;
		}
    }
    
    return SLD_List;
}

public static double Levenshtein_distance_Normalize( String domain , ArrayList<String> resource) throws IOException, URISyntaxException {
		double result = 0;
		ArrayList<String> SLD_List = new ArrayList<String>();
		SLD_List.addAll(SLDs(resource));
		double[] LD = new double[SLD_List.size()];
		for(int i=0 ; i<SLD_List.size() ; i++){
			int max = 0;
			if(SLD_List.get(i).length() > domain.length()){
				max = SLD_List.get(i).length();
			}else{
				max = domain.length();
			}
			LD[i] = (double)LD_distance(SLD_List.get(i), domain)/max;
		}
		
		for(int i=0 ; i<LD.length ; i++){
			result = result + LD[i];
		}
		result = (double)result / LD.length;
		return result;
}

public static int LD_distance(String a, String b) {
	    a = a.toLowerCase();
	    b = b.toLowerCase();
	    // i == 0
	    int [] costs = new int [b.length() + 1];
	    for (int j = 0; j < costs.length; j++)
	        costs[j] = j;
	    for (int i = 1; i <= a.length(); i++) {
	        // j == 0; nw = lev(i - 1, j)
	        costs[0] = i;
	        int nw = i - 1;
	        for (int j = 1; j <= b.length(); j++) {
	            int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
	            nw = costs[j];
	            costs[j] = cj;
	        }
	    }
	    return costs[b.length()];
}


}
