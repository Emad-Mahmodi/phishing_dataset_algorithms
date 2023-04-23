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

import org.apache.xerces.util.SynchronizedSymbolTable;
import org.jboss.netty.util.internal.SystemPropertyUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.TemporaryFilesystem;

import ReadFromFolder.FileOfFolder;

public class retrival_URL_from_localDataSet {

	public static void main(String[] args) {
		String FolderName="E:\\Wamp\\wamp64\\www\\leg\\dlweb_legit";
		

		FileOfFolder fifo=new FileOfFolder();
		fifo.main(FolderName);
		ArrayList<String> html=FileOfFolder.htmlcontent_path;
		ArrayList<String> URL_file=FileOfFolder.URL_path;
		ArrayList<String> error=FileOfFolder.error_path;
		ArrayList<String> Parsing_Error=FileOfFolder.error_path;
		String File_Number="";
		
		   int[][] Top_site = new int[2][4995];
		   int[][] My_Top_site = new int[2][4995];
		   ArrayList<String> My_URL = new ArrayList<String>();
	       for (int i = 0 ; i < 4995; i++) {
			

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
				Top_site[0][i]=Integer.valueOf(File_Number);
				Top_site[1][i]=i;
				My_URL.add(URL);
				//-------------------------------------------------------------
	       }
	       My_Top_site = Sort(Top_site);
	       for (int i = 0; i < Top_site[1].length; i++) {
	    	   Filesave(My_URL.get(Top_site[1][i]));
	       }
			

    
	}

public static void Filesave(String URL ){
	try {
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("AlexaTopSite_New_B_localDataSet.txt", true)));
	    out.println(URL);
	    out.close();
	} catch (IOException e) {
	    //exception handling left as an exercise for the reader
	 }
}

public static int[][] Sort( int[][] data2 ){
  	//		  ---------------------------------sort [iteration][index]----------------------------
	
	//			=========================================================================
		int temp;
		for(int i=1;i<data2[0].length;i++)
			for(int j=0;j<data2[0].length-i;j++){
				if(data2[0][j]>data2[0][j+1]){
					temp=data2[0][j+1];
					data2[0][j+1]=data2[0][j];
					data2[0][j]=temp;
					temp=data2[1][j+1];
					data2[1][j+1]=data2[1][j];
					data2[1][j]=temp;
				}
			}
		return data2;
}
public static int ordinalIndexOf(String str, String substr, int n) {
    int pos = str.indexOf(substr);
    while (--n > 0 && pos != -1)
        pos = str.indexOf(substr, pos + 1);
    return pos;
}
}
