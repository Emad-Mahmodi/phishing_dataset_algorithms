package com.lynda.javatraining.db;

import java.sql.ResultSet;

import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.beans.website;
import com.lynda.javatraining.db.tables.AdminManager2;
import com.lynda.javatraining.util.InputHelper;

import ParsingWithSoup_xxx.*;
import netPC.*;
import netPC.ReadNetworkFile;

public class Main {

	public static void main(String[] args) throws Exception {

		AdminManager2.displayAllRows();
		int[] x=AdminManager2.id;
		
//		int adminId = InputHelper.getIntegerInput("Select a row to update: ");
		
		for (int i = 0; i < x.length; i++) {
			website bean = AdminManager2.getRow(x[i]);
			if (bean == null) {
				System.err.println("Row not found");
				return;
			}
		String x2=AdminManager2.url2;
//---------------------------------------------------------------
//		ReadNetworkFile read=new ReadNetworkFile();
//		String res=read.ContentWeb(x2);
//---------------------------------------------------------------		
		contentWithTag co=new contentWithTag();
		String res=co.coTag(x2);
		bean.setHtmlContent(res);
		
		if (AdminManager2.update(bean)) {
			System.out.println("Success!");
		} else
		{
			System.err.println("whoops!");
		}
		}
	}
}
