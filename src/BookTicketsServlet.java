import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbconnection.DBConnection;

@WebServlet("/booktickets") 

public class BookTicketsServlet extends HttpServlet
{
 
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		res.setContentType("text/html"); // For rendering, else html code is displayed
	    
	    PrintWriter out = res.getWriter();
	    
	    RequestDispatcher rd=req.getRequestDispatcher("customermenu.html");
		
		rd.include(req, res);
		HttpSession session= req.getSession(false);
		
		String uname=(String)session.getAttribute("uname");
	    
		out.print("<h1 align=center> Welcome "+uname+"</h1>");
		

		out.println("<h2 align=center > Ticket Booking !!</h2>");
		
		out.print("<center>");
		
		out.print("<form method='post' action='booktickets1'>");
		
		out.print("<table>");
		
		
		out.println("<tr>");
		out.println("<td><b>Username</b></td>");
		out.println("<td>"+uname+"</td>");
		
		out.println("</tr>");
		
		
		   try
		    {
		    	
		    	Connection con=DBConnection.getConnection();
		    	
		    	PreparedStatement psmt=con.prepareStatement("select * from movie");
		    	    	
		    	ResultSet rs=psmt.executeQuery();
		    	
		    	out.println("<tr>");
		    	
		    	out.println("<td><b>Movie name</b> </td>");
		    	
		    	out.println("<td><select name='movieid'>");
		    	out.println("<option value=''>--Select--</option>");
		    	
		    
		    	while(rs.next())
		    	{
		    	
		    		out.println("<option value="+rs.getString("movieid")+">");
		    		
		    		out.println(rs.getString("moviename"));
		    		
		    		out.println("</option>");
		    		
		     	
		    	} 
		    	out.println("</select>");
		    	
		    	out.println("</td>");
		    	
		    	
		    	out.println("</tr>");
		    	
		    	
		    	
		    }
		    catch(Exception e) {
		    	out.println(e);
		    }
		
		   try
		    {
		    	
		    	Connection con=DBConnection.getConnection();
		    	
		    	PreparedStatement psmt=con.prepareStatement("select * from theatre");
		    	    	
		    	ResultSet rs=psmt.executeQuery();
		    	
		    	out.println("<tr>");
		    	out.println("<td><b>Theatre Name </b> </td>");
		    	out.println("<td><select name='theatreid'>");
		    	out.println("<option value=''>--Select--</option>");
		    	
		    
		    	while(rs.next())
		    	{
		    	
		    		out.println("<option value="+rs.getString("theatreid")+">");
		    		
		    		out.println(rs.getString("name"));
		    		
		    		out.println("</option>");
		    		
		     	
		    	} 
		    	out.println("</select>");
		    	out.println("</td>");
		    	out.println("</tr>");
		    	
		    	
		    	
		    }
		    catch(Exception e) {
		    	out.println(e);
		    }
		
		   
		   

			out.println("<tr>");
			out.println("<td><b>show date</b></td>");
			out.println("<td><input type='date' name='showdate' required></td>");
			out.println("</tr>");
			
			
			out.println("<tr>");
			out.println("<td><b>show slot</b></td>");
			out.println("<td> <select name='showslot'>");
			
			out.println("<option value=''>--Select--</option>");
			out.println("<option value='9AM'>9AM</option>");
			out.println("<option value='1PM'>1PM</option>");
			out.println("<option value='6:30PM'>6:30PM</option>");
			out.println("<option value='9:30PM'>9:30PM</option>");
			out.println("</td>");
			out.println("</tr>");
			
			
			out.println("<tr>");
			out.println("<td><b>Number of tickets</b></td>");
			out.println("<td><input type='number' name='nooftickets' required></td>");
			out.println("</tr>");
		   
			out.println("<tr>");
			out.println("<td><b></b></td>");
			out.println("<td colspan=2 align=center><input type='submit' value='Proceed' required></td>");
			out.println("</tr>");
	
		out.print("</form>");
		
		out.print("</table>");
		
		out.print("</center>");
		
	    
	}
	
}