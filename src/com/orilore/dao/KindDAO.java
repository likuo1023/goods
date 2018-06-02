package com.orilore.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.orilore.db.DBUtil;
import com.orilore.model.Kind;
/**
 * 商品类别表数据访问对象
 * @author Yue
 */
public class KindDAO {
	//声明数据库连接对象
	private Connection conn;
	//声明SQL语句执行器
	private PreparedStatement ps;
	//关闭数据库接口对象
	public void close(){
		try {
			if(this.ps!=null) this.ps.close();
			if(this.conn!=null && !this.conn.isClosed()) this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 向Kind表中添加一条新记录
	 * @param Kind
	 * @return boolean
	 */
	public boolean insert(Kind bean){
		boolean flag = false;
		String name = bean.getName();
		//预编译SQL
		String sql = "insert into kind(name) values(?)";
		try{
			this.conn = DBUtil.getConnector();
			this.ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			if(ps.executeUpdate()>0){
				flag = true;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return flag;
	}
}
