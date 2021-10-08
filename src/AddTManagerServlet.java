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

import dbconnection.DBConnection;

@WebServlet("/addtmanager") 

public class AddTManagerServlet extends HttpServlet
{

	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException 
	{
		
		res.setContentType("text/html");
		PrintWriter out= res.getWriter();
		
		
		RequestDispatcher rd =req.getRequestDispatcher("superadminmenu.html");
		rd.include(req, res);
		
		
		out.println("<h2 align=center > Add Theatre manager !!</h2>");
		
		out.print("<center>");
		
		out.print("<form method='post' action='addtmanager1'>");
		
		out.print("<table>");
		
		RequestDispatcher rd1=req.getRequestDispatcher("addtmanager.html");
		
		rd1.include(req, res);
	  
	    try
	    {
	    	
	    	Connection con=DBConnection.getConnection();
	    	
	    	PreparedStatement psmt=con.prepareStatement("select * from theatre");
	    	    	
	    	ResultSet rs=psmt.executeQuery();
	    	
	    	out.println("<tr>");
	    	out.println("<td><b>Theatre");
	    	out.println("<select name='theatreid'>");
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
		out.print("<tr align=center> <td> <input type='submit' value='Add'>  </td></td>");
		
		out.print("</form>");
		
		out.print("</table>");
		
		out.print("</center>");
	}
	
}