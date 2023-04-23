package Selenium_Text_URL;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class test1 {

	public static void main(String[] args) throws InterruptedException {

		ArrayList<String> src;
		ArrayList<String> href;
		ArrayList<String> title;
		ArrayList<String> body;
		ArrayList<String> meta;


		src = new ArrayList<String>();
		href = new ArrayList<String>();
		title = new ArrayList<String>();
		body = new ArrayList<String>();
		meta = new ArrayList<String>();


		String S="http://ir.voanews.com/" ;
		int number ;
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x1\\libs\\selenium\\geckodriver.exe");
	    WebDriver driver = new FirefoxDriver();

	    driver.get("https://moz.com/top500");//http://www.bbc.com/persian

//	    WebElement element = driver.findElement(By.name("q"));
//	    element.sendKeys(S); // send also a "\n"
//	    element.submit();
//
//	    // wait until the google page shows the result
	   // WebElement myDynamicElement = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a")));

	    List<WebElement> href_links = driver.findElements(By.xpath("//a"));
	    List<WebElement> src_links = driver.findElements(By.xpath("//img"));
	    List<WebElement> meta_link = driver.findElements(By.xpath("//meta"));
	    List<WebElement> Alexa_sites = driver.findElements(By.xpath(".//*[@id='top-500']//a"));
	    // this are all the links you like to visit
	    for (WebElement Alexa_site : Alexa_sites)
	    {
	        System.out.println(Alexa_site.getAttribute("href"));
	        href.add(Alexa_site.getAttribute("href"));

	    }
	    
	  //----------------------     href , src   -------------------------

	    for (WebElement href_link : href_links)
	    {
	        System.out.println(href_link.getAttribute("href"));
	        href.add(href_link.getAttribute("href"));

	    }
	    for (WebElement src_link : src_links)
	    {
	        System.out.println(src_link.getAttribute("src"));
	        src.add(src_link.getAttribute("src"));

	    }
	  //----------------------     Text(meta)   -------------------------

	    /*for (WebElement webElement : meta_link)
	    {
	        System.out.println(webElement.getAttribute("content"));
	        meta.add(webElement.getAttribute("content"));
	    }*/
	    
	  //----------------------     Text(alt)   -------------------------
	    
	    /*List<WebElement> alt = driver.findElements(By.xpath("//*"));
	    // this are all the links you like to visit
	    for (WebElement webElement : alt)
	    {
	        System.out.println(webElement.getAttribute("alt"));
//	        UrlList.add(webElement.getAttribute("href"));
	    }*/
	   //----------------------  Text(body)      -------------------------
	    
	    String yourtext= driver.findElement(By.tagName("body")).getText() ;
		  for (int i = 0; i < getWords(yourtext).size(); i++) {
			  System.out.println(getWords(yourtext).get(i));
		  }
	   //----------------------  Text(Title)      -------------------------
	    
//	    driver.getTitle();
	   //----------------------             -------------------------
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

}
