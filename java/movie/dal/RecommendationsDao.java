package movie.dal;
import movie.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RecommendationsDao {
	protected ConnectionManager connectionManager;

	private static RecommendationsDao instance = null;
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}
	
	public Recommendations create(Recommendations recommendation) throws SQLException {
		String insertRecommendations =
			"INSERT INTO Recommendations(UserId, MovieId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecommendations,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, recommendation.getUser().getUserId());
			insertStmt.setInt(2, recommendation.getMovie().getMovieId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int recommendationId = -1;
			if(resultKey.next()) {
				recommendationId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recommendation.setRecommendationId(recommendationId);
			return recommendation;
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
	
	public Recommendations delete(Recommendations recommendation) throws SQLException {
		String deleteReview = "DELETE FROM Recommendations WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, recommendation.getRecommendationId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogComments instance.
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
	
	public Recommendations getRecommendationById(int recommendationId)throws SQLException {
		String selectRecommendation =
				"SELECT RecommendationId,UserId,MovieId " +
				"FROM Recommendations " +
				"WHERE RecommendationId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UsersDao usersDao = UsersDao.getInstance();
		MoviesDao moviesDao = MoviesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, recommendationId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultRecommendationId = results.getInt("RecommendationId");
				int userId = results.getInt("UserId");
				int movieId =  results.getInt("MovieId");
				Users user = usersDao.getUserByUserId(userId);
				Movies movie = moviesDao.getMovieByMovieId(movieId);
				Recommendations r = new Recommendations(resultRecommendationId,user,movie);
				return r;
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
	
	public List<Recommendations> getRecommendationByUserId(int userId)throws SQLException {
		List<Recommendations> recs = new ArrayList<Recommendations>();
		String selectRecommendation =
				"SELECT RecommendationId,UserId,MovieId " +
				"FROM Recommendations " +
				"WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UsersDao usersDao = UsersDao.getInstance();
		MoviesDao moviesDao = MoviesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				int resultUserId = results.getInt("UserId");
				int movieId =  results.getInt("MovieId");
				Users user = usersDao.getUserByUserId(resultUserId);
				Movies movie = moviesDao.getMovieByMovieId(movieId);
				Recommendations r = new Recommendations(recommendationId,user,movie);
				recs.add(r);
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
		return recs;
	}
	
	
	public List< Recommendations> getRecommendationByMovieId(int movieId)throws SQLException {
		List<Recommendations> recs = new ArrayList<Recommendations>();
		String selectRecommendation =
				"SELECT RecommendationId,UserId,MovieId " +
				"FROM Recommendations " +
				"WHERE MovieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UsersDao usersDao = UsersDao.getInstance();
		MoviesDao moviesDao = MoviesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecommendation);
			selectStmt.setInt(1, movieId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recommendationId = results.getInt("RecommendationId");
				int userId = results.getInt("UserId");
				int resultMovieId =  results.getInt("MovieId");
				Users user = usersDao.getUserByUserId(userId);
				Movies movie = moviesDao.getMovieByMovieId(resultMovieId);
				Recommendations r = new Recommendations(recommendationId,user,movie);
				recs.add(r);
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
		return recs;
	}

}

	

