package com.orilore.db;
import java.sql.*;
/**
 * 提供数据连接对象的工具类
 * @author Yue
 */
public class DBUtil {
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String uid = "root";
	private static String pwd = "123456";
	/**
	 * 建立MySQL数据库连接
	 * @return Connection
	 */
	public static Connection getConnector(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,uid,pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
