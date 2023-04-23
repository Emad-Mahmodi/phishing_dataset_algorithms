package SaveResultIntoFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SaveToFile {


	public static void Filesave(String Phish_id , String url , String orginal_lable , String predictive_result ,List<String> myx , ArrayList<String> resultSorted ,ArrayList<String> UrlListOfGoogleSearch ,ArrayList<String> SLD , String zz2) {
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("PhishWho_Result.txt", true)));
		    PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("PhishWho_Result2.txt", true)));

//		    out2.println("id"+","+"url"+","+"Orginal_lable"+","+"Predective_Lable");
		    out2.println(Phish_id+","+url+","+orginal_lable+","+predictive_result);
		    
		    
		    String S="";
		    String S1="";
		    String S2="";
		    String S3="";
		    
		    for (int i = 0; i < myx.size(); i++) {
				S=S.concat("<td>\n").concat(myx.get(i)).concat("</td>\n");
			}
		    myx.clear();
		    
		    for (int i = 0; i < resultSorted.size(); i++) {
		    	S1=S1.concat("<td>\n").concat(resultSorted.get(i)).concat("</td>\n");
			}
		    resultSorted.clear();
		    
		    for (int i = 0; i < UrlListOfGoogleSearch.size(); i++) {
		    	S2=S2.concat("<td>\n").concat(UrlListOfGoogleSearch.get(i)).concat("</td>\n");
			}
		    UrlListOfGoogleSearch.clear();
		    
		    for (int i = 0; i < SLD.size(); i++) {
		    	S3=S3.concat("<td>\n").concat(SLD.get(i)).concat("</td>\n");
			}
		    SLD.clear();
		    
		    String color="";
		    if (orginal_lable.matches(predictive_result)) {
				color="<table border=\"1\">\n<thead>\n<tr>\n<td colspan=\"4\" bgcolor=\"green\" width=\"100%\">\n"+url+"   =>by Lable : "+orginal_lable +"       => Predict Lable : "+predictive_result+"   => "+Phish_id+"</td>\n</tr>\n</thead>\n";
			}else {
				color="<table border=\"1\">\n<thead>\n<tr>\n<td colspan=\"4\" bgcolor=\"red\" width=\"100%\">\n"+url+"   =>by Lable : "+orginal_lable +"       => Predict Lable : "+predictive_result+"   =>  "+Phish_id+"</td>\n</tr>\n</thead>\n";
			}
		    out.println(
		    		
//		    		Phish_id+","+url+","+orginal_lable+","+predictive_result+
		    		color
		    		+"<tbody>\n"
		    		
		    		+" <tr>\n"
		    		+"<td> 1 </td>" //1 : All Word With Weight
		    		+"<td>==></td>"
//		    		+"   <td>\n loop Of word</td>\n" 
		    		+S
		    		+" </tr>\n"
		    		
		    		+" <tr>\n"
		    		+"<td> 2 </td>" //2 : Sorted Word
		    		+"<td>==></td>"
//		    		+"   <td>\n loop Of word</td>\n" 
		    		+S1
		    		+" </tr>\n"
		    		
		    		+" <tr>\n"
		    		+" <td bgcolor=\"yellow\" >Google search:</td>\n"
		    		+"</tr>\n"
		    		
		    		+" <tr>\n"
		    		+"<td>Google Response</td>"
		    		+"<td>==></td>"
//		    		+"   <td>\nloop for Search 1</td>\n"
		    		+S2
		    		+" </tr>\n"
		    		
		    		+" <tr>\n"
		    		+"<td> 3 </td>"// 3 : Second Level Domain Of Google Response
		    		+"<td>==></td>"
//		    		+"   <td>\nloop for Search 1</td>\n"
		    		+S3
		    		+" </tr>\n"
		    		
		    		+" <tr>\n"
		    		+"<td> 4 </td>"//4 : Target Domain
		    		+"<td>==></td>"
		    		+"<td>"
		    		+zz2
		    		+"</td>"
		    		+" </tr>\n"
		    		
		    		+"</tbody>\n"
		    		+"</table>\n"
		    		+"\n"
		    		);
		    out.close();
		    out2.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}

	}

}
