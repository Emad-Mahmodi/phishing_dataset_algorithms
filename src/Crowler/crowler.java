package Crowler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import OurWork2_DataSet.URLNode;

public class crowler {

	public static ArrayList<String> src = new ArrayList<String>();
	public static ArrayList<String> href = new ArrayList<String>();
	public static ArrayList<String> Script = new ArrayList<String>();
	public static ArrayList<String> Css = new ArrayList<String>();
	public static ArrayList<String> SLD_List = new ArrayList<String>();
	public static ArrayList<String> AllWord_OK = new ArrayList<String>();
	public static ArrayList<String> title = new ArrayList<String>();
	public static ArrayList<String> body = new ArrayList<String>();
	public static ArrayList<String> meta = new ArrayList<String>();
	public static ArrayList<String> TopWord_TFDF = new ArrayList<String>();
	public static ArrayList<String> AllLinks = new ArrayList<String>();
	public static ArrayList<String> Final_Sord_word2 = new ArrayList<String>();
	public static int flag;
	public static ArrayList<String> AllWord_Frequent_word = new ArrayList<String>();
	public static String Copyright_word;
	public static int found;
	
	public static void main(String URL, WebDriver driver) {
		ArrayList<String> URLs = new ArrayList<String>();
		driver.get(URL);
		URLs = Set_Resource(driver, URL);
		Filesave(URLs);

	}
	
	
	
	
	
	public static void Filesave(ArrayList<String> URLs) {
		try {

			String path = "";
			path = "C:\\Users\\Emad\\workspace\\x3\\Crowler\\contentURL.txt";
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));

			for (int i = 0; i < URLs.size(); i++) {
				out.println(URLs.get(i));
			}

			out.close();
		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}

	}

	public static ArrayList<String> Set_Resource(WebDriver driver, String URL) {
		// ---------------------------------------------------------------
		List<WebElement> href_links = driver.findElements(By.xpath(".//a"));
		List<WebElement> src_links = driver.findElements(By.xpath(".//img"));
		List<WebElement> Script_links = driver.findElements(By.xpath(".//script"));
		List<WebElement> Css_links = driver.findElements(By.xpath(".//link"));
		ArrayList<String> URLs = new ArrayList<String>();
		// -------- href , src , Script(JavaScript files) , Link(style sheet
		// files) -------------------------

		for (WebElement href_link : href_links) {
			if (href_link.getAttribute("href") == null || href_link.getAttribute("href").isEmpty()) {
				continue;
			}
			URLs.add(href_link.getAttribute("href"));
			System.out.println(href_link.getAttribute("href"));
		}
//		URLs.clear();
		// ______________________________________________________________

		/*			for (WebElement src_link : src_links) {
			if (src_link.getAttribute("src") == null || src_link.getAttribute("src").isEmpty()) {
				continue;
			}
			URLs.add(src_link.getAttribute("src"));
			System.out.println(src_link.getAttribute("src"));
		}
		URLs.clear();
		// ______________________________________________________________

	     for (WebElement Script_link : Script_links) {
			if (Script_link.getAttribute("src") == null || Script_link.getAttribute("src").isEmpty()) {
				continue;
			}
			URLs.add(Script_link.getAttribute("src"));
			System.out.println(Script_link.getAttribute("src"));
		}
		URLs.clear();
		// ______________________________________________________________

		for (WebElement Css_link : Css_links) {
			if (Css_link.getAttribute("href") == null || Css_link.getAttribute("href").isEmpty()) {
				continue;
			}
			URLs.add(Css_link.getAttribute("href"));
			System.out.println(Css_link.getAttribute("href"));
		}
		URLs.clear();*/
		// ______________________________________________________________

		URLs.addAll(href);
//		URLs.addAll(src);
//		URLs.addAll(Script);
//		URLs.addAll(Css);
		
		return URLs;

	}

}
