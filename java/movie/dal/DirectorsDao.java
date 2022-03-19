package movie.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import movie.model.*;
public class DirectorsDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static DirectorsDao instance = null;
  protected DirectorsDao() {
    connectionManager = new ConnectionManager();
  }
  public static DirectorsDao getInstance() {
    if(instance == null) {
      instance = new DirectorsDao();
    }
    return instance;
  }


  public Directors create(Directors director) throws SQLException {
    String insertDirector = "INSERT INTO Directors(Name) VALUES(?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
	ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertDirector,
				Statement.RETURN_GENERATED_KEYS);
      insertStmt.setString(1, director.getName());
      insertStmt.executeUpdate();
      resultKey = insertStmt.getGeneratedKeys();
		int directorId = -1;
		if(resultKey.next()) {
			directorId = resultKey.getInt(1);
		} else {
			throw new SQLException("Unable to retrieve auto-generated key.");
		}
		director.setDirectorId(directorId);
      return director;
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

  public Directors delete(Directors director) throws SQLException {
    String deleteDirector = "DELETE FROM Directors WHERE DirectorId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteDirector);
      deleteStmt.setInt(1, director.getDirectorId());
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

  public Directors getDirectorByDirectorId(int directorId) throws SQLException {
    String selectDirector = "SELECT DirectorId,Name FROM Directors WHERE DirectorId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectDirector);
      selectStmt.setInt(1, directorId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        String name = results.getString("Name");
        Directors director = new Directors(directorId, name);
        return director;
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
  public Directors getDirectorByDirectorName(String name) throws SQLException {
	    String selectDirector = "SELECT DirectorId,Name FROM Directors WHERE Name=?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectDirector);
	      selectStmt.setString(1, name);
	      results = selectStmt.executeQuery();
	      if(results.next()) {
	        Integer resultDirectorId = results.getInt("DirectorId");
	        Directors director = new Directors(resultDirectorId, name);
	        return director;
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
}

