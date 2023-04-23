package Weka_Text2arrf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class test2 {

	public static void main(String[] args) {

		String fileName="D:\\AZ\\Dr_ghaemi\\test\\phish2.txt";
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
		 int i1=0;
		 int i2=0;
		 int i3=0;
		 for (int i = 0; i < MyString.size(); i++) {
			 if (MyString.get(i).contains("NaN")) {
					int num = countOccurrences(",", MyString.get(i));
					String input = new String(MyString.get(i));
					input = input.replace("NaN", "0");
					input = input.replace('%', ';');
					input = input.replace('\'', ';');
					i1++;
					//System.out.println(input);
			}else if (MyString.get(i).contains("NaN")) {
				i2++; 
				continue;
			}else{
					int num = countOccurrences(",", MyString.get(i));
					String input = new String(MyString.get(i));
					input = input.replace('%', ';');
					input = input.replace('\'', ';');
					//System.out.println(input);
					i3++;
			}
		}
		 System.out.println(i1+"  "+i2+"  "+i3);
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
