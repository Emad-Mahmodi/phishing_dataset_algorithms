package com.lynda.javatraining.db;

import LPD.*;
import PhishWho.*;
import SaveResultIntoFile.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.beans.website;
import com.lynda.javatraining.db.tables.AdminManager2;
import com.lynda.javatraining.util.InputHelper;

import ParsingWithSoup_xxx.*;
import netPC.*;
import netPC.ReadNetworkFile;

public class LPD_old_URL {

	public static void main(String[] args) throws Exception {
			
		AdminManager2.displayAllRows();
		ArrayList<Integer> x=AdminManager2.id;
		
		LPD_old_URL_algorithm mytest=new LPD_old_URL_algorithm();
		for (int i = 1610; i < x.size(); i++) {
			Integer S=x.get(i);
			website bean = AdminManager2.getRow(S);
			if (bean == null) {
				System.err.println("Row not found");
				return;
			}
			
		String URL=AdminManager2.url2;
		contentWithTag co=new contentWithTag();
//		String Content=co.coTag(URL); read online
		String Content=bean.getHtmlContent();
		String Phish_id=bean.getPhish_id();
		String lable=bean.getLable();
		GoogleSearch gs=new GoogleSearch();
		SaveToFile_2_LPD sv2 = new SaveToFile_2_LPD();
		SaveToFileLPD sv = new SaveToFileLPD();



		
		//-----------------------  Run PhishWho   -----------------------
		int result;
		try {
			Document doc = Jsoup.parse(Content);
			result = mytest.main(URL , gs);
		} catch (Exception e) {
			result=3;
			sv2.Filesave(i,Phish_id, URL, lable);
			continue;
		}
//		SaveToFile sv=new SaveToFile();
		String Title="";
		List<String> myx = LPD.myx;
		ArrayList<String> resultSorted=LPD.resultSorted;
		ArrayList<String> MyUrlListOfGoogleSearch = new ArrayList<String>();
		ArrayList<String> SLD = LPD.SLD;
		String zz2="";

		
		sv.Filesave(i,Phish_id, Title ,URL, lable,Integer.toString(result) , myx , resultSorted , MyUrlListOfGoogleSearch , SLD ,zz2);

//		sv.Filesave(Phish_id, URL, lable,Integer.toString(result));
		System.out.println(result);

		
		}
	}


}
