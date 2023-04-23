package OurWork2_DataSet;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearch_Selenium {
	public static ArrayList<String> main(String S , int number ,WebDriver driver) {
		ArrayList<String> UrlList;
		UrlList = new ArrayList<String>();
		

	    driver.get("http://www.google.com");

	    WebElement element = driver.findElement(By.name("q"));
	    element.sendKeys(S); // send also a "\n"
	    element.submit();

	    // wait until the google page shows the result
	    WebElement myDynamicElement = (new WebDriverWait(driver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

	    List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));

	    // this are all the links you like to visit
	    for (WebElement webElement : findElements)
	    {
	        System.out.println(webElement.getAttribute("href"));
	        UrlList.add(webElement.getAttribute("href"));
	    }
	  return UrlList;  
	}
}
