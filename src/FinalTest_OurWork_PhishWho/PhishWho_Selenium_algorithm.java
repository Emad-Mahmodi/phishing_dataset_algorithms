package FinalTest_OurWork_PhishWho;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gargoylesoftware.htmlunit.javascript.host.URL;

import LPD.GoogleSearch_Selenium;
import PhishWho.ChechDouble;
import PhishWho.DomainName;
import PhishWho.FindTopValuesSelectionSortImpl;
import PhishWho.GoogleSearch;
import PhishWho.RemoveStopWord;
import mydomain.TopLevelDomainChecker;
import mydomain.TopLevelDomainParser;

public class PhishWho_Selenium_algorithm {
	public static List<String> myx = new ArrayList<String>();//insert each word and corresponding weighted
	public static ArrayList<String> resultSorted_X=new ArrayList<String>();
	public static ArrayList<String> resultSorted_Y=new ArrayList<String>();

	public static ArrayList<String> identity_keyword = new ArrayList<String>();
	public static int number_google_occure = 11;

	public static ArrayList<String> title = new ArrayList<String>();
	public static ArrayList<String> body = new ArrayList<String>();
	public static ArrayList<String> meta = new ArrayList<String>();
	public static ArrayList<String> SLD = new ArrayList<String>();
	public static String zz2 = "";
	public static ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
	public static  ArrayList<String> AllWord_OK=new ArrayList<String>();

