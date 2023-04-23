package DrSadoghi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
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

public class MainPhishWho_Phishtank_New_Localdata {


	public static void main(String[] args) throws IOException {
		String lable="1";

//		-------------------------------ALEXA Page------------------------
		Alexa_List al=new Alexa_List();
		ArrayList<String> URL_file=new ArrayList<String>();
		URL_file=al.TopLis();
//		-----------------------------------------------------------------
		Dataset1 mytest=new Dataset1();

		ArrayList<String> error=FileOfFolder.error_path;
		ArrayList<String> Parsing_Error=FileOfFolder.error_path;

		SaveToFile_2_LPD sv2 = new SaveToFile_2_LPD();



		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\geckodriver.exe");
	    WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(200, TimeUnit.SECONDS);

		
//----------------------------------------------------------------------------------------------------------		
		
	       for (int i = 280; i < 500; i++) {
//			Document doc = null;
			String Phish_id=Integer.toString(i).concat(" - ").concat(URL_file.get(i));
			
			
			

		    
		
//			-------------------------------------------Read url from file -------------------
			String URL=URL_file.get(i);
//			-----------------------------------------------------------------------------------		
			try {
				/*if(SuspendURL(URL)==5){
					driver2.quit();
					continue;
				}*/
				
					
				mytest.main(URL , URL , driver , Phish_id , i , lable );
				
				
				
				
			} catch (Exception e1) {
					Parsing_Error.add(Integer.toString(i));
					Parsing_Error.add(URL);
					sv2.Filesave(i,Phish_id, URL, lable);
//					driver.quit();
					continue;
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
