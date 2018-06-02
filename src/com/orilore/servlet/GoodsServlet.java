package com.orilore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orilore.dao.GoodsDAO;
import com.orilore.model.Goods;
@WebServlet("/goods.do")
public class GoodsServlet extends HttpServlet {
	private GoodsDAO dao = new GoodsDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		List<Goods> list = dao.select();
		out.print("<table border='1' width='90%' align='center'>");
		out.print("<tr>");
		out.print("<th>序号</th><th>商品名称</th><th>类别</th><th>型号</th>");
		out.print("<th>价格</th><th>品牌</th><th>产地</th><th>详情</th><th>删除</th>");
		out.print("</tr>");
		int i = 0;
		for(Goods bean : list){
			out.print("<tr>");
			out.print("<td>"+(++i)+"</td>");	
			out.print("<td>"+bean.getName()+"</td>");
			out.print("<td>"+bean.getKname()+"</td>");
			out.print("<td>"+bean.getSize()+"</td>");	
			out.print("<td>"+bean.getPrice()+"</td>");	
			out.print("<td>"+bean.getBrand()+"</td>");	
			out.print("<td>"+bean.getAddr()+"</td>");
			out.print("<td style='text-align:center'>");
			out.print("<a href='goodsremove.do?method=find&id="+bean.getId()+"'>详情</a>");
			out.print("</td>");
			out.print("<td style='text-align:center'>");
			out.print("<a href='goodsremove.do?method=remove&id="+bean.getId()+"'>删除</a>");
			out.print("</td>");
			out.print("</tr>");
		}
		out.print("</table>");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理请求中的中文字符
		request.setCharacterEncoding("utf-8");
		/**********从请求中获取所有商品信息****************/
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String kid = request.getParameter("kid");
		String size = request.getParameter("size");
		String brand = request.getParameter("brand");
		String price = request.getParameter("price");
		String addr = request.getParameter("addr");
		String info = request.getParameter("info");
		/*****************************************************/
		/*******将商品信息封装到Goods模型对象中**********/
		Goods bean = new Goods();
		bean.setName(name);
		bean.setSize(size);
		bean.setAddr(addr);
		bean.setBrand(brand);
		bean.setInfo(info);
		bean.setKid(Integer.parseInt(kid));
		bean.setPrice(Float.parseFloat(price));
		/**************将Goods模型对象传给DAO**************/
		boolean flag = false;
		if(id==null){
			flag = this.dao.insert(bean);
		}else{
			bean.setId(Integer.parseInt(id));
			flag = this.dao.update(bean);
		}
		if(flag){
			response.sendRedirect("goods.do");
		}else{
			response.sendRedirect("error.html");
		}
	}

}
