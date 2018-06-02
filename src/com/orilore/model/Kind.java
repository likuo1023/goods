package com.orilore.model;
/**
 * 商品类别数据模型（实体类，JavaBean类）
 * @author Yue
 */
public class Kind {
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
