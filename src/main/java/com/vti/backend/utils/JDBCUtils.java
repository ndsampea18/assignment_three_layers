package com.vti.backend.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

	public static Connection getConnection() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/main/resource/dbconfig.properties"));
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
			e1.printStackTrace();
			return null;
		} catch (IOException e1) {
			System.out.println("File IOException");
			e1.printStackTrace();
			return null;
		}
		String dbUrl = properties.getProperty("dburl");
		String dbUser = properties.getProperty("dbuser");
		String dbPass = properties.getProperty("dbpass");
		Connection connecttion = null;
		try {
			connecttion = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (connecttion != null) {
			System.out.println("Connection thành công");
			return connecttion;
		} else {
			System.out.println("Connection đến database thất bại");
			return null;
		}

	}

	public static void closeConnection(Connection con, ResultSet rs, PreparedStatement pstm, CallableStatement cs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstm != null) {
			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (cs != null) {
			try {
				cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
