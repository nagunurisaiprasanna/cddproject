import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbconnection.DBConnection;

@WebServlet("/addmovie") 

public class AddMovieServlet extends HttpServlet
{

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		
		res.setContentType("text/html");
		
		PrintWriter out = res.getWriter();
		
		String moviename = req.getParameter("moviename"); 
		String[] language = req.getParameterValues("language");
		
		String language1="";
		for(String value: language) {
			language1=language+value+",";
		}
		System.out.println(language1);
		
		String genre = req.getParameter("genre");
		String director = req.getParameter("director");
		String producer = req.getParameter("producer");
		String cast = req.getParameter("cast");
		String rdate = req.getParameter("rdate");
		String cbrating = req.getParameter("cbrating");
		
		
		try {
			
			Connection con= DBConnection.getConnection();
			
			PreparedStatement psmt=con.prepareStatement("insert into movie(moviename,language,genre,director,producer,cast,releasedate,censorboardrating) values(?,?,?,?,?,?,?,?)");
		
			psmt.setString(1, moviename);
			psmt.setString(2, language1);
			psmt.setString(3, genre);
			psmt.setString(4, director);
			psmt.setString(5, producer);
			psmt.setString(6, cast);
			psmt.setString(7, rdate);
			psmt.setString(8, cbrating);
			
			int i=psmt.executeUpdate();
			
			if(i>0) 
			{
				
				out.println("<b>Movie Added Successfully</b><br>");
	    		out.println("<a href=addmovie.html> Add Another</a>");
				
			}
		
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
	}
	
	
	
}
 