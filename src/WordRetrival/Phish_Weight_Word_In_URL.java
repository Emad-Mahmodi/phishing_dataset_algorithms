package WordRetrival;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import PhishWho.FindTopValuesSelectionSortImpl;

public class Phish_Weight_Word_In_URL {


	public static String WeightWordInURL(List<String> AllURL , ArrayList<String> AllWord ,List<List<Integer>> ArrayLevel , int n ) {
//		  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++   compression   +++++++++++++++++++++++++++++++++++++  
		  double[] WordWeightInAllUrl = new double[AllWord.size()];
		  int Numberword=0;
		  ArrayList<String> resultSorted=new ArrayList<String>();

			
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
				  return "URL Weong In Level's";
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
//		 			myx.add(word);
//		 			myx.add(Double.toString(SumWeightwodInEachUrlLevel));
//		 	 		System.out.println(myx);//word+weight	  
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
			return resultSorted.get(0);

	}

}
