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

@WebServlet("/booktickets1") 

public class BookTicketsServlet1 extends HttpServlet
{

	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		
		int value=(int)((Math.random()*999999))+1;
		
		String bookingid="TKT"+value;
		
		HttpSession session= req.getSession(false);
		
		String customeruname=(String)session.getAttribute("uname");
		
		int movieid =Integer.parseInt(req.getParameter("movieid"));
		
		int theatreid =Integer.parseInt(req.getParameter("theatreid"));
		
		String showdate =req.getParameter("showdate");
		String showslot =req.getParameter("showslot");
		int nooftickets = Integer.parseInt(req.getParameter("nooftickets"));
		
		int capacity=0;
		double ticketprice=0.0;
		int ticketsbooked=0;
		String status="BOOKED";
		
		 try
		    {
		    	
		    	Connection con=DBConnection.getConnection();
		    	
		    	PreparedStatement psmt=con.prepareStatement("select capacity,ticketprice from theatre where theatreid= ? ");
		    			    	
		    	
		    	psmt.setInt(1, theatreid);

		    	ResultSet rs=psmt.executeQuery();
		    	
		    	if(rs.next()) {
		    		capacity = rs.getInt(1);
		    		ticketprice = rs.getInt(1);
		    	
		    	}
		    	
		    	
		    } 
		    catch(Exception e) {
		    	out.println(e);
		    }
		 
		 
		 double totalprice = nooftickets * ticketprice ; 
		 
		 try {
		      Connection con = DBConnection.getConnection();
		      
		      PreparedStatement pstmt = con.prepareStatement("select sum(nooftickets) from ticketbooking where theatreid= ? and movieid=? and showslot=? and status!=? and showdate=?");
		     
		      pstmt.setInt(1,theatreid);
		      pstmt.setInt(2,movieid);
		      pstmt.setString(3,showslot);
		      pstmt.setString(4, "CANCELLED"); 
		      pstmt.setString(5, showdate);
		      
		      ResultSet rs = pstmt.executeQuery();
		      
		      if(rs.next())
		      {
		        ticketsbooked=rs.getInt(1);
		      }
		      
		    }
		    catch(Exception e) {
		      out.println(e);
		    }
		 
		 
			
		if(ticketsbooked +nooftickets<= capacity)
		{
		 
		 
		try {
			
			Connection con= DBConnection.getConnection();
			
			PreparedStatement psmt=con.prepareStatement("insert into ticketbooking(bookingid,customeruname,movieid,theatreid,showdate,showslot,nooftickets,price,status) values(?,?,?,?,?,?,?,?,?)");
			
			
			psmt.setString(1, bookingid);
			psmt.setString(2, customeruname);
			psmt.setInt(3, movieid);
			psmt.setInt(4, theatreid);
			psmt.setString(5,showdate);
			psmt.setString(6,showslot);
			psmt.setInt(7,nooftickets);
			psmt.setDouble(8, totalprice);
			psmt.setString(9, status);
			
		
			int i=psmt.executeUpdate();
			
			if(i>0) 
			{
				
				out.println("<b>Tickets booked Successfully</b><br>");
	    		out.println("<a href=booktickets> Back</a>");
				
			}
	
		}
		catch(Exception e)
		{
			out.println(e);
		}

		}
		
		else {
			
			out.println("<b>Tickets filled</b><br>");
    		out.println("<a href=booktickets> Back</a>");
		
		}
	}
	
	
	
}
 