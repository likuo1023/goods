package com.orilore.db;
import java.sql.*;
/**
 * �ṩ�������Ӷ���Ĺ�����
 * @author Yue
 */
public class DBUtil {
	private static String url = "jdbc:mysql://localhost:3306/test";
	private static String uid = "root";
	private static String pwd = "123456";
	/**
	 * ����MySQL���ݿ�����
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
