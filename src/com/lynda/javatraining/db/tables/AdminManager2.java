package com.lynda.javatraining.db.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lynda.javatraining.db.DBType;
import com.lynda.javatraining.db.DBUtil;
import com.lynda.javatraining.db.beans.Admin;
import com.lynda.javatraining.db.beans.website;

public class AdminManager2 {
public static ArrayList<Integer> id = new ArrayList<Integer>();
public static String url2 ;

 
	public static void displayAllRows() throws SQLException {

		
		String sql2 = "SELECT COUNT(*) AS COUNT FROM websites";
		Connection conn2 = DBUtil.getConnection(DBType.MYSQL);
		Statement stmt2 = conn2.createStatement();
		ResultSet rs2 = stmt2.executeQuery(sql2);
		rs2.next();
		int count = rs2.getInt(1);
		
		String sql = "SELECT id FROM websites";
		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				){


//			System.out.println("websites Table:");
			
				StringBuffer bf = new StringBuffer();
//				bf.append(rs.getInt("id"));
		
//					 int id = rs.getInt("id");
//						System.out.println(id);

				

				for (int i = 0; i<count && rs.next(); i++) {
//						id[i] = rs.getInt("id");
						id.add(rs.getInt("id"));
//						System.out.println(id[i]);
				}

				System.out.println(bf.toString());
			
			
		}
	
	}

	public static website getRow(int webId) throws SQLException {

		String sql = "SELECT * FROM websites WHERE id = ?";
		ResultSet rs = null;

		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setInt(1, webId);
			rs = stmt.executeQuery();

			if (rs.next()) {
				website bean = new website();
				bean.setId(webId);
				bean.setPhish_id(rs.getString("id"));//bean.setPhish_id(rs.getString("phish_id"));
				bean.setUrl(rs.getString("url"));
				url2=rs.getString("url");
				
				bean.setLable(rs.getString("isPhish"));//bean.setLable(rs.getString("lable"));
				bean.setHtmlContent(rs.getString("htmlContent"));
				return bean;
			} else {
				return null;
			}

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
		}

	}

	public static boolean insert(Admin bean) throws Exception {

		String sql = "INSERT into admin (userName, password) " +
				"VALUES (?, ?)";
		ResultSet keys = null;
		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				) {
			
			stmt.setString(1, bean.getUserName());
			stmt.setString(2, bean.getPassword());
			int affected = stmt.executeUpdate();
			
			if (affected == 1) {
				keys = stmt.getGeneratedKeys();
				keys.next();
				int newKey = keys.getInt(1);
				bean.setAdminId(newKey);
			} else {
				System.err.println("No rows affected");
				return false;
			}
			
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally{
			if (keys != null) keys.close();
		}
		return true;
	}

	public static boolean update(website bean) throws Exception {

		String sql =
				"UPDATE websites SET " +
				"url = ?, phish_id = ? , lable=? , htmlContent=? " +
				"WHERE id = ?";
		try (
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			
			stmt.setString(1, bean.getUrl());
			stmt.setString(2, bean.getPhish_id());
			stmt.setString(3, bean.getLable());
			stmt.setString(4, bean.getHtmlContent());
		
			stmt.setInt(5, bean.getId());
			
			int affected = stmt.executeUpdate();
			if (affected == 1) {
				return true;
			} else {
				return false;
			}
			
		}
		catch(SQLException e) {
			System.err.println(e);
			return false;
		}

	}
	
}
