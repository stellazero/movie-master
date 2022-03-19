package movie.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.dal.MoviesDao;
import movie.model.Movies;

@WebServlet("/findmoviebydirector")
public class FindMovieByDirectorName extends HttpServlet{
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
        String movieDirector = req.getParameter("movieDirector");
        if (movieDirector == null || movieDirector.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		movies = moviesDao.getMovieByDirectorName(movieDirector);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + movieDirector);
        	messages.put("movie", movieDirector);
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/FindMovieByDirector.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        List<Movies> movies = new ArrayList<Movies>();
        String movieDirector = req.getParameter("movieDirector");
        if (movieDirector == null || movieDirector.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		movies = moviesDao.getMovieByDirectorName(movieDirector);
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + movieDirector);
        }
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/FindMovieByDirector.jsp").forward(req, resp);
	}

}
