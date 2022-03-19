package movie.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import movie.model.*;

public class ActorsDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static ActorsDao instance = null;
  protected ActorsDao() {
    connectionManager = new ConnectionManager();
  }
  public static ActorsDao getInstance() {
    if(instance == null) {
      instance = new ActorsDao();
    }
    return instance;
  }


  public Actors create(Actors actor) throws SQLException {
    String insertActor = "INSERT INTO Actors(ActorName) VALUES(?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertActor);
      insertStmt = connection.prepareStatement(insertActor,
				Statement.RETURN_GENERATED_KEYS);
      
      insertStmt.setString(1, actor.getActorName());
      insertStmt.executeUpdate();
      
      resultKey = insertStmt.getGeneratedKeys();
		int actorId = -1;
		if(resultKey.next()) {
			actorId = resultKey.getInt(1);
		} else {
			throw new SQLException("Unable to retrieve auto-generated key.");
		}
		actor.setActorId(actorId);
      return actor;
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

  public Actors delete(Actors actor) throws SQLException {
    String deleteActor = "DELETE FROM Actors WHERE ActorId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteActor);
      deleteStmt.setInt(1, actor.getActorId());
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

  public Actors getActorByActorId(int actorId) throws SQLException {
    String selectActor = "SELECT ActorId,ActorName FROM Actors WHERE ActorId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectActor);
      selectStmt.setInt(1, actorId);
      results = selectStmt.executeQuery();
      if(results.next()) {
        Integer resultActorId = results.getInt("ActorId");
        String actorName = results.getString("ActorName");
        Actors actor = new Actors(resultActorId, actorName);
        return actor;
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
  
  public Actors getActorByActorName(String actorName) throws SQLException {
	    String selectActor = "SELECT ActorId,ActorName FROM Actors WHERE ActorName=?;";
	    Connection connection = null;
	    PreparedStatement selectStmt = null;
	    ResultSet results = null;
	    try {
	      connection = connectionManager.getConnection();
	      selectStmt = connection.prepareStatement(selectActor);
	      selectStmt.setString(1, actorName);
	      results = selectStmt.executeQuery();
	      if(results.next()) {
	        Integer resultActorId = results.getInt("ActorId");
	        Actors actor = new Actors(resultActorId, actorName);
	        return actor;
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
