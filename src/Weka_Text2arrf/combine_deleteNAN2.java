package Weka_Text2arrf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class combine_deleteNAN2 {

	public static void main(String[] args) {

		String fileName_Phish = "D:\\AZ\\Dr_ghaemi\\test\\phish2.txt";
		String fileName_Legith = "D:\\AZ\\Dr_ghaemi\\test\\legit2.txt";
		ArrayList<String> phish = new ArrayList<String>();
		ArrayList<String> phish_without_NAN = new ArrayList<String>();
		
		ArrayList<String> legith = new ArrayList<String>();
		ArrayList<String> legith_without_NAN = new ArrayList<String>();
		try {

			// Create object of FileReader
			FileReader inputFile = new FileReader(fileName_Phish);

			// Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			// Variable to hold the one line data
			String line;

			// add each row in to MyString cell
			while ((line = bufferReader.readLine()) != null) {
				phish.add(line);
			}

			// ------------------------------------------------------------------

			// Create object of FileReader
			FileReader inputFile2 = new FileReader(fileName_Legith);

			// Instantiate the BufferedReader Class
			BufferedReader bufferReader2 = new BufferedReader(inputFile2);

			// Variable to hold the one line data
			String line2;

			// add each row in to MyString cell
			while ((line2 = bufferReader2.readLine()) != null) {
				legith.add(line2);
			}

		} catch (Exception e) {
			System.out.println("Error while reading file line by line:" + e.getMessage());
		}


		for (int i = 0; i < phish.size(); i++) {
			if (phish.get(i).contains("NaN")) {
				continue;
			}else{
				phish_without_NAN.add(phish.get(i));
			}
		}
		for (int i = 0; i < legith.size(); i++) {
			if (legith.get(i).contains("NaN")) {
				continue;
			}else{
				legith_without_NAN.add(legith.get(i));
			}
		}

		int max = phish_without_NAN.size();
		if (max < legith_without_NAN.size()) {
			max = legith.size();
		}
		
		for (int i = 0; i < max; i++) {
			String input = ""; 
			String input2 = ""; 
			if(i < legith_without_NAN.size()){
				input = new String(legith_without_NAN.get(i));
				input = input.replace('%', ';');
				input = input.replace('\'', ';');
				System.out.println(input);
			}
			if(i < phish_without_NAN.size()){
				input2 = new String(phish_without_NAN.get(i));
				input2 = input2.replace('%', ';');
				input2 = input2.replace('\'', ';');
				System.out.println(input2);
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
