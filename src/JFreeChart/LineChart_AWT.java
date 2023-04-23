package JFreeChart;

import test.*;
import org.jfree.chart.ChartPanel;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends ApplicationFrame
{
	public static ArrayList<String> ChartTPR2=new ArrayList<String>();
	public static ArrayList<String> ChartTNR2=new ArrayList<String>();
	public static ArrayList<String> Accuracy2=new ArrayList<String>();
	public static ArrayList<String> FP2=new ArrayList<String>();
	public static ArrayList<String> TP2=new ArrayList<String>();
	
   public LineChart_AWT( String applicationTitle , String chartTitle )
   {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "sort by Id in DataBase (perhaps Time)","Rank",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 1200 , 500 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( )
   {
      
	  new2 nw=new2();//you must use new data LPD in evaluation
	  nw.main();
	  ChartTPR2=new2.TPR2;
	  ChartTNR2=new2.TNR2;
	  Accuracy2=new2.Accuracy2;

	  
//	  from_DB nw=new from_DB();//you must use old data LPD evaluation
	  /*nw.main();
	  ChartTPR2=from_DB.TPR2;
	  ChartTNR2=from_DB.TNR2;
	  Accuracy2=from_DB.Accuracy2;
	  FP2=from_DB.FP2;
	  TP2=from_DB.TP2;*/
	  
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

      for (int i = 0; i < ChartTPR2.size(); i++) {
    	  float z=Float.parseFloat(ChartTPR2.get(i));
    	  dataset.addValue( z , "TPR" , String.valueOf(i) );
	}
      
      for (int i = 0; i < ChartTNR2.size(); i++) {
    	  float z=Float.parseFloat(ChartTNR2.get(i));
    	  dataset.addValue( z , "TNR" , String.valueOf(i) );
	}
      
      for (int i = 0; i < Accuracy2.size(); i++) {
    	  float z=Float.parseFloat(Accuracy2.get(i));
    	  dataset.addValue( z , "Accuracy" , String.valueOf(i) );
	}
      
/*      for (int i = 0; i < FP2.size(); i++) {
    	  float z=Float.parseFloat(FP2.get(i));
    	  System.out.println(z);
    	  dataset.addValue( z , "FP" , String.valueOf(i) );
	}
      
      for (int i = 0; i < TP2.size(); i++) {
    	  float z=Float.parseFloat(TP2.get(i));
    	  System.out.println(z);
    	  dataset.addValue( z , "TP" , String.valueOf(i) );
	}*/
      
/*      dataset.addValue( 15 , "schools" , "1970" );
      dataset.addValue( 30 , "schools" , "1980" );
      dataset.addValue( 60 , "schools" ,  "1990" );
      dataset.addValue( 120 , "schools" , "2000" );
      dataset.addValue( 240 , "schools" , "2010" );
      dataset.addValue( 300 , "schools" , "2014" );
      dataset.addValue( 18 , "aa" , "1970" );
      dataset.addValue( 38 , "aa" , "1980" );
      dataset.addValue( 50 , "aa" ,  "1990" );
      dataset.addValue( 100 , "aa" , "2000" );
      dataset.addValue( 260 , "aa" , "2010" );
      dataset.addValue( 310 , "aa" , "2014" );*/
      return dataset;
   }
   public static void main( String[ ] args ) 
   {
//		  nw.main();
//		  ChartTPR2=new2.TPR2;

      LineChart_AWT chart = new LineChart_AWT(
      "URL Phish" ,
      "Detection By Title+URL");//Title+URL

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }

private static SortTest SortTest() {
	// TODO Auto-generated method stub
	return null;
}

private static new2 new2() {
	// TODO Auto-generated method stub
	return null;
}
private static from_DB from_DB(){
	// TODO Auto-generated method stub
	return null;
}
}