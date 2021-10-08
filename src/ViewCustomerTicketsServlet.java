import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.DBConnection;

 @WebServlet("/viewcustomertickets") 

public class ViewCustomerTicketsServlet extends HttpServlet
{
  
  public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
  {
    
    res.setContentType("text/html"); // For rendering, else html code is displayed
    
    PrintWriter out = res.getWriter();
    

	HttpSession session= req.getSession(false);
	
	String uname=(String)session.getAttribute("uname");
	

	out.println("<h1 align=center> Welcome " +uname+"</h1>");

	out.println("<h2 align=center>Tickets booked Successfully</h2>");
	
    out.println("<table border=2 align=center>");
    out.println("<tr>");
    
    out.println("<th> Booking ID </th>");
    
    out.println("<th> Movie Id</th>");
    out.println("<th> Theatre Id </th>");
    out.println("<th> Show Date</th>");
    out.println("<th> Show Slot</th>");
    out.println("<th> No of tickets</th>");
    out.println("<th> Price</th>");
    out.println("<th> Status</th>");
    out.println("<th> Transcation Time</th>");
    out.println("<th> Actions</th>");
      
    out.println("</tr>");
    
    
    
    try
    {
    	
    	Connection con=DBConnection.getConnection();
    	
    	PreparedStatement psmt=con.prepareStatement("select * from ticketbooking where customeruname=? and status!=?");
    	
    	psmt.setString(1,uname);
    	psmt.setString(2,"CANCELLED");
    	    	
    	ResultSet rs=psmt.executeQuery();
    	
    	while(rs.next())
    	{
    		out.println("<tr>");
    		
    		out.println("<td>"+rs.getString(1)+"</td>");
    	
    		out.println("<td>"+rs.getInt(3)+"</td>");
    		out.println("<td>"+rs.getInt(4)+"</td>");
    		out.println("<td>"+rs.getString(5)+"</td>");
    		out.println("<td>"+rs.getString(6)+"</td>");
    		out.println("<td>"+rs.getInt(7)+"</td>");
    		out.println("<td>"+rs.getDouble(8)+"</td>");
    		out.println("<td>"+rs.getString(9)+"</td>");
    		out.println("<td>"+rs.getTimestamp(10)+"</td>");
    		
    		out.println("<td><a href='canceltickets?bookingid="+rs.getString(1)+"'>Cancel </a>");
    		
    		out.println("&nbsp;&nbsp;");
    		out.println("<a href='updateticket?bookingid="+rs.getString(1)+"'>Update </a></td>");
    		
    		out.println("</tr>");
    		
     	
    	} 
    }
    catch(Exception e) {
    	out.println(e);
    }
    
    out.println("<table>");
  }
 
}