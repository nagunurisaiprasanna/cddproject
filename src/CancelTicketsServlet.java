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

 @WebServlet("/canceltickets") 

public class CancelTicketsServlet extends HttpServlet
{
  
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
  {
    
    res.setContentType("text/html"); // For rendering, else html code is displayed
    
    PrintWriter out = res.getWriter();
    
    

	HttpSession session= req.getSession(false);
	
	String uname=(String)session.getAttribute("uname");
	    
	String bookingid=req.getParameter("bookingid");
	
	String status="CANCELLED";
	
	
	 try
	    {
	    	
	    	Connection con=DBConnection.getConnection();
	    	PreparedStatement psmt = con.prepareStatement("update ticketbooking set status=? where bookingid=? ");
	    	
	    	psmt.setString(1, status);
	    	psmt.setString(2,bookingid);
	    	
	    	int i = psmt.executeUpdate();
//	    	out.print(i);
	    	if(i>0)
	    	{
	    		
	    		out.println("<b> Tickets Cancelled</b>");
	    		out.println("<a href='booktickets'>Back</a>");
	    	}
	    	
	     	
	    	 
	    }
	    catch(Exception e) {
	    	out.println(e);
	    }
    
    
  }
  
}