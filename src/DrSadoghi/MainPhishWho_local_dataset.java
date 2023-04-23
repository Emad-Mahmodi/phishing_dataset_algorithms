package DrSadoghi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import PhishWho.GoogleSearch;
import LPD.*;
import PhishWho_Selenium.*;
import ReadFromFolder.FileOfFolder;
import SaveResultIntoFile.SaveToFile;
import SaveResultIntoFile.SaveToFile2;
import SaveResultIntoFile.SaveToFileLPD;
import SaveResultIntoFile.SaveToFile_2_LPD;
import SaveResultIntoFile.SaveToFile_FN;
import SaveResultIntoFile.SaveToFile_FP;
import SaveResultIntoFile.SaveToFile_TN;
import SaveResultIntoFile.SaveToFile_TP;

public class MainPhishWho_local_dataset {
	static String zz2="";
	static String Identity_Key="";
	static String status="";
	static int Google_number_result=0;
	static ArrayList<String> resultSorted=new ArrayList<String>();//LPD.resultSorted;
	static ArrayList<String> SLD = new ArrayList<String>();//LPD.SLD;
	static ArrayList<String> MyUrlListOfGoogleSearch = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		String lable="";
		lable="0";
		
		String FolderName="E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit";
//		String FolderName="E:\\Wamp\\wamp64\\www\\Phish\\dlweb_phish";
		if(FolderName.equals("E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit")){
			 lable="1";
		}else {
			 lable="-1";

		}
//		-------------------------------local Page------------------------
		FileOfFolder fifo=new FileOfFolder();
		fifo.main(FolderName);
		ArrayList<String> html=FileOfFolder.htmlcontent_path;
		ArrayList<String> URL_file=FileOfFolder.URL_path;
		ArrayList<String> error=FileOfFolder.error_path;
		ArrayList<String> Parsing_Error=FileOfFolder.error_path;
//		-----------------------------------------------------------------
		Dataset1 mytest=new Dataset1();


		
		GoogleSearch gs=new GoogleSearch();
		GoogleSearch_Selenium gs2=new GoogleSearch_Selenium();
//      use for selenium 

		

//		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\geckodriver.exe");
//	    WebDriver driver = new FirefoxDriver();
//		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

//----------------------------------------------------------------------------------------------------------		
		
	       for (int i = 548; i < 5000; i++) {
//			Document doc = null;
			String Phish_id=URL_file.get(i);
			String SThtml=html.get(i);
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



			SThtml=SThtml.substring(SThtml.indexOf("www")+4, SThtml.length());
			SThtml=SThtml.replace("\\", "/");
			SThtml="http://localhost/".concat(SThtml);
			//-----------------------------------------------------------------------------------		
			try {
				/*if(SuspendURL(URL)==5){
					driver2.quit();
					continue;
				}*/
				
				Delete_PhishWho();
				mytest.main(URL , SThtml, driver ,Phish_id,i,lable );
				
			} catch (Exception e1) {
					Parsing_Error.add(Integer.toString(i));
					Parsing_Error.add(URL);
//					driver.quit();
					continue;
				}
			
			

			
			
			resultSorted.clear();
			MyUrlListOfGoogleSearch.clear();
			SLD.clear();
	     }
	       
	}
	public static void setValue_LPD() {
		resultSorted=PhishWho_Selenium_algorithm.resultSorted_X;
		SLD=PhishWho_Selenium_algorithm.SLD;
		zz2=PhishWho_Selenium_algorithm.zz2;
		Identity_Key=PhishWho_Selenium_algorithm.identity_keyword.toString();
		Google_number_result=0;
		status=" ";
		
		boolean zzz=!PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.isEmpty();
		for (int i2 = 0; i2 < 6; i2++) {
			if(!PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.isEmpty()){
			try {
				      MyUrlListOfGoogleSearch.add(PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.get(i2));
			    } catch (Exception e) {
					  MyUrlListOfGoogleSearch.add(" ");
	           } 
			}
		}
		}
	
	public static void Delete_PhishWho() {
		resultSorted.clear();
		SLD.clear();
		zz2="";
		Identity_Key="";
		Google_number_result=20;
		status="";
		MyUrlListOfGoogleSearch.clear();
		}
	public static void setValue_SeleniumLPD() {
		resultSorted=PhishWho_Selenium_algorithm.resultSorted_X;
		SLD=PhishWho_Selenium_algorithm.SLD;
		zz2=PhishWho_Selenium_algorithm.zz2;
		Identity_Key=PhishWho_Selenium_algorithm.identity_keyword.toString();
		Google_number_result=0;
		status="";
		
		boolean zzz=!PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.isEmpty();
		for (int i2 = 0; i2 < 6; i2++) {
			if(!PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.isEmpty()){
			try {
				      MyUrlListOfGoogleSearch.add(PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.get(i2));
			    } catch (Exception e) {
					  MyUrlListOfGoogleSearch.add(" ");
	           } 
			}
		}
		}
	public static void Screenshot(WebDriver driver , int i) {

		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			 // now copy the  screenshot to desired location using copyFile //method
			String ss="./ScreenShot/".concat(Integer.toString(i)).concat(".png");
			FileUtils.copyFile(src, new File(ss));
		 }
		
		catch (IOException e)
		 {
		  System.out.println(e.getMessage());
		
		 }
	}
	public static int SuspendURL(String s) {
		String URL1="http://phish-education.apwg.org/r/en/index.htm";
		String URL2="https://www.hostinger.co.uk/error_403";
		String URL3="http://error.hostinger.eu/403.php?";
		String URL4="http://phish-education.apwg.org/r/en/?www.phishsite.com/the-phishing-page.html";
		if(s.equals(URL1) || s.equals(URL2) || s.equals(URL3)||s.equals(URL4)){
			return 5;
		}
		return 0;
	}
}
