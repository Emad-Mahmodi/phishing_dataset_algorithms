package SaveResultIntoFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SaveToFile2 {


	public static void Filesave(String Phish_id , String url , String orginal_lable) {
		try {
		    PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("Error.txt", true)));

//		    out2.println("id"+","+"url"+","+"Orginal_lable"+","+"Predective_Lable");
		    out2.println(Phish_id+","+url+","+orginal_lable);

		    out2.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}

	}

}
