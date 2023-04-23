package OurWork;

import EditDistance.*;
import LPD.GoogleSearch_Selenium;

import java.net.*;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

import PhishWho.ChechDouble;
import PhishWho.DomainName;
import mydomain.TopLevelDomainChecker;
import mydomain.TopLevelDomainParser;

import java.io.*;

public class OurWork_Edit_destance {
	public static ArrayList<String> src = new ArrayList<String>();
	public static ArrayList<String> href = new ArrayList<String>();
	public static ArrayList<String> Script = new ArrayList<String>();
	public static ArrayList<String> Css = new ArrayList<String>();
	public static ArrayList<String> title = new ArrayList<String>();
	public static ArrayList<String> body = new ArrayList<String>();
	public static ArrayList<String> meta = new ArrayList<String>();
	public static ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
	public static ArrayList<String> SLD = new ArrayList<String>();
	public static ArrayList<String> Final_Sord_word_plus_weight = new ArrayList<String>();
	public static  ArrayList<String> AllWord_OK=new ArrayList<String>();
	public static int number_google_occure = 11;
	public static int flag=1;

	
//public static void main(String[] args) throws IOException, URISyntaxException {
public static int main(String TestURL , String Local_URL, WebDriver driver , WebDriver driver2 , String Phish_id , int number , String orginal_lable) throws Exception {

		//this arguman requaire
	         int detection = 3;
	    
	    try {
//	    	long start = System.currentTimeMillis();
//			long end = start + 60000*1000; // 60 seconds * 1000 ms/sec
//			while (System.currentTimeMillis() < end)
 
			           
						Delete_array();
						driver.get(Local_URL);
						String TargetURL = "";
						  if(TestURL .equals(Local_URL)){
							  TargetURL = driver.getCurrentUrl();
						  }else{
							  TargetURL = TestURL ;
						  }
						/*String URL ="http://stackoverflow.com/questions/20647619/global-sequence-alignment-dynamic-programming-finding-the-minimum-in-a-matrix";//"http://stackoverflow.com/questions/4448698/java-array-if-all-the-same";//"http://rozeinternational.com/ld/linkedin.com/index.html" ;
						URL aURL = new URL(URL);
						System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x2\\libs\\selenium\\geckodriver.exe");
						WebDriver driver = new FirefoxDriver();*/
						
						ArrayList<String> AllURL = new ArrayList<String>();
						String domain_name=domain(driver , TargetURL );
						
						ArrayList<String> AllWord_Frequent_word=new ArrayList<String>();
						ArrayList<String> AllSLD_Frequent_SLD=new ArrayList<String>();
						ArrayList<String> AllSLD_OK=new ArrayList<String>();
			
						AllURL = Set_Resource(driver);
						System.out.println(AllURL.size());
						AllSLD_Frequent_SLD = SLDs(AllURL);
						AllSLD_OK = CheckWord(AllSLD_Frequent_SLD);
			
						
						AllWord_Frequent_word = T_Plain(driver);
						AllWord_OK = CheckWord(AllWord_Frequent_word);
						
						ArrayList<String> Result_word = new ArrayList<String>();
						
						long startTime = System.currentTimeMillis();
						Result_word = Propose_System(AllWord_OK, AllURL, AllSLD_Frequent_SLD, AllSLD_OK);
						long endTime   = System.currentTimeMillis();
						long totalTime = endTime - startTime;
						
						ArrayList<String> identity_keyword = new ArrayList<String>();
						identity_keyword = Sequential_Rule_base_selection(domain_name, Result_word, 5);
						SLD = SLDs( GoogleSearch(identity_keyword , domain_name , driver2) );
						
						detection= My_detection(domain_name , SLD );
						 	    
						Filesave(number ,
								 Phish_id, TargetURL, orginal_lable,
								 String.valueOf(detection),
								 Final_Sord_word_plus_weight, 
								 AllSLD_OK, domain_name , 
								 number_google_occure,
								 totalTime , 
								 AllSLD_Frequent_SLD , 
								 AllSLD_OK);
						
						return detection;

		} catch (Exception e) {

			return detection;
		}
 	    
	}
	
	
public static ArrayList<String> Propose_System( ArrayList<String> AllWord_OK , ArrayList<String> AllURL  , ArrayList<String> AllSLD_Frequent_SLD , ArrayList<String> AllSLD_OK ) {
	
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
					int x3 = minDistance(s1 , s2);
					double x1 = (double)flag *((double) 1 / ( x3 +1)) ; 
					double x2 = (double)freq[i]/Weight_ALL_SLD ; 
					
					distance_word_in_SLD[i][j] = (double)(x2 * x1);
					//distance_word_in_SLD[i][j] = (double)((double)freq[i]/Weight_ALL_SLD) *((double)1 / (minDistance(AllSLD_OK.get(i) , AllWord_OK.get(j))+1));
				}
			}
			String[][] Sort_Word = new String[freq.length][10 /*AllWord_OK.size()*/];
			double[][] Sort_Word_weight = new double[freq.length][10 /*AllWord_OK.size()*/];
			double[][] data2 = new double[2][AllWord_OK.size()];
			ArrayList<String> allword_array = new ArrayList<String>();
			double[] weight_allword_array = new double[freq.length * 10];
			for (int i = 0; i < freq.length; i++) {
				    data2 =Sort(distance_word_in_SLD[i], AllWord_OK);
					
					for (int j = 0; j < 10; j++) {
						int z = (int) data2[1][j];
						double z2 = data2[0][j];
						Sort_Word[i][j]=AllWord_OK.get(z);
						allword_array.add(AllWord_OK.get(z));
						
						Sort_Word_weight[i][j]=z2;
						weight_allword_array[j] = z2; 
					}
			}
			
			return FinallSort(weight_allword_array, allword_array , freq.length * 10);
			
}

