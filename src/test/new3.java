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

public class new3 extends ApplicationFrame{
	public static ArrayList<String> TPR2=new ArrayList<String>();
	  public new3( String applicationTitle , String chartTitle )
	   {
	      super(applicationTitle);
	      JFreeChart lineChart = ChartFactory.createLineChart(
	         chartTitle,
	         "Years","Number of Schools",
	         createDataset(),
	         PlotOrientation.VERTICAL,
	         true,true,false);
	         
	      ChartPanel chartPanel = new ChartPanel( lineChart );
	      chartPanel.setPreferredSize( new java.awt.Dimension( 700 , 500 ) );
	      setContentPane( chartPanel );
	   }
	   private DefaultCategoryDataset createDataset( )
	   {
	      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
	      for (int i = 0; i < TPR2.size(); i++) {
	    	  int z=Integer.parseInt(TPR2.get(i));
	    	  dataset.addValue( z , "TPR" , String.valueOf(i) );
		}
	      
	      /*dataset.addValue( 15 , "schools" , "1970" );
	      dataset.addValue( 30 , "schools" , "1980" );
	      dataset.addValue( 60 , "schools" ,  "1990" );
	      dataset.addValue( 120 , "schools" , "2000" );
	      dataset.addValue( 240 , "schools" , "2010" );
	      dataset.addValue( 300 , "schools" , "2014" );*/
	      /*dataset.addValue( 18 , "aa" , "1970" );
	      dataset.addValue( 38 , "aa" , "1980" );
	      dataset.addValue( 50 , "aa" ,  "1990" );
	      dataset.addValue( 100 , "aa" , "2000" );
	      dataset.addValue( 260 , "aa" , "2010" );
	      dataset.addValue( 310 , "aa" , "2014" );*/
	      return dataset;
	   }
	public static int main() {
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
	       
//		  ---------------------------------sort [iteration][index]----------------------------
	       int[] NumberOfPage = new int[MyString.size()];	
	       for (int i = 0; i < MyString.size(); i++) {//MyString.size()
	    	   String num=MyString.get(i);
	    	   
	    	    	int StartIndex=num.indexOf("dlweb_phish\\");
	    	    	if (StartIndex!=-1) {
					num=num.substring(StartIndex+12, num.length());
					num=num.substring(0,num.indexOf("\\"));
					NumberOfPage[i]=Integer.valueOf(num);
					}else {
						num=num.substring(num.indexOf("dlweb_legit\\")+12, num.length());
						num=num.substring(0,num.indexOf("\\"));		
						NumberOfPage[i]=Integer.valueOf(num);
					}
			}
	
//-----------------------------------------------------------
    	   int[] data1=NumberOfPage;
			int size = data1.length;
			int[][] data2 = new int[2][size];
			for (int i19 = 0; i19 < data1.length; i19++) {
				data2[0][i19]= data1[i19];
				data2[1][i19]= i19;
			}
//			========================================================================

//		=========================================================================
			int temp;
			for(int i=1;i<data2[0].length;i++)
				for(int j=0;j<data2[0].length-i;j++){
					if(data2[0][j]>data2[0][j+1]){
						temp=data2[0][j+1];
						data2[0][j+1]=data2[0][j];
						data2[0][j]=temp;
						temp=data2[1][j+1];
						data2[1][j+1]=data2[1][j];
						data2[1][j]=temp;
					}
				}
			
			System.out.println(data2);

//			========================================================================
			
	       int group=100; 
	       int start,end;

	          for (int j = 0; j < group; j++) {
	   	        start=(MyString.size()/group)*j;
	   	        end=(MyString.size()/group)*(j+1);



	   	        int w=0;
				for (int i = start; i <= end; i++) {
					w=data2[1][i];
					int virgol = MyString.get(w).lastIndexOf(",");
					String s = "";
					String s2=MyString.get(w);
					s = MyString.get(w).substring(virgol - 1, MyString.get(w).length()).toString();
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
				int a = TN.size();
				int b = FP.size();
				int c = FN.size();
				int d = TP.size();
				float TPR = (float) a / (a + c);
				if(String.valueOf(TPR)!="NaN"){
				TPR2.add(String.valueOf(TPR));
				}else{
				TPR2.add(String.valueOf("0"));
				}
				
				float TNR = (float) d / (b + d);
				float Accuracy = (float) (a + d) / (a + b + c + d);
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
