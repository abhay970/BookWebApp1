package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Result;
@WebServlet("/bookList")

public class BookListServlet extends HttpServlet {
	private static final String query="SELECT ID BOOKNAME,BOOKEDITION,BOOKPRICE from BOOKDATA"; 
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 //get printWriter
		 PrintWriter pw=resp.getWriter();
		 //set content type
		 resp.setContentType("text/html");
		 
		 
		 //load jdbc driver
		 try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
	}catch(ClassNotFoundException cnf) {
		cnf.printStackTrace();
	}
		 //generate the connection
		 try(Connection con=DriverManager.getConnection("jdbc:mysql:///book1", "root", "Abhay@123");
			PreparedStatement ps= con.prepareStatement(query);){
			 ResultSet rs=ps.executeQuery();
			 pw.print("<table>");
			 pw.print("<tr>");
			 pw.print("<th>Book Id</th>");
			 pw.print("<th>Book Name</th>");
			 pw.print("<th>Book Edition</th>");
			 pw.print("<th>Book Price</th>");
			 pw.print("</tr>");
			 while(rs.next()) {
				 pw.print("<tr>");
				 pw.print("<td>"+rs.getInt(1)+"</td>");
				 pw.print("<td>"+rs.getString(2)+"</td>");
				 pw.print("<td>"+rs.getString(3)+"</td>");
				 pw.print("<td>"+rs.getFloat(4)+"</td>");
				 pw.print("</tr>");
				 
				 
			 }
		 pw.print("</table>");
			 
			 
		 } catch(SQLException se) {
			 se.printStackTrace();
			 pw.print("<h1>"+se.getMessage()+"</h2>");
		 }catch(Exception e) {
			 e.printStackTrace();
			 pw.print("<h1>"+e.getMessage()+"</h2>");
			  
		 }
		 pw.print("<a href='bookList'>Book List</a>");
	 }
	 @Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doGet(req, resp);
}}
