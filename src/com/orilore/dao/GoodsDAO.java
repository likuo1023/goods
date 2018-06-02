package com.orilore.dao;
/**
 * 商品信息数据访问对象
 * @author Yue
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orilore.db.DBUtil;
import com.orilore.model.Goods;

public class GoodsDAO implements IGoodsDAO{
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	/**
	 * 删除商品信息的方法
	 * @param int id
	 * @return boolean
	 */
	public boolean delete(int id){
		boolean flag = false;
		String sql = "delete from goods where id=?";
		try {
			this.conn = DBUtil.getConnector();
			this.ps = this.conn.prepareStatement(sql);
			this.ps.setInt(1,id);
			if(this.ps.executeUpdate()>0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return flag;
	}
	
	/**
	 * 查询一条商品信息的方法
	 * @param int id
	 * @return Goods bean
	 */
	public Goods select(int id){
		Goods bean = null;
		String sql = "select a.*,b.name kname from goods a left join kind b on a.kid=b.id where a.id=?";
		try{
			this.conn = DBUtil.getConnector();
			this.ps = this.conn.prepareStatement(sql);
			this.ps.setInt(1, id);
			this.rs = this.ps.executeQuery();
			if(this.rs.next()){
				bean = new Goods();
				bean.setId(this.rs.getInt("id"));
				bean.setName(this.rs.getString("name"));
				bean.setSize(this.rs.getString("size"));
				bean.setKid(this.rs.getInt("kid"));
				bean.setKname(this.rs.getString("kname"));
				bean.setBrand(this.rs.getString("brand"));
				bean.setAddr(this.rs.getString("addr"));
				bean.setPrice(this.rs.getFloat("price"));
				bean.setInfo(this.rs.getString("info"));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return bean;
	}
	
	/**
	 * 查询商品信息的方法
	 * @return List<Goods>
	 */
	public List<Goods> select(){
		List<Goods> list = new ArrayList<Goods>();
		String sql = "select a.*,b.name kname from goods a left join kind b on a.kid=b.id order by id desc";
		try{
			this.conn = DBUtil.getConnector();
			this.ps = this.conn.prepareStatement(sql);
			this.rs = this.ps.executeQuery();
			while(this.rs.next()){
				Goods bean = new Goods();
				bean.setId(this.rs.getInt("id"));
				bean.setName(this.rs.getString("name"));
				bean.setSize(this.rs.getString("size"));
				bean.setKid(this.rs.getInt("kid"));
				bean.setKname(this.rs.getString("kname"));
				bean.setBrand(this.rs.getString("brand"));
				bean.setAddr(this.rs.getString("addr"));
				bean.setPrice(this.rs.getFloat("price"));
				bean.setInfo(this.rs.getString("info"));
				list.add(bean);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return list;
	}
	/**
	 * 向商品信息表中添加一条新记录
	 * @param Goods bean
	 * @return boolean
	 */
	public boolean insert(Goods bean){
		boolean flag = false;
		String sql = "insert into goods(name,kid,size,brand,addr,info,price) values(?,?,?,?,?,?,?)";
		try{
			this.conn = DBUtil.getConnector();
			this.ps = this.conn.prepareStatement(sql);
			this.ps.setString(1, bean.getName());
			this.ps.setInt(2, bean.getKid());
			this.ps.setString(3, bean.getSize());
			this.ps.setString(4, bean.getBrand());
			this.ps.setString(5, bean.getAddr());
			this.ps.setString(6, bean.getInfo());
			this.ps.setFloat(7, bean.getPrice());
			if(this.ps.executeUpdate()>0){
				flag = true;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			this.close();
		}
		return flag;
	}
	
	//关闭数据库对象的方法
	public void close(){
		try {
			if(this.rs!=null) this.rs.close();
			if(this.ps != null) this.ps.close();
			if(this.conn!=null && !this.conn.isClosed()) this.conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改商品信息的方法
	 * @param Goods bean
	 * @return boolean
	 */
	@Override
	public boolean update(Goods bean) {
		boolean flag = false;
		String sql = "update goods set name=?,size=?,brand=?,price=?,addr=?,info=?,kid=? where id=?";
		try {
			this.conn = DBUtil.getConnector();
			this.ps = this.conn.prepareStatement(sql);
			this.ps.setString(1, bean.getName());
			this.ps.setString(2, bean.getSize());
			this.ps.setString(3, bean.getBrand());
			this.ps.setFloat(4, bean.getPrice());
			this.ps.setString(5, bean.getAddr());
			this.ps.setString(6, bean.getInfo());
			this.ps.setInt(7, bean.getKid());
			this.ps.setInt(8, bean.getId());
			if(this.ps.executeUpdate()>0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
		return flag;
	}
	
}
