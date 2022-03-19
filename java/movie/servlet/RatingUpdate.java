package movie.servlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import movie.dal.*;
import movie.model.Ratings;

@WebServlet("/ratingupdate")
public class RatingUpdate extends HttpServlet {
	
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

        // Retrieve user and validate.
        String stringratingId = req.getParameter("ratingId");
        if (stringratingId == null || stringratingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ratingId.");
        } else {
        	try {
        		int ratingId = Integer.parseInt(stringratingId);
        		Ratings rating = ratingsDao.getRatingById(ratingId);
        		if(rating == null) {
        			messages.put("success", "ratingId does not exist.");
        		}
        		req.setAttribute("rating", rating);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String stringratingId = req.getParameter("ratingId");
        if (stringratingId == null || stringratingId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ratingId.");
        } else {
        	try {
        		int ratingId = Integer.parseInt(stringratingId);
        		Ratings rating = ratingsDao.getRatingById(ratingId);
        		if(rating == null) {
        			messages.put("success", "ratingId does not exist. No update to perform.");
        		} else {
        			String stringnewScore = req.getParameter("newScore");
        			if (stringnewScore == null || stringnewScore.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid score.");
        	        } else {
        	        	int newScore = Integer.parseInt(stringnewScore);
        	        	rating = ratingsDao.updateScore(rating,newScore);
        	        	messages.put("success", "Successfully updated " + stringnewScore);
        	        }
        		}
        		req.setAttribute("rating", rating);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingUpdate.jsp").forward(req, resp);
    }
}
