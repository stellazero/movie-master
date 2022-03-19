package movie.dal;
import movie.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MoviesDao {
	protected ConnectionManager connectionManager;
    
    private static MoviesDao instance = null;
    
    protected MoviesDao() {
        connectionManager = new ConnectionManager();
    }

    public static MoviesDao getInstance(){
        if(instance == null) {
            instance = new MoviesDao();
        }
        return instance;
    }
    
    public Movies create(Movies movie) throws SQLException {
    	String insertMovie =
    			"INSERT INTO Movies(Title, Year, Duration, Languages, Description) " +
    			"VALUES(?,?,?,?,?);";
    		Connection connection = null;
    		PreparedStatement insertStmt = null;
    		ResultSet resultKey = null;
    		try {
    			
    			connection = connectionManager.getConnection();
    			insertStmt = connection.prepareStatement(insertMovie, 
    					Statement.RETURN_GENERATED_KEYS);
    			insertStmt.setString(1, movie.getTitle());
    			insertStmt.setInt(2, movie.getYear());
    			insertStmt.setInt(3, movie.getDuration());
    			insertStmt.setString(4, movie.getLanguages());
    			insertStmt.setString(5, movie.getDescription());
    			insertStmt.executeUpdate();
    			resultKey = insertStmt.getGeneratedKeys();
    			int movieId = -1;
    			if(resultKey.next()) {
    				movieId = resultKey.getInt(1);
    			} else {
    				throw new SQLException("Unable to retrieve auto-generated key.");
    			}
    			movie.setMovieId(movieId);
    			insertStmt.executeUpdate();
    			return movie;
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
    
	public Movies delete(Movies movie) throws SQLException {
		String deleteReview = "DELETE FROM Movies WHERE MovieId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, movie.getMovieId());
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
	
	public Movies getMovieByMovieId(int movieId)throws SQLException {
		String selectMovies = 
				"SELECT MovieId, Title, Year, Duration, Languages, Description "
				+ "FROM Movies "
				+ "WHERE MovieId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovies);
			selectStmt.setInt(1, movieId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultMovieId = results.getInt("MovieId");
				String title = results.getString("Title");
				int year = results.getInt("Year");
				int duration = results.getInt("Duration");
				String language = results.getString("Languages");
				String des = results.getString("Description");
				Movies m = new Movies(resultMovieId, title, year, duration, language, des);
				return m;
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
	
	public List<Movies> getMovieByYear(int year)throws SQLException {
		List<Movies> movies = new ArrayList<Movies>();
		String selectMovies = 
				"SELECT MovieId, Title, Year, Duration, Languages, Description "
				+ "FROM Movies "
				+ "WHERE Year=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovies);
			selectStmt.setInt(1, year);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int movieId = results.getInt("MovieId");
				String title = results.getString("Title");
				int resultYear = results.getInt("Year");
				int duration = results.getInt("Duration");
				String language = results.getString("Languages");
				String des = results.getString("Description");
				Movies m = new Movies(movieId, title, resultYear, duration, language, des);
				movies.add(m);
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
		return movies;
	}
	
	public List<Movies> getMovieByLanguage(String language)throws SQLException {
		List<Movies> movies = new ArrayList<Movies>();
		String selectMovies = 
				"SELECT MovieId, Title, Year, Duration, Languages, Description "
				+ "FROM Movies "
				+ "WHERE Languages=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovies);
			selectStmt.setString(1, language);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int movieId = results.getInt("MovieId");
				String title = results.getString("Title");
				int year = results.getInt("Year");
				int duration = results.getInt("Duration");
				String resultLanguage = results.getString("Languages");
				String des = results.getString("Description");
				Movies m = new Movies(movieId, title, year, duration, resultLanguage, des);
				movies.add(m);
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
		return movies;
	}
	
	public List<Movies> getMovieByTitle(String title)throws SQLException {
		List<Movies> movies = new ArrayList<Movies>();
		String selectMovies = 
				"SELECT MovieId, Title, Year, Duration, Languages, Description "
				+ "FROM Movies "
				+ "WHERE Title=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovies);
			selectStmt.setString(1, title);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int movieId = results.getInt("MovieId");
				String resultTitle = results.getString("Title");
				int year = results.getInt("Year");
				int duration = results.getInt("Duration");
				String language = results.getString("Languages");
				String des = results.getString("Description");
				Movies m = new Movies(movieId, resultTitle, year, duration, language, des);
				movies.add(m);
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
		return movies;
	}
	
	public List<Movies> getMovieByActorName(String actorName)throws SQLException {
		List<Movies> movies = new ArrayList<Movies>();
		String selectMovies = 
				"SELECT  Movies.MovieId, Movies.Title, Movies.Year, Movies.Duration, Movies.Languages, Movies.Description FROM Movies "
						+ "INNER JOIN  Collaboration "
						+ "on Movies.MovieId = Collaboration.MovieId "
						+ "WHERE Collaboration.ActorName=? "
						+ "GROUP BY Movies.MovieId;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovies);
			selectStmt.setString(1, actorName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int movieId = results.getInt("MovieId");
				String title = results.getString("Title");
				int year = results.getInt("Year");
				int duration = results.getInt("Duration");
				String language = results.getString("Languages");
				String des = results.getString("Description");
				Movies m = new Movies(movieId, title, year, duration, language, des);
				movies.add(m);
			}} catch (SQLException e) {
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
			return movies;		
	}
	
	public List<Movies> getMovieByDirectorName(String directorName)throws SQLException {
		List<Movies> movies = new ArrayList<Movies>();
		String selectMovies = 
				"SELECT  Movies.MovieId, Movies.Title,Movies.Year, Movies.Duration, Movies.Languages, Movies.Description FROM Movies "
						+ "INNER JOIN Collaboration "
						+ "on Movies.MovieId = Collaboration.MovieId "
						+ "WHERE Collaboration.DirectorName=? "
						+ "GROUP BY Movies.MovieId;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectMovies);
			selectStmt.setString(1, directorName);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int movieId = results.getInt("MovieId");
				String title = results.getString("Title");
				int year = results.getInt("Year");
				int duration = results.getInt("Duration");
				String language = results.getString("Languages");
				String des = results.getString("Description");
				Movies m = new Movies(movieId, title, year, duration, language, des);
				movies.add(m);
			}} catch (SQLException e) {
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
			return movies;		
	}
}
