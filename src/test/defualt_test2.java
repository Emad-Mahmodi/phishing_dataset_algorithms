package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import JFreeChart.LineChart_AWT;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class defualt_test2{


	public static void main(String[] args) {
	       //Name of the fileh 
	       String fileName="evaluation.txt";
	  		  ArrayList<String> MyString = new ArrayList<String>();
	  		  
	  		ArrayList<String> FP = new ArrayList<String>();
	  		ArrayList<String> FN = new ArrayList<String>();
	  		ArrayList<String> TP = new ArrayList<String>();
	  		ArrayList<String> TN = new ArrayList<String>();
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
	          for (int i = 0; i < MyString.size(); i++) {
	        	  int virgol=MyString.get(i).lastIndexOf(",");
	        	  String s="";
	        	  s=MyString.get(i).substring(virgol-1, MyString.get(i).length()).toString();
//	        	  System.out.println(s);
	        	  if(s.matches("0,0")){
	        		  TP.add("tp");
	        	  }else if(s.matches("0,1") || s.matches("0,4") ){
	        		  FP.add("fp");
	        	  }else if(s.matches("1,0")|| s.matches("1,4")){
	        		  FN.add("fn");
	        	  }else if(s.matches("1,1")){
	        		  TN.add("tn");
	        	  }else if(s.matches("0,3")){
	        		  ParseLegitmate.add("N");
	        	  }else if(s.matches("1,3")){
	        		  ParsePhish.add("N");
	        	  }
	        	  /*else if(s.matches("0,4") || s.matches("1,4")){
	        		  Title.add("T");
	        	  }*/
	        	  
			  }
	          int a=TN.size();
	          int b=FP.size();
	          int c=FN.size();
	          int d=TP.size();
	          
	          float TPR=(float)a/(a+c);
	          float TNR=(float)d/(b+d);
	          float Accuracy=(float)(a+d)/(a+b+c+d);
	          float x1 = (d*a)+(b*c);
	          int z1 = (b+d);int z2 = (d+c); int z3=(a+b);int z4=(a+c);
	          int w1 = z1*z2; int w2=z3*z4; double w3=Math.sqrt(w1);double w4=Math.sqrt(w2);
	          double w5 = w3*w4;
	          double x2 = (z1*z2*z3*z4);
	          float MCC = (float) ((float)(x1)/w5);
	          System.out.println("TPR : "+TPR*100);
	          System.out.println("TNR : "+TNR*100);
	          System.out.println("Accuracy : "+Accuracy*100);
	          System.out.println(MCC);
	          System.out.println("-----------------------");
	          System.out.println("TN : "+TN.size());
	          System.out.println("TP : "+TP.size());
	          System.out.println("FP : "+FP.size());
	          System.out.println("FN : "+FN.size());
	          System.out.println("TiTle not found : "+Title.size());
	          System.out.println("parse Error : "+ParsePhish.size()+"  and   "+ParseLegitmate.size());




		
		
	}
	

}
