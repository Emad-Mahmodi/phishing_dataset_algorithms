package ReadFromFolder;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Folder {

		public ArrayList<String> main(String url) {

			File folder = new File(url);
			ArrayList<String> FoldersOfSites=new ArrayList<String>();
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				FoldersOfSites.add((String)listOfFiles[i].getPath());
			}
			
			return FoldersOfSites;
	}	
}
