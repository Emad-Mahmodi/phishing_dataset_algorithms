package Weka_Text2arrf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class combine_deleteNAN {

	public static void main(String[] args) {

		String fileName_Phish = "D:\\AZ\\Dr_ghaemi\\test\\phish2.txt";
		String fileName_Legith = "D:\\AZ\\Dr_ghaemi\\test\\legit2.txt";
		ArrayList<String> phish = new ArrayList<String>();
		ArrayList<String> legith = new ArrayList<String>();
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
		int i1 = 0;
		int phish_number = 0;
		int legith_number = 0;
		int max = phish.size();
		if (max < legith.size()) {
			max = legith.size();
		}
		max = phish.size()+legith.size() ;
		for (int i = 0; i < max; i++) {

			if (i % 2 == 0) {
				if (i < phish.size()) {
					if (phish.get(phish_number).contains("NaN")) {
						phish_number++;
						continue;
					} else {

						String input = new String(phish.get(phish_number));
						input = input.replace('%', ';');
						input = input.replace('\'', ';');
						System.out.println(i + " - " + input);
						phish_number++;
					}
				} else {
					if (legith.get(legith_number).contains("NaN")) {
						legith_number++;
						continue;
					} else {
						String input = new String(legith.get(legith_number));
						input = input.replace('%', ';');
						input = input.replace('\'', ';');
						System.out.println(i + " - " + input);
						legith_number++;
					}
				}
			} else {
				if (i < legith.size()) {
					if (legith.get(legith_number).contains("NaN")) {
						legith_number++;
						continue;
					} else {
						String input = new String(legith.get(legith_number));
						input = input.replace('%', ';');
						input = input.replace('\'', ';');
						System.out.println(i + " - " + input);
						legith_number++;
					}
				} else {
					if (phish.get(phish_number).contains("NaN")) {
						phish_number++;
						continue;
					} else {

						String input = new String(phish.get(phish_number));
						input = input.replace('%', ';');
						input = input.replace('\'', ';');
						System.out.println(i + " - " + input);
						phish_number++;
					}
				}
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
