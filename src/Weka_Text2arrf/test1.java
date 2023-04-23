package Weka_Text2arrf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class test1 {

	public static void main(String[] args) {

		String fileName="C:\\Users\\Emad\\workspace\\x3\\DataSet\\p.txt";
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
		 }catch(Exception e){
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }
		 for (int i = 0; i < MyString.size(); i++) {
			 int num = countOccurrences(",", MyString.get(i));
			 String input=new String(MyString.get(i));
			 input=input.replace('%', ';');
			 input=input.replace('\'', ';');
			 /*if (input.contains("%")) {
				 	continue;
				 	
				 	
			 	}*/
			 //-----------------------------------------------------------
				if (num == 44) {
					if (input.indexOf("-  ") == -1) {
						System.out.println(input);
					} else {
						String s = input.substring(input.indexOf("  "), input.length());
						System.out.println(s);
					}
				} else {
					String s1 = input.substring(0, input.lastIndexOf(","));
					s1 = s1.substring(0, s1.lastIndexOf(","));
					String s2 = s1.concat(",-1");
					System.out.println(s2);
					continue;
				} 
			
		}
	}
	public static int countOccurrences(String find, String string) {
		int count = 0;
		int indexOf = 0;

		while (indexOf > -1) {
			indexOf = string.indexOf(find, indexOf + 1);
			if (indexOf > -1)
				count++;
		}

		return count;
	}

}
