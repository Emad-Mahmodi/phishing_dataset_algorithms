package OurWork2_DataSet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	static String zz2 = "";
	static String Identity_Key = "";
	static String status = "";
	static int Google_number_result = 0;
	static ArrayList<String> resultSorted = new ArrayList<String>();// LPD.resultSorted;
	static ArrayList<String> SLD = new ArrayList<String>();// LPD.SLD;
	static ArrayList<String> MyUrlListOfGoogleSearch = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		String lable = "";
		lable = "0";

		 String FolderName="E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit";
//		String FolderName = "E:\\Wamp\\wamp64\\www\\Phish\\dlweb_phish";
		if (FolderName.equals("E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit")) {
			lable = "1";
		} else {
			lable = "-1";

		}
		// -------------------------------- indexing tfidf -----------------
		// indexing( FolderName );
		// -------------------------------local Page------------------------
		FileOfFolder fifo = new FileOfFolder();
		fifo.main(FolderName);
		ArrayList<String> html = FileOfFolder.htmlcontent_path;
		ArrayList<String> URL_file = FileOfFolder.URL_path;
		ArrayList<String> error = FileOfFolder.error_path;
		ArrayList<String> Parsing_Error = FileOfFolder.error_path;
		// -----------------------------------------------------------------
		// Creat_DataSet mytest=new Creat_DataSet();
		Creat_DataSet_New mytest = new Creat_DataSet_New();

		GoogleSearch gs = new GoogleSearch();
		GoogleSearch_Selenium gs2 = new GoogleSearch_Selenium();
		// use for selenium

