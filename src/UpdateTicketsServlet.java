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

 @WebServlet("/updateticket") 

public class UpdateTicketsServlet extends HttpServlet
{
  
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException
  {
    
    res.setContentType("text/html"); // For rendering, else html code is displayed
    
    PrintWriter out = res.getWriter();
    
    

	HttpSession session= req.getSession(false);
	
	String uname=(String)session.getAttribute("uname");
	    
	
	out.println("<h1 align=center> Welcome " +uname+"</h1>");

	out.println("<h2 align=center>Updating tickets</h2>");
	
	
	String bookingid=req.getParameter("bookingid");
	
	session.setAttribute("bookingid", bookingid);

	out.println("<h2 align=center> Welcome " +bookingid+"</h2>");
	
	out.println("<center>");
	
	out.println("Number of tickets <form method=post action=updatetickets1>");
	
	out.println("<input align=center type='number' name='nooftickets' required>");
	
	out.println("<input type='submit' value='Update'>");
	
	out.println("</form>");
	
	out.println("</center>");
	
	
    
  }
  
}