package FinalTest_OurWork_PhishWho;

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
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.TemporaryFilesystem;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

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

public class MainOurwork_PhishTank_internet_wrong {

	public static ArrayList<String> src = new ArrayList<String>();
	public static ArrayList<String> href = new ArrayList<String>();
	public static ArrayList<String> Script = new ArrayList<String>();
	public static ArrayList<String> Css = new ArrayList<String>();
	public static ArrayList<String> title = new ArrayList<String>();
	public static ArrayList<String> body = new ArrayList<String>();
	public static ArrayList<String> meta = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		String lable = "1";
		String type = "phish";
		// -------------------------------ALEXA Page------------------------
		PhishTank_List al = new PhishTank_List();
		ArrayList<String> URL_file = new ArrayList<String>();
		URL_file = al.TopLis();
		// -----------------------------------------------------------------
		OurWork_Edit_destance ourWork = new OurWork_Edit_destance();
		OurWork_Edit_destance2_TFIDF ourWorkTFIDF = new OurWork_Edit_destance2_TFIDF();
		PhishWho_Selenium_algorithm PhishWho = new PhishWho_Selenium_algorithm();

		ArrayList<String> error = FileOfFolder.error_path;
		ArrayList<String> Parsing_Error = FileOfFolder.error_path;

		SaveToFile_2_LPD sv2 = new SaveToFile_2_LPD();


		IndexFiles index = new IndexFiles();

		for (int i = 501; i < 601/* URL_file.size() */ ; i++) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\new\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\new\\chromedriver.exe");
			WebDriver driver2 = new ChromeDriver();
			// Document doc = null;
			int result = -1;
			int result2 = -1;
			int result3 = -1;
			String Phish_id = Integer.toString(i).concat(" - ").concat(URL_file.get(i - 501));

			String URL = URL_file.get(i - 501);

			try {

				driver2.get(URL);

				Convert_web2File_Internet.main(driver2, URL, i, type);
				IndexFiles_bm25.main();

				// -----------------------------------------------------------------------------------

				result = ourWorkTFIDF.main(URL, URL, driver2, driver, Phish_id, i, lable);

				if (/* result==-1 || */ result2 == -1 /* || result3==-1 */) {
					if (result == 0) {
						Screenshot_ourWork_TFIDF(driver2, i);
					} else if (result2 == 0) {
						Screenshot_ourWork(driver2, i);
					} else if (result3 == 0) {
						Screenshot_PhishWho(driver2, i);
					}
//					 driver.quit();
//					 driver2.quit();
//					continue;
				}
				if (result == 0 || result2 == 0) {
					Screenshot(driver2, i);
				}
				if (result == 0) {
					Screenshot_ourWork_TFIDF(driver2, i);
				} else if (result2 == 0) {
					Screenshot_ourWork(driver2, i);
				} else if (result3 == 0) {
					Screenshot_PhishWho(driver2, i);
				}

			} catch (Exception e1) {
				Parsing_Error.add(Integer.toString(i));
				Parsing_Error.add(URL);
				sv2.Filesave(i, Phish_id, URL, lable);
				TemporaryFilesystem tempFS = TemporaryFilesystem.getDefaultTmpFS();
				tempFS.deleteTemporaryFiles();
				 driver.quit();
				 driver2.quit();
				continue;
			}

			TemporaryFilesystem tempFS = TemporaryFilesystem.getDefaultTmpFS();
			tempFS.deleteTemporaryFiles();
			 driver.quit();
			 driver2.quit();

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

	public static void Screenshot_ourWork(WebDriver driver, int i) {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile
			// //method
			String ss = "./ProposeResaule/ScreenShot/".concat(Integer.toString(i)).concat(".png");
			FileUtils.copyFile(src, new File(ss));
		}

		catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}

	public static void Screenshot_ourWork_TFIDF(WebDriver driver, int i) {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile
			// //method
			String ss = "./ProposeResaule2/ScreenShot/".concat(Integer.toString(i)).concat(".png");
			FileUtils.copyFile(src, new File(ss));
		}

		catch (IOException e) {
			System.out.println(e.getMessage());

		}
	}

	public static void Filesave(String Word, String id, String i) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
					"D:\\WEBSIT_TEXT_File\\legit\\URLLocal_ContentInternet\\phish\\" + i + ".txt", true)));
			out.println(Word);
			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}
	}

	public static void Screenshot_PhishWho(WebDriver driver, int i) {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile
			// //method
			String ss = "./PhishWhoResult/ScreenShot/".concat(Integer.toString(i)).concat(".png");
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

	/*
	 * public static ArrayList<String> Set_Resource( WebDriver driver) {
	 * 
	 * ArrayList<String> AllURL; AllURL = new ArrayList<String>();
	 * 
	 * try { //---------------------------------------------------------------
	 * List<WebElement> href_links = driver.findElements(By.xpath("//a"));
	 * List<WebElement> src_links = driver.findElements(By.xpath("//img"));
	 * List<WebElement> Script_links =
	 * driver.findElements(By.xpath("//script")); List<WebElement> Css_links =
	 * driver.findElements(By.xpath("//link")); //-------- href , src ,
	 * Script(JavaScript files) , Link(style sheet files)
	 * ------------------------- int i=0; for (WebElement href_link :
	 * href_links) { System.out.println(i+++href_link.getAttribute("href")); if
	 * (href_link.getAttribute("href")== null
	 * ||href_link.getAttribute("href").isEmpty()
	 * ||href_link.getAttribute("href").contains("javascript:void(0)")
	 * ||href_link.getAttribute("href").contains("javascript:;")
	 * 
	 * ) { continue; }else if(
	 * href_link.getAttribute("href").contains("http://")==false &&
	 * href_link.getAttribute("href").contains("https://")==false ) { continue;
	 * }
	 * 
	 * href.add(href_link.getAttribute("href"));
	 * 
	 * System.out.println(" href  :  "+href_link.getAttribute("href")); }
	 * AllURL.addAll(href); i=0; for (WebElement src_link : src_links) {
	 * System.out.println(i++);
	 * System.out.println(src_link.getAttribute("src")); if
	 * (src_link.getAttribute("src")== null
	 * ||src_link.getAttribute("src").isEmpty()
	 * ||src_link.getAttribute("src").contains("javascript:void(0)")
	 * ||src_link.getAttribute("src").contains("javascript:;")
	 * 
	 * ) { continue; }else if(
	 * src_link.getAttribute("src").contains("http://")==false &&
	 * src_link.getAttribute("src").contains("https://")==false) { continue; }
	 * 
	 * 
	 * 
	 * src.add(src_link.getAttribute("src")); System.out.println(" src img :  "
	 * +src_link.getAttribute("src")); } AllURL.addAll(src);
	 * 
	 * 
	 * // for (WebElement Script_link : Script_links) // { // if
	 * (Script_link.getAttribute("src")== null //
	 * ||Script_link.getAttribute("src").isEmpty() //
	 * ||Script_link.getAttribute("src").contains("javascript:void(0) //
	 * ||Script_link.getAttribute("src").contains("javascript:;") // ")) { //
	 * continue; // } // // Script.add(Script_link.getAttribute("src")); //
	 * System.out.println(" src script :  "+Script_link.getAttribute("src")); //
	 * } // AllURL.addAll(Script); // // for (WebElement Css_link : Css_links)
	 * // { // if (Css_link.getAttribute("href")== null //
	 * ||Css_link.getAttribute("href").isEmpty() //
	 * ||Css_link.getAttribute("href").contains("javascript:void(0)") //
	 * ||Css_link.getAttribute("href").contains("javascript:;") // ) { //
	 * continue; // } // // Css.add(Css_link.getAttribute("href")); //
	 * System.out.println(" src  :  "+Css_link.getAttribute("href")); // } //
	 * AllURL.addAll(Css); return AllURL;
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block return AllURL;
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
	public static ArrayList<String> T_Plain(WebDriver driver) {
		ArrayList<String> Words = new ArrayList<String>();
		ArrayList<String> my_string_word = new ArrayList<String>();
		List<WebElement> meta_link = driver.findElements(By.xpath("//meta"));

		// ---------------------- Text(Title) -------------------------
		String Title_String = driver.getTitle();
		my_string_word = getWords(Title_String);
		Words.addAll(my_string_word);
		title.addAll(my_string_word);

		// ---------------------- Text(body) -------------------------

		String body_String = driver.findElement(By.tagName("body")).getText();
		my_string_word.clear();
		my_string_word = getWords(body_String);
		Words.addAll(my_string_word);
		body.addAll(my_string_word);

		// ---------------------- Text -------------------------
		my_string_word.clear();
		for (WebElement webElement : meta_link) {
			String meta_text = webElement.getAttribute("content");
			my_string_word = getWords(meta_text);
			meta.addAll(my_string_word);
			Words.addAll(my_string_word);
			my_string_word.clear();
		}
		// -------------------------------------------------------------------
		return Word_that_host_plus_SLD(Words);
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

	public static ArrayList<String> Word_that_host_plus_SLD(ArrayList<String> Words) {
		for (int i = 0; i < Words.size(); i++) {
			if (Words.get(i).contains(".")) {
				String[] s2 = Words.get(i).split(Pattern.quote("."));// String[]
																		// s2 =
																		// Words.get(i).split("\\.");
				for (int i2 = 0; i2 < s2.length; i2++) {
					Words.add(s2[i2]);
				}
			}
		}
		return Words;
	}

	public static void Delete_array() {
		src.clear();
		href.clear();
		Script.clear();
		Css.clear();
		title.clear();
		body.clear();
		meta.clear();
		System.gc();
	}

	public static String T_Plain_for_web2fileWebDriver(WebDriver driver) {
		String Word = " ";
		List<WebElement> meta_link = driver.findElements(By.xpath("//meta"));

		// ---------------------- Text(Title) -------------------------
		String Title_String = driver.getTitle();

		// ---------------------- Text(body) -------------------------

		String body_String = driver.findElement(By.tagName("body")).getText();

		// ---------------------- Text -------------------------
		String meta_text = " ";
		for (WebElement webElement : meta_link) {
			meta_text = meta_text.concat(" ").concat(webElement.getAttribute("content"));

		}

		Word = Title_String.concat(body_String).concat(meta_text);

		// -------------------------------------------------------------------
		return Word;
	}

}
