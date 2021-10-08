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

 @WebServlet("/updatetickets1") 

public class UpdateTicketsServlet1 extends HttpServlet
{
  
  public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
  {
    
    res.setContentType("text/html"); // For rendering, else html code is displayed
    
    PrintWriter out = res.getWriter();
    
    

	HttpSession session= req.getSession(false);
	
	String uname=(String)session.getAttribute("uname");
	

	String bookingid=(String)session.getAttribute("bookingid");
    
	int nooftickets=Integer.parseInt(req.getParameter("nooftickets"));
	
	String status="UPDATED";
	
	int capacity=0;
	double ticketprice=0.0;
	
	int theatreid=0;
	
	try {
		
		Connection con=DBConnection.getConnection();
    	PreparedStatement psmt = con.prepareStatement(" select theatreid from ticketbooking where bookingid= ? ");
    	
    	psmt.setString(1, bookingid);
    	
		ResultSet rs= psmt.executeQuery();
		
		if(rs.next()) {
			
			theatreid = rs.getInt(1);
		}
		
	}
	catch(Exception e) {
		
		out.println(e);
	}
	
	 try
	    {
	    	
	    	Connection con=DBConnection.getConnection();
	    	
	    	PreparedStatement psmt=con.prepareStatement("select capacity,ticketprice from theatre where theatreid= ? ");
	    			    	
	    	
	    	psmt.setInt(1, theatreid);

	    	ResultSet rs=psmt.executeQuery();
	    	
	    	if(rs.next()) {
	    		
	    		capacity = rs.getInt(2);
	    		ticketprice = rs.getDouble(1);
	    	
	    	}
	    	
	    	
	    } 
	    catch(Exception e) {
	    	out.println(e);
	    }
	
	 
	 double totalprice = nooftickets * ticketprice ; 
	
	try {
		
		Connection con=DBConnection.getConnection();
    	PreparedStatement psmt = con.prepareStatement("update ticketbooking set status=?, nooftickets=? , price=? where bookingid=? ");
    	
		psmt.setString(1, status);
		psmt.setInt(2,nooftickets);
		psmt.setDouble(3, totalprice);
		psmt.setString(4, bookingid);
		
		
		int i=psmt.executeUpdate();
		
		if(i>0) 
		{
			
			out.println("<b>Tickets Updated Successfully</b><br>");
    		out.println("<a href='booktickets'> Back</a>");
			
		}
	}
	catch(Exception e) {
		 
		out.println(e);
	}
	
  }
  
}
    