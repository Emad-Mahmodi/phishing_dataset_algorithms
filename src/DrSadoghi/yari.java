package DrSadoghi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class yari {

	public static void main(String[] args) {
		ArrayList<String> PathFile = new ArrayList<String>();
		PathFile = FileofFolser();
		String MyString;
		try {

			for (int i = 0; i < PathFile.size(); i++) {
				MyString = "";
				// Create object of FileReader
				String fileName = PathFile.get(i);
				FileReader inputFile = new FileReader(fileName);
				// Instantiate the BufferedReader Class
				BufferedReader bufferReader = new BufferedReader(inputFile);
				// Variable to hold the one line data
				String line;
				// add each row in to MyString cell
				while ((line = bufferReader.readLine()) != null) {
					MyString = MyString.concat(line);
					MyString = MyString.concat(" ");
				}
				Document doc = Jsoup.parse(MyString);
				String text = doc.body().text();
//				System.out.println(text);
				Filesave(text, fileName);
			}
		} catch (Exception e) {
			System.out.println("Error while reading file line by line:" + e.getMessage());
		}
		// System.out.println(MyString);

	}

	public static void Filesave(String text, String number) throws IOException {
		String path = "D:\\Arshad\\FINAL_SORTED_ARTICLE\\phishing\\dataset\\CSDMC2010_SPAM\\CSDMC2010_SPAM\\CSDMC2010_SPAM\\New TRAINING_without_tag";
		String mynumber = number.substring(number.lastIndexOf("\\"),number.length());
		mynumber = mynumber.replace(".eml", ".txt");
		path = path.concat(mynumber);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
		out.println(text);
		out.close();
	}

	public static ArrayList<String> FileofFolser() {
		File folder = new File(
				"D:\\Arshad\\FINAL_SORTED_ARTICLE\\phishing\\dataset\\CSDMC2010_SPAM\\CSDMC2010_SPAM\\CSDMC2010_SPAM\\New TRAINING");
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> result = new ArrayList<String>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
			result.add(listOfFiles[i].toString());
		}
		return result;
	}

}
