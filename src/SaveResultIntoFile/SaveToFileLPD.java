package SaveResultIntoFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SaveToFileLPD {


	public static void Filesave(int i2,String Phish_id , String Title,String url , String orginal_lable , String predictive_result ,List<String> myx , ArrayList<String> resultSorted ,ArrayList<String> UrlListOfGoogleSearch ,ArrayList<String> SLD , String zz2 , String status , int number_google_occure) {
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\FN_FP_TN_TP_( LPD )\\LPD_result.txt", true)));
		    PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Emad\\workspace\\x2\\FN_FP_TN_TP_( LPD )\\LPD_result2.txt", true)));

//		    out2.println("id"+","+"url"+","+"Orginal_lable"+","+"Predective_Lable");
		    out2.println(i2+"-"+Phish_id+","+url+","+orginal_lable+","+predictive_result);

		    
		    String S="";
		    String S1="";
		    String S2="";
		    String S3="";
		    
		    for (int i = 0; i < myx.size(); i++) {
				S=S.concat("<td>").concat(myx.get(i)).concat("</td>");
			}
		    myx.clear();
		    
		    for (int i = 0; i < resultSorted.size(); i++) {
		    	S1=S1.concat("<td>").concat(resultSorted.get(i)).concat("</td>");
			}
		    resultSorted.clear();
		    
		    for (int i = 0; i < UrlListOfGoogleSearch.size(); i++) {
		    	if(number_google_occure-1==i){
		    	S2=S2.concat("<td bgcolor=\"yellow\">").concat(UrlListOfGoogleSearch.get(i)).concat("</td>");
		    	}else{
			    S2=S2.concat("<td>").concat(UrlListOfGoogleSearch.get(i)).concat("</td>");
		    	}
			}
		    UrlListOfGoogleSearch.clear();
		    
		    for (int i = 0; i < SLD.size(); i++) {
		    	if(number_google_occure-1==i){
		    	S3=S3.concat("<td  bgcolor=\"yellow\">").concat(SLD.get(i)).concat("</td>");
		    	}else {
			    S3=S3.concat("<td>").concat(SLD.get(i)).concat("</td>");

				}
			}
		    SLD.clear();
//------------------------------------------------------------FP , TP , FN , TN Color		    
		    String color_label_detect="";
		    if (orginal_lable.equals("0") && predictive_result.equals("0")) {
		    	color_label_detect="<td bgcolor=\"green\">0</td>"
		    					 + "<td bgcolor=\"green\">0</td>";
			}else if (orginal_lable.equals("1") && predictive_result.equals("1")||orginal_lable.equals("1") && predictive_result.equals("4")) {
				color_label_detect="<td bgcolor=\"green\">1</td>"
						         + "<td bgcolor=\"green\">1</td>";
			}else if (orginal_lable.equals("0") && predictive_result.equals("1")||orginal_lable.equals("0") && predictive_result.equals("4")) {
				color_label_detect="<td bgcolor=\"red\">0</td>"
				                 + "<td bgcolor=\"red\">1</td>";
	        }else if (orginal_lable.equals("1") && predictive_result.equals("0")) {
				color_label_detect="<td bgcolor=\"pink\">1</td>"
				                 + "<td bgcolor=\"pink\">0</td>";
	        }
	        else if (orginal_lable.equals("0") && predictive_result.equals("3")||orginal_lable.equals("1") && predictive_result.equals("3")) {
				color_label_detect="<td>-</td>"
				                 + "<td>-</td>";
	        }
//------------------------------------------------------------FP , TP , FN , TN Color		    
		    String color_label_detect_URL="";
		    if (orginal_lable.equals("0") && predictive_result.equals("0")) {
		    	color_label_detect_URL="<td bgcolor=\"green\">"+url+"</td>"
		    					 + "<td bgcolor=\"green\">"+url+"</td>";
			}else if (orginal_lable.equals("1") && predictive_result.equals("1")||orginal_lable.equals("1") && predictive_result.equals("4")) {
				color_label_detect_URL="<td bgcolor=\"green\">"+url+"</td>"
						         + "<td bgcolor=\"green\">"+url+"</td>";
			}else if (orginal_lable.equals("0") && predictive_result.equals("1")||orginal_lable.equals("0") && predictive_result.equals("4")) {
				color_label_detect_URL="<td bgcolor=\"red\">"+url+"</td>"
				                 + "<td bgcolor=\"red\">"+url+"</td>";
	        }else if (orginal_lable.equals("1") && predictive_result.equals("0")) {
	        	color_label_detect_URL="<td bgcolor=\"pink\">"+url+"</td>"
				                 + "<td bgcolor=\"pink\">"+url+"</td>";
	        }
	        else if (orginal_lable.equals("0") && predictive_result.equals("3")||orginal_lable.equals("1") && predictive_result.equals("3")) {
	        	color_label_detect_URL="<td>"+url+"</td>"
				                 + "<td>"+url+"</td>";
	        }
//------------------------------------------------------------  Status		    
		    String Status="";
		    if(status.equals("Title")){
		    	Status="<td>"+Title+"</td>";
		    }else if(status.equals("Domain")){
		    	Status="<td>"+zz2+"</td>";
		    }else if(status.equals("Domain+Title")){
		    	Status="<td>"+zz2+"</td>"
		    		  +"<td>"+Title+"</td>"
		    		  +"<td>"+"yes"+"</td>";
		    }
//--------------------------------------------------------------My <tr>	background	     
		    String myTR="";
		    if(predictive_result.equals("3")){
		    	myTR="<tr bgcolor=\"Orange\">";
		    }else{
		    	myTR="<tr>";
		    }
		    out.println(
		    		myTR
		    	   +"<td>"+i2+"</td>"
                   +color_label_detect_URL//+"<td>"+url+"</td>"
		    	   +Status
		    	   +color_label_detect
		    	   +"<td>"+Integer.toString(number_google_occure)+"</td>"
		    	   +S3
		    	   +S2
		    	   +"<td>"+Phish_id+"</td>"
		    	   + "</tr>"
		    		);
		    out.close();
		    out2.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}

	}

}
