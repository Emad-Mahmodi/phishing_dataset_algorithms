package LPD;

import mydomain.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

import ParsingWithSoup_xxx.contentWithTag;
import PhishWho.ChechDouble;
import PhishWho.DomainName;
import PhishWho.FindTopValuesSelectionSortImpl;
import PhishWho.GoogleCrawler;
import PhishWho.GoogleSearch;
import PhishWho.RemoveStopWord;


public class LPD_old_URL_algorithm {
	public static List<String> myx = new ArrayList<String>();//insert each word and corresponding weighted
	public static ArrayList<String> resultSorted=new ArrayList<String>();
	public static ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
	public static ArrayList<String> SLD = new ArrayList<String>();
	public static String zz2;
	public static String S_title;
	
	  public static int main(String TestURL , GoogleSearch gs) throws Exception {
//		  public static void main(String arg[]) throws Exception {
		  
		  String TargetURL=TestURL;//String TargetURL="https://www.google.com/";
//		  Document doc;
//		  doc = Jsoup.connect(TargetURL).get();
//		  --------------------------------------------------------------------------------
		  
//		  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
//		    String zz2="";
			zz2="";
		    zz2=MainDomain2.substring(0,MainDomain2.indexOf(sld2));
		    if(zz2.contains("."))
		    	zz2=MainDomain2.substring(zz2.lastIndexOf(".")+1,zz2.length());
//		--------------------------------------- Check if website has a Tiitle or not ---------------

//		---------------------------------------Use GoogleSearch-------------------------------------
		String CondidKey="";
		
//		CondidKey=S_title.concat("+").concat(zz2);
//		CondidKey=S_title;
		CondidKey=zz2;
		
//		ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
//		GoogleSearch gs=new GoogleSearch();
		int number=6;
		UrlListOfGoogleSearch=gs.MySearch(CondidKey , number);
		if(UrlListOfGoogleSearch.size()==0){
			return 3;
		}
//		ArrayList<String> SLD = new ArrayList<String>();
//-----------------------------------------------------calculate SLD of URL------------------		

		for (int i = 0; i < UrlListOfGoogleSearch.size(); i++) {		
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

				for (int i = 0; i < UrlListOfGoogleSearch.size(); i++) {
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
					//----------------------------------------------
					if (zz.matches(zz2)) {
						//					SLD.clear();
						//					SLD2.clear();
						System.out.println("legitimate");
						return 0;
					} 
					/*else {
						System.out.println("Phishing");
						return 1;
					}*/ 
				}
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
	  
}