	public static int main(String TestURL ,
			   String Local_URL,
			   WebDriver driver ,
			   WebDriver driver2 , 
			   String Phish_id , 
			   int number , 
			   String orginal_lable 
			   ) throws Exception {
		//		public static void main(String arg[]) throws Exception {
//		String TargetURL=TestURL;
//		String TargetURL="http://upornia.com/";
//		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x1\\libs\\selenium\\geckodriver.exe");
//	    WebDriver driver = new FirefoxDriver();
// 	    driver.get(TargetURL);
	      //driver.get(Local_URL);
	      //----------------------------------------------------------------------
	      Delete_array();
//	      Set_array();
		  String TargetURL = "";
		  if(TestURL .equals(Local_URL)){
			  TargetURL = driver.getCurrentUrl();
		  }else{
			  TargetURL = TestURL ;
		  }
		  
		  String domain_name=domain(driver , TargetURL );//"facebook";
		  zz2=domain_name;
		 //-------------------------------- (remove Double word)  -----------------
		  ArrayList<String> AllWord=new ArrayList<String>();
		  ArrayList<String> AllWord_one_gram=new ArrayList<String>();
		  ArrayList<String> AllURL=new ArrayList<String>();
		  AllWord = T_Plain(driver);
		  AllWord_one_gram=CheckWord(AllWord);
		  AllWord_OK.addAll(AllWord_one_gram);
		  //------------------------------------
		  ArrayList<String> AllWord_n_gram_with_double_word=new ArrayList<String>();
		  ArrayList<String> AllWord_n_gram=new ArrayList<String>();
		  
		  for (int i = 2; i <= 5; i++) {
			  AllWord_n_gram_with_double_word.addAll(ngrams(AllWord_one_gram, i));
		  }
		  AllWord_n_gram = CheckWord(AllWord_n_gram_with_double_word);
		  AllWord_OK.addAll(AllWord_n_gram);
		  
		  AllURL=CheckURL(T_URL(driver , TestURL));

		  List<List<Integer>> ArrayLevel = new ArrayList<List<Integer>>();
		  
		  long startTime = System.currentTimeMillis();
		  //))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))    Time     ))))))))))))))))))))))))

		  ArrayLevel=Check_Level(AllURL, ArrayLevel);   // compute Level
		  //----------------------------------check ArrayLevel at least have a size (or level)		  
		  for(int i2=ArrayLevel.size()-1;i2>0;i2--){
			 if( ArrayLevel.get(i2).size()==0 ){
				 AllURL.remove(i2);
				 ArrayLevel.remove(i2);
			 }
		  }
		  //-----------------------------------algorithm weight URL Token system (One - Gram) -------------
		  ArrayList<String> top_best_word_X=new ArrayList<String>();
		  ArrayList<String> top_best_word_Y=new ArrayList<String>();
		  int n=AllWord_one_gram.size();
		  double[] WordWeightInAllUrl = new double[AllWord_one_gram.size()];
		  int Numberword=0;
		  
		  WordWeightInAllUrl = Weited_URL_Token_System(AllWord_one_gram, AllURL, ArrayLevel, n, Numberword);
		  Sort(WordWeightInAllUrl, AllWord_one_gram , 1);
		  top_best_word_X = Sequential_Rule_base_selection(domain_name,resultSorted_X , 5);
		  System.out.println(top_best_word_X);
		  //-----------------------------------algorithm weight URL Token system (n - Gram) -------------
		  n=AllWord_n_gram.size();
		  double[] nGram_WordWeightInAllUrl = new double[AllWord_n_gram.size()];
		  Numberword=0;
		  nGram_WordWeightInAllUrl = Weited_URL_Token_System(AllWord_n_gram, AllURL, ArrayLevel, n, Numberword);
		  Sort(nGram_WordWeightInAllUrl, AllWord_n_gram , 2 );
		  top_best_word_Y = Sequential_Rule_base_selection(domain_name,resultSorted_Y , 5);
		  System.out.println(top_best_word_Y);
		  identity_keyword = identity_keyword(top_best_word_X ,top_best_word_Y );
		  //))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))
		  long endTime   = System.currentTimeMillis();
		  long totalTime = endTime - startTime;
		  
		  SLD = Google_SLDs( GoogleSearch(identity_keyword , domain_name , driver) );
		
		  /*double[] F1 = new double[SLDs.size()];
		  F1 = Feature1(SLDs , title, body, meta, domain_name , AllURL);
		  double[] F2 = new double[SLDs.size()];
		  F2 = Feature2(SLDs);
		  double[] F3 = new double[SLDs.size()];
		  F3 = Feature3(SLDs , AllURL );
		  double[] Lp= new double[F1.length];
		  Lp=Compromise_programming(F1, F2, F3); // LP is Less iis the best
		  double[][] best_SLD = new double[2][SLDs.size()];
		  best_SLD = My_Sort(Lp);
		  int detection = detection(domain_name, SLDs.get((int)best_SLD[1][SLDs.size()-1]));
		  System.out.println(detection);*/
		  
		  int detection= My_detection(domain_name , SLD );
		  
		  Filesave(number , 
				   Phish_id ,
				   TargetURL , 
				   orginal_lable ,  
				   String.valueOf(detection) , 
				   myx, 
				   AllWord_OK , 
				   domain_name , 
				   number_google_occure ,
				   totalTime ,
				   AllWord_n_gram , 
				   AllURL);
//		  System.out.println(detection);
		  return detection;

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
public static ArrayList<String> T_URL( WebDriver driver , String TestURL) {
		  ArrayList<String> URLs = new ArrayList<String>();
		  ArrayList<String> src;
		  ArrayList<String> href;
		  src = new ArrayList<String>();
		  href = new ArrayList<String>();
		  //---------------------------------------------------------------
		  List<WebElement> href_links = driver.findElements(By.xpath("//a"));
		  List<WebElement> src_links = driver.findElements(By.xpath("//img"));
		  
		  //----------------------     href , src   -------------------------

		    for (WebElement href_link : href_links)
		    {
		    	if (  href_link.getAttribute("href")== null 
			        ||href_link.getAttribute("href").isEmpty() 
		    		||href_link.getAttribute("href").contains("javascript:void(0)")
		    		||href_link.getAttribute("href").contains("javascript:;")
		    		) {
						continue;
					}else if(   href_link.getAttribute("href").contains("http://")==false
				    		 && href_link.getAttribute("href").contains("https://")==false
				    		 )
					{
						    continue;
					}
		        href.add(href_link.getAttribute("href"));
		        URLs.add(href_link.getAttribute("href"));
		    }
		    
		    for (WebElement src_link : src_links)
		    {
		    	if (  src_link.getAttribute("src")== null 
		    		||src_link.getAttribute("src").isEmpty()
		    		||src_link.getAttribute("src").contains("javascript:void(0)")
		    		||src_link.getAttribute("src").contains("javascript:;")
		    		) {
					continue;
				}else if(   src_link.getAttribute("src").contains("http://")==false
			    		 && src_link.getAttribute("src").contains("https://")==false)
				{
					    continue;
				}
		        src.add(src_link.getAttribute("src"));
		        URLs.add(src_link.getAttribute("src"));
		    }
		   // ------------------------------------------------------------------
  
		    
		  return Check_localhost(URLs , TestURL);
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
		    


		   //-------------------------------------------------------------------
		  return Word_that_host_plus_SLD( Words);
	  }
public static ArrayList<String> Word_that_host_plus_SLD(ArrayList<String> Words) {
      for (int i = 0; i < Words.size(); i++) {
		if(Words.get(i).contains(".")){
			String[] s2 = Words.get(i).split("\\.");
			for (int i2 = 0; i2 < s2.length; i2++) {
				Words.add(s2[i2]);
			}
		}
	  }
	
	  return Words;
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
	  
public static ArrayList<String> CheckURL( ArrayList<String> AllURL ) {
		  for(int i=AllURL.size()-1 ; i>0 ; i--){
			  if( AllURL.get(i)==null ){
				  AllURL.remove(i);
			  }
		  }
		  String URLString = "";
		  ArrayList<String> AllURL2 = new ArrayList<String>();
		  //for(String url : AllURL){
		  for (int j4 = 0; j4 < AllURL.size(); j4++) { 
			  String st1=AllURL.get(j4);
			  int sds=AllURL.get(j4).indexOf("/");

			  if(StringUtils.countMatches(AllURL.get(j4),"/")<=1){
				continue;
			  }
			//-------------------------------------------------------------- when tow up to backslash occur ---------------------------------
			  if(StringUtils.countMatches(AllURL.get(j4),"//")>=2){
				      int flag1=0;
				      int aa=1;
			   		  for (int j2 = 0; aa != -1; ) {
						  if(aa==j2-1){
							    flag1=1;
								break;
							}
						  aa=j2;
						  j2 =AllURL.get(j4).indexOf("//", j2 + 1);
						}
			   		  if(flag1==1)
			   			  continue;
			  		}	  
	
			if(StringUtils.countMatches(AllURL.get(j4),"//")>1){
				  ArrayList<Integer> TowBackSlashInOneURL=new ArrayList<Integer>();//Sample : https://accounts.google.com/ServiceLogin?hl=en&passive=true&continue=https://www.google.com/
				  for (int j2 = -1; (j2 = AllURL.get(j4).indexOf("//", j2 + 1)) != -1; ) {
					    System.out.println("URL has tow backslash is : "+AllURL.get(j4)+"index is"+j2);
					    TowBackSlashInOneURL.add(j2);
					}
				  for (int j3 = 0; j3 < TowBackSlashInOneURL.size(); j3++) {
					if(j3==TowBackSlashInOneURL.size()-1){
					AllURL2.add(AllURL.get(j4).substring(TowBackSlashInOneURL.get(j3),AllURL.get(j4).length()));
					}else{
					AllURL2.add(AllURL.get(j4).substring(TowBackSlashInOneURL.get(j3),TowBackSlashInOneURL.get(j3+1)-1));
					}
				   }
				  continue;
			  }
		  if(!AllURL.get(j4).contains("//") && !AllURL.get(j4).contains("/")){
			  continue;
		   }
		  AllURL2.add(AllURL.get(j4));
		  }
		  //-----------------------------------------remove white space {""} in one cell-------
		  ArrayList<Integer> Del=new ArrayList<Integer>();
		  for (int j10 = 0; j10 < AllURL2.size(); j10++) {
			String test1=AllURL2.get(j10);
			if(AllURL2.get(j10)==""){
				Del.add(j10);
			}
			if (StringUtils.countMatches(AllURL2.get(j10),"///")!=0) {
				Del.add(j10);
			}
		  }
		  int test3=Del.size();
		  for (int j11 = Del.size()-1; j11 >= 0; j11--) {
			    int test2=Del.get(j11);
				AllURL2.remove(test2);
		  }
		  
		  return AllURL2;
	  }
	  
public static List<List<Integer>> Check_Level( ArrayList<String> AllURL ,  List<List<Integer>> ArrayLevel ) {
		  //------------------------------------- Level ------------------------------------------------
		  for (int i5 = 0; i5 < AllURL.size(); i5++) {
			  String url = AllURL.get(i5);  
			  //---------------------------------------------------------------------------------------     Split URL of Level    ----------------------------
			  ArrayList level = new ArrayList();
			  
			  
			 if(url.contains("//") && url.contains("/") && url.lastIndexOf("/")!=url.length()-1 ){ //some times  start of url by {//} but some times url start with {/} 
				  for (int i2 = -1; (i2 = url.indexOf("//", i2 + 1)) != -1; ) {
					    level.add(i2);
					    int j=1;
					    for (int i4 = i2+1; (i4 = url.indexOf("/", i4 + 1)) != -1; ) {
						        level.add(i4);
						    	j++;
						}
					    level.add(url.length());
					}
			 }else if(url.contains("//") && url.contains("/") && url.lastIndexOf("/")==url.length()-1 ){ //some times  start of url by {//} but some times url start with {/} 
					  for (int i2 = -1; (i2 = url.indexOf("//", i2 + 1)) != -1; ) {
						    level.add(i2);
						    int j=1;
						    for (int i4 = i2+1; (i4 = url.indexOf("/", i4 + 1)) != -1; ) {
							        level.add(i4);
							    	j++;
							}
						}
					  
				}else if(!url.contains("//") && url.contains("/") && url.lastIndexOf("/")!=url.length()-1 ) {
					int j=1;
					level.add(0);
				    for (int i4 = 1; (i4 = url.indexOf("/", i4 + 1)) != -1; ) {
					        level.add(i4);
					    	j++;
					}
				    level.add(url.length());
				}else if(!url.contains("//") && url.contains("/") && url.lastIndexOf("/")==url.length()-1 ) {
					int j=1;
					level.add(0);
				    for (int i4 = 1; (i4 = url.indexOf("/", i4 + 1)) != -1; ) {
					        level.add(i4);
					    	j++;
					}
				}
			  else if(!url.contains("//") && !url.contains("/")){
				  level.add(0);
				  level.add(url.length());
				  continue;
			 }
			 //--------------------------------------------------------------------------
			 ArrayLevel.add(level);
		  }

		  return ArrayLevel;
	  }

public static List<List<Integer>> Check_Level2( ArrayList<String> AllURL ,  List<List<Integer>> ArrayLevel ) {
	  //------------------------------------- Level ------------------------------------------------
	  for (int i5 = 0; i5 < AllURL.size(); i5++) {
		  String url = AllURL.get(i5);  
		  //---------------------------------------------------------------------------------------     Split URL of Level    ----------------------------
		  if (url.contains("http://")) {
			url=url.substring(url.indexOf("http://")+7 , url.length());
			
		  }else if ( url.contains("https://")) {
		  }
		  ArrayList level = new ArrayList();
		  
		  

		 //--------------------------------------------------------------------------
		 ArrayLevel.add(level);
	  }

	  return ArrayLevel;
}

public static double[] Weited_URL_Token_System( ArrayList<String> AllWord , ArrayList<String> AllURL , List<List<Integer>> ArrayLevel , int n  , int Numberword) {
		  //++++++++++++++++++++++++++++++++   compression   +++++++++++++++++++++++++++++++++++++  
	      int NumberUrl=AllURL.size();
	      double[] WordWeightInAllUrl = new double[AllWord.size()];
	      
		  for (int i=0;i<AllWord.size();i++){
			  String word = AllWord.get(i);  //token word in the arraylist
			  System.out.println(word);
			 //------------------------------------------------ 
			  int Li = word.length(); // L : size of the word
			  double[] WordWeightInOneUrl = new double[n];
			  
			  //----------------------- compute max of url level in all url---------------
 			  int maxlevel=0;
		      int x3=0;
 			    for (int i16=0; i16<ArrayLevel.size();i16++){
 			    	x3=ArrayLevel.get(i16).size();
 			    	if(maxlevel<x3)
 			    		maxlevel=x3;
 			    }
 			  //------------------------------------------------------------------------------------------------------- 
			  ArrayList level = new ArrayList();
			  
			  double[] x=new double[maxlevel-1];

			  
 			  for (int i17 = 0; i17 < maxlevel-1; i17++) {
				  ArrayList myurlLevel = new ArrayList();
				  ArrayList NumberLevelOccur = new ArrayList();
				  System.out.println("i1 : =>"+i17);
 	 			  for (int i5 = 0; i5 < AllURL.size(); i5++) {
	 					String url = AllURL.get(i5);
	 					int w=ArrayLevel.get(i5).size();
						if(i17>=w-1){
							continue;
						}
					    myurlLevel=(ArrayList) ArrayLevel.get(i5);
						level=myurlLevel;
						int Start=0;
						int End=0;
						if(i17==level.size()){
							Start=(int)level.get(i17);
							End=url.length();
						}else{
							Start=(int)level.get(i17)+1;
							End=(int)level.get(i17+1)-1;	
						}
						  System.out.println("i2 : =>"+i5);

						String UrlLevel_by_dot=url.substring(Start,End).toLowerCase(); //specify each level 
						String []UrlLevel=UrlLevel_by_dot.split("\\.");
						for (int j = 0; j < UrlLevel.length; j++) {
							
							//if (UrlLevel[j].toLowerCase().equals(word.toLowerCase())) { // Check the occurrence word in each level of URL 
							if (UrlLevel[j].toLowerCase().contains(word.toLowerCase())) { 
							    NumberLevelOccur.add(url);
								break;
							} 
						}
				    }
					int NLOCC=0;
					NLOCC=(int)NumberLevelOccur.size();
					int k2=((int)Math.pow(i17+1,2));    
					x[i17]=(double)NLOCC/(double)k2;
 	 			  }	  
 			       //=====================================new ==============
		 		    double SumWeightwodInEachUrlLevel=0;
					for (int i8 = 0; i8 < x.length; i8++) {
						SumWeightwodInEachUrlLevel+=x[i8];
					}
					double xx = (double)((double)Li/NumberUrl)*SumWeightwodInEachUrlLevel;
		 			WordWeightInAllUrl[i]=(double)((double)Li/NumberUrl)*SumWeightwodInEachUrlLevel;
		 			
		 			//--------------------------------------------------------------compute extra-------------------					
		 			myx.add(word);
		 			myx.add(Double.toString(WordWeightInAllUrl[i]));
		 			//--------------------------------------------------------------------------------------------- 	
		  }
		  return WordWeightInAllUrl;
	  }
	  
public static void Sort( double[] WordWeightInAllUrl , ArrayList<String> AllWord , int flag){
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
			//	   	   ========================================================================
				int num=data2[0].length;
			if (flag==1) {
				for (int i = 0; i < num; i++) {
					int z = (int) data2[1][i];
					// System.out.println(AllWord.get(z)+" => "+Double.toString(data2[0][i]));
					resultSorted_X.add(AllWord.get(z));
				} 
			}else if(flag==2){
				for (int i = 0; i < num; i++) {
					int z = (int) data2[1][i];
					// System.out.println(AllWord.get(z)+" => "+Double.toString(data2[0][i]));
					resultSorted_Y.add(AllWord.get(z));
				}
			}
	  }
	  
	  
public static ArrayList<String> Sequential_Rule_base_selection(String domain_name , ArrayList<String> result ,int num_best_onegram_word ){
	    ArrayList<String> best_word=new ArrayList<String>();
		ArrayList<String> top_best_word=new ArrayList<String>();
		for(int i=0 ; i<num_best_onegram_word ; i++){
			best_word.add(result.get(i));
		}
		//---------------------if current word is domain name------------
		for(int i=0 ; i< best_word.size() ; i++){
			if(domain_name.toLowerCase().equals(best_word.get(i).toLowerCase())){
				top_best_word.clear();
				top_best_word.add(best_word.get(i));
				return top_best_word;
			}else if(Character.isUpperCase(best_word.get(i).charAt(0)))
			{
				top_best_word.add(best_word.get(i));
			}
		}
		return top_best_word;
}


public static String domain( WebDriver driver , String TargetURL) throws IOException, URISyntaxException{
	    //String TargetURL = driver.getCurrentUrl();
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

public static ArrayList<String> Google_SLDs( ArrayList<String> Google_result) throws IOException, URISyntaxException{
	    String TargetURL = "";
	    
	    FileReader fr = new FileReader("public_suffix_list.dat.txt");
	    TopLevelDomainChecker checker = new TopLevelDomainChecker();
	    TopLevelDomainParser parser = new TopLevelDomainParser(checker);
	    parser.parse(fr);
	    ArrayList<String> SLD_List = new ArrayList<String>();
	    for (int i = 0; i < Google_result.size(); i++) {
			 TargetURL = Google_result.get(i);
		

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
	    }
	    return SLD_List;
}

public static ArrayList<String> ngrams(ArrayList<String> AllWord, int len) {
			  String s = "";
			  for (String listString : AllWord)
			  {
				      s += listString + " ";
			  }
			ArrayList<String> result =new ArrayList<String>();
	        for (int i = 0; i < ngrams_concat(s, len).length; i++) {
	        	result.add(ngrams_concat(s, len)[i]);
			}
	        for (int i = 0; i < ngrams_acronym(s, len).length; i++) {
	        	result.add(ngrams_acronym(s, len)[i]);
			}
		    return result;
		}
public static ArrayList<String> ngrams_String_inpute(String AllWord, int len) {

	ArrayList<String> result =new ArrayList<String>();
  for (int i = 0; i < ngrams_concat(AllWord, len).length; i++) {
  	result.add(ngrams_concat(AllWord, len)[i]);
	}
  for (int i = 0; i < ngrams_acronym(AllWord, len).length; i++) {
  	result.add(ngrams_acronym(AllWord, len)[i]);
	}
  return result;
}
public static String[] ngrams_concat(String s, int len) {
		    String[] parts = s.split(" ");
		    String[] result = new String[parts.length - len + 1];
		    for(int i = 0; i < parts.length - len + 1; i++) {
		       StringBuilder sb = new StringBuilder();
		       for(int k = 0; k < len; k++) {
		           if(k > 0) sb.append(' ');
		           sb.append(parts[i+k]);
//		           sb.append(parts[i+k].charAt(0));
		       }
		       result[i] = sb.toString();
		    }
		    for(int i=0 ; i<result.length ; i++){
		    	result[i] = result[i].replaceAll("\\s+","");
		    }
		    return result;
		}
public static String[] ngrams_acronym(String s, int len) {
	    String[] parts = s.split(" ");
	    String[] result = new String[parts.length - len + 1];
	    for(int i = 0; i < parts.length - len + 1; i++) {
	       StringBuilder sb = new StringBuilder();
	       for(int k = 0; k < len; k++) {
	           if(k > 0) sb.append(' ');
//	           sb.append(parts[i+k]);
	           sb.append(parts[i+k].charAt(0));
	       }
	       result[i] = sb.toString();
	    }
	    for(int i=0 ; i<result.length ; i++){
	    	result[i] = result[i].replaceAll("\\s+","");
	    }
	    return result;
	}
public static ArrayList<String> GoogleSearch( ArrayList<String> identity_key ,String domain , WebDriver driver2) {
		    //---------------------------------------Use GoogleSearch-----------------------------
			String CondidKey="";
			
			for (int i = 0; i < identity_key.size(); i++) {
				if(identity_key.get(i).toLowerCase().equals(domain)){
					CondidKey = identity_key.get(i);
					break;
				}
				CondidKey = CondidKey.concat("+").concat(identity_key.get(i));
			}
			
			
			GoogleSearch_Selenium gs2=new GoogleSearch_Selenium();
			int number=20;
			UrlListOfGoogleSearch=gs2.main(CondidKey , number , driver2);
			if(UrlListOfGoogleSearch.size()==0){
				UrlListOfGoogleSearch.add("null");
			}
			return UrlListOfGoogleSearch;	
}
public static ArrayList<String> identity_keyword(ArrayList<String> X , ArrayList<String> Y){
	ArrayList<String> identity_keyword = new ArrayList<String>();
	for (int i = 0; i < X.size(); i++) {
		identity_keyword.add(X.get(i));
	}
	for (int i = 0; i < Y.size(); i++) {
		identity_keyword.add(Y.get(i));
	}
	return identity_keyword;
}
public static double[] Feature1(ArrayList<String> identity_keyword
												   , ArrayList<String> wordsTitle
												   , ArrayList<String> WordsBody
												   , ArrayList<String> WordsMeta
												   , String Current_URL
												   , ArrayList<String> URL
												   ) {
	
			//double[] f1 = {0,0,0,0,0};


			/*String title_string="";
			title_string = Array_to_string(wordsTitle);
			for (int i = 2; i <= 5; i++) {
				if(wordsTitle.size()>=i){
					wordsTitle.addAll(ngrams_String_inpute(title_string, i));
				}else{
					break;
				}
			  }
			String Meta_string="";
			Meta_string = Array_to_string(WordsMeta);
			for (int i = 2; i <= 5; i++) {
				if(WordsMeta.size()>=i){
					WordsMeta.addAll(ngrams_String_inpute(Meta_string, i));/////////
				}else{
					break;
				}
			  }
			String Body_string="";
			Body_string = Array_to_string(WordsBody);
			for (int i = 2; i <= 5; i++) {
				if(WordsBody.size()>=i){
//					System.out.println(ngrams_String_inpute(Body_string, i));
					WordsBody.addAll(ngrams_String_inpute(Body_string, i));
				}else{
					break;
				}
				
			  }*/
			
			
			
			

			double[] F_density = new double[identity_keyword.size()];
			
			for (int i20 = 0; i20 < identity_keyword.size() ; i20++) {
				int WordInTitle=0; 
				int WordInMeta=0;
				int WordInSLD=0;
				int WordInBody=0;
				int WordInURL=0;
				//0000000000000000000000000000    f1  in Title  00000000000000000000
				for (int i1 = 0; i1 < wordsTitle.size(); i1++) {
					if(wordsTitle.get(i1).toLowerCase().equals(identity_keyword.get(i20).toLowerCase()) && WordInTitle!=1 ){
						WordInTitle=1;//f1[0]=1;
						break;
					}
				}
				//0000000000000000000000000000    f1  in Meta   00000000000000000000
				for (int i2 = 0; i2 < WordsMeta.size(); i2++) {
					if(WordsMeta.get(i2).toLowerCase().equals(identity_keyword.get(i20).toLowerCase()) && WordInMeta!=1){
					WordInMeta= 1 ; //f1[1]=1;
					break;
					}
				}
				//0000000000000000000000000000    f1  in SLDs   00000000000000000000
					if(Current_URL.toLowerCase().equals(identity_keyword.get(i20).toLowerCase()) && WordInSLD!=1){
					WordInSLD=1;//f1[2]=1;
					}
				
				//0000000000000000000000000000    f1  in Body   00000000000000000000
				for (int i4 = 0; i4 < WordsBody.size(); i4++) {
					if(WordsBody.get(i4).toLowerCase().equals(identity_keyword.get(i20).toLowerCase()) && WordInBody!=1){
						WordInBody=1;//f1[3]=1;
						break;
					}
				}
				//0000000000000000000000000000    f1  in URLs   00000000000000000000
				for (int i5 = 0; i5 < URL.size(); i5++) {
					if(URL.get(i5).toLowerCase().contains(identity_keyword.get(i20).toLowerCase()) && WordInURL!=1){
					WordInURL=1;//f1[4]=1;
					break;
					}
				}
				 F_density[i20] = (double)(  WordInTitle 
							       + WordInMeta
							       + WordInSLD 
							       + WordInBody
							       + WordInURL ) / 5 ;
			}
			
		return F_density;	
}
public static String Array_to_string(ArrayList<String> X){
		String ww="";
		StringBuilder stringBuilder = new StringBuilder();
		for(String s : X){
			stringBuilder.append(s+" ");
		}
		ww = stringBuilder.toString();
		return ww;
}

public static double[] Feature2(ArrayList<String> SLD ) {
			ArrayList<String> SLD2 = new ArrayList<String>();
			double[] freq2=new double[SLD.size()];
			int Freq3Count=0;
			SLD2.addAll(SLD);
			for (int i26 = 0; i26 < SLD.size(); i26++) {
				for (int i27 = 0; i27 < SLD2.size(); i27++) {
					if(SLD.get(i26).equals(SLD2.get(i27))){
						Freq3Count++;
					}
				}
				freq2[i26]=Freq3Count;
				Freq3Count=0;
			}
			return freq2;
}
public static double[] Feature3(ArrayList<String> SLD , ArrayList<String> AllURL ) {
			//----------------------------------------------------Ffreq2-----------------
		   //-------------------------------- (remove Double word)  -----------------
			/*String ConvertListToString = "";
			  for (String s : SLD)
			  {
				  ConvertListToString += s + " ";
			  }
			  ChechDouble ch2=new ChechDouble();
			  String aa = ch2.check(ConvertListToString);
			  //         -------------------------------convert String into array list--------------
			  ArrayList<String> SLD3 = new ArrayList<String>();
			  SLD3=getWords(aa);*/
	        ArrayList<String> SLD3 = new ArrayList<String>();
	        SLD3.addAll(SLD);
			  
	        double[] NumberSLDSearchResualtOccurInHTMLURL=new double[SLD3.size()];
			  for (int i=0;i<SLD3.size();i++){
				  String word = SLD3.get(i);  //token Domain in the all search
					ArrayList NumberSLDSearchResualtOccurInHTMLURLList = new ArrayList();
					for (int i2 = 0; i2<AllURL.size();i2++ ) { // Check the occurrence word in each level of URL 
						String myurl = AllURL.get(i2);
						if(myurl.indexOf(word)!=-1){
							NumberSLDSearchResualtOccurInHTMLURLList.add(i2);
						    }
						}
				NumberSLDSearchResualtOccurInHTMLURL[i]=NumberSLDSearchResualtOccurInHTMLURLList.size();	
			  }
	  return NumberSLDSearchResualtOccurInHTMLURL;
}


public static double[] Compromise_programming(double[] F1 , double[] F2 , double[] F3) {
			
			double[][] SortF1 = new double[2][F1.length];
			double[][] SortF2 = new double[2][F2.length];
			double[][] SortF3 = new double[2][F3.length];
			double[] Lp= new double[F1.length];
					
			SortF1 = My_Sort(F1); // sortF[0] = max  ,  sortF[F.length - 1] = Min  
			SortF2 = My_Sort(F2);
			SortF3 = My_Sort(F3);
			int P=1;
			
			Math.pow(0.333, P);
			
			for (int i = 0; i < F1.length; i++) {
				double Compute_F1 = 0;
				double Compute_F2 = 0;
				double Compute_F3 = 0;
					double x=SortF1[0][0];
					double x2=SortF2[0][0];

					double y=F1[i] ;
					double w=SortF1[0][F1.length - 1];
					 Compute_F1 = Math.pow(0.333, P) * Math.pow(((SortF1[0][0]-F1[i])/(SortF1[0][0] - SortF1[0][F1.length - 1 ])) , P);
					 Compute_F2 = Math.pow(0.333, P) * Math.pow(((SortF2[0][0]-F2[i])/(SortF2[0][0] - SortF2[0][F2.length - 1 ])) , P) ;
				     Compute_F3 = Math.pow(0.333, P) * Math.pow(((SortF3[0][0]-F3[i])/(SortF3[0][0] - SortF3[0][F3.length - 1 ])) , P) ;
				Lp[i] = Math.pow(Compute_F1 + Compute_F2 + Compute_F3, 1/P);
			}
			return Lp;
}

public static double[][] My_Sort(double[] x) {
			double[] data1=x;
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
			return data2;
}

public static int detection(String domain , String best_search_result) {
		    if(domain.toLowerCase().equals(best_search_result)){
		    	return 0;
		    }else{
		    	return 1;
		    }
}

public static int My_detection(String domain , ArrayList<String> sld) {
	int detection = 3;
    for (int i = 0; i < /*sld.size()*/ 6; i++) {
		if (domain.toLowerCase().equals(sld.get(i).toLowerCase())) {
			detection = 0;
			number_google_occure = i+1;

			break;
		} else {
			detection = 1;
			continue;
		} 
	}
    return detection;
}
 
public static void Filesave(int i2,String Phish_id ,
		   String url ,
		   String orginal_lable , 
		   String predictive_result ,
		   List<String> myx , 
		   ArrayList<String> SLD , 
		   String zz2 , 
		   int number_google_occure,
		   long totalTime ,
		   ArrayList<String> AllSLD_Frequent_SLD ,
		   ArrayList<String> AllSLD_OK) {
	
					try {
					    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\PhishWhoResult\\LPD_result.txt", true)));
					    PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\PhishWhoResult\\LPD_result2.txt", true)));
					    PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\PhishWhoResult\\LPD_result3.txt", true)));
					    PrintWriter out4 = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\PhishWhoResult\\LPD_result4.txt", true)));
				
				//	    out2.println("id"+","+"url"+","+"Orginal_lable"+","+"Predective_Lable");
					    out2.println(i2+"-"+Phish_id+","+url+","+orginal_lable+","+predictive_result);
					    
					    out3.println(i2+"-"+Phish_id+","+url+","+orginal_lable+","+predictive_result);
					    out3.println("SLD   :  "+SLD);
					    out3.println(myx);
					    out3.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					    
					    out4.println(Phish_id+","+
					    		     String.valueOf(AllSLD_Frequent_SLD.size())+","+
					    		     String.valueOf(AllSLD_OK.size())+","+
					                 String.valueOf(AllWord_OK.size())+","+
					    		     totalTime);
				
					    String S="";
					    String S1="";
					    String S2="";
					    String S3="";
					    
					    for (int i = 0; i < myx.size(); i++) {
							S=S.concat("<td>").concat(myx.get(i)).concat("</td>");
						}
					    myx.clear();
					    
					    for (int i = 0; i < UrlListOfGoogleSearch.size(); i++) {
					    	if(number_google_occure-1==i){
					    	S2=S2.concat("<td bgcolor=\"yellow\">").concat(UrlListOfGoogleSearch.get(i)).concat("</td>");
					    	}else{
						    S2=S2.concat("<td>").concat(UrlListOfGoogleSearch.get(i)).concat("</td>");
					    	}
						}
					    UrlListOfGoogleSearch.clear();
					    
					    for (int i = 0; i < SLD.size(); i++) {
					    	if(number_google_occure-1==i){
					    	S3=S3.concat("<td  bgcolor=\"yellow\">").concat(SLD.get(i)).concat("</td>");
					    	}else {
						    S3=S3.concat("<td>").concat(SLD.get(i)).concat("</td>");
				
							}
						}
					    SLD.clear();
				//------------------------------------------------------------FP , TP , FN , TN Color		    
					    String color_label_detect="";
					    if (orginal_lable.equals("0") && predictive_result.equals("0")) {
					    	color_label_detect="<td bgcolor=\"green\">0</td>"
					    					 + "<td bgcolor=\"green\">0</td>";
						}else if (orginal_lable.equals("1") && predictive_result.equals("1")||orginal_lable.equals("1") && predictive_result.equals("4")) {
							color_label_detect="<td bgcolor=\"green\">1</td>"
									         + "<td bgcolor=\"green\">1</td>";
						}else if (orginal_lable.equals("0") && predictive_result.equals("1")||orginal_lable.equals("0") && predictive_result.equals("4")) {
							color_label_detect="<td bgcolor=\"red\">0</td>"
							                 + "<td bgcolor=\"red\">1</td>";
				        }else if (orginal_lable.equals("1") && predictive_result.equals("0")) {
							color_label_detect="<td bgcolor=\"pink\">1</td>"
							                 + "<td bgcolor=\"pink\">0</td>";
				        }
				        else if (orginal_lable.equals("0") && predictive_result.equals("3")||orginal_lable.equals("1") && predictive_result.equals("3")) {
							color_label_detect="<td>-</td>"
							                 + "<td>-</td>";
				        }
				//------------------------------------------------------------FP , TP , FN , TN Color		    
					    String color_label_detect_URL="";
					    if (orginal_lable.equals("0") && predictive_result.equals("0")) {
					    	color_label_detect_URL="<td bgcolor=\"green\">"+url+"</td>"
					    					 + "<td bgcolor=\"green\">"+url+"</td>";
						}else if (orginal_lable.equals("1") && predictive_result.equals("1")||orginal_lable.equals("1") && predictive_result.equals("4")) {
							color_label_detect_URL="<td bgcolor=\"green\">"+url+"</td>"
									         + "<td bgcolor=\"green\">"+url+"</td>";
						}else if (orginal_lable.equals("0") && predictive_result.equals("1")||orginal_lable.equals("0") && predictive_result.equals("4")) {
							color_label_detect_URL="<td bgcolor=\"red\">"+url+"</td>"
							                 + "<td bgcolor=\"red\">"+url+"</td>";
				        }else if (orginal_lable.equals("1") && predictive_result.equals("0")) {
				        	color_label_detect_URL="<td bgcolor=\"pink\">"+url+"</td>"
							                 + "<td bgcolor=\"pink\">"+url+"</td>";
				        }
				        else if (orginal_lable.equals("0") && predictive_result.equals("3")||orginal_lable.equals("1") && predictive_result.equals("3")) {
				        	color_label_detect_URL="<td>"+url+"</td>"
							                 + "<td>"+url+"</td>";
				        }
				
				//--------------------------------------------------------------My <tr>	background	     
					    String myTR="";
					    if(predictive_result.equals("3")){
					    	myTR="<tr bgcolor=\"Orange\">";
					    }else{
					    	myTR="<tr>";
					    }
					    out.println(
					    		myTR
					    	   +"<td>"+i2+"</td>"
				               +color_label_detect_URL//+"<td>"+url+"</td>"
					    	   +color_label_detect
					    	   +"<td>"+Integer.toString(number_google_occure)+"</td>"
					    	   +S3
					    	   +S2
					    	   +"<td>"+Phish_id+"</td>"
					    	   + "</tr>"
					    		);
					    out.close();
					    out2.close();
					    out3.close();
					    out4.close();
					} catch (IOException e) {
					    //exception handling left as an exercise for the reader
					 }
}

public static void Delete_array(){
	  myx.clear();
	  resultSorted_X.clear();
	  resultSorted_Y.clear();
	  identity_keyword.clear();
	  title.clear();
	  body.clear();
	  meta.clear();
	  SLD.clear();
	  System.gc();
}
/*public static void Set_array(){
	MainOurwork_Alexa_B MainAlexa = new MainOurwork_Alexa_B();
	title.addAll(MainAlexa.title);
	body.addAll(MainAlexa.body);
	meta.addAll(MainAlexa.meta);
}*/

}
