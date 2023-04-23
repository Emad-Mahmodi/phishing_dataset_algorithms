package WordRetrival;

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
import PhishWho.GoogleSearch;
import PhishWho.RemoveStopWord;


public class MyWordRetrival {
	public static List<String> myx = new ArrayList<String>();//insert each word and corresponding weighted
	public static ArrayList<String> resultSorted=new ArrayList<String>();
	public static ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
	public static ArrayList<String> SLD = new ArrayList<String>();
	public static String zz2;


	  public static int main(String TestURL , Document doc ) throws Exception {
//		  public static void main(String arg[]) throws Exception {
		  
		  String TargetURL=TestURL;//String TargetURL="https://www.google.com/";
//		  Document doc;
//		  doc = Jsoup.connect(TargetURL).get();
//		  --------------------------------------------------------------------------------
		  
//		  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//		  +++++++++++++++++++++++++++++++++++++++++++++++++++++   PLAIN TEXT   +++++++++++++++++++++++++++++++++
		  Elements TitleAttrs = doc.select("title");
		  Elements bodys = doc.select("body");
		  Elements metas1 = doc.select("meta");
		  Elements metas2 = doc.select("meta");
		  Elements alts1 = doc.select("img");

//         							  ====================================>TITLE<======
		  List<String> wordsTitle = new ArrayList<String>();
		  for (Element TitleAttr : TitleAttrs) {
			  String S=TitleAttr.text();
			  for (int i = 0; i < getWords(S).size(); i++) {
				  wordsTitle.add(getWords(S).get(i));
		  	  }
			}
		  TitleAttrs.clear();		  
//		                              ====================================>BODY<======
		  List<String> WordsBody = new ArrayList<String>();
		  for (Element body : bodys) {
			  String S=body.text();
			  for (int i = 0; i < getWords(S).size(); i++) {
				  WordsBody.add(getWords(S).get(i));
			  }
			}
		  bodys.clear();
//         							  ====================================>META1 WARNING  ****** <======
		  List<String> wordsMeta1 = new ArrayList<String>();
		  for (Element meta1 : metas1) {
			  String S=meta1.attr("name");
			  for (int i = 0; i < getWords(S).size(); i++) {
				  wordsMeta1.add(getWords(S).get(i));
			       }
			  }
		  metas1.clear();
//         							   ====================================>META2<======
		  List<String> wordsMeta2 = new ArrayList<String>();
		  for (Element meta2 : metas2) {
			  String S=meta2.attr("content");
			  for (int i = 0; i < getWords(S).size(); i++) {
				  wordsMeta2.add(getWords(S).get(i));
			  }		  
	        }
		  metas2.clear();
		  //++
		  /*for (Element meta2 : metas2) {
			  String S=meta2.toString();
			  S = S.substring((S.indexOf("content=\""))+9);
			  S = S.substring(0, S.indexOf("\""));
			  for (int i = 0; i < getWords(S).size(); i++) {
				  wordsMeta2.add(getWords(S).get(i));
			  }		  
	        }*/
		  
		 //         				   ====================================>ALT<======
		  List<String> wordsAlt = new ArrayList<String>();
		  for (Element alt1 : alts1) {
			  String S=alt1.attr("alt");
			  for (int i = 0; i < getWords(S).size(); i++) {
				  wordsAlt.add(getWords(S).get(i));
			  }
	        }
		  alts1.clear();
//		                               ====================================> Concate All of the Word <============
		  List<String> AllWord = new ArrayList<String>();
		  AllWord.addAll(wordsTitle);
		  AllWord.addAll(WordsBody);
//		  AllWord.addAll(wordsMeta1);
		  AllWord.addAll(wordsMeta2);
		  AllWord.addAll(wordsAlt);
		  //		  ----------------------------convert array list into string ------------
		  String listString = "";
		  for (String s : AllWord)
		  {
			  if(s.matches("[a-zA-Z]+")){    // if 's' means one word in all word arraylist cause a letter(english alphabet)
			      listString += s + " ";
			  }else {
				  listString += s.replaceAll("\\d",""); // if 's' means one word in all word arraylist have not a letter (ore contain only number)
			}
		  }
		  System.out.println( "All Word is ==>"+listString);
		  // 		 -------------------------------- (remove Double word)  -----------------
		  ChechDouble ch=new ChechDouble();
		  listString = ch.check(listString);
		  // 		 -------------------------------- (remove Stop word)  -----------------
		  RemoveStopWord rsw=new RemoveStopWord();
		  String listString2 =rsw.Remove(listString);
		  //         -------------------------------convert String into array list--------------
		  AllWord.clear();
		  AllWord=getWords(listString2);
//		  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    URL     ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		  
		  Elements medias1 = doc.select("img");
		  Elements hrefs1 = doc.select("a");
		  Elements hrefs2 = doc.select("link");
//		                                      ==========================>   SRC   <=========================
		  List<String> media = new ArrayList<String>();
		  for (Element media1 : medias1) {
			  String S=media1.attr("src");
			  media.add(S);
	        }
		  medias1.clear();
//    										  ==========================>   href by a  <=========================
		  List<String> href_a = new ArrayList<String>();
		  for (Element href1 : hrefs1) {
			  String S=href1.attr("href");
			  href_a.add(S);
	        }
		  hrefs1.clear();
//                                            ==========================>   href by Link   <=========================
		  List<String> href_Link = new ArrayList<String>();
		  for (Element href2 : hrefs2) {
			  String S=href2.attr("href");
			  href_Link.add(S);
	        }
		  hrefs2.clear();
//                                              =======================>Concate All URL   <=================
		  List<String> AllURL = new ArrayList<String>();
		  AllURL.addAll(media);
		  AllURL.addAll(href_a);
		  AllURL.addAll(href_Link);
		  //----------------------------------------------
		  String URLString = "";
		  List<String> AllURL2 = new ArrayList<String>();
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
          //------------------------------------------
		  int n=AllURL.size(); // total number of URLs extracted from the web page
		  AllURL.clear();
		  AllURL.addAll(AllURL2);//AllURL=AllURL2;
		  
		  //------------------------------------------
		  double[] WordWeightInAllUrl = new double[AllWord.size()];
		  int Numberword=0;
		  List<List<Integer>> ArrayLevel = new ArrayList<List<Integer>>();

		    
//------------------------------------------------------------------------------------------------------------ Level ------------------------------------------------
		  for (int i5 = 0; i5 < AllURL.size(); i5++) {
			  String url = AllURL.get(i5);  
//			  System.out.println(url);
//			  ---------------------------------------------------------------------------------------     Split URL of Level    ----------------------------
//			  int[] level=new int[100];
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
//			 -----------------------------------------------------
			 ArrayLevel.add(level);
		  }
//		  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   compression   +++++++++++++++++++++++++++++++++++++  

		  for (int i=0;i<AllWord.size();i++){
			  String word = AllWord.get(i);  //token word in the arraylist
//			  System.out.println(word);
			 //------------------------------------------------ 
			  int Li = word.length(); // L : size of the word
			  double[] WordWeightInOneUrl = new double[n];
			  int NumberUrl=0;
//------------------------------------------------ compute max of url level in all url---------------
 			  int maxlevel=0;
		      int x3=0;
 			    for (int i16=0; i16<ArrayLevel.size();i16++){
 			    	x3=ArrayLevel.get(i16).size();
 			    	if(maxlevel<x3)
 			    		maxlevel=x3;
 			    }
//------------------------------------------------------------------------------------------------------- 
			  ArrayList level = new ArrayList();
			  if(maxlevel==0){
				  return 3;
			  }
			  
			  double[] x=new double[maxlevel-1];

			  
 			  for (int i17 = 0; i17 < maxlevel-1; i17++) {
				  ArrayList myurlLevel = new ArrayList();
					ArrayList NumberLevelOccur = new ArrayList();

 	 			  for (int i5 = 0; i5 < AllURL.size(); i5++) {
	 					String url = AllURL.get(i5);
	 					int w=ArrayLevel.get(i5).size();
						if(i17>=w-1){
							continue;
						}
					    myurlLevel=(ArrayList) ArrayLevel.get(i5);
//						System.out.println(myurlLevel);
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
//						System.out.println(i5);
						
						String UrlLevel=url.substring(Start,End).toLowerCase(); //specify each level 
						if ( UrlLevel.indexOf(word.toLowerCase())!=-1) { // Check the occurrence word in each level of URL 
									   NumberLevelOccur.add(url);
								       continue;				
						 }
				    }
					int NLOCC=0;
					NLOCC=(int)NumberLevelOccur.size();
					int k2=((int)Math.pow(i17+1,2));    
					x[i17]=(double)NLOCC/(double)k2;
 	 			  }	  
////>>>>>>>>>>>>>>>>>>>>>>>>>>///=====================================new ==============
		 		    double SumWeightwodInEachUrlLevel=0;
					for (int i8 = 0; i8 < x.length; i8++) {
						SumWeightwodInEachUrlLevel+=x[i8];
					}
		 			WordWeightInAllUrl[Numberword]=SumWeightwodInEachUrlLevel;
		 			Numberword++;
//--------------------------------------------------------------compute extra-------------------					
		 			myx.add(word);
		 			myx.add(Double.toString(SumWeightwodInEachUrlLevel));
		 	 		System.out.println(myx);//word+weight	  
//--------------------------------------------------------------------------------------------- 	
		  }
//		  ---------------------------------sort [iteration][index]----------------------------
			double[] data1=WordWeightInAllUrl;
			int size = data1.length;
			double[][] data2 = new double[2][size];
			for (int i19 = 0; i19 < data1.length; i19++) {
				data2[0][i19]= data1[i19];
				data2[1][i19]= i19;
			}
			int n2=data2[0].length;
			FindTopValuesSelectionSortImpl f=new FindTopValuesSelectionSortImpl();
			int[] result=f.findTopNValues2(data2, n2);
			/*for (int i = 0; i < AllWord.size(); i++) {
				System.out.println(AllWord.get(result[i]));
			}*/
			for (int i = 0; i < result.length; i++) {
				 resultSorted.add(AllWord.get(result[i]));
			}
//		---------------------------------------Use GoogleSearch-----------------------------
		String CondidKey="";
		CondidKey=AllWord.get(result[0]).concat("+"+AllWord.get(result[1])).
										 concat("+"+AllWord.get(result[2])).
										 concat("+"+AllWord.get(result[3])).
										 concat("+"+AllWord.get(result[4]));
//		ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
				GoogleSearch gs=new GoogleSearch();
		int number=20;
		UrlListOfGoogleSearch=gs.MySearch(CondidKey , number);
		if(UrlListOfGoogleSearch.size()==0){
			return 3;
		}
//		ArrayList<String> SLD = new ArrayList<String>();
//-----------------------------------------------------calculate SLD of URL------------------		
	    FileReader fr = new FileReader("public_suffix_list.dat.txt");
	    TopLevelDomainChecker checker = new TopLevelDomainChecker();
	    TopLevelDomainParser parser = new TopLevelDomainParser(checker);
	    parser.parse(fr);
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
//		-----------------------------------------------------Weight Of Google Response Domain In URL
				Phish_Weight_Word_In_URL PhWei=new Phish_Weight_Word_In_URL();
				String result_Domain_Google_in_URL="";
				result_Domain_Google_in_URL=PhWei.WeightWordInURL(AllURL, SLD, ArrayLevel, number);
				

			    //------------------ SLD Target-----------------
			    DomainName dm5=new DomainName();
				String MainDomain2=dm5.MyName(TargetURL);
				String sld2;
				sld2 = ".".concat(checker.extractSLD(MainDomain2));
//			    String zz2="";
				zz2="";
			    zz2=MainDomain2.substring(0,MainDomain2.indexOf(sld2));
			    if(zz2.contains("."))
			    	zz2=MainDomain2.substring(zz2.lastIndexOf(".")+1,zz2.length());
			    //----------------------------------------------------------clear all obj---------
//			    ArrayLevel.clear();
//			    AllURL.clear();
//			    AllURL2.clear();
//			    AllWord.clear();
//			    wordsAlt.clear();
//			    wordsMeta1.clear();
//			    wordsMeta2.clear();
//			    wordsTitle.clear();
//			    WordsBody.clear();
				//----------------------------------------------
				if(result_Domain_Google_in_URL.matches(zz2)){
//					SLD.clear();
//					SLD2.clear();
					System.out.println("legitimate");
					return 0;
				}else{
					System.out.println("Phishing");
					return 1;
				}
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
