package OurWork2_DataSet;

import java.net.*;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.UrlValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import LPD.GoogleSearch_Selenium;


import FinalTest_OurWork_PhishWho.SearchFiles;
import PhishWho.DomainName;
import mydomain.TopLevelDomainChecker;
import mydomain.TopLevelDomainParser;

import java.io.*;

public class Creat_DataSet {
	public static ArrayList<String> src = new ArrayList<String>();
	public static ArrayList<String> href = new ArrayList<String>();
	public static ArrayList<String> Script = new ArrayList<String>();
	public static ArrayList<String> Css = new ArrayList<String>();
	public static ArrayList<String> SLD_List = new ArrayList<String>();
	public static  ArrayList<String> AllWord_OK = new ArrayList<String>();
	public static ArrayList<String> title = new ArrayList<String>();
	public static ArrayList<String> body = new ArrayList<String>();
	public static ArrayList<String> meta = new ArrayList<String>();
	public static  ArrayList<String> TopWord_TFDF = new ArrayList<String>();
	public static ArrayList<String> AllLinks = new ArrayList<String>();
	public static ArrayList<String> Final_Sord_word2 = new ArrayList<String>();
	public static int flag=1;


public static void main(String URL ,
						String URL2 ,
						WebDriver driver ,
						String Phish_id ,
						int Doc_number ,
						String label ) throws Exception {
		//fr parser for SLD n function SLDs and domain must be in main algorithm and call that in here
	    String domain_name= "";//domain(driver);
		String TargetURL = "";
		  if(URL .equals(URL2)){
			  TargetURL = driver.getCurrentUrl();
			  domain_name= domain(driver);
		  }else{
			  TargetURL = URL ;
			  domain_name = URL;
		  }
	  
		//String URL = "http://www.yahoo.com/";
		Delete();
		URL aURL = new URL(URL);
		
		/*System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\geckodriver.exe");
	    WebDriver driver = new FirefoxDriver();*/
		
	    ArrayList<String> AllWord_Frequent_word=new ArrayList<String>();
 	    driver.get(URL2);
 	    Set_Resource(driver , URL);
 	    AllWord_Frequent_word = T_Plain(driver);
		AllWord_OK = CheckWord(AllWord_Frequent_word);
 	    double[] F14_F15_F16_F17 = new double[4];
 	    //String domain_name=domain(driver);
 	    

		

 	    //____________________________________________________________________     feature _____________________________________________________
 	    
 	    int f1_Number_of_iframe = NumberOf_iframe(driver);
 	    int f2_Number_of_frame = NumberOf_frame(driver);
 	    int f3_Number_of_form = NumberOf_form(driver);
 	    int f4_Number_of_input = NumberOf_input(driver);
 	    
 	    int f5_if_have_object = HavingObject(driver); 
 	    int f6_codebse_attr_in_object = CodebseAttrInObject(driver);
 	    
 	    int f7_if_have_applet= HavingApplet(driver);
 	    int f8_codebse_attr_in_applet = CodebseAttrInApplet(driver);
 	    
 	    int f7_if_have_Link= HavingLink(driver);
 	    int f8_href_attr_in_Link = HrefAttrInLink(driver);
 	    
 	    int f9_action_attr_in_form = ActionAttrInform(driver);
 	    
 	    int f10_number_of_script = HavingScript(driver);
 	    
 	    //int f11_number_OnClick = OnClickWin(driver);
 	    
 	    int f12_atsign_in_URL = haveAtSign(URL);
 	    int f13_UnderLine_in_URL = haveUnderLine(URL);
 	    int f14_dash_in_URL = havedash(URL);
 	    int f15_question_in_URL = haveQuestionsign(URL);
 	    int f16_Equal_in_URL = haveEqualsign(URL);
 	    int f17_special_symbol = haveSpecialSymbol(URL);
 	    
 	    int f18_Valid_url = haveCodedURL(URL);
 	    int F19_IPAddress = IP_address(URL);
 	    int F20_MLDLength = MLDLength(domain_name);
 	    int F21_N_dot = countOccurrences(".", URL);  //URL.split("\\.",-1).length-1;
		int F22_path = aURL.getPath().length();
		int F23_host = aURL.getHost().length();
		int F24_file = aURL.getFile().length();
		int F25_SSLcertificate = SSL_certificate(aURL.getProtocol()); //SSL_certificate2(URL) 		
//		int F26_Validate_Portnumber = aURL.getPort();
		/*double[] F26_Identity_U_K_Booth_in_URL = new double[4];
		int number_of_top_word_in_TFIDF = 10;
		int DocNumber = 98;
		F26_Identity_U_K_Booth_in_URL = IdentityFrequent_U_InURL(href , domain_name , DocNumber , number_of_top_word_in_TFIDF ); 
		 */
		int F26_TLD_in_path = aURL.getPath().length();
		
 	    //------------------------------------------------------------------------------------------------------------------------------------
		
		
		double F10_LD = Levenshtein_distance_Normalize(domain_name, href);
		double F11_LD = Levenshtein_distance_Normalize(domain_name, Script);
		double F12_LD = Levenshtein_distance_Normalize(domain_name, Css);
		double F13_LD = Levenshtein_distance_Normalize(domain_name, src);

		F14_F15_F16_F17 = Page_resource_access_protocol_feature_set();
		
		//----------------------------------------------------------------------------------
 	    int F1 = Number_of_AllLinks(AllLinks);
 	    int F2 = Number_of_SLD(AllLinks); 	
 	    int F3_nullLink = F1-F2;
 	    
 	    
 	    int F4 = Number_of_uniq_Links(SLD_List);
 	    double F5 = Normalized_Frequent_Link(F2);

 	    ArrayList<String> Result_word = new ArrayList<String>();
 	    
		if (label.equals("1")) {
			/*TopWord_TFDF = WordRankByTFIDF(AllWord_OK, Doc_number, 100 AllWord_OK.size());
			Result_word = Propose_System(TopWord_TFDF, AllLinks, SLD_List);*/
			Result_word = Propose_System(AllWord_OK, AllLinks, SLD_List);
		}else{
			Result_word = Propose_System(AllWord_OK, AllLinks, SLD_List);
		}
		double[] F26_Identity_U_K_Booth_in_URL = new double[11];
		int sizeofindex=0;
		if(F26_Identity_U_K_Booth_in_URL.length > Result_word.size()){
			sizeofindex = Result_word.size();
		}else{
			sizeofindex = F26_Identity_U_K_Booth_in_URL.length;
		}
 	    for (int i = 0; i < sizeofindex ; i++) {
 	    	F26_Identity_U_K_Booth_in_URL[i] = Double.valueOf(Result_word.get(i));
		}
//---------------------------------------------------------------------------------------------
		
		
		
		
		
	 	Filesave(Doc_number,
	 			 URL,
	 			 F1,
	 			 F2,
	 			 F3_nullLink,
	 			 F4,
	 			 F5,
	 			 F26_Identity_U_K_Booth_in_URL ,
	 			 f1_Number_of_iframe,
	 			 f2_Number_of_frame,
	 			 f3_Number_of_form,
	 			 f4_Number_of_input,
	 			 f5_if_have_object,
	 			 f6_codebse_attr_in_object,
	 			 f7_if_have_applet,
	 			 f8_codebse_attr_in_applet ,
	 			 f9_action_attr_in_form,
	 			 f10_number_of_script,
	 			 f12_atsign_in_URL,
	 			 f13_UnderLine_in_URL,
	 			 f14_dash_in_URL,
	 			 f15_question_in_URL,
	 			 f16_Equal_in_URL,
	 			 f17_special_symbol,
	 			 f18_Valid_url,
	 			 F19_IPAddress,
	 			 F20_MLDLength,
	 			 F21_N_dot,
	 			 F22_path,
	 			 F23_host,
	 			 F24_file,
	 			 F25_SSLcertificate,
	 			 F26_TLD_in_path,
	 			 F10_LD,
	 			 F11_LD,
	 			 F12_LD,
	 			 F13_LD,
	 			 F14_F15_F16_F17,
	 			 label);

	}
	


public static void Filesave(
							 int Doc_number ,
							 String URL ,
							 int f1 ,
							 int f2 ,
							 int f3 ,
							 int f4 ,
							 double f5 ,
							 double[] Weight ,
							 
							 int f1_Number_of_iframe,
							 int f2_Number_of_frame,
							 int f3_Number_of_form,
							 int f4_Number_of_input,
							 int f5_if_have_object,
							 int f6_codebse_attr_in_object,
							 int f7_if_have_applet,
							 int f8_codebse_attr_in_applet ,
							 int f9_action_attr_in_form,
							 int f10_number_of_script,
							 int f12_atsign_in_URL,
							 int f13_UnderLine_in_URL,
							 int f14_dash_in_URL,
							 int f15_question_in_URL,
							 int f16_Equal_in_URL,
							 int f17_special_symbol,
							 int f18_Valid_url,
							 int F19_IPAddress,
							 int F20_MLDLength,
							 int F21_N_dot,
							 int F22_path,
							 int F23_host,
							 int F24_file,
							 int F25_SSLcertificate,
							 int F26_TLD_in_path,
							 double F10_LD,
							 double F11_LD,
							 double F12_LD,
							 double F13_LD,
							 double[]  F14_F15_F16_F17,
							 String label)
	{
	try {
		
		String path = "";
		if(label.equals("1")){
			path = "C:\\Users\\Emad\\workspace\\x3\\DataSet\\legit2.txt";
		}else{
			path = "C:\\Users\\Emad\\workspace\\x3\\DataSet\\phish2.txt";
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
	    String str= "";
	    for (int i = 0; i < Weight.length; i++) {
			str = str.concat(String.valueOf(Weight[i])).concat(",");
		}
	    
	    String str2= "";
	    for (int i = 0; i < F14_F15_F16_F17.length; i++) {
			str2 = str2.concat(String.valueOf(F14_F15_F16_F17[i])).concat(",");
		}
	    System.out.println(Doc_number+","+URL+","+f1+","+f2+","+f3+","+f4+","+f5+","+str +label);
	    out.println(Doc_number+","+
	    			URL+","+
	    		    f1+","+
	    			f2+","+
	    		    f3+","+
	    			f4+","+
	    		    f5+","+
	    			str+
	    			f1_Number_of_iframe+","+
	    			f2_Number_of_frame+","+
	    			f3_Number_of_form+","+
	    			f4_Number_of_input+","+
	    			f5_if_have_object+","+
	    			f6_codebse_attr_in_object+","+
	    			f7_if_have_applet+","+
	    			f8_codebse_attr_in_applet+","+
	    			f9_action_attr_in_form+","+
	    			f10_number_of_script+","+
	    			f12_atsign_in_URL+","+
	    			f13_UnderLine_in_URL+","+
	    			f14_dash_in_URL+","+
	    			f15_question_in_URL+","+
	    			f16_Equal_in_URL+","+
	    			f17_special_symbol+","+
	    			f18_Valid_url+","+
	    			F19_IPAddress+","+
	    			F20_MLDLength+","+
	    			F21_N_dot+","+
	    			F22_path+","+
	    			F23_host+","+
	    			F24_file+","+
	    			F25_SSLcertificate+","+
	    			F26_TLD_in_path+","+
	    			F10_LD+","+
	    			F11_LD+","+
	    			F12_LD+","+
	    			F13_LD+","+
	    			str2+
	    			
	    			label);

	    out.close();
	}
	catch (IOException e) {
	    //exception handling left as an exercise for the reader
	}
	
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
	        System.out.println(href_link.getAttribute("href"));
	    }
	    
	    for (WebElement src_link : src_links)
	    {
	        src.add(src_link.getAttribute("src"));
	        System.out.println(src_link.getAttribute("src"));
	    }
	    
	    for (WebElement Script_link : Script_links)
	    {
	    	Script.add(Script_link.getAttribute("src"));
	    	System.out.println(Script_link.getAttribute("src"));
	    }
	    
	    for (WebElement Css_link : Css_links)
	    {
	    	Css.add(Css_link.getAttribute("href"));
	    	System.out.println(Css_link.getAttribute("href"));
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
	FileReader fr = new FileReader("public_suffix_list.dat.txt");
	TopLevelDomainChecker checker = new TopLevelDomainChecker();
	TopLevelDomainParser parser = new TopLevelDomainParser(checker);
	parser.parse(fr);
    for (int i = 0; i < Inpute.size(); i++) {
		 TargetURL = Inpute.get(i);
	
	    try {

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
	try {
			
		 if (resource== null ||
			 resource.isEmpty() ) {
				return -1;
			}
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return -1;
		}
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

public static int NumberOf_iframe(WebDriver driver) {
		List<WebElement> iframe = driver.findElements(By.xpath("//iframe"));//iframe
		int i=0;
		for (WebElement my : iframe)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	
	    return i;
}

public static int NumberOf_frame(WebDriver driver) {
		List<WebElement> frame = driver.findElements(By.xpath("//frame"));//iframe
		int i=0;
		for (WebElement my : frame)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	
	    return i;
}

public static int NumberOf_form(WebDriver driver) {
		List<WebElement> form = driver.findElements(By.xpath("//form"));//iframe
		int i=0;
		for (WebElement my : form)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	
	    return i;
}

public static int NumberOf_input(WebDriver driver) {
		List<WebElement> input = driver.findElements(By.xpath("//input"));//input[@id or  @name]
		int i=0;
		for (WebElement my : input)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}

public static int HavingObject(WebDriver driver) {
		List<WebElement> myobject = driver.findElements(By.xpath("//object"));//input[@id or  @name]
		int i=0;
		for (WebElement my : myobject)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}

public static int CodebseAttrInObject(WebDriver driver) {
		List<WebElement> codebase = driver.findElements(By.xpath("//object/@codebase"));//input[@id or  @name]
		int i=0;
		for (WebElement my : codebase)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}


public static int HavingApplet(WebDriver driver) {
		List<WebElement> myobject = driver.findElements(By.xpath("//applet"));//input[@id or  @name]
		int i=0;
		for (WebElement my : myobject)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}

public static int CodebseAttrInApplet(WebDriver driver) {
		List<WebElement> codebase = driver.findElements(By.xpath("//applet/@codebase"));//input[@id or  @name]
		int i=0;
		for (WebElement my : codebase)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}

public static int HavingLink(WebDriver driver) {
		List<WebElement> mylink = driver.findElements(By.xpath("//link"));//input[@id or  @name]
		int i=0;
		for (WebElement my : mylink)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}

public static int HrefAttrInLink(WebDriver driver) {
		List<WebElement> href = driver.findElements(By.xpath("//link/@href"));//input[@id or  @name]
		int i=0;
		for (WebElement my : href)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}



public static int ActionAttrInform(WebDriver driver) {
		List<WebElement> action = driver.findElements(By.xpath("//form/@action"));//input[@id or  @name]
		int i=0;
		for (WebElement my : action)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}

public static int HavingScript(WebDriver driver) {
		List<WebElement> action = driver.findElements(By.xpath("//script"));
		int i=0;
		for (WebElement my : action)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}

public static int OnClickWin(WebDriver driver) {
		List<WebElement> action = driver.findElements(By.xpath("//*[@onClick='window.open()']")); //"//div/@onClick='window.open()'"
		int i=0;
		for (WebElement my : action)
	    {
			i++;
	        System.out.println(i+"  -  ");
	    }
	    return i;
}//haveAtSign

public static int haveAtSign(String URL) {
		if(URL.contains("@")){
			return 1;
		}else{
			return 0;
		}
}

public static int haveUnderLine(String URL) {
		if(URL.contains("_")){
			return 1;
		}else{
			return 0;
		}
}

public static int havedash(String URL) {
		if(URL.contains("-")){
			return 1;
		}else{
			return 0;
		}
}

public static int haveQuestionsign(String URL) {
		if(URL.contains("?")){
			return 1;
		}else{
			return 0;
		}
}

public static int haveEqualsign(String URL) {
		if(URL.contains("=")){
			return 1;
		}else{
			return 0;
		}
}//haveSpecialSymbol

public static int haveSpecialSymbol(String URL) {
		if(   URL.contains("=") 
		   || URL.contains("?")
		   || URL.contains("-")
		   || URL.contains("_")
		   || URL.contains("@")
				){
			return 1;
		}else{
			return 0;
		}
}
public static int haveCodedURL(String URL) {
		UrlValidator urlValidator = new UrlValidator();	
		if(  urlValidator.isValid(URL) ){
				return 0;
		}else{
				return 1;
		}
}

public static int MLDLength(String Domain) {
		return Domain.length();
}

public static ArrayList<String> CheckWord( List<String> AllWord ) {
		  ArrayList<String> Word_filter = new ArrayList<String>();
		  //----------------------------convert array list into string ------------
		  String listString = "";
		  for (String s : AllWord)
		  {
			      listString += s + " ";
		  }
		  //-------------------------------- (remove Double word)  -----------------
		  /*ChechDouble ch=new ChechDouble();
		  listString = ch.check(listString);*/
		  
					
		  //-------------------------------convert String into array list--------------
		  Word_filter.clear();
		  Word_filter=getWords(deDup(listString));
		  
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

public static double[] IdentityFrequent_U_InURL(ArrayList<String> resource , String domain , int DocNumber, int SizeOfTopWord ) throws Exception {
	
			    double[] Identity_U_K_Both_in_URL_And_TFIDF_in_google = new double[4];
				SLD_List.addAll(SLDs(resource));
				TopWord_TFDF = WordRankByTFIDF(AllWord_OK , DocNumber , SizeOfTopWord /*AllWord_OK.size()*/);
				
				
				ArrayList<String> AllSLD_OK=new ArrayList<String>();
				AllSLD_OK = CheckWord(SLD_List);
				double[] freq=new double[AllSLD_OK.size()];
				freq = Frequence_SLD_in_URL(AllSLD_OK , SLD_List);
				double Weight_ALL_SLD = 0 ;
				for (int i = 0; i < freq.length; i++) {
					Weight_ALL_SLD = Weight_ALL_SLD + freq[i];
				}
				
				System.out.println(Weight_ALL_SLD);
				ArrayList<String> Final_Sord_word = new ArrayList<String>();
				double[] Final_Sord_word_weight = new double[freq.length];
				double[][] data2 = new double[2][freq.length];
				data2 = Sort(freq, AllSLD_OK);
				int number = (int)data2[1][0];
				
				if( domain.equals(AllSLD_OK.get(number)) ){
					Identity_U_K_Both_in_URL_And_TFIDF_in_google[0]=0;
				}else {
					Identity_U_K_Both_in_URL_And_TFIDF_in_google[0]=1;
				}
			//---------------------------------------------------------------------------
				for(int i=0 ; i<TopWord_TFDF.size() ; i++){
					if( domain.equals(TopWord_TFDF.get(i)) ){
						Identity_U_K_Both_in_URL_And_TFIDF_in_google[1]=0;
						 break;
					}else {
						Identity_U_K_Both_in_URL_And_TFIDF_in_google[1]=1;
					}
				}
			//----------------------------------------------------------------------------
				if( domain.equals(AllSLD_OK.get(number)) ){
					for(int i=0 ; i<TopWord_TFDF.size() ; i++){
						if( domain.equals(TopWord_TFDF.get(i)) ){
							Identity_U_K_Both_in_URL_And_TFIDF_in_google[2]=0;
							 break;
						}else {
							Identity_U_K_Both_in_URL_And_TFIDF_in_google[2]=1;
						}
					}
				}
				
		  //-------------------------------------------------------------------------------------------
		
			ArrayList<String> GoogleResponse = new ArrayList<String>();
			
			int flag=0;
			GoogleResponse = SLDs(GoogleSearch(TopWord_TFDF, domain));
			for (int i = 0; i < GoogleResponse.size() && flag==0 ; i++) {
				
					if(GoogleResponse.get(i).equals(domain))
					{
						Identity_U_K_Both_in_URL_And_TFIDF_in_google[3]= i+1;
						flag=1;
						break;
					}else{
						Identity_U_K_Both_in_URL_And_TFIDF_in_google[3]= -1;
					}
				
			}
				
				
				
			return Identity_U_K_Both_in_URL_And_TFIDF_in_google;	
}


public static double[][] Sort( double[] WordWeightInAllUrl , ArrayList<String> AllWord ){
	  	//		  ---------------------------------sort [iteration][index]----------------------------
		double[] data1=WordWeightInAllUrl;
		int size = data1.length;
		double[][] data2 = new double[2][size];
		for (int i19 = 0; i19 < data1.length; i19++) {
			data2[0][i19]= data1[i19];
			data2[1][i19]= i19;
		}
		//			=========================================================================
			double temp;
			for(int i=1;i<data2[0].length;i++)
				for(int j=0;j<data2[0].length-i;j++){
					if(data2[0][j]<data2[0][j+1]){
						temp=data2[0][j+1];
						data2[0][j+1]=data2[0][j];
						data2[0][j]=temp;
						temp=data2[1][j+1];
						data2[1][j+1]=data2[1][j];
						data2[1][j]=temp;
					}
				}
			System.out.println(data2);
			return data2;
}

public static double[] Frequence_SLD_in_URL(ArrayList<String> AllSLD_OK , ArrayList<String> AllSLD_Frequent_SLD ) {
		double[] freq2=new double[AllSLD_OK.size()];
		int Freq3Count=0;
		
		for (int i = 0; i < AllSLD_OK.size(); i++) {
			for (int j = 0; j < AllSLD_Frequent_SLD.size(); j++) {
				if(AllSLD_OK.get(i).equals(AllSLD_Frequent_SLD.get(j))){
					Freq3Count++;
				}
			}
			freq2[i]=Freq3Count;
			Freq3Count=0;
		}
		return freq2;
}

public static ArrayList<String> WordRankByTFIDF(ArrayList<String> AllWord ,int DocNumber , int SizeOfTopWord) throws Exception{
	
						SearchFiles search = new SearchFiles();
					    ArrayList<String> top_best_word=new ArrayList<String>();
					    ArrayList<String> TopWord_TFDF_Plus_Scor = new ArrayList<String>();
					    
					    System.out.println();
					    double[][] Scor_Each_Word = new double [2][AllWord.size()];// 0D for index ith word and 1D for index Score
					    for (int i = 0; i < AllWord.size(); i++) {
					    	
					    	Scor_Each_Word[0][i]= i;
					        System.out.println("Number Word : "+i);
					        if (AllWord.get(i).matches("^-?\\d+$")) {
					        	Scor_Each_Word[1][i] = 0;
					        	continue;
						    }
					        double[][] NumbrDoc_Scor2 = search.main(AllWord.get(i));//oD for DocNumber and 1D for Score
					        if (NumbrDoc_Scor2[0]==null || NumbrDoc_Scor2[0].length==0 ) { // if word not score same as "the,of,a,..."
					        	Scor_Each_Word[1][i] = 0 ; 
					        	continue;
							}else{
								int resultSize = NumbrDoc_Scor2[0].length;
								if (NumbrDoc_Scor2[0].length>=100) {
									resultSize = 100;
								}
						        for (int j = 0 ; j < resultSize; j++) {
						        	if (NumbrDoc_Scor2[0][j] == DocNumber) {
						        		Scor_Each_Word[1][i] = NumbrDoc_Scor2[1][j];
							        	System.out.println("Number Score is : "+NumbrDoc_Scor2[0][j]+"Scor is : "+ NumbrDoc_Scor2[1][j]);
						        		break;
									}
							        Scor_Each_Word[1][i] = 0 ;
						        	System.out.println(j+" : Number Score is : "+NumbrDoc_Scor2[0][j]+"Scor is : "+ NumbrDoc_Scor2[1][j]);
								}
							}
					    	System.out.println("String Word is : "+AllWord.get(i));
					
						}
					    Scor_Each_Word = Sort_TFIDF_Score(Scor_Each_Word);
					    
					    if( SizeOfTopWord > Scor_Each_Word[0].length ){
					    	SizeOfTopWord = Scor_Each_Word[0].length;
					    }
					    for (int i = 0 ; i<SizeOfTopWord ; i++) {
					    	if (Scor_Each_Word[1][i]!=0) {
								top_best_word.addAll(One_Word_that_host_plus_SLD(AllWord.get((int) Scor_Each_Word[0][i])));
								System.out.println(i +" : "+ AllWord.get((int) Scor_Each_Word[0][i]) + " => " + Scor_Each_Word[1][i]);
								TopWord_TFDF_Plus_Scor.add(AllWord.get((int) Scor_Each_Word[0][i]));
								TopWord_TFDF_Plus_Scor.add(String.valueOf(Scor_Each_Word[1][i]));
							}
						}
					
					    System.out.println();
					return top_best_word;
}

public static double[][] Sort_TFIDF_Score( double[][] data2 ){

			//			=========================================================================
			double temp;
			for(int i=1;i<data2[0].length;i++)
				for(int j=0;j<data2[0].length-i;j++){
					if(data2[1][j]<data2[1][j+1]){
						temp=data2[1][j+1];
						data2[1][j+1]=data2[1][j];
						data2[1][j]=temp;
						temp=data2[0][j+1];
						data2[0][j+1]=data2[0][j];
						data2[0][j]=temp;
					}
				}
			System.out.println(data2);
			return data2;
}
public static ArrayList<String> One_Word_that_host_plus_SLD(String Words) {
			ArrayList<String> result = new ArrayList<String>();
				if(Words.contains(".")){
					String[] s2 = Words.split(Pattern.quote("."));//String[] s2 = Words.get(i).split("\\.");
					for (int i2 = 0; i2 < s2.length; i2++) {
						result.add(s2[i2]);
					}
				}else{
					result.add(Words);
				}
			 
			 return result;
}
public static ArrayList<String> T_Plain(WebDriver driver) {
	  ArrayList<String> Words = new ArrayList<String>();
	  ArrayList<String> my_string_word = new ArrayList<String>();
	    List<WebElement> meta_link = driver.findElements(By.xpath("//meta"));
	    
	   //----------------------  Text(Title)      -------------------------
	    String Title_String=driver.getTitle();
	    my_string_word = getWords(Title_String);
		Words.addAll(my_string_word);
		title.addAll(my_string_word);
		
		//----------------------  Text(body)      -------------------------
		    
		String body_String= driver.findElement(By.tagName("body")).getText() ;
		my_string_word.clear();
		my_string_word = getWords(body_String);
		Words.addAll(my_string_word);
		body.addAll(my_string_word);
		
		//----------------------     Text  -------------------------
		my_string_word.clear();
	    for (WebElement webElement : meta_link)
	    {
	    	String meta_text = webElement.getAttribute("content");
	    	my_string_word = getWords(meta_text);
			meta.addAll(my_string_word);
			Words.addAll(my_string_word);
			my_string_word.clear();
	    }
	    for (int i = Words.size()-1; i >= 0 ; i--) {
			if(Words.get(i)==null || Words.get(i).isEmpty()){
				Words.remove(i);
			}
		}
	   //-------------------------------------------------------------------
	  return  Word_that_host_plus_SLD( Words);
}
public static ArrayList<String> Word_that_host_plus_SLD(ArrayList<String> Words) {
			for (int i = 0; i < Words.size(); i++) {
				if(Words.get(i).contains(".")){
					String[] s2 = Words.get(i).split(Pattern.quote("."));//String[] s2 = Words.get(i).split("\\.");
					for (int i2 = 0; i2 < s2.length; i2++) {
						Words.add(s2[i2]);
					}
				}
			 }
			 return Words;
}

public static ArrayList<String> GoogleSearch( ArrayList<String> identity_key ,String domain ) {
		    //---------------------------------------Use GoogleSearch-----------------------------
			String CondidKey="";
			ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
			for (int i = 0; i < identity_key.size(); i++) {
				if(identity_key.get(i).toLowerCase().equals(domain)){
					CondidKey = identity_key.get(i);
					break;
				}
				CondidKey = CondidKey.concat("+").concat(identity_key.get(i));
			}
			
			
			/*GoogleSearch_Selenium gs2=new GoogleSearch_Selenium();
			int number=20;
			UrlListOfGoogleSearch=gs2.main(CondidKey , number , driver2);
			if(UrlListOfGoogleSearch.size()==0){
				UrlListOfGoogleSearch.add("null");
			}*/
			
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\geckodriver.exe");
		    WebDriver driver = new FirefoxDriver();
			driver.get("https://www.google.com/search?num=30&q="+CondidKey);
			List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
			// this are all the links you like to visit
		    for (WebElement webElement : findElements)
		    {
		        System.out.println(webElement.getAttribute("href"));
		        UrlListOfGoogleSearch.add(webElement.getAttribute("href"));
		    }
			
			driver.quit();
			return UrlListOfGoogleSearch;	
}

public static ArrayList<String> Check_localhost( ArrayList<String> URLs , String My_URL) {
				ArrayList<String> clean_URL = new ArrayList<String>(); 
				if(My_URL.lastIndexOf("/")==My_URL.length()-1){
					My_URL = My_URL.substring(0 , My_URL.length()-1);
				}
				System.out.println(URLs.size());
				for (int i = 0; i < URLs.size(); i++) {
					if(URLs.get(i).toLowerCase().contains("localhost/leg".toLowerCase()) 
							|| URLs.get(i).toLowerCase().contains("localhost/phish".toLowerCase())
							 ) {
						String s = URLs.get(i);
						String s3 = s.substring(s.indexOf("RAW-HTML")+8 , s.length() );
						String s2 = 
								My_URL
								.concat(s3)
								;
						clean_URL.add(s2);
					}else if (URLs.get(i).toLowerCase().contains("localhost/".toLowerCase())) {
						String s = URLs.get(i);
						String s3 = s.substring(s.indexOf("localhost")+10 , s.length() );
						String s2 = 
								My_URL
								.concat(s3)
								;
						clean_URL.add(s2);
					}else{
						  clean_URL.add(URLs.get(i));
					  }
				  }
				    
			return clean_URL;
}

public static void Delete(){
			src.clear(); 
			href.clear();  
			Script.clear(); 
			Css.clear(); 
			AllLinks.clear();  
			SLD_List.clear(); 
			AllWord_OK.clear();  
			title.clear();  
			body.clear();  
			meta.clear();  
			TopWord_TFDF.clear(); 
			Final_Sord_word2.clear();
}


public static ArrayList<String> Propose_System( ArrayList<String> AllWord_OK , ArrayList<String> AllURL  , ArrayList<String> AllSLD_Frequent_SLD ) {
	
	ArrayList<String> Final_Sord_word = new ArrayList<String>();
	
	ArrayList<String> AllSLD_OK = new ArrayList<String>();
	AllSLD_OK = CheckWord(SLD_List);
	
	try {
		double[] freq=new double[AllSLD_OK.size()];
		freq = Frequence_SLD_in_URL(AllSLD_OK , AllSLD_Frequent_SLD);
		double[][] distance_word_in_SLD = new double[AllSLD_OK.size()][AllWord_OK.size()];
		double Weight_ALL_SLD = 0 ;
		for (int i = 0; i < freq.length; i++) {
			Weight_ALL_SLD = Weight_ALL_SLD + freq[i];
		}
		
		
		for (int i = 0; i < AllSLD_OK.size(); i++) {
			for (int j = 0; j < AllWord_OK.size() ; j++) {
				String s1 = AllSLD_OK.get(i);
				String s2 = AllWord_OK.get(j);
				flag=1;
				
				if (s1.length()>=s2.length()) {
					int x3 = minDistance(s1, s2);
					double x1 = (double) flag * ((double) 1 / (x3 + 1));
					double x2 = (double) freq[i] / Weight_ALL_SLD;
					distance_word_in_SLD[i][j] = (double) (x2 * x1);
					//distance_word_in_SLD[i][j] = (double)((double)freq[i]/Weight_ALL_SLD) *((double)1 / (minDistance(AllSLD_OK.get(i) , AllWord_OK.get(j))+1));
				}else{
					distance_word_in_SLD[i][j] = 0 ; 
				}
			}
		}
		String[][] Sort_Word = new String[freq.length][10 /*AllWord_OK.size()*/];
		double[][] Sort_Word_weight = new double[freq.length][10 /*AllWord_OK.size()*/];
		double[][] data2 = new double[2][AllWord_OK.size()];
		ArrayList<String> allword_array = new ArrayList<String>();
		double[] weight_allword_array = new double[freq.length * 10];
		int counter=0;
		for (int i = 0; i < freq.length; i++) {
			    data2 =Sort(distance_word_in_SLD[i], AllWord_OK);
				for (int j = 0; j < 10; j++) {
					int z = (int) data2[1][j];
					double z2 = data2[0][j];
					Sort_Word[i][j]=AllWord_OK.get(z);
					allword_array.add(AllWord_OK.get(z));
					
					Sort_Word_weight[i][j]=z2;
					weight_allword_array[counter] = z2;
					counter++;
				}
		}
		Final_Sord_word = FinallSort(weight_allword_array, allword_array , freq.length * 10);
		return Final_Sord_word;
	} catch (Exception e) {
		return Final_Sord_word;
	}
	
}



public static ArrayList<String> FinallSort(double[] WordWeightInAllUrl , ArrayList<String> AllWord , int n ){
	double[][] data2 = new double[2][n];
	data2 = Sort(WordWeightInAllUrl, AllWord);
	
	ArrayList<String> Final_Sord_word = new ArrayList<String>();
	
	double[] Final_Sord_word_weight = new double[n];
	
	for (int j = 0; j < n; j++) {
		int z = (int) data2[1][j];
		double z2 = data2[0][j];
		Final_Sord_word.add(String.valueOf(z2));
		Final_Sord_word2.add(AllWord.get(z));
		Final_Sord_word_weight[j] = z2;
		

	}
return Final_Sord_word;	
}

public static int minDistance(String word2, String word1) {
		int len1 = word1.length();
		int len2 = word2.length();
		
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 3;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}

		for (int i = 0 ; i<= len2 ; i++){
			System.out.print(i+"\t");
		}
		System.out.println();

		System.out.println("---------------------------------------------------------------------------------");

		for (int i = len1 ; i>=0 ; i--){
			for (int j = 0; j <= len2; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}
		if(word2.contains(word1)){
			/*int index = word2.indexOf(word1);
			int end = word1.length();
			int start = index-1;
			//int myend = end - index +1;
			for (int j2 = end+1; j2>=0 ; j2--) {
				int j = 0;
				int[] matris = new int[j2];
				boolean allEqual;
			    start++;
			    int mystart = start;
			    
				for (int i = 0; i < matris.length; i++) {
					matris[i] = dp[j][mystart];
					if (i == matris.length - 1) {
						 allEqual =false;
						 allEqual = Sets.newHashSet(Ints.asList(matris)).size() == 1;
						 if (allEqual==true) {
							flag=10;
						 }
						 System.out.println(allEqual);
					}
					j++;
					mystart++;
				} 
			}*/
			
			flag=10;
		}
		return dp[len1][len2];
}

public static int Number_of_AllLinks(ArrayList<String> Alllinks) {
			int number = AllLinks.size();
			return number;
}

public static int Number_of_SLD(ArrayList<String> Alllinks) throws IOException, URISyntaxException {
			int number = SLDs(Alllinks).size();
			return number;
}


public static int Number_of_uniq_Links(ArrayList<String> Alllinks) {
			int number = CheckWord(Alllinks).size();
			return number;
}
public static double Feature26_Sensetive_Words(String domain){
	int result=0;
	if(domain.contains("secure") ||
			domain.contains("account") ||
			domain.contains("webscr") ||
			domain.contains("login") ||
			domain.contains("ebayisapi") ||
			domain.contains("signin") ||
			domain.contains("banking")||
			domain.contains("confirm")){
	  result = 1;	
	}
		return result;

}
public static int Feature27_Out_of_position_top_level_domain(String TargetURL) throws IOException, URISyntaxException {

	int result = 0;
	FileReader fr = new FileReader("public_suffix_list.dat.txt");
	TopLevelDomainChecker checker = new TopLevelDomainChecker();
	TopLevelDomainParser parser = new TopLevelDomainParser(checker);
	parser.parse(fr);
	// ------------------ SLD Target-----------------
	DomainName dm5 = new DomainName();
	String MainDomain2 = dm5.MyName(TargetURL);
	String sld2;
	sld2 = ".".concat(checker.extractSLD(MainDomain2));
	int countTLd=0;
	countTLd = countOccurrences(sld2,TargetURL);
	if(countTLd>1){
		result=1;
	}
	return result;
}

public static double Normalized_Frequent_Link(int NumberOfAllLinks ) {
	try {
		
		ArrayList<String> uniqLink = new ArrayList<String>();
		uniqLink = CheckWord(SLD_List);
		double[] freq=new double[uniqLink.size()];
		freq = Frequence_SLD_in_URL(uniqLink , SLD_List);
		
		int max=0;
		for (int i = 0; i < freq.length; i++) {
			if(freq[i]>max){
				max = (int) freq[i];
			}
		}
		
		
		double number = (double)max / NumberOfAllLinks;
		return number;
	} catch (Exception e) {
		return -1;
	}
}


public static void Set_Resource( WebDriver driver , String URL) {
	  //---------------------------------------------------------------
	  List<WebElement> href_links = driver.findElements(By.xpath("//a"));
	  List<WebElement> src_links = driver.findElements(By.xpath("//img"));
	  List<WebElement> Script_links = driver.findElements(By.xpath("//script"));
	  List<WebElement> Css_links = driver.findElements(By.xpath("//link"));
	  ArrayList<String> URLs = new ArrayList<String>();
	  //--------  href , src , Script(JavaScript files) , Link(style sheet files)   -------------------------

	    for (WebElement href_link : href_links)
	    {
	    	if (href_link.getAttribute("href")== null 
		    		||href_link.getAttribute("href").isEmpty() ) {
					continue;
				}
	    	URLs.add(href_link.getAttribute("href"));
	        System.out.println(href_link.getAttribute("href"));
	    }
	    href =Check_localhost(URLs , URL);
	    URLs.clear();
	    //______________________________________________________________
	    
	    for (WebElement src_link : src_links)
	    {
	    	if (src_link.getAttribute("src")== null 
		    		||src_link.getAttribute("src").isEmpty() ) {
					continue;
				}
	    	URLs.add(src_link.getAttribute("src"));
	        System.out.println(src_link.getAttribute("src"));
	    }
	    src = Check_localhost(URLs , URL);
	    URLs.clear();
	    //______________________________________________________________

	    
	    for (WebElement Script_link : Script_links)
	    {
	    	if (Script_link.getAttribute("src")== null 
		    		||Script_link.getAttribute("src").isEmpty() ) {
					continue;
				}
	    	URLs.add(Script_link.getAttribute("src"));
	    	System.out.println(Script_link.getAttribute("src"));
	    }
	    Script = Check_localhost(URLs , URL);
	    URLs.clear();
	    //______________________________________________________________

	    
	    for (WebElement Css_link : Css_links)
	    {
	    	if (Css_link.getAttribute("href")== null 
		    		||Css_link.getAttribute("href").isEmpty() ) {
					continue;
				}
	    	URLs.add(Css_link.getAttribute("href"));
	    	System.out.println(Css_link.getAttribute("href"));
	    }
	    Css = Check_localhost(URLs , URL);
	    URLs.clear();
	    //______________________________________________________________


	    URLs.addAll(href);
	    URLs.addAll(src);
	    URLs.addAll(Script);
	    URLs.addAll(Css);
	    
	    AllLinks = Check_localhost(URLs , URL);
	    
}








}
