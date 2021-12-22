package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Vision_Write {
	private static Vision_Write vision = new Vision_Write();
	public static Vision_Write getWrite() {
		return vision;
	}
	
	private String returns = "";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public String write(String id, String pwd) {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/oracle_test");
			conn = ds.getConnection();
			
			String sql = "insert into TEST_USER values(seq_tuser_idx.nextVal, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			pstmt.setString(2, pwd);
			pstmt.executeUpdate();
			
			returns = String.format("{res : [{'result' : '%s'}]}", "success");
		} catch(Exception e) {
			returns = String.format("{res : [{'result' : '%s'}]}", "fail");
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				
			}
		}
		return returns;
	}
}
