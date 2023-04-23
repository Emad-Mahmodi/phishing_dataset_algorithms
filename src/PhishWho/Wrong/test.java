package PhishWho.Wrong;

import java.io.File;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class test {
	  public static void main(String[] args) throws Exception {

		  
		  Document doc;
		  doc = Jsoup.connect("http://www.wikipedia.com/").get();
//		  --------------------------------------------------------------------------------
//		  +++++++++++++++++++++++++++++++++++++++++++++++++++++   PLAIN TEXT   +++++++++++++++++++++++++
		  Elements TitleAttrs = doc.select("title");
		  Elements bodys = doc.select("body");
		  Elements metas1 = doc.select("meta");
		  Elements metas2 = doc.select("meta");
		  Elements alts1 = doc.select("img");

//         							  ====================================>TITLE<======
		  List<String> wordsTitle = new ArrayList<String>();
		  for (Element TitleAttr : TitleAttrs) {
			  String S=TitleAttr.text();
			  wordsTitle=getWords(S);	  
	        }
		  
//		                              ====================================>BODY<======
		  List<String> WordsBody = new ArrayList<String>();
		  for (Element body : bodys) {
			  String S=body.text();
			  WordsBody=getWords(S);
	        }
//         							  ====================================>META1 WARNING  ****** <======
		  List<String> wordsMeta1 = new ArrayList<String>();
		  for (Element meta1 : metas1) {
			  String S=meta1.attr("name");
			  wordsMeta1=getWords(S);
	        }
//         							   ====================================>META2<======
		  List<String> wordsMeta2 = new ArrayList<String>();
		  for (Element meta2 : metas2) {
			  String S=meta2.attr("content");
			  wordsMeta2=getWords(S);
	        }
//         							   ====================================>ALT<======
		  List<String> wordsAlt = new ArrayList<String>();
		  for (Element alt1 : alts1) {
			  String S=alt1.attr("alt");
			  wordsAlt=getWords(S);
	        }
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
		      listString += s + " ";
		  }
		  System.out.println(listString);
		  // 		 -------------------------------- (remove Double word)  -----------------
		  ChechDouble ch=new ChechDouble();
		  listString = ch.check(listString);
		  // 		 -------------------------------- (remove Stop word)  -----------------
		  RemoveStopWord rsw=new RemoveStopWord();
		  String listString2 =rsw.Remove(listString);
		  System.out.println(listString2);
		  //         -------------------------------convert String into array list--------------
		  AllWord=getWords(listString2);
//      	  ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++    URL     ++++++++++++++++++++++++++++++
		  Elements medias1 = doc.select("img");
		  Elements hrefs1 = doc.select("a");
		  Elements hrefs2 = doc.select("link");
//		                                      ==========================>   SRC   <=========================
		  List<String> media = new ArrayList<String>();
		  for (Element media1 : medias1) {
			  String S=media1.attr("src");
			  media.add(S);
	        }
//    										  ==========================>   href by a  <=========================
		  List<String> href_a = new ArrayList<String>();
		  for (Element href1 : hrefs1) {
			  String S=href1.attr("href");
			  href_a.add(S);
	        }
//                                            ==========================>   href by Link   <=========================
		  List<String> href_Link = new ArrayList<String>();
		  for (Element href2 : hrefs2) {
			  String S=href2.attr("href");
			  href_Link.add(S);
	        }
//                                              =======================>Concat All URL   <=================
		  List<String> AllURL = new ArrayList<String>();
		  AllURL.addAll(media);
		  AllURL.addAll(href_a);
		  AllURL.addAll(href_Link);
          //------------------------------------------
		  int n=AllURL.size(); // total number of URLs extracted from the web page
		  //------------------------------------------
		  double[] WordWeightInAllUrl = new double[AllWord.size()];
		  int Numberword=0;
//		  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   compression   +++++++++++++++++++++++++++++++++++++  
		  for (int i=0;i<AllWord.size();i++){
			  String word = AllWord.get(i);  //token word in the arraylist
//			  System.out.println(word);
			 //------------------------------------------------ 
			  int Li = word.length(); // L : size of the word
			  double[] WordWeightInOneUrl = new double[n];
			  int NumberUrl=0;
			 //------------------------------------------------ 
 			  for (int i5 = 0; i5 < AllURL.size(); i5++) {
				  String url = AllURL.get(i5);  
//				  System.out.println(url);
//				  ----------------------------------     Split URL of Level    ----------------------------
//				  int[] level=new int[100];
				  ArrayList level = new ArrayList();
				  
				  
				 if(url.contains("//") && url.contains("/") ){ //some times  start of url by {//} but some times url start with {/} 
					  for (int i2 = -1; (i2 = url.indexOf("//", i2 + 1)) != -1; ) {
//						    System.out.println(i2);
	//					    level[0]=i2;
						    level.add(i2);
						    int j=1;
						    for (int i4 = i2+1; (i4 = url.indexOf("/", i4 + 1)) != -1; ) {
	//						    	level[j]=i4;
							        level.add(i4);
							    	j++;
							} 
						} 
					}else if(!url.contains("//") && url.contains("/")) {
						int j=1;
						level.add(0);
					    for (int i4 = 1; (i4 = url.indexOf("/", i4 + 1)) != -1; ) {
//						    	level[j]=i4;
						        level.add(i4);
						    	j++;
						}
					}
				  else if(!url.contains("//") && !url.contains("/")){
					 continue;
				 }
//				  ----------------------------------     Check word in url     ----------------------------
				double[] x=new double[level.size()+1];
				for (int i7 = 1; i7 <= level.size(); i7++) {
					ArrayList NumberLevelOccur = new ArrayList();
					int Start=0;
					int End=0;
					if(i7==level.size()){
						Start=(int)level.get(i7-1)+1;
						End=url.length();
					}else{
						Start=(int)level.get(i7-1)+1;
						End=(int)level.get(i7);	
					}

					String UrlLevel=url.substring(Start,End).toLowerCase(); //specify each level 
					for (int i3 = -1; (i3 = UrlLevel.indexOf(word.toLowerCase(), i3 + 1)) != -1; ) { // Check the occurrence word in each level of URL 
							   if(i3!=-1){
								   NumberLevelOccur.add(i3);
							    }					
					 }
					int NLOCC=0;
					NLOCC=(int)NumberLevelOccur.size();
					int k2=((int)Math.pow(i7,2));    
					x[i7]=(double)NLOCC/(double)k2;
			    }
				double SumWeightAllLevelInOneUrl=0;
				for (int i8 = 0; i8 < x.length; i8++) {
					SumWeightAllLevelInOneUrl+=x[i8];
				}
				WordWeightInOneUrl[NumberUrl]=SumWeightAllLevelInOneUrl;
				NumberUrl++;
 			}
			double SumWeightOneWordInAllUrl=0;
 			for (int i9 = 0; i9 < WordWeightInOneUrl.length; i9++) {
 				SumWeightOneWordInAllUrl+=WordWeightInOneUrl[i9];
			}
 			WordWeightInAllUrl[Numberword]=SumWeightOneWordInAllUrl;
 			Numberword++;
 		}
		  System.err.println();
//		  ---------------------------------sort [iteration][index]----------------------------
			double[] data1=WordWeightInAllUrl;
			int size = data1.length;
			double[][] data2 = new double[2][size];
			for (int i = 0; i < data1.length; i++) {
				data2[0][i]= data1[i];
				data2[1][i]= i;
			}
			int n2=data2[0].length;
			FindTopValuesSelectionSortImpl f=new FindTopValuesSelectionSortImpl();
			int[] result=f.findTopNValues2(data2, n2);
			/*for (int i = 0; i < AllWord.size(); i++) {
				System.out.println(AllWord.get(result[i]));
			}*/
			String CondidKey=AllWord.get(result[0]);
			System.out.println(CondidKey);
//			---------------------------------------Use GoogleSearch-----------------------------
			ArrayList<String> UrlListOfGoogleSearch = new ArrayList<String>();
			GoogleSearch gs=new GoogleSearch();
			int number=5;
			UrlListOfGoogleSearch=gs.MySearch(CondidKey , number);
			for (int i = 0; i < UrlListOfGoogleSearch.size(); i++) {
				System.out.println(UrlListOfGoogleSearch.get(i));

			}
			
//-------------------------------------------------------------------------------------------------------		  
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
	  
}
