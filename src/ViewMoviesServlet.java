import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import dbconnection.DBConnection;

 @WebServlet("/viewmovies") 

public class ViewMoviesServlet extends HttpServlet
{
  
  public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
  {
    
    res.setContentType("text/html"); 
    
    PrintWriter out = res.getWriter();
    
    out.println("<table border=2 align=center>");
    out.println("<tr>");
    
    out.println("<th> ID </th>");
    out.println("<th> Name</th>");
    //out.println("<th> Language</th>");
    out.println("<th> Genre </th>");
    out.println("<th> Director</th>");
    out.println("<th> Producer</th>");
    out.println("<th>Cast</th>");
    out.println("<th>ReleaseDate</th>");
    out.println("<th>Censor Boarding Rating</th>");
    
      
    out.println("</tr>");
    
    
    
    try
    {
    	
    	Connection con=DBConnection.getConnection();
    	
    	PreparedStatement psmt=con.prepareStatement("select * from movie");
    	    	
    	ResultSet rs=psmt.executeQuery();
    	
    	while(rs.next())
    	{
    		out.println("<tr>");
    		
    		out.println("<td>"+rs.getInt(1)+"</td>");
    		out.println("<td>"+rs.getString(2)+"</td>");
    		//out.println("<td>"+rs.getString(3)+"</td>");
    		out.println("<td>"+rs.getString(4)+"</td>");
    		out.println("<td>"+rs.getString(5)+"</td>");
    		out.println("<td>"+rs.getString(6)+"</td>");
    		out.println("<td>"+rs.getString(7)+"</td>");
    		out.println("<td>"+rs.getString(8)+"</td>");
    		out.println("<td>"+rs.getString(9)+"</td>");
    	    		   		
    		out.println("</tr>");
    		     	
    	} 
    }
    catch(Exception e) 
    {
    
    	out.println(e);
    }
    
    out.println("<table>");
  }
 
}