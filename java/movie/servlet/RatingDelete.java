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
import movie.model.*;

@WebServlet("/ratingdelete")
public class RatingDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Rating");        
        req.getRequestDispatcher("/RatingDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String stringratingId = req.getParameter("ratingId");
        if (stringratingId == null || stringratingId.trim().isEmpty()) {
            messages.put("title", "Invalid ratingId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
        	int ratingId = Integer.parseInt(stringratingId);
        	Ratings rating = new Ratings(ratingId);
	        try {
	        	rating = ratingsDao.delete(rating);
	        	// Update the message.
		        if (rating == null) {
		            messages.put("title", "Successfully deleted " + stringratingId);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + stringratingId);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RatingDelete.jsp").forward(req, resp);
    }
}
