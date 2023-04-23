package DrSadoghi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class parseFiletoOnlineLearning {

	public static void main(String[] args) throws IOException {
		ArrayList<String> ContetntEachLine = new ArrayList<String>();
		ArrayList<String> Feature = new ArrayList<String>();
		ContetntEachLine =  TopLis("D:\\Arshad\\Day0.svm");
		Feature = TopLis("D:\\Arshad\\Feature.txt");
		creatDataset(Feature ,ContetntEachLine ,"D:\\Arshad\\dataset.txt");
		System.out.println();

	}
	public static void creatDataset(ArrayList<String> feature ,
								    ArrayList<String> eachrow ,
								    String path) throws IOException {
		
		try {
			String label;
			String myeach;
			String myeach2;
			String myfeature;
			String test;
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));

			for (int i = 0; i < eachrow.size(); i++) {
				label="";
				myeach= eachrow.get(i);
				test="";
				StringBuffer str = new StringBuffer();
				label= eachrow.get(i).substring(0, myeach.indexOf("1")+1);
				str.append(label.concat(","));
				myeach = myeach.substring( myeach.indexOf("1")+2,myeach.length());
				for (int j = 1; j < 271 ; j++) {
					myeach2 = myeach.substring(0,myeach.indexOf(" ")+1); 
					myfeature = String.valueOf(j);
					myfeature = myfeature.concat(":");
					
					if (myeach2.contains(myfeature)) {
						int x1 = myeach2.indexOf(myfeature) + myfeature.length() ;
						int x2 = myeach2.indexOf(" ");//StringUtils.ordinalIndexOf(myeach, " ", j+2);
						test = myeach2.substring(x1, x2).concat(",");
						myeach = myeach.substring(x2+1 , myeach.length());
						myeach2 = myeach;
						str.append(test);
					}else{
						test = "0,";
						str.append(test);
					}
					System.out.print(test);
				}
				System.out.println();
				out.println(str);
			}
			out.close();

		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	
	}
	
	public static ArrayList<String> TopLis(String input) {
	       String fileName=input;
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
