package movie.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import movie.model.*;





public class RatingsDao {
	
	protected ConnectionManager connectionManager;
	private static RatingsDao instance = null;
	protected RatingsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RatingsDao getInstance() {
		if(instance == null) {
			instance = new RatingsDao();
		}
		return instance;
	}
	
	public Ratings create(Ratings rating) throws SQLException {
		String insertRatings =
				"INSERT INTO Ratings(UserId, MovieId, Score, Time) " +
						"VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRatings,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, rating.getUser().getUserId());
			insertStmt.setInt(2, rating.getMovie().getMovieId());
			insertStmt.setInt(3, rating.getScore());
			insertStmt.setTimestamp(4, new Timestamp(rating.getTimes().getTime()));
			insertStmt.executeUpdate();

			resultKey = insertStmt.getGeneratedKeys();
			int ratingId = -1;
			if(resultKey.next()) {
				ratingId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			rating.setRatingId(ratingId);
			return rating;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	
	public Ratings delete(Ratings rating) throws SQLException {
		String deleteRatings = "DELETE FROM Ratings WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRatings);
			deleteStmt.setInt(1, rating.getRatingId());
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
	
	public Ratings updateScore(Ratings rating, int newScore) throws SQLException {
		String updateRatings = "UPDATE Ratings SET Score=?, Time=? WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateRatings);
			updateStmt.setInt(1, newScore);
			Date newCreatedTimestamp = new Date();
			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
			updateStmt.setInt(3, rating.getRatingId());
			updateStmt.executeUpdate();
			rating.setScore(newScore);

			rating.setScore(newScore);
			rating.setTime(newCreatedTimestamp);
			return rating;
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
	public Ratings getRatingById(int ratingId) throws SQLException {
		String selectRating =
			"SELECT * " +
			"FROM Ratings " +
			"WHERE RatingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRating);
			selectStmt.setInt(1, ratingId);
			
			results = selectStmt.executeQuery();
			UsersDao usersdao = UsersDao.getInstance();
			MoviesDao moviesdao = MoviesDao.getInstance();
			if(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				int userId = results.getInt("UserId");
				int movieId = results.getInt("MovieId");
				int score = results.getInt("Score");
				
				
				Date times = new Date(results.getTimestamp("Time").getTime());

				Movies movie = moviesdao.getMovieByMovieId(movieId);
				Users user = usersdao.getUserByUserId(userId);
				
				
				Ratings rating = new Ratings(resultRatingId, score, user, movie, times);
				return rating;
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
	
	public List<Ratings> getRatingsByUserId(int userId) throws SQLException {
		List<Ratings> ratings = new ArrayList<Ratings>();
		String selectRatings =
				"SELECT * " +
				"FROM Ratings " +
				"WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatings);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			MoviesDao moviesdao = MoviesDao.getInstance();
			UsersDao userdao = UsersDao.getInstance();
			while(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				int movieId = results.getInt("MovieId");
				int score = results.getInt("Score");
				Date times = new Date(results.getTimestamp("Time").getTime());
				Movies movie = moviesdao.getMovieByMovieId(movieId);
				Users user = userdao.getUserByUserId(userId);
				
				
				Ratings rating = new Ratings(resultRatingId, score, user, movie, times);
				ratings.add(rating);
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
		return ratings;
	}
	public List<Ratings> getRatingsByMovieId(int movieId) throws SQLException {
		List<Ratings> ratings = new ArrayList<Ratings>();
		String selectRatings =
				"SELECT * " +
						"FROM Ratings " +
						"WHERE movieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatings);
			selectStmt.setInt(1, movieId);
			results = selectStmt.executeQuery();
			UsersDao usersdao = UsersDao.getInstance();
			MoviesDao moviesdao = MoviesDao.getInstance();
			while(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				int userId = results.getInt("UserId");
				int score = results.getInt("Score");
				Date times = new Date(results.getTimestamp("Time").getTime());

				Users user = usersdao.getUserByUserId(userId);
				Movies movie = moviesdao.getMovieByMovieId(movieId);
				Ratings rating = new Ratings(resultRatingId, score, user, movie, times);
				ratings.add(rating);
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
		return ratings;
	}
	public List<Ratings> getRatingsForUserObject(Users user) throws SQLException {
		List<Ratings> ratings = new ArrayList<Ratings>();
		String selectRatings =
				"SELECT * " +
				"FROM Ratings " +
				"WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatings);
			selectStmt.setInt(1, user.getUserId());
			results = selectStmt.executeQuery();
			MoviesDao moviesdao = MoviesDao.getInstance();
			while(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				int movieId = results.getInt("MovieId");
				int score = results.getInt("Score");
				Date times = new Date(results.getTimestamp("Time").getTime());

				Movies movie = moviesdao.getMovieByMovieId(movieId);
				
				
				Ratings rating = new Ratings(resultRatingId, score, user, movie, times);
				ratings.add(rating);
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
		return ratings;
	}
	public List<Ratings> getRatingsForMovieObject(Movies movie) throws SQLException {
		List<Ratings> ratings = new ArrayList<Ratings>();
		String selectRatings =
				"SELECT * " +
						"FROM Ratings " +
						"WHERE movieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRatings);
			selectStmt.setInt(1, movie.getMovieId());
			results = selectStmt.executeQuery();
			UsersDao usersdao = UsersDao.getInstance();
			while(results.next()) {
				int resultRatingId = results.getInt("RatingId");
				int userId = results.getInt("UserId");
				int score = results.getInt("Score");
				Date times = new Date(results.getTimestamp("Time").getTime());

				Users user = usersdao.getUserByUserId(userId);
				
				
				Ratings rating = new Ratings(resultRatingId, score, user, movie, times);
				ratings.add(rating);
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
		return ratings;
	}
}
