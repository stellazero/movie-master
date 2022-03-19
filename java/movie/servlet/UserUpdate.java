package movie.servlet;

import movie.dal.*;
import movie.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);

        // Retrieve user and validate.
        String stringUserId = req.getParameter("userId");
        String stringUserPassword = req.getParameter("password");
        //long testId = Integer.parseInt(req.getParameter("phone"));
        System.out.println(stringUserId);
        System.out.println(stringUserPassword);
       // System.out.println(testId);

        if (stringUserId == null || stringUserId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserId.");
        } else {
        	try {
        		int userId = Integer.parseInt(stringUserId);
        		Users user = usersDao.getUserByUserId(userId);
        		if(user == null) {
        			messages.put("success", "UserId does not exist.");
        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String stringUserId = req.getParameter("userId");
        if (stringUserId == null || stringUserId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid UserId.");
        } else {
        	try {
        		int userId = Integer.parseInt(stringUserId);
        		Users user = usersDao.getUserByUserId(userId);
        		if(user == null) {
        			messages.put("success", "UserId does not exist. No update to perform.");
        		} else {
        			String newPassword = req.getParameter("password");
 
        			if (newPassword == null || newPassword.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid password.");
        	        } else {
        	        	user = usersDao.updatePassword(user, newPassword);
        	        	messages.put("success", "Successfully updated " + userId);
        	        }
        		}
        		req.setAttribute("user", user);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserUpdate.jsp").forward(req, resp);
    }
}
