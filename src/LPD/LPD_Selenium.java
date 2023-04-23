package LPD;

import mydomain.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.Doc;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import ParsingWithSoup_xxx.contentWithTag;
import PhishWho.ChechDouble;
import PhishWho.DomainName;
import PhishWho.FindTopValuesSelectionSortImpl;
import PhishWho.GoogleCrawler;
import PhishWho.GoogleSearch;
import PhishWho.RemoveStopWord;


public class LPD_Selenium {
	public static List<String> myx = new ArrayList<String>();//insert each word and corresponding weighted
	public static ArrayList<String> resultSorted=new ArrayList<String>();
	public static ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
	public static ArrayList<String> SLD = new ArrayList<String>();
	public static String zz2;
	public static String S_title;
	public static int Google_number_occure;
	public static String status;
	
	  public static int main(String TestURL  , GoogleSearch_Selenium gs ,WebDriver driver,WebDriver driver2) throws Exception {
//		  public static void main(String arg[]) throws Exception {
		  
//		  Document doc;
//		  doc = Jsoup.connect(TargetURL).get();
//		  --------------------------------------------------------------------------------
		  
//		  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		  +++++++++++++++++++++++++++++++++++++++++++++++++++++   PLAIN TEXT   +++++++++++++++++++++++++++++++++
		  driver.get(TestURL);
		  String Title_Selenium=driver.getTitle();


//         							  ====================================>TITLE and Current URL in Selenium<======

		  S_title=Title_Selenium;
		  String url = driver.getCurrentUrl();// corrent URL
		  String TargetURL=url;//String TargetURL="https://www.google.com/";
		  URL url3 = new URL(TestURL);
		  String HostURL= url3.getHost();

		  if(S_title.equals(HostURL) || URLtitleError(S_title)==5){
			  return 5;
		  }
		  
//		  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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
//		--------------------------------------- Check if website has a Tiitle or not ---------------
		    String aa=TargetURL.substring(0 , TargetURL.indexOf("//")+2);
//--------------------------------------------------------------------------------------------------Get host of url-------------
			URL url2 = new URL(url);
			zz2 = url2.getHost();
			zz2=zz2.substring(zz2.indexOf("www.")+4,zz2.length());
			
			//  ==>    https://google.com
			/*int x1=StringUtils.ordinalIndexOf(url, "/", 3);
			int x2=url.indexOf("www.");
			zz2=url.substring(0,x2).concat(url.substring(x2+4,x1));*/
//		    ---------------------------------------------------------------------------------
			String CondidKey=""; status="";
			
//			CondidKey=S_title.concat("+").concat(zz2);status="Domain+Title";
//			CondidKey=Title_Selenium.concat("+").concat(zz2);status="Domain+Title";
			CondidKey=S_title;status="Title";
//			CondidKey=zz2;status="Domain";
//---------------------------------------------------------------------------------------		    

		   /* if(S_title==""){
		    	 try {
					S_title="";
					 Document doc2 = null;
					 doc2=Jsoup.connect(aa.concat(MainDomain2)).get();
					 TitleAttrs = doc2.select("title");
					 for (Element TitleAttr : TitleAttrs) {
						 S_title=TitleAttr.text();
						  for (int i = 0; i < getWords(S_title).size(); i++) {
							  wordsTitle.add(getWords(S_title).get(i));
					  	  }
						}
					 if( S_title==""){
						 S_title="Not Exist";
						 return 4;// '4' means website not has a Title 
					 }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return 4;// '4' means website not has a Title 				}
				}
		    }*/	 
  
//		---------------------------------------Use GoogleSearch-------------------------------------

		
//		ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
//		GoogleSearch gs=new GoogleSearch();
		int number=6;
//		UrlListOfGoogleSearch=gs.MySearch(CondidKey , number);
		UrlListOfGoogleSearch=gs.main(CondidKey , number , driver2);

		if(UrlListOfGoogleSearch.size()==0){
			return 4;
		}
//		ArrayList<String> SLD = new ArrayList<String>();
//-----------------------------------------------------calculate SLD of URL------------------		

		for (int i = 0; i < 6 /*UrlListOfGoogleSearch.size()*/; i++) {		
			String S=UrlListOfGoogleSearch.get(i);
			DomainName dm3=new DomainName();
			String MainDomain=dm3.MyName(S);
			if(MainDomain==null){
				continue;
			}else {
				String sld;
				
			    sld = ".".concat(checker.extractSLD(MainDomain));
			    String zz="";
			    if(StringUtils.countMatches(MainDomain,".")==0){//StringUtils.countMatches(MainDomain,"/")
			    	SLD.add(MainDomain);
			    	continue;
			    }
			    zz=MainDomain.substring(0,MainDomain.indexOf(sld));
			    if(zz.contains("."))
			    	zz=MainDomain.substring(zz.lastIndexOf(".")+1,zz.length());
			
				SLD.add(zz);
			}

		}
		
		
		System.out.println(SLD);

				
//------------------------------------------Tir 1 ------------------------------
				DomainName dm=new DomainName();
				DomainName dm2=new DomainName();
				
				
				

			    //----------------------------------------------------------clear all obj---------

				for (int i = 0; i <6 /*UrlListOfGoogleSearch.size()*/; i++) {
					String BestResult = "";
					if (StringUtils.countMatches(UrlListOfGoogleSearch.get(i), ".") == 0) {//when google result for example is "tribal" not "https:\\..."
						BestResult = UrlListOfGoogleSearch.get(i);
					} else {
						BestResult = dm2.MyName(UrlListOfGoogleSearch.get(i));
					}
					//------------------SLD best result-------------
					DomainName dm4 = new DomainName();
					String MainDomain = BestResult;
					String zz = "";
					if (StringUtils.countMatches(MainDomain, ".") == 0) {
						zz = MainDomain;
					} else {
						String sld;
						sld = ".".concat(checker.extractSLD(MainDomain));
						zz = MainDomain.substring(0, MainDomain.indexOf(sld));
						if (zz.contains("."))
							zz = MainDomain.substring(zz.lastIndexOf(".") + 1, zz.length());
					}
					//-------------------------------------checking Domain and Result-----
					if (zz.matches(URLSLD)) {
						//					SLD.clear();
						//					SLD2.clear();
						System.out.println("legitimate");
						Google_number_occure=i+1;
						//---------------------------------------------------------------------------------------		    
												    if(S_title==""){
												    	 try {
															S_title="";
															 Document doc2 = null;
															 doc2=Jsoup.connect(aa.concat(MainDomain2)).get();
															 driver.get(aa.concat(MainDomain2));
															 S_title=driver.getTitle();
															 if( S_title==""){
																 S_title="Not Exist";
																 return 4;// '4' means website not has a Title 
															 }
														} catch (Exception e) {
															// TODO Auto-generated catch block
															return 4;// '4' means website not has a Title 				}
														}
												    }                
								return 0;
						} 
						/*else {
							System.out.println("Phishing");
							return 1;
						}*/ 
					}
				Google_number_occure=20;
				return 1;
	  }
		    

			
//-------------------------------------------------------------------------------------------------------		  
	 
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
//-------------------------------------------------------------------------------	  
		public static int URLtitleError(String title) {
			String URL0="403";
			String URL1="404";
			String URL50="500";
			String URL51="unavailable";
			String URL52="found";
			String URL53="available";
			String URL54="Inactive";
			String URL55="H1-3";
			String URL56="H1-4";
			String URL57="H1-5";
			String URL58="H1-6";
			String URL59="H1-7";
			String URL60="H1-8";
			String URL61="H1-9";
			String URL62="H1-10";
			String URL63="page";


			String URL2="HTTP 404 Resource not found";
			String URL3="You got 403 - Hostinger United Kingdom";
			String URL4="https://oaklanduniversity-edu.jimdo.com/";
			String URL5="Warning! | There might be a problem with the requested link";
			String URL6="403 Forbidden";
			String URL7="404 - PAGE NOT FOUND";
			String URL8="404 Not Found";
			String URL9="Account Suspended";
			String URL10="Contact Support";
			String URL11="This website is currently unavailable.";
			String URL12="Create a Website | Tripod Web Hosting";
			String URL13="TinyURL.com - where tiny is better!";
			String URL14="Nothing found for  Pk 1 Alert";
			String URL15="500 Internal Server Error";
			String URL16="Page not found &laquo;  Loomis Law Firm";
			String URL17="The website is currently not available";
			String URL18="Page not found &#8211; HiFad";
			String URL19="TinyURL.com - where tiny is better!";
			String URL20="Page not found | World Wear Company";
			String URL21="Sorry, this page is not available";
			String URL22="Website Inactive";
			String URL23="obi007.com";
			String URL24="beigeengineers.com";
			String URL25="No such website | 000webhost";

			
			if(title.toLowerCase().contains(URL0) ||
			   title.toLowerCase().contains(URL1) ||
			   title.toLowerCase().contains(URL50) ||
			   title.toLowerCase().contains(URL51) ||
			   title.toLowerCase().contains(URL52) ||
			   title.toLowerCase().contains(URL53) ||
			   title.toLowerCase().contains(URL54) ||
			   title.toLowerCase().contains(URL55) ||
			   title.toLowerCase().contains(URL56) ||
			   title.toLowerCase().contains(URL57) ||
			   title.toLowerCase().contains(URL58) ||
			   title.toLowerCase().contains(URL59) ||
			   title.toLowerCase().contains(URL60) ||
			   title.toLowerCase().contains(URL61) ||
			   title.toLowerCase().contains(URL62) ||
			   title.toLowerCase().contains(URL63) 

			   ){
				return 5;
			}
			if(title.toLowerCase().equals(URL2.toLowerCase()) ||
			   title.toLowerCase().equals(URL3.toLowerCase()) ||
			   title.toLowerCase().equals(URL4.toLowerCase()) ||
			   title.toLowerCase().equals(URL5.toLowerCase()) ||
			   title.toLowerCase().equals(URL6.toLowerCase()) ||
			   title.toLowerCase().equals(URL7.toLowerCase()) ||
			   title.toLowerCase().equals(URL8.toLowerCase()) ||
			   title.toLowerCase().equals(URL9.toLowerCase()) ||
			   title.toLowerCase().equals(URL10.toLowerCase()) ||
			   title.toLowerCase().equals(URL11.toLowerCase()) ||
			   title.toLowerCase().equals(URL11.toLowerCase()) ||
			   title.toLowerCase().equals(URL12.toLowerCase()) ||
			   title.toLowerCase().equals(URL13.toLowerCase()) ||
			   title.toLowerCase().equals(URL14.toLowerCase()) ||
			   title.toLowerCase().equals(URL15.toLowerCase()) ||
			   title.toLowerCase().equals(URL16.toLowerCase()) ||
			   title.toLowerCase().equals(URL17.toLowerCase()) ||
			   title.toLowerCase().equals(URL18.toLowerCase()) ||
			   title.toLowerCase().equals(URL19.toLowerCase()) ||
			   title.toLowerCase().equals(URL20.toLowerCase()) ||
			   title.toLowerCase().equals(URL21.toLowerCase()) ||
			   title.toLowerCase().equals(URL22.toLowerCase()) ||
			   title.toLowerCase().equals(URL23.toLowerCase()) ||
			   title.toLowerCase().equals(URL24.toLowerCase()) ||
			   title.toLowerCase().equals(URL25.toLowerCase()) 
					){
				return 5;
			}

			return 0;
		}
}
