package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import JFreeChart.LineChart_AWT;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Time_test{


	public static void main(String[] args) {
	       //Name of the fileh 
//	       String fileName="C:\\Users\\Emad\\workspace\\x2\\ProposeResaule\\LPD_result4.txt";
	       String fileName="C:\\Users\\Emad\\workspace\\x2\\ProposeResaule2\\LPD_result4.txt";
//	       String fileName="C:\\Users\\Emad\\workspace\\x2\\PhishWhoResult\\LPD_result4.txt";

	       ArrayList<String> MyString = new ArrayList<String>();
	  		  

	  		ArrayList<String> ParseLegitmate = new ArrayList<String>();
	  		ArrayList<String> ParsePhish = new ArrayList<String>();
	  		ArrayList<String> Title = new ArrayList<String>();

	       try{

	          //Create object of FileReader
	          FileReader inputFile = new FileReader(fileName);

	          //Instantiate the BufferedReader Class
	          BufferedReader bufferReader = new BufferedReader(inputFile);

	          //Variable to hold the one line data
	          String line;


	          // add each row in to MyString cell 
	          while ((line = bufferReader.readLine()) != null)   {
	            MyString.add(line);
	          }
	          for (int i2 =MyString.size()-1; i2 >= 0; i2--) {
				if(MyString.get(i2).matches("")){
					MyString.remove(i2);
				}
			}
	          
	          
	          //Close the buffer reader
	          bufferReader.close();
	       }catch(Exception e){
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }
	       int[] time = new int[MyString.size()];
	       int[] AllWord_Ngram = new int[MyString.size()];//All_freq_SLD
	       int[] All_URL = new int[MyString.size()];// All_SLD_ok
	       int[] AllWord = new int[MyString.size()];//size TFIDF
	       int max_time = 0 ; 
	       int max_AllWord_Ngram = 0 ;
	       int max_All_URL = 0 ;
	       int max_AllWord = 0 ;
	       
	          for (int i = 0; i < MyString.size(); i++) {
	        	  String ss = MyString.get(i);
	        	  int virgol=MyString.get(i).lastIndexOf(",")+2;
	        	  String s5="";
	        	  String s2="";
	        	  String s3="";
	        	  String s4="";
  
	        	  s2 = MyString.get(i).substring(ss.indexOf(",")+1 , ordinalIndexOf(ss, ",", 2));
	        	  AllWord_Ngram[i] = Integer.valueOf(s2);
	        	  
	        	  s3 = MyString.get(i).substring(ordinalIndexOf(ss, ",", 2)+1 , ordinalIndexOf(ss, ",", 3));
	        	  All_URL[i] = Integer.valueOf(s3);
	        	  
	        	  s4 = MyString.get(i).substring(ordinalIndexOf(ss, ",", 3)+1 , ordinalIndexOf(ss, ",", 4));
	        	  AllWord[i] = Integer.valueOf(s4);
	        	  
	        	  s5=MyString.get(i).substring(virgol-1, MyString.get(i).length()).toString();
	        	  time[i] = Integer.valueOf(s5);

	        	  //System.out.println(AllWord[i]);

			  }
	          for (int i = 0; i < AllWord.length; i++) {
	        	  if(time[i]>max_time) {max_time = time[i];}
	        	  if(AllWord_Ngram[i]>max_AllWord_Ngram) {max_AllWord_Ngram = AllWord_Ngram[i];}
	        	  if(All_URL[i]>max_All_URL) {max_All_URL = All_URL[i];}
	        	  if(AllWord[i]>max_AllWord) {max_AllWord = AllWord[i];}
			}
	          System.out.println("max Time : " + max_time);
	          System.out.println("max max_AllWord_Ngram : " + max_AllWord_Ngram);
	          System.out.println("max max_All_URL : " + max_All_URL);
	          System.out.println("max max_AllWord : " + max_AllWord);
	          System.out.println("_______________________________________________________________");
	          System.out.println();
	          int sum = 0 ;
	          int sum2 = 0 ;
	          int sum3 = 0 ;
	          int sum4 = 0 ;
	          for (int i = 0; i < time.length; i++) {
				sum = sum + time[i];
				sum2 = sum2 + AllWord_Ngram[i];
				sum3 = sum3 + All_URL[i];
				sum4 = sum4 + AllWord[i];
			  }
	          
	          double avg = (double)(sum/time.length);
	          double avg2 = (double)(sum2/time.length);
	          double avg3 = (double)(sum3/time.length);
	          double avg4 = (double)(sum4/time.length);
	          System.out.println("avrage Time : "+avg);
	          System.out.println("avrage AllWord_Ngram : "+avg2);
	          System.out.println("avrage All_URL : "+avg3);
	          System.out.println("avrage AllWord : "+avg4);
	}
	
	public static int ordinalIndexOf(String str, String substr, int n) {
	    int pos = str.indexOf(substr);
	    while (--n > 0 && pos != -1)
	        pos = str.indexOf(substr, pos + 1);
	    return pos;
	}

}
