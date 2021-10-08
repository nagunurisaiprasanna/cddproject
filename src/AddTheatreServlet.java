import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.DBConnection;

@WebServlet("/addtheatre") 

public class AddTheatreServlet extends HttpServlet
{

	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		
		String tname = req.getParameter("tname"); 
		String year = req.getParameter("year");
		String address = req.getParameter("address");
		String pincode = req.getParameter("pincode");
		String capacity = req.getParameter("capacity");
		String tprice = req.getParameter("tprice");
	
		try {
			
			Connection con= DBConnection.getConnection();
			
			PreparedStatement psmt=con.prepareStatement("insert into theatre(name,establishedyear,address,pincode,capacity,ticketprice) values(?,?,?,?,?,?)");
			
			
			psmt.setString(1, tname);
			psmt.setString(2, year);
			psmt.setString(3, address);
			psmt.setString(4, pincode);
			psmt.setString(5, capacity);
			psmt.setString(6, tprice);
			
			int i=psmt.executeUpdate();
			
			if(i>0) 
			{
				
				out.println("<b>Theatre Added Successfully</b><br>");
	    		out.println("<a href=addtheatres.html> Add Another</a>");
				
			}
		
		}
		catch(Exception e)
		{
			out.println(e);
		}
	
	}
	
}
 