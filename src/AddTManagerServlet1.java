import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.DBConnection;

@WebServlet("/addtmanager1") 

public class AddTManagerServlet1 extends HttpServlet
{

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		
		String name = req.getParameter("name"); 
		String gender = req.getParameter("gender");
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String location = req.getParameter("location");
		String theatreid =req.getParameter("theatreid");
		
		int tid = Integer.parseInt(theatreid);
		try {
			
			Connection con= DBConnection.getConnection();
			
			PreparedStatement psmt=con.prepareStatement("insert into manager(name,gender,username,email,password,mobileno,location,theatreid) values(?,?,?,?,?,?,?,?)");
			
			psmt.setString(1, name);
			psmt.setString(2, gender);
			psmt.setString(3, uname);
			psmt.setString(4, email);
			psmt.setString(5, pwd);
			psmt.setString(6, mobile);
			psmt.setString(7, location);
			psmt.setInt(8, tid);
			
			int i=psmt.executeUpdate();
			
			if(i>0) {
				
				out.println("<b>Add Theatre Manager Successfully</b><br>");
	    		out.println("<a href='addtmanager'> Add Another</a>");
				
			}
			
	}
		
		catch(Exception e)
		{
			out.println(e);
		}
		
	}
	
	
	
}
 