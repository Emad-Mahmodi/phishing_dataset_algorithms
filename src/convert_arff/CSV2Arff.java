package convert_arff;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
 
import java.io.File;
 
public class CSV2Arff {
  /**
   * takes 2 arguments:
   * - CSV input file
   * - ARFF output file
   */
  public static void main(String[] args) throws Exception {
//    if (args.length != 2) {
//      System.out.println("\nUsage: CSV2Arff <input.csv> <output.arff>\n");
//      System.exit(1);
//    }
    
	 String s= "D:\\AZ\\Dr_ghaemi\\complex 2.csv";
    // load CSV
    CSVLoader loader = new CSVLoader();
    loader.setSource(new File(s));
    Instances data = loader.getDataSet();
 
    // save ARFF
    ArffSaver saver = new ArffSaver();
    saver.setInstances(data);
    saver.setFile(new File(args[1]));
    saver.setDestination(new File(args[1]));
    saver.writeBatch();
  }
}