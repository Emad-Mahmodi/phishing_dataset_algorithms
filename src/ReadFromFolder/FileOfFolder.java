package ReadFromFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class FileOfFolder {
	public static ArrayList<String> htmlcontent_path  = new ArrayList<String>();
	public static ArrayList<String> URL_path  = new ArrayList<String>();
	public static ArrayList<String> error_path  = new ArrayList<String>();




		public static void main(String url) {

			File folder = new File(url);
			ArrayList<String> FoldersOfSites=new ArrayList<String>();
			File[] listOfFiles = folder.listFiles();
	        Arrays.sort(listOfFiles, LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
	        /*for (int i = 0; i < listOfFiles.length; i++) {
	            File file = listOfFiles[i];
	            System.out.printf("File %s - %2$tm %2$te,%2$tY%n= ", file.getName(),
	                    file.lastModified());
	        }*/
	        
			System.out.println();
			for (int i = 0; i < listOfFiles.length; i++) {
				String s = (String)listOfFiles[i].getPath().concat("\\RAW-HTML");
				File folder2 = new File(s);
				File[] listOfFiles2 = folder2.listFiles();
				if (listOfFiles2==null) {
					error_path.add(s);
					continue;
				}
				
				
//				for (File file : listOfFiles2) {
				for (int j = 0; j < listOfFiles2.length; j++) {
					File file=listOfFiles2[j];
					if (file.isFile()) {
//						System.out.println(file.getAbsolutePath().concat(file.getName()));
//						htmlcontent_path.add(file.getAbsolutePath().concat(file.getName()));
						htmlcontent_path.add(file.getAbsolutePath());

					}
					else {
						continue;
					}
				}
				String s2 = listOfFiles[i].getPath().concat("\\URL");
				File folder3 = new File(s2);
				File[] listOfFiles3 = folder3.listFiles();
				if (listOfFiles3==null) {
					error_path.add(s);
					continue;
				}
//				for (File file : listOfFiles3) {
				for (int j = 0; j < listOfFiles3.length; j++) {
					File file2=listOfFiles3[j];
					if (file2.isFile()) {
//						System.out.println(file.getPath());
						URL_path.add(file2.getPath());
					}
					else {
						continue;
					}
				} 
			}
				
				
				
				
	}	
}
