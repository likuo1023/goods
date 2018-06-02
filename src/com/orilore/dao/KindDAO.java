package com.orilore.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.orilore.db.DBUtil;
import com.orilore.model.Kind;
/**
 * ��Ʒ�������ݷ��ʶ���
 * @author Yue
 */
public class KindDAO {
	//�������ݿ����Ӷ���
	private Connection conn;
	//����SQL���ִ����
	private PreparedStatement ps;
	//�ر����ݿ�ӿڶ���
	public void close(){
		try {
			if(this.ps!=null) this.ps.close();
			if(this.conn!=null && !this.conn.isClosed()) this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��Kind�������һ���¼�¼
	 * @param Kind
	 * @return boolean
	 */
	public boolean insert(Kind bean){
		boolean flag = false;
		String name = bean.getName();
		//Ԥ����SQL
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
