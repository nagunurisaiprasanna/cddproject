import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.DBConnection;

@WebServlet("/checkcustomer") 

public class CheckCustomerServlet extends HttpServlet
{
 
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		res.setContentType("text/html"); // For rendering, else html code is displayed
	    
	    PrintWriter out = res.getWriter();
	    
	    String uname=req.getParameter("uname");
	    String pwd=req.getParameter("pwd");
	    
	    System.out.println(uname+" "+pwd);
	    
	    try
	    {
	    	
	    	Connection con=DBConnection.getConnection();
	    	
	    	PreparedStatement psmt=con.prepareStatement("select * from customer where username=? and password=?;");
	    	
	    	psmt.setString(1, uname);
	    	psmt.setString(2, pwd);
	    	
	    	ResultSet rs=psmt.executeQuery();
	    	
	    	if(rs.next())
	    	{
	    		//out.print("login successful");
	    		
	    		
	    		HttpSession session= req.getSession();
	    		
	    		session.setAttribute("uname", uname);
	    		
	    		
	    		RequestDispatcher rd=req.getRequestDispatcher("customerhome");
	    		
	    		rd.forward(req, res);
	    		
	    		
	    		
	    	}
	    	else {
	    		out.println("<b>login failed</b><br>");
	    		out.println("<a href=customerlogin.html> Try again</a>");
	    		
	    	}
	    	
	    	
	    } 
	    catch(Exception e) {
	    	out.println(e);
	    }
		
	}
	
}