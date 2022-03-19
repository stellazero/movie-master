package movie.dal;

import movie.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Users} into your MySQL instance and retrieve 
 * {@link Users} from MySQL instance.
 */
public class UsersDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}

	public Users create(Users user) throws SQLException {
		String insertUser = "INSERT INTO Users(Password,UserName,FirstName,LastName,Email,Phone) VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt = connection.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
			// PreparedStatement allows us to substitute specific types into the query template.
			// For an overview, see:
			// http://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html.
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// For nullable fields, you can check the property first and then call setNull()
			// as applicable.
			insertStmt.setString(1, user.getPassword());
			insertStmt.setString(2, user.getUserName());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());
			insertStmt.setString(5, user.getEmail());
			insertStmt.setInt(6, user.getPhone());
			// Note that we call executeUpdate(). This is used for a INSERT/UPDATE/DELETE
			// statements, and it returns an int for the row counts affected (or 0 if the
			// statement returns nothing). For more information, see:
			// http://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html
			// I'll leave it as an exercise for you to write UPDATE/DELETE methods.
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int UserId = -1;
			if(resultKey.next()) {
				UserId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			user.setUserId(UserId);
			
			// Note 1: if this was an UPDATE statement, then the person fields should be
			// updated before returning to the caller.
			// Note 2: there are no auto-generated keys, so no update to perform on the
			// input param person.
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	public Users updatePassword(Users user, String newPassword) throws SQLException {
		// The field to update only exists in the superclass table, so we can
		// just call the superclass method.
		String updateUser = "UPDATE Users SET Password=? WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			
			updateStmt.setString(1, newPassword);
			updateStmt.setInt(2, user.getUserId());
			
			updateStmt.executeUpdate();
			
			// Update the person param before returning to the caller.
			user.setPassword(newPassword);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	

//	public Users getUserByUserName(String userName) throws SQLException {
//		String selectUser = "SELECT * FROM Users WHERE UserName=?;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectUser);
//			selectStmt.setString(1, userName);
//
//			results = selectStmt.executeQuery();
//
//			if(results.next()) {
//				int userId = results.getInt("UserId");
//				String password = results.getString("Password");
//				String resultUserName = results.getString("UserName");
//				String firstName = results.getString("FirstName");
//				String lastName = results.getString("LastName");
//				String email = results.getString("Email");
//				int phone = results.getInt("Phone");
//				Users user = new Users(userId, password, resultUserName, firstName, lastName, email, phone);
//				return user;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(selectStmt != null) {
//				selectStmt.close();
//			}
//			if(results != null) {
//				results.close();
//			}
//		}
//		return null;
//	}
	
	public Users getUserByUserId(int userId) throws SQLException {
		String selectUser = "SELECT * FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);

			results = selectStmt.executeQuery();

			if(results.next()) {
				int resultUserId = results.getInt("UserId");
				String password = results.getString("Password");
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				int phone = results.getInt("Phone");
				Users user = new Users(resultUserId, password, userName, firstName, lastName, email, phone);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<Users> getUsersFromFirstName(String firstName)
			throws SQLException {
		List<Users> users = new ArrayList<Users>();
		String selectUsers =
				
			"SELECT * FROM Users WHERE Users.FirstName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, firstName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int userId = results.getInt("UserId");
				String password = results.getString("Password");
				String userName = results.getString("UserName");
				String resultFirstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String email = results.getString("Email");
				int phone = results.getInt("Phone");
				Users user = new Users(userId, password, userName, resultFirstName, lastName, email, phone);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return users;
	}


	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, user.getUserId());
			deleteStmt.executeUpdate();

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
