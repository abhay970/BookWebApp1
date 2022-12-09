package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.PreparableStatement;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final String query="INSERT INTO BOOK1.BOOKDATA VALUE(?,?,?)"; 
 @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 //get printWriter
	 PrintWriter pw=resp.getWriter();
	 //set content type
	 resp.setContentType("text/html");
	 //GET THE BOOK info
	 String bookName=req.getParameter("bookName");
	 String bookEdition=req.getParameter("bookEdition");
	 float bookPrice=Float.parseFloat(req.getParameter("bookPrice"));
	 //load jdbc driver
	 try {
	 Class.forName("com.mysql.cj.jdbc.Driver");
}catch(ClassNotFoundException cnf) {
	cnf.printStackTrace();
}
	 //generate the connection
	 try(Connection con=DriverManager.getConnection("jdbc:mysql:///book1", "root", "Abhay@123");
		PreparedStatement ps= con.prepareStatement(query);){ 
		 ps.setString(1, bookName);
		 ps.setString(2, bookEdition);
		 ps.setFloat(3, bookPrice);
		 int count=ps.executeUpdate();
		 if(count==1) {
			 pw.print("<h2>Record is Registered Sucessfully</h2>");
		 }else {
			 pw.print("<h2>Record is not Register Sucessfully</h2>");
		 }
	 } catch(SQLException se) {
		 se.printStackTrace();
		 pw.print("<h1>"+se.getMessage()+"</h2>");
	 }catch(Exception e) {
		 e.printStackTrace();
		 pw.print("<h1>"+e.getMessage()+"</h2>");
		  
	 }
	 pw.print("<a href='bookList'>Book List</a>");
	 pw.print("<br>");
	 pw.print("<a href='bookList'>Book List</a>");
	 }
		 
		 
		
	
	 
 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 doGet(req, resp);
	}
}
