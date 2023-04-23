package Convert_Web_Content_to_file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.TemporaryFilesystem;

import LPD.Alexa_List;
import LPD.PhishTank_List;
import ReadFromFolder.FileOfFolder;

public class Convert_web2File_Internet {

	public static void main(String[] args) {

		

//		-------------------------------ALEXA Page------------------------
		PhishTank_List al=new PhishTank_List();
		ArrayList<String> URL_file=new ArrayList<String>();
		URL_file=al.TopLis();
//		-----------------------------------------------------------------
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Emad\\workspace\\x2\\libs\\selenium\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
//		driver.manage().window().setPosition(new Point(-2000, 0));
		
	       for (int i = 0 ; i < 505; i++) {
			

//			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
//	   		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x2\\libs\\selenium\\geckodriver.exe");
//		    WebDriver driver = new FirefoxDriver();
//			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

	    	   String URL=URL_file.get(i);
//				-------------------------------------------Read url from file -------------------
	    	   System.out.println(i);

				
				try {
					driver.get(URL);
					Filesave(T_Plain(driver) , String.valueOf(i) , String.valueOf(i));

				} catch (Exception e) {
					ErrorSave(String.valueOf(i),String.valueOf(i),URL);
			        TemporaryFilesystem tempFS = TemporaryFilesystem.getDefaultTmpFS();
			        tempFS.deleteTemporaryFiles();
//					driver.close();
//					driver.quit();
					continue;
				}
		        TemporaryFilesystem tempFS = TemporaryFilesystem.getDefaultTmpFS();
		        tempFS.deleteTemporaryFiles();
//				driver.close();
//				driver.quit();
	       }
    
	}
public static String T_Plain(WebDriver driver) {
		  String Word = " ";
		  List<WebElement> meta_link = driver.findElements(By.xpath("//meta"));
		    
		   //----------------------  Text(Title)      -------------------------
		    String Title_String=driver.getTitle();
			
			//----------------------  Text(body)      -------------------------
			    
			String body_String= driver.findElement(By.tagName("body")).getText() ;
			
			//----------------------     Text  -------------------------
			String meta_text = " ";
			for (WebElement webElement : meta_link)
		    {
		    	 meta_text = meta_text.concat(" ").concat(webElement.getAttribute("content"));

		    }
			
			Word = Title_String.concat(body_String).concat(meta_text);
		   //-------------------------------------------------------------------
		  return  Word;
}	
public static void Filesave(String Word  ,String id , String i){
	try {
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\WEBSIT_TEXT_File\\"+i+".txt", true)));
	    out.println(Word);
	    out.close();
	} catch (IOException e) {
	    //exception handling left as an exercise for the reader
	 }
}

public static void ErrorSave( String i , String id , String URL ){
	try {
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\WEBSIT_TEXT_File\\Error\\ErrorList.txt", true)));
	    out.println(i+"-"+id+"-"+URL);
	    out.close();
	} catch (IOException e) {
	    //exception handling left as an exercise for the reader
	 }
}

public static int ordinalIndexOf(String str, String substr, int n) {
    int pos = str.indexOf(substr);
    while (--n > 0 && pos != -1)
        pos = str.indexOf(substr, pos + 1);
    return pos;
}
}
