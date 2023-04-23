package FinalTest_OurWork_PhishWho;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//import org.jboss.netty.util.internal.SystemPropertyUtil;
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

	public static void main(WebDriver driver,String URL,int i,String type) {

//				-------------------------------------------Read url from file -------------------
	    	   System.out.println(i);

				try {
					Filesave(T_Plain(driver) , String.valueOf(i) , String.valueOf(i),type);

				} catch (Exception e) {
					ErrorSave(String.valueOf(i),String.valueOf(i),URL);
			        TemporaryFilesystem tempFS = TemporaryFilesystem.getDefaultTmpFS();
			        tempFS.deleteTemporaryFiles();
//					driver.close();
//					driver.quit();
				}
		        TemporaryFilesystem tempFS = TemporaryFilesystem.getDefaultTmpFS();
		        tempFS.deleteTemporaryFiles();
//				driver.close();
//				driver.quit();
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
public static void Filesave(String Word  ,String id , String i,String type){
	try {
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\WEBSIT_TEXT_File2\\"+type+"\\URLLocal_ContentInternet\\"+i+".txt", true)));
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
