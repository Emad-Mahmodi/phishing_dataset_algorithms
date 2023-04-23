package LPD;

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

public class MainLPD_Alexa {
	static String zz2="";
	static String Title="";
	static String status="";
	static int Google_number_result=0;
	static ArrayList<String> resultSorted=new ArrayList<String>();//LPD.resultSorted;
	static ArrayList<String> SLD = new ArrayList<String>();//LPD.SLD;
	static ArrayList<String> MyUrlListOfGoogleSearch = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		String lable="0";

		

//		-------------------------------ALEXA Page------------------------
		Alexa_List al=new Alexa_List();
		ArrayList<String> URL_file=new ArrayList<String>();
		URL_file=al.TopLis();
//		-----------------------------------------------------------------
		LPD mytest=new LPD();
		LPD_Selenium mytest2=new LPD_Selenium();

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
		GoogleSearch_Selenium gs2=new GoogleSearch_Selenium();
//      use for selenium 

		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x2\\libs\\selenium\\geckodriver.exe");
	    WebDriver driver2 = new FirefoxDriver();
//		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver2.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
//----------------------------------------------------------------------------------------------------------		
		
	       for (int i = 1; i < 500; i++) {
			Document doc = null;
			int result=-1;
			String Phish_id=Integer.toString(i).concat(" - ").concat(URL_file.get(i));
			
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Emad\\workspace\\x2\\libs\\selenium\\chromedriver.exe");
			WebDriver driver=new ChromeDriver();
//			driver.manage().window().setPosition(new Point(-2000, 0));
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//			-------------------------------------------Read url from file -------------------
			String URL=URL_file.get(i);
//			-----------------------------------------------------------------------------------		
			try {
				/*doc = Jsoup.parse(
						new File(SThtml),
						"ISO-8859-1");*/
				/*SThtml=SThtml.substring(SThtml.indexOf("www")+4, SThtml.length());
				SThtml=SThtml.replace("\\", "/");
				doc = Jsoup.connect("http://localhost/".concat(SThtml)).get();*/
//				String hh="https://www.".concat(URL);
//				doc = Jsoup.connect("https://www.".concat(URL)).get();

				Response response = Jsoup.connect("http://www.".concat(URL)).followRedirects(true).execute();
				String s=response.url().toString();
				Delete_LPD();
				doc = Jsoup.connect(s).get();
				result = mytest.main(s,doc,gs,gs2,driver2);
				setValue_LPD();
				
				if(result==1 || result==4 || result==3|| result==-1){
					Delete_LPD();
					result = mytest2.main(s,gs2,driver,driver2);
					if(result==5){
						driver.quit();
						continue;
					}
					if(result==1){
						Screenshot(driver , i);
					}
					setValue_SeleniumLPD();
				}

			} catch (Exception e) {
				try {
					String s="http://www.".concat(URL);
					Delete_LPD();
					if(SuspendURL(s)==5){
						driver.quit();
						continue;
					}
					result = mytest2.main(s,gs2,driver,driver2);
					setValue_SeleniumLPD();
					if(result==5){
						driver.quit();
						continue;
					}
					if(result==1 ){
						Screenshot(driver , i);
					}
				} catch (Exception e1) {
					Parsing_Error.add(Integer.toString(i));
					Parsing_Error.add(URL);
					sv2.Filesave(i,Phish_id, URL, lable);
					driver.quit();
					continue;
				}
			}
			

			
			

			
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
			
			resultSorted.clear();
			MyUrlListOfGoogleSearch.clear();
			SLD.clear();
			myx.clear();
			driver.quit();
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
