import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dbconnection.DBConnection;

 @WebServlet("/checksuperadmin") 

public class checksuperadmin extends HttpServlet
{
  
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
  {
    
    res.setContentType("text/html"); // For rendering, else html code is displayed
    
    PrintWriter out = res.getWriter();
    
    String uname=req.getParameter("uname");
    String pwd=req.getParameter("pwd");
    
    System.out.println(uname+" "+pwd);
    
    try
    {
    	
    	Connection con=DBConnection.getConnection();
    	
    	PreparedStatement psmt=con.prepareStatement("select * from superadmin where username=? and password=?");
    	
    	psmt.setString(1, uname);
    	psmt.setString(2, pwd);
    	
    	
    	ResultSet rs=psmt.executeQuery();
    	
    	if(rs.next())
    	{
    		//out.print("login successful");
    		
    		RequestDispatcher rd=req.getRequestDispatcher("superadminhome.html");
    		
    		rd.forward(req, res);
    		
    		
    		
    	}
    	else {
    		out.println("<b>login failed</b><br>");
    		out.println("<a href=superadminlogin.html> Try again</a>");
    		
    	}
    	
    	
    } 
    catch(Exception e) {
    	out.println(e);
    }
    
    
  }
 
}