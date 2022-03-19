package movie.servlet;

import movie.dal.*;
import movie.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ratingcreate")
public class RatingCreate extends HttpServlet {
	
	protected RatingsDao ratingsDao;
	
	@Override
	public void init() throws ServletException {
		ratingsDao = RatingsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/RatingCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String stringuserName = req.getParameter("userId");
        String Title = req.getParameter("Title");
        if (stringuserName == null || stringuserName.trim().isEmpty() || Title == null || Title.trim().isEmpty()) {
            messages.put("success", "Invalid userId or Title");
        } else {
	        try {
	        	UsersDao usersDao = UsersDao.getInstance();
	        	MoviesDao moviesDao = MoviesDao.getInstance();
	        	String stringscore = req.getParameter("score");
	        	Integer score = Integer.parseInt(stringscore);
	        	Integer userId = Integer.parseInt(stringuserName);
	        	Users user = usersDao.getUserByUserId(userId);
	        	Movies movie = moviesDao.getMovieByTitle(Title).get(0);
	        	Date time = new Date();
	        	Ratings rating = new Ratings(score, userId, user, movie, time);
	        	rating = ratingsDao.create(rating);
	        	messages.put("success", "Successfully created a rating");
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingCreate.jsp").forward(req, resp);
    }
}
