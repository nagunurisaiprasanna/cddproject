import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dbconnection.DBConnection;

 @WebServlet("/viewcustomers") 

public class ViewCustomerServlet extends HttpServlet
{
  
  public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
  {
    
    res.setContentType("text/html"); // For rendering, else html code is displayed
    
    PrintWriter out = res.getWriter();
    
    out.println("<table border=2 align=center>");
    out.println("<tr>");
    
    out.println("<th> ID </th>");
    out.println("<th> Name</th>");
    out.println("<th> Gender </th>");
    out.println("<th> Username</th>");
    out.println("<th> Email id</th>");
    out.println("<th> Mobile No</th>");
    out.println("<th> Location</th>");
      
    out.println("</tr>");
    
    
    
    try
    {
    	
    	Connection con=DBConnection.getConnection();
    	
    	PreparedStatement psmt=con.prepareStatement("select * from customer");
    	    	
    	ResultSet rs=psmt.executeQuery();
    	
    	while(rs.next())
    	{
    		out.println("<tr>");
    		
    		out.println("<td>"+rs.getInt(1)+"</td>");
    		out.println("<td>"+rs.getString(2)+"</td>");
    		out.println("<td>"+rs.getString(3)+"</td>");
    		out.println("<td>"+rs.getString(4)+"</td>");
    		out.println("<td>"+rs.getString(5)+"</td>");
    		out.println("<td>"+rs.getString(7)+"</td>");
    		out.println("<td>"+rs.getString(8)+"</td>");
    		//out.println("<td>"+rs.getString(8)+"</td>");
    		
    		
    		out.println("</tr>");
    		
     	
    	} 
    }
    catch(Exception e) {
    	out.println(e);
    }
    
    out.println("<table>");
  }
 
}