//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);

		// ----------------------------------------------------------------------------------------------------------
		 System.setProperty("webdriver.gecko.driver",
		 "C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\geckodriver.exe");
		for (int i = 0; i < 5000; i++) {
			// WebDriver driver=new ChromeDriver();
			// driver.manage().timeouts().pageLoadTimeout(100,
			// TimeUnit.SECONDS);

			 WebDriver driver = new FirefoxDriver();
			 driver.manage().timeouts().pageLoadTimeout(100,
			 TimeUnit.SECONDS);
			// Document doc = null;
			String Phish_id = URL_file.get(i);
			String SThtml = html.get(i);
			// -------------------------------------------Read url from file
			// -------------------

			String URL = "";
			try (BufferedReader br = new BufferedReader(new FileReader(URL_file.get(i)))) {
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					URL = sCurrentLine;
				}
			} catch (IOException e) {
				Parsing_Error.add(URL);
			}

			SThtml = SThtml.substring(SThtml.indexOf("www") + 4, SThtml.length());
			SThtml = SThtml.replace("\\", "/");
			SThtml = "http://localhost/".concat(SThtml);
			// -----------------------------------------------------------------------------------
			try {
				/*
				 * if(SuspendURL(URL)==5){ driver2.quit(); continue; }
				 */
				if (i == 0) {
					Title_of_first_Filesave(lable);
				}

				Delete_PhishWho();
				mytest.main(URL, SThtml, driver, Phish_id, i, lable);

			} catch (Exception e1) {
				Parsing_Error.add(Integer.toString(i));
				Parsing_Error.add(URL);
				 //driver.close();
				 driver.quit();
				continue;
			}

			resultSorted.clear();
			MyUrlListOfGoogleSearch.clear();
			SLD.clear();
			 //driver.close();
			 driver.quit();
		}

	}
	


	public static void setValue_LPD() {
		resultSorted = PhishWho_Selenium_algorithm.resultSorted_X;
		SLD = PhishWho_Selenium_algorithm.SLD;
		zz2 = PhishWho_Selenium_algorithm.zz2;
		Identity_Key = PhishWho_Selenium_algorithm.identity_keyword.toString();
		Google_number_result = 0;
		status = " ";

		boolean zzz = !PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.isEmpty();
		for (int i2 = 0; i2 < 6; i2++) {
			if (!PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.isEmpty()) {
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
		zz2 = "";
		Identity_Key = "";
		Google_number_result = 20;
		status = "";
		MyUrlListOfGoogleSearch.clear();
	}

	public static void setValue_SeleniumLPD() {
		resultSorted = PhishWho_Selenium_algorithm.resultSorted_X;
		SLD = PhishWho_Selenium_algorithm.SLD;
		zz2 = PhishWho_Selenium_algorithm.zz2;
		Identity_Key = PhishWho_Selenium_algorithm.identity_keyword.toString();
		Google_number_result = 0;
		status = "";

		boolean zzz = !PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.isEmpty();
		for (int i2 = 0; i2 < 6; i2++) {
			if (!PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.isEmpty()) {
				try {
					MyUrlListOfGoogleSearch.add(PhishWho_Selenium_algorithm.UrlListOfGoogleSearch.get(i2));
				} catch (Exception e) {
					MyUrlListOfGoogleSearch.add(" ");
				}
			}
		}
	}

	public static void Screenshot(WebDriver driver, int i) {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile
			// //method
			String ss = "./ScreenShot/".concat(Integer.toString(i)).concat(".png");
			FileUtils.copyFile(src, new File(ss));
		}

		catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}

	public static int SuspendURL(String s) {
		String URL1 = "http://phish-education.apwg.org/r/en/index.htm";
		String URL2 = "https://www.hostinger.co.uk/error_403";
		String URL3 = "http://error.hostinger.eu/403.php?";
		String URL4 = "http://phish-education.apwg.org/r/en/?www.phishsite.com/the-phishing-page.html";
		if (s.equals(URL1) || s.equals(URL2) || s.equals(URL3) || s.equals(URL4)) {
			return 5;
		}
		return 0;
	}

	public static void indexing(String FolderName) {
		// ------------------------------- indexing file for tfidf
		// -----------------------------------
		IndexFiles index = new IndexFiles();
		String docsPath = "";
		if (FolderName.equals("E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit")) {
			docsPath = "D:\\WEBSIT_TEXT_File\\legit\\URLLocal_ContentInternet";
		} else {
			docsPath = "D:\\WEBSIT_TEXT_File\\phish";
		}
		index.main(docsPath);
	}

	public static void Title_of_first_Filesave(String label) {
		try {

			String path = "";
			if (label.equals("1")) {
				path = "C:\\Users\\Emad\\workspace\\x3\\DataSet\\legit2.txt";
			} else {
				path = "C:\\Users\\Emad\\workspace\\x3\\DataSet\\phish2.txt";
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));

			StringBuffer str = new StringBuffer();

			str.append("URL");
			str.append(',');

			str.append("Feature1");
			str.append(',');
			str.append("Feature2");
			str.append(',');
			str.append("Feature3");
			str.append(',');
			str.append("Feature4");
			str.append(',');
			str.append("Feature5");
			str.append(',');
			str.append("Feature6");
			str.append(',');
			str.append("Feature7");
			str.append(',');
			str.append("Feature8");
			str.append(',');
			str.append("Feature9");
			str.append(',');
			str.append("Feature10");
			str.append(',');
			str.append("Feature11");
			str.append(',');
			str.append("Feature12");
			str.append(',');
			str.append("Feature13");
			str.append(',');
			str.append("Feature14");
			str.append(',');
			str.append("Feature15");
			str.append(',');
			str.append("Feature16");
			str.append(',');
			str.append("Feature17");
			str.append(',');
			str.append("Feature18");
			str.append(',');
			str.append("Feature19");
			str.append(',');
			str.append("Feature20");
			str.append(',');
			str.append("Feature21");
			str.append(',');
			str.append("Feature22");
			str.append(',');
			str.append("Feature23");
			str.append(',');
			str.append("Feature24");
			str.append(',');
			str.append("Feature25");
			str.append(',');
			str.append("Feature26");
			str.append(',');
			str.append("Feature27");
			str.append(',');
			str.append("Feature28");
			str.append(',');
			str.append("Feature29");
			str.append(',');
			str.append("Feature30");
			str.append(',');
			str.append("Feature31");
			str.append(',');
			str.append("Feature32");
			str.append(',');
			str.append("Feature33");
			str.append(',');
			str.append("Feature34");
			str.append(',');
			str.append("Feature35");
			str.append(',');
			str.append("Feature36");
			str.append(',');
			str.append("Feature37");
			str.append(',');
			str.append("Feature38");
			str.append(',');
			str.append("Feature39");
			str.append(',');
			str.append("Feature40");
			str.append(',');
			str.append("Feature41");
			str.append(',');
			str.append("Feature42");
			str.append(',');
			str.append("Feature43");
			str.append(',');
			str.append("Feature44");
			str.append(',');

			str.append("label");

			System.out.println(str);
			out.println(str);

			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}

	}
}
