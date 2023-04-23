package Weka_Text2arrf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

import ParsingWithSoup_xxx.contentWithTag;
import weka.clusterers.Cobweb.CNode;

public class test3_TLD_2_Number {

	public static void main(String[] args) {
       //____________________  This program change the TLD into number   _____________________________________
		
		
		String fileName = "D:\\AZ\\Dr_ghaemi\\test\\combine_legith_phish.txt";
		ArrayList<String> MyString = new ArrayList<String>();
		try {

			// Create object of FileReader
			FileReader inputFile = new FileReader(fileName);

			// Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			// Variable to hold the one line data
			String line;

			// add each row in to MyString cell
			while ((line = bufferReader.readLine()) != null) {
				MyString.add(line);
			}
		} catch (Exception e) {
			System.out.println("Error while reading file line by line:" + e.getMessage());
		}
		ArrayList<String> My_TLD = new ArrayList<String>();
		for (int i = 0; i < MyString.size(); i++) {
			String eachSample = MyString.get(i);
			int start = ordinalIndexOf(eachSample, ",", 24);
			int end = ordinalIndexOf(eachSample, ",", 25);
			String TLD = eachSample.substring(start + 1, end);
			My_TLD.add(TLD);

		}
		ArrayList<String> My_TLD2 = new ArrayList<String>();
		My_TLD2 = CheckWord(My_TLD);
		
		for (int i = 0; i < MyString.size(); i++) {
			String eachSample = MyString.get(i);
			int start = ordinalIndexOf(eachSample, ",", 24);
			int end = ordinalIndexOf(eachSample, ",", 25);
			String TLD = eachSample.substring(start + 2, end);
			for (int j = 0; j < My_TLD2.size(); j++) {
				String URL="";
				if(TLD.equals(My_TLD2.get(j))){
					int num=countOccurrences(",",eachSample);
					if(num>76){
						URL = eachSample.replace(".".concat(TLD),String.valueOf(j));

					}else{
						URL = eachSample.replace(".".concat(TLD),",".concat(String.valueOf(j)));

					}
					System.out.println(URL);
					break;
				}

			}
		}
		
		
	}
	public static int countOccurrences(String find, String string) {
		int count = 0;
		try {
			int indexOf = 0;

			while (indexOf > -1) {
				indexOf = string.indexOf(find, indexOf + 1);
				if (indexOf > -1)
					count++;
			}

			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return count;
		}
	}

	public static int ordinalIndexOf(String str, String substr, int n) {
		int pos = str.indexOf(substr);
		while (--n > 0 && pos != -1)
			pos = str.indexOf(substr, pos + 1);
		return pos;
	}
	public static ArrayList<String> CheckWord(List<String> AllWord) {
		ArrayList<String> Word_filter = new ArrayList<String>();
		// ----------------------------convert array list into string
		// ------------
		String listString = "";
		for (String s : AllWord) {
			s = s.toLowerCase();
			listString += s + " ";
		}

		// list--------------
		Word_filter.clear();
		Word_filter = getWords(deDup(listString));

		return Word_filter;
	}
	
	public static String deDup(String s) {
		return new LinkedHashSet<String>(Arrays.asList(s.split(" "))).toString();
	}
	public static ArrayList<String> getWords(String text) {
		ArrayList<String> words = new ArrayList<String>();
		BreakIterator breakIterator = BreakIterator.getWordInstance();
		breakIterator.setText(text);
		int lastIndex = breakIterator.first();
		while (BreakIterator.DONE != lastIndex) {
			int firstIndex = lastIndex;
			lastIndex = breakIterator.next();
			if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
				words.add(text.substring(firstIndex, lastIndex));
			}
		}

		return words;
	}
}
