package LPD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PhishWho.GoogleCrawler;
import PhishWho.GoogleSearch;
import PhishWho.MyPhishwho;
import LPD.*;
import ReadFromFolder.FileOfFolder;
import SaveResultIntoFile.SaveToFile;
import SaveResultIntoFile.SaveToFile2;
import SaveResultIntoFile.SaveToFileLPD;
import SaveResultIntoFile.SaveToFile_2_LPD;
import SaveResultIntoFile.SaveToFile_FN;
import SaveResultIntoFile.SaveToFile_FP;
import SaveResultIntoFile.SaveToFile_TN;
import SaveResultIntoFile.SaveToFile_TP;

public class MainLPD2_Selenium {
	
	static String zz2="";
	static String Title="";
	static String status="";
	static int Google_number_result=0;
	static ArrayList<String> resultSorted=new ArrayList<String>();//LPD.resultSorted;
	static ArrayList<String> SLD = new ArrayList<String>();//LPD.SLD;
	static ArrayList<String> MyUrlListOfGoogleSearch = new ArrayList<String>();


	public static void main(String[] args) throws Exception {
		String lable="";

		
		
//		String FolderName="E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit";
		String FolderName="E:\\Wamp\\wamp64\\www\\Phish\\dlweb_phish";
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
		LPD mytest=new LPD();
		LPD_Selenium mytest2=new LPD_Selenium();

		
		FileOfFolder fifo=new FileOfFolder();
		fifo.main(FolderName);
		ArrayList<String> html=FileOfFolder.htmlcontent_path;
		ArrayList<String> URL_file=FileOfFolder.URL_path;
		ArrayList<String> error=FileOfFolder.error_path;
		ArrayList<String> Parsing_Error=FileOfFolder.error_path;
		List<String> myx = LPD.myx;
		SaveToFileLPD sv = new SaveToFileLPD();
		SaveToFile_2_LPD sv2 = new SaveToFile_2_LPD();
		SaveToFile_FN FN=new SaveToFile_FN();
		SaveToFile_FP FP=new SaveToFile_FP();
		SaveToFile_TN TN=new SaveToFile_TN();
		SaveToFile_TP TP=new SaveToFile_TP();
		
		GoogleSearch gs=new GoogleSearch();
//		GoogleCrawler gs=new GoogleCrawler();
		GoogleSearch_Selenium gs2=new GoogleSearch_Selenium();
//      use for selenium 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Emad\\workspace\\SeleniumHQ\\bin\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\SeleniumHQ\\bin\\firefox\\geckodriver.exe");
	    WebDriver driver2 = new FirefoxDriver();
//		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

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
		
	       for (int i = 0; i < 5000; i++) {
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
				/*doc = Jsoup.parse(
						new File(SThtml),
						"ISO-8859-1");*/
				SThtml=SThtml.substring(SThtml.indexOf("www")+4, SThtml.length());
				SThtml=SThtml.replace("\\", "/");
				doc = Jsoup.connect("http://localhost/".concat(SThtml)).get();
				
				result = mytest.main(URL,doc,gs);
				setValue_LPD();

			} catch (Exception e) {
				try {
					Delete_LPD();
					File input = new File(html.get(i));
					Document doc2 = Jsoup.parse(input, "UTF-8", "http://example.com/");
					result = mytest.main(URL,doc2,gs);
					setValue_LPD();
				} catch (Exception e1) {
					try {
						Delete_LPD();
						result = mytest2.main("http://localhost/".concat(SThtml),gs2,driver,driver2);	
						setValue_SeleniumLPD();
					} catch (Exception e2) {
						Parsing_Error.add(Integer.toString(i));
						Parsing_Error.add(URL);
						Parsing_Error.add(html.get(i));
						sv2.Filesave(i,Phish_id, URL, lable);
						continue;	
						}

				}

			}
			if(result==1 || result==4 || result==3){
				try {
					Response response = Jsoup.connect(URL).followRedirects(true).execute();
					String s=response.url().toString();
					doc = Jsoup.connect(s).get();
					result = mytest.main(s,doc,gs);
					if(result==1 || result==4 || result==3){
						Delete_LPD();
						result = mytest2.main(s,gs2,driver,driver2);	
						setValue_SeleniumLPD();
					}
				} catch (Exception e1) {
					sv2.Filesave(i,Phish_id, URL, lable);
					continue;
				}

			}
			//-----------------------  Run PhishWho   -----------------------
			boolean zzz=!LPD.UrlListOfGoogleSearch.isEmpty();
			for (int i2 = 0; i2 < 6; i2++) {
				if(!LPD.UrlListOfGoogleSearch.isEmpty()){
				try {
					      MyUrlListOfGoogleSearch.add(LPD.UrlListOfGoogleSearch.get(i2));
				    } catch (Exception e) {
						  MyUrlListOfGoogleSearch.add(" ");
		           } 
				}
			}
			
			//----------------------------------------------------------------
			
			
			zz2=LPD.zz2;
			Title=LPD.S_title;
			Google_number_result=LPD.Google_number_occure;
			status=LPD.status;
			
			int xx=Integer.parseInt(lable);
			if(Integer.parseInt(lable)==1 && result==1 || Integer.parseInt(lable)==1 && result==4){
				TP.Filesave(i,Phish_id, Title ,URL, lable,Integer.toString(result) , myx , resultSorted , MyUrlListOfGoogleSearch , SLD ,zz2, status, Google_number_result);

			}else if(Integer.parseInt(lable)==0 && result==1 || Integer.parseInt(lable)==0&& result==4){
				FP.Filesave(i,Phish_id, Title ,URL, lable,Integer.toString(result) , myx , resultSorted , MyUrlListOfGoogleSearch , SLD ,zz2, status, Google_number_result);

			}else if(Integer.parseInt(lable)==1 && result==0){
				FN.Filesave(i,Phish_id, Title ,URL, lable,Integer.toString(result) , myx , resultSorted , MyUrlListOfGoogleSearch , SLD ,zz2, status, Google_number_result);

			}else if(Integer.parseInt(lable)==0 && result==0){
				TN.Filesave(i,Phish_id, Title ,URL, lable,Integer.toString(result) , myx , resultSorted , MyUrlListOfGoogleSearch , SLD ,zz2, status, Google_number_result);

			}
			
			
			sv.Filesave(i,Phish_id, Title ,URL, lable,Integer.toString(result) , myx , resultSorted , MyUrlListOfGoogleSearch , SLD ,zz2 , status , Google_number_result);
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
	public static void setValue_LPD() {
		resultSorted=LPD.resultSorted;
		SLD=LPD.SLD;
		zz2=LPD.zz2;
		Title=LPD.S_title;
		Google_number_result=LPD.Google_number_occure;
		status=LPD.status;
		
		boolean zzz=!LPD.UrlListOfGoogleSearch.isEmpty();
		for (int i2 = 0; i2 < 6; i2++) {
			if(!LPD.UrlListOfGoogleSearch.isEmpty()){
			try {
				      MyUrlListOfGoogleSearch.add(LPD.UrlListOfGoogleSearch.get(i2));
			    } catch (Exception e) {
					  MyUrlListOfGoogleSearch.add(" ");
	           } 
			}
		}
		}
	
	public static void Delete_LPD() {
		resultSorted.clear();
		SLD.clear();
		zz2="";
		Title="";
		Google_number_result=20;
		status="";
		MyUrlListOfGoogleSearch.clear();
		}
	public static void setValue_SeleniumLPD() {
		resultSorted=LPD_Selenium.resultSorted;
		SLD=LPD_Selenium.SLD;
		zz2=LPD_Selenium.zz2;
		Title=LPD_Selenium.S_title;
		Google_number_result=LPD_Selenium.Google_number_occure;
		status=LPD_Selenium.status;
		
		boolean zzz=!LPD_Selenium.UrlListOfGoogleSearch.isEmpty();
		for (int i2 = 0; i2 < 6; i2++) {
			if(!LPD_Selenium.UrlListOfGoogleSearch.isEmpty()){
			try {
				      MyUrlListOfGoogleSearch.add(LPD_Selenium.UrlListOfGoogleSearch.get(i2));
			    } catch (Exception e) {
					  MyUrlListOfGoogleSearch.add(" ");
	           } 
			}
		}
		}

}
