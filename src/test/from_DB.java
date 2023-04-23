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
import LPD.LPD;
import PhishWho.FindTopValuesSelectionSortImpl;
import ReadFromFolder.FileOfFolder;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class from_DB {
	public static ArrayList<String> TPR2=new ArrayList<String>();
	public static ArrayList<String> TNR2=new ArrayList<String>();
	public static ArrayList<String> Accuracy2=new ArrayList<String>();
	public static ArrayList<String> FP2=new ArrayList<String>();
	public static ArrayList<String> TP2=new ArrayList<String>();
	
	public static int main() {
	       //Name of the fileh 
	       String fileName="evaluation.txt";
	  		  ArrayList<String> MyString = new ArrayList<String>();
	  		  
	  		/*ArrayList<String> FP = new ArrayList<String>();
	  		ArrayList<String> FN = new ArrayList<String>();
	  		ArrayList<String> TP = new ArrayList<String>();
	  		ArrayList<String> TN = new ArrayList<String>();
	  		ArrayList<String> ParseLegitmate = new ArrayList<String>();
	  		ArrayList<String> ParsePhish = new ArrayList<String>();
	  		ArrayList<String> Title = new ArrayList<String>();*/
	  		int a,b,c,d;
	  		float TPR;
	  		float TNR;
	  		float Accuracy;
	  		
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
	       



			
	       int group=15; 
	       int start,end;

	          for (int j = 0; j < group; j++) {
	   	        start=(MyString.size()/group)*j;
	   	        end=(MyString.size()/group)*(j+1);
//	   	        int w=j;
	   	        a=0;b=0;c=0;d=0;TPR=0;TNR=0;Accuracy=0;
	   	        ArrayList<String> FP = new ArrayList<String>();
		  		ArrayList<String> FN = new ArrayList<String>();
		  		ArrayList<String> TP = new ArrayList<String>();
		  		ArrayList<String> TN = new ArrayList<String>();
		  		ArrayList<String> ParseLegitmate = new ArrayList<String>();
		  		ArrayList<String> ParsePhish = new ArrayList<String>();
		  		ArrayList<String> Title = new ArrayList<String>();
	   	        
	   	        
//				for (int i = 0; i <= end; i++) {
				for (int i = start; i <= end; i++) {
					String mys=MyString.get(i);
					int virgol = MyString.get(i).lastIndexOf(",");
					String s = "";
					String s2=MyString.get(i);
					s = MyString.get(i).substring(virgol - 1, MyString.get(i).length()).toString();
					//	        	  System.out.println(s);
					
					
					if (s.matches("0,0")) {
						TP.add("tp");
					} else if (s.matches("0,1")) {
						FP.add("fp");
					} else if (s.matches("1,0")) {
						FN.add("fn");
					} else if (s.matches("1,1")) {
						TN.add("tn");
					} else if (s.matches("0,3")) {
						ParseLegitmate.add("N");
					} else if (s.matches("1,3")) {
						ParsePhish.add("N");
					} else if (s.matches("0,4") || s.matches("1,4")) {
						Title.add("T");
					}
				}
				 a = TN.size();
				 b = FP.size();
				if(String.valueOf(FP)!="NaN"){
					FP2.add(String.valueOf(FP.size()));
					}else{
					FP2.add(String.valueOf("0"));
					}
				
				 c = FN.size();
				 d = TP.size();
				if(String.valueOf(TP)!="NaN"){
					TP2.add(String.valueOf(TP.size()));
					}else{
					TP2.add(String.valueOf("0"));
					}
				
				 TPR = (float) a / (a + c);
				if(String.valueOf(TPR)!="NaN"){
				TPR2.add(String.valueOf(TPR));
				}else{
				TPR2.add(String.valueOf("0"));
				}
				
				 TNR =	(float) d / (b + d);
				if(String.valueOf(TNR)!="NaN"){
					TNR2.add(String.valueOf(TNR));
					}else{
					TNR2.add(String.valueOf("0"));
					}
				
				Accuracy = (float) (a + d) / (a + b + c + d);
				if(String.valueOf(Accuracy)!="NaN"){
					Accuracy2.add(String.valueOf(Accuracy));
					}else{
					Accuracy2.add(String.valueOf("0"));
					}
				
				System.out.println("TPR : " + TPR * 100);
				System.out.println("TNR : " + TNR * 100);
				System.out.println("Accuracy : " + Accuracy * 100);
				System.out.println("");
				System.out.println("TN : " + TN.size());
				System.out.println("TP : " + TP.size());
				System.out.println("FP : " + FP.size());
				System.out.println("FN : " + FN.size());
				System.out.println("TiTle not found : " + Title.size());
				System.out.println("parse Error : " + ParsePhish.size() + "  and   " + ParseLegitmate.size());
				System.out.println("-----------------------");
				
				TN.clear();
				FP.clear();
				FN.clear();
				TN.clear();
				ParsePhish.clear();
				Title.clear();
	}
	          
			//	          ------------------------------------------CHRAT CREAT-------------------------------------
	          /*LineChart_AWT chart = new LineChart_AWT(
	        	      "URL Phish" ,
	        	      "Numer of Detection in period of time");

	         chart.pack( );
	         RefineryUtilities.centerFrameOnScreen( chart );
	         chart.setVisible( true );*/


		
		return 1;
	}
	

}
