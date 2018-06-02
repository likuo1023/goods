package com.orilore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orilore.dao.GoodsDAO;
import com.orilore.model.Goods;

@WebServlet("/goodsremove.do")
public class GoodsRemove extends HttpServlet {
	private GoodsDAO dao = new GoodsDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String method = request.getParameter("method");
		if(method.equals("remove")){
			boolean flag = dao.delete(Integer.parseInt(id));
			if(flag){
				response.sendRedirect("goods.do");
			}else{
				response.sendRedirect("error.html");
			}
		}else{
			Goods bean = this.dao.select(Integer.parseInt(id));
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<link href='css/style.css' type='text/css' rel='stylesheet'>");
			out.print("<h2>��Ʒ����</h2><hr/>");
			out.print("<form action='goods.do?id="+bean.getId()+"' method='post'>");
			out.print("<div class='row'><label>��Ʒ���ƣ�</label><input name='name' value='"+bean.getName()+"'></div>");
			out.print("<div class='row'><label>��Ʒ���</label>");
			out.print("<select name='kid'>");
			out.print("<option value='7'>ͨѶ�豸</option>");
			out.print("<option value='8'>��ͯ��װ</option>");
			out.print("</select>");
			out.print("</div>");
			out.print("<div class='row'><label>��Ʒ�ͺţ�</label><input name='size' value='"+bean.getSize()+"'></div>");
			out.print("<div class='row'><label>��ƷƷ�ƣ�</label><input name='brand' value='"+bean.getBrand()+"'></div>");
			out.print("<div class='row'><label>��Ʒ�۸�</label><input name='price' value='"+bean.getPrice()+"'></div>");
			out.print("<div class='row'><label>��Ʒ���أ�</label><input name='addr' value='"+bean.getAddr()+"'></div>");
			out.print("<div class='row'><label>��Ʒ���飺</label><textarea name='info' rows=10>"+bean.getInfo()+"</textarea></div>");
			out.print("<div class='row'><button>����</button></div>");
			out.print("</form>");
		}	
	}
}
