package LPD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
public class AlexaFromFile {

	public static void main(String[] args) {
	       String fileName="C:\\Users\\Emad\\Desktop\\New folder (2)\\3\\LPD_result2.txt";
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

	          for(int i=0;i<MyString.size();i++){
	        	  String s=MyString.get(i);
//	        	  int x1=StringUtils.ordinalIndexOf(s, "<td>", 2);
//	        	  int x2=StringUtils.ordinalIndexOf(s, "</td>", 2);
//	        	  String s2=s.substring(x1+4,x2);
	        	  String s2=s.substring(s.indexOf("- ")+2,s.indexOf(","));
	        	  System.out.println(s2);

	          }

	          
	          
	          //Close the buffer reader
	          bufferReader.close();
	       }catch(Exception e){
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }
	}

}