public static ArrayList<String> FinallSort(double[] WordWeightInAllUrl , ArrayList<String> AllWord , int n ){
			double[][] data2 = new double[2][n];
			data2 = Sort(WordWeightInAllUrl, AllWord);
			
			ArrayList<String> Final_Sord_word = new ArrayList<String>();
			double[] Final_Sord_word_weight = new double[n];
			
			for (int j = 0; j < n; j++) {
				int z = (int) data2[1][j];
				double z2 = data2[0][j];
				Final_Sord_word.add(AllWord.get(z));
				Final_Sord_word_weight[j] = z2;
				
				Final_Sord_word_plus_weight.add(AllWord.get(z));
				Final_Sord_word_plus_weight.add(Double.toString(z2));
			}
		return Final_Sord_word;	
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



public static ArrayList<String> Set_Resource( WebDriver driver) {
	
				 ArrayList<String> AllURL = new ArrayList<String>();
				  //---------------------------------------------------------------
				  List<WebElement> href_links = driver.findElements(By.xpath("//a"));
				  List<WebElement> src_links = driver.findElements(By.xpath("//img"));
				  List<WebElement> Script_links = driver.findElements(By.xpath("//script"));
				  List<WebElement> Css_links = driver.findElements(By.xpath("//link"));
				  //--------  href , src , Script(JavaScript files) , Link(style sheet files)   -------------------------
			
				    for (WebElement href_link : href_links)
				    {
				    	if (href_link.getAttribute("href")== null 
					    		||href_link.getAttribute("href").isEmpty()
					    		||href_link.getAttribute("href").contains("javascript:void(0)")
					    		) {
								continue;
						}
				    	
				    	
				        href.add(href_link.getAttribute("href"));
				        
				        System.out.println(" href  :  "+href_link.getAttribute("href"));
				    }
				    AllURL.addAll(href);
				    
				    for (WebElement src_link : src_links)
				    {
				    	if (src_link.getAttribute("src")== null 
					    		||src_link.getAttribute("src").isEmpty()
					    		||src_link.getAttribute("src").contains("javascript:void(0)")) {
								continue;
						}
				    	
				    
				        src.add(src_link.getAttribute("src"));
				        System.out.println(" src img :  "+src_link.getAttribute("src"));
				    }
			        AllURL.addAll(src);

			        
				    for (WebElement Script_link : Script_links)
				    {
				    	if (Script_link.getAttribute("src")== null 
					        ||Script_link.getAttribute("src").isEmpty()
					        ||Script_link.getAttribute("src").contains("javascript:void(0)")) {
								continue;
						}
				    	
				    	Script.add(Script_link.getAttribute("src"));
				        System.out.println(" src script :  "+Script_link.getAttribute("src"));
				    }
			        AllURL.addAll(Script);

				    for (WebElement Css_link : Css_links)
				    {
				    	if (Css_link.getAttribute("href")== null 
					    	||Css_link.getAttribute("href").isEmpty()
					    	||Css_link.getAttribute("href").contains("javascript:void(0)")) {
								continue;
						}
				    	
				    	Css.add(Css_link.getAttribute("href"));
				        System.out.println(" src  :  "+Css_link.getAttribute("href"));
				    }
			        AllURL.addAll(Css);

				    
				    return AllURL;
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
				  return  Word_that_host_plus_SLD( Words);
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
			    System.out.println(i+" - "+URLSLD);
			SLD_List.add(URLSLD);
		} catch (Exception e) {
			continue;
		}
    }
    
    return SLD_List;
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
			int index = word2.indexOf(word1);
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
			}
		}
		return dp[len1][len2];
}

public static int My_detection(String domain , ArrayList<String> sld) {
			int detection = 3;
			
		    for (int i = 0; i < /*sld.size()*/ 10; i++) {
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
				    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\ProposeResaule\\LPD_result.txt", true)));
				    PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\ProposeResaule\\LPD_result2.txt", true)));
				    PrintWriter out3 = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\ProposeResaule\\LPD_result3.txt", true)));
				    PrintWriter out4 = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\ProposeResaule\\LPD_result4.txt", true)));

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
					src.clear();
					href.clear();
					Script.clear();
					Css.clear();
					title.clear();
					body.clear();
					meta.clear();
					UrlListOfGoogleSearch.clear();
					SLD.clear();
					Final_Sord_word_plus_weight.clear();
					AllWord_OK.clear();
}


}
