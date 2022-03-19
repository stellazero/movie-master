package movie.dal;

import movie.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class KeywordsInMovieDao {
	protected ConnectionManager connectionManager;
	private static KeywordsInMovieDao instance = null;
	protected KeywordsInMovieDao() {
		connectionManager = new ConnectionManager();
	}
	public static KeywordsInMovieDao getInstance() {
		if(instance == null) {
			instance = new KeywordsInMovieDao();
		}
		return instance;
	}
	
	public KeywordsInMovie create(KeywordsInMovie keywordsInMovie) throws SQLException {
		String insertKeywordsInMovie =
			"INSERT INTO KeywordsInMovie(KeywordId,MovieId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertKeywordsInMovie,
					Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, keywordsInMovie.getKeyword().getKeywordId());
			insertStmt.setInt(2, keywordsInMovie.getMovie().getMovieId());
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int keywordsInMovieId = -1;
			if(resultKey.next()) {
				keywordsInMovieId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			keywordsInMovie.setKeywordsInMovieId(keywordsInMovieId);
			return keywordsInMovie;
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
	
	public KeywordsInMovie delete(KeywordsInMovie keywordsInMovie) throws SQLException {
		String deleteKeywordsInMovie = "DELETE FROM KeywordsInMovie WHERE KeywordsInMovieId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteKeywordsInMovie);
			deleteStmt.setInt(1, keywordsInMovie.getKeywordsInMovieId());
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
	
	public KeywordsInMovie getKeywordsInMovieById(int keywordsInMovieId) throws SQLException {
		String selectKeywordsInMovie =
			"SELECT KeywordsInMovieId,KeywordId,MovieId " +
			"FROM KeywordsInMovie " +
			"WHERE KeywordsInMovieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectKeywordsInMovie);
			selectStmt.setInt(1, keywordsInMovieId);
			results = selectStmt.executeQuery();
			KeywordsDao keywordsDao = KeywordsDao.getInstance();
			MoviesDao moviesDao = MoviesDao.getInstance();
			if(results.next()) {
				int resultKeywordsInMovieId = results.getInt("KeywordsInMovieId");
				int keywordId = results.getInt("KeywordId");
				int movieId = results.getInt("MovieId");
				Keywords keyword = keywordsDao.getKeywordById(keywordId);
				Movies movie = moviesDao.getMovieByMovieId(movieId);
				KeywordsInMovie keywordsInMovie = new KeywordsInMovie(resultKeywordsInMovieId, keyword, movie);
				return keywordsInMovie;
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
	
	public List<KeywordsInMovie> getKeywordNameByMovieTitle(String title) throws SQLException {
		List<KeywordsInMovie> keywordsInMovie = new ArrayList<KeywordsInMovie>();
		String selectKeywordsInMovie =
			"SELECT Movies.Title, KeywordsInMovie.KeywordId, KeywordsInMovie.KeywordsInMovieId, KeywordsInMovie.MovieId"
			+ "	FROM Movies"
			+ "	INNER JOIN KeywordsInMovie ON"
			+ "    KeywordsInMovie.MovieId = Movies.MovieId"
			+ "    WHERE Movies.Title = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectKeywordsInMovie);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			KeywordsDao keywordsDao = KeywordsDao.getInstance();
			MoviesDao moviesDao = MoviesDao.getInstance();
			while(results.next()) {
				int keywordsInmovieId = results.getInt("KeywordsInMovieId");
				int keywordId = results.getInt("KeywordId");
				Keywords keyword = keywordsDao.getKeywordById(keywordId);
				int movieId = results.getInt("movieId");
				Movies movie = moviesDao.getMovieByMovieId(movieId);
				KeywordsInMovie keywordInMovie = new KeywordsInMovie(keywordsInmovieId, keyword, movie);
				keywordsInMovie.add(keywordInMovie);
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
		return keywordsInMovie;
	}
	

}