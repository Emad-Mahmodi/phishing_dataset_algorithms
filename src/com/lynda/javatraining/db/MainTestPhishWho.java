package com.lynda.javatraining.db;

import PhishWho.*;
import SaveResultIntoFile.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.beans.website;
import com.lynda.javatraining.db.tables.AdminManager2;
import com.lynda.javatraining.util.InputHelper;

import ParsingWithSoup_xxx.*;
import netPC.*;
import netPC.ReadNetworkFile;

public class MainTestPhishWho {

	public static void main(String[] args) throws Exception {
			
		AdminManager2.displayAllRows();
		ArrayList<Integer> x=AdminManager2.id;
		
		MyPhishwho mytest=new MyPhishwho();
		for (int i = 0; i < x.size(); i++) {
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
		
		//-----------------------  Run PhishWho   -----------------------
		int result;
		try {
			Document doc = Jsoup.parse(Content);
			result = mytest.main(URL,doc);
		} catch (Exception e) {
			result=3;
		}
		SaveToFile sv=new SaveToFile();
//		sv.Filesave(Phish_id, URL, lable,Integer.toString(result));
		System.out.println(result);

		
		}
	}


}
