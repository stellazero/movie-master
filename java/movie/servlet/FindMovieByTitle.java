package movie.servlet;
import movie.dal.*;
import movie.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findmoviebytitle")
public class FindMovieByTitle extends HttpServlet{
	protected MoviesDao moviesDao;
	
	@Override
	public void init() throws ServletException {
		moviesDao = MoviesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        List<Movies> movies = new ArrayList<Movies>();
        String movieTitle = req.getParameter("movieTitle");
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		movies = moviesDao.getMovieByTitle(movieTitle);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + movieTitle);
        	messages.put("movie", movieTitle);
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/FindMovieByTitle.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Movies> movies = new ArrayList<Movies>();
        String movieTitle = req.getParameter("movieTitle");
        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		movies = moviesDao.getMovieByTitle(movieTitle);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + movieTitle);
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/FindMovieByTitle.jsp").forward(req, resp);
	}
}
