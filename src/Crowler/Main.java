package Crowler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import OurWork2_DataSet.Creat_DataSet_New;
import OurWork2_DataSet.GoogleSearch_Selenium;
import PhishWho.GoogleSearch;
import ReadFromFolder.FileOfFolder;

public class Main {

	public static void main(String[] args) {

		
		// -----------------------------------------------------------------


		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Emad\\workspace\\x3\\libs\\selenium\\selenium\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		// ----------------------------------------------------------------------------------------------------------
		ArrayList<String> AllURL = new ArrayList<String>();
		AllURL = readFile();
		crowler cw = new crowler();

		for (int i = 1; i < AllURL.size(); i++) {
			// WebDriver driver=new ChromeDriver();
			// driver.manage().timeouts().pageLoadTimeout(100,
			// TimeUnit.SECONDS);

			// Document doc = null;

			String URL = "";
			URL = AllURL.get(i);
			cw.main(URL, driver);

		}
	}

	public static ArrayList<String> readFile() {

		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME = "C:\\Users\\Emad\\workspace\\x3\\AlexaTopSite_New_B_localDataSet.txt";
		ArrayList<String> AllURL = new ArrayList<String>();

		try {

			// br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				AllURL.add(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return AllURL;
	}

}
