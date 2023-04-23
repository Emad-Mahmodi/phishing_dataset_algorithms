package com.lynda.javatraining.db;

import WordRetrival.*;
import SaveResultIntoFile.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ReadFromFolder.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.beans.website;
import com.lynda.javatraining.db.tables.AdminManager2;
import com.lynda.javatraining.util.InputHelper;

import ParsingWithSoup_xxx.*;
import netPC.*;
import netPC.ReadNetworkFile;

public class MainTestPhishWho2_File_in_Folder {

	public static void main(String[] args) throws Exception {
		
		String lable="";

		
		String FolderName="E:\\Wamp\\wamp64\\www\\Phish\\dlweb_phish";
//		String FolderName="E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit";
		
		if(FolderName.equals("E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit")){
			 lable="0";
		}else {
			lable="1";
		}
//		Folder fo=new Folder();
//		ArrayList<String> folder=new ArrayList<String>(); 
//		folder.addAll(fo.main(FolderName));
//		
//		FileOfFolder fifo=new FileOfFolder();
//		for (int i = 0; i < folder.size(); i++) {
//			String FolderOfFolder=folder.get(i);
//			fifo.main(FolderOfFolder);
//		}
//		
//		
		MyWordRetrival mytest=new MyWordRetrival();
		
		FileOfFolder fifo=new FileOfFolder();
		fifo.main(FolderName);
		ArrayList<String> html=FileOfFolder.htmlcontent_path;
		ArrayList<String> URL_file=FileOfFolder.URL_path;
		ArrayList<String> error=FileOfFolder.error_path;
		ArrayList<String> Parsing_Error=FileOfFolder.error_path;
		List<String> myx = MyWordRetrival.myx;
		ArrayList<String> resultSorted=MyWordRetrival.resultSorted;
		ArrayList<String> MyUrlListOfGoogleSearch = new ArrayList<String>();
		ArrayList<String> SLD = MyWordRetrival.SLD;
		String zz2="";
		SaveToFile2 sv2 = new SaveToFile2();
		SaveToFile sv = new SaveToFile();




//---------------------------------------------------------------------write <!DOCTYPE html> into file------
		/*FileWriter fWriter = null;
		BufferedWriter writer = null;
		try {
		    fWriter = new FileWriter("PhishWho_Result.txt");
		    writer = new BufferedWriter(fWriter);
		    writer.write("<!DOCTYPE html>");
		    writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
		    writer.write("<html>");
		    writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
		    writer.write("<head>");
		    writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
		    writer.write("<title>Phishing Detection Result</title>");
		    writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
		    writer.write("</head>");
		    writer.newLine(); //this is not actually needed for html files - can make your code more readable though 
		    writer.write("<body>");
		    writer.close(); //make sure you close the writer object 
		} catch (Exception e) {
		  //catch any exceptions here
		}*/
		
		

		    
//----------------------------------------------------------------------------------------------------------		
		
	       for (int i = 1; i < 1000; i++) {
			Document doc = null;
			int result=-1;
			String SThtml=html.get(i);
			String Phish_id=URL_file.get(i);
//			-------------------------------------------Read url from file -------------------
			
			String URL="";
			try (BufferedReader br = new BufferedReader(new FileReader(URL_file.get(i)))) {
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					URL=sCurrentLine;
				}
			} catch (IOException e) {
				Parsing_Error.add(URL);
			}

//			-----------------------------------------------------------------------------------		
			try {
				doc = Jsoup.parse(
						new File(SThtml),
						"ISO-8859-1");
				result = mytest.main(URL,doc);

			} catch (Exception e) {
				Parsing_Error.add(Integer.toString(i));
				Parsing_Error.add(URL);
				Parsing_Error.add(html.get(i));
				sv2.Filesave(Phish_id, URL, lable);
				continue;
			}
			//-----------------------  Run PhishWho   -----------------------
			for (int i2 = 0; i2 < MyWordRetrival.UrlListOfGoogleSearch.size(); i2++) {
				MyUrlListOfGoogleSearch.add(MyWordRetrival.UrlListOfGoogleSearch.get(i2));
			}
			
			//----------------------------------------------------------------
			
			
			zz2=MyWordRetrival.zz2;
			
			sv.Filesave(Phish_id, URL, lable,Integer.toString(result) , myx , resultSorted , MyUrlListOfGoogleSearch , SLD ,zz2);
		}
	       
//------------------------------------------------------------write close html-----------
	     //---------------------------------------------------------------------write <!DOCTYPE html> into file------
		   /* try {
			    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("PhishWho_Result.txt", true)));
			    out.println(
			    		"</body>\n"+
			    		"</html>"		
			    		);
			    out.close();
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}*/
	//----------------------------------------------------------------------------------------------------------		


		
		}
	}



