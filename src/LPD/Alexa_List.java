package LPD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
public class Alexa_List {

	public static ArrayList<String> TopLis() {
	       String fileName="AlexaTopSite_New_B_localDataSet.txt";
		   //String fileName="phishtankURL.txt";
	  		  ArrayList<String> MyString = new ArrayList<String>();
	  		  
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
	          
	          //Close the buffer reader
	          bufferReader.close();
	       }catch(Exception e){
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }
	      return MyString;
	}

}
