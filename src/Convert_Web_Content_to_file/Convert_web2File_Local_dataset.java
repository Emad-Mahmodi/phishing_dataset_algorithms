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

import ReadFromFolder.FileOfFolder;

public class Convert_web2File_Local_dataset {

	public static void main(String[] args) {
		String FolderName="E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit";
//		String FolderName="E:\\Wamp\\wamp64\\www\\Phish\\dlweb_phish";
		

		FileOfFolder fifo=new FileOfFolder();
		fifo.main(FolderName);
		ArrayList<String> html=FileOfFolder.htmlcontent_path;
		ArrayList<String> URL_file=FileOfFolder.URL_path;
		ArrayList<String> error=FileOfFolder.error_path;
		ArrayList<String> Parsing_Error=FileOfFolder.error_path;
		String File_Number="";
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Emad\\workspace\\x2\\libs\\selenium\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
//		driver.manage().window().setPosition(new Point(-2000, 0));
		
	       for (int i = 0 ; i < 5000; i++) {
			

//			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
//	   		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Emad\\workspace\\x2\\libs\\selenium\\geckodriver.exe");
//		    WebDriver driver = new FirefoxDriver();
//			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	    	   
	    	   
	    	   
				String Phish_id=URL_file.get(i);
				String SThtml=html.get(i);
//				-------------------------------------------Read url from file -------------------
	        	File_Number = Phish_id.substring(ordinalIndexOf(Phish_id, "\\", 6)+1 , ordinalIndexOf(Phish_id, "\\", 7));

				String URL="";
				try (BufferedReader br = new BufferedReader(new FileReader(URL_file.get(i)))) {
					String sCurrentLine;
					while ((sCurrentLine = br.readLine()) != null) {
						URL=sCurrentLine;
					}
				} catch (IOException e) {
					Parsing_Error.add(URL);
				}
				//-------------------------------------------------------------
				SThtml=SThtml.substring(SThtml.indexOf("www")+4, SThtml.length());
				SThtml=SThtml.replace("\\", "/");
				SThtml="http://localhost/".concat(SThtml);
				
				try {
					driver.get(SThtml);
					Filesave(T_Plain(driver) , File_Number , String.valueOf(i));

				} catch (Exception e) {
					ErrorSave(String.valueOf(i),File_Number,URL);
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
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\WEBSIT_TEXT_File\\"+i+"-"+id+"-phish.txt", true)));
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
