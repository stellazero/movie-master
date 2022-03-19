package movie.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import movie.model.*;

public class CollaborationDao {
  protected ConnectionManager connectionManager;

  // Single pattern: instantiation is limited to one object.
  private static CollaborationDao instance = null;
  protected CollaborationDao() {
    connectionManager = new ConnectionManager();
  }
  public static CollaborationDao getInstance() {
    if(instance == null) {
      instance = new CollaborationDao();
    }
    return instance;
  }


  public Collaboration create(Collaboration collaboration) throws SQLException {
    String insertCollaboration = "INSERT INTO Collaboration(MovieId,ActorId,ActorName,DirectorId,DirectorName) VALUES(?,?,?,?,?);";
    Connection connection = null;
    PreparedStatement insertStmt = null;
    ResultSet resultKey = null;
    try {
      connection = connectionManager.getConnection();
      insertStmt = connection.prepareStatement(insertCollaboration,
          Statement.RETURN_GENERATED_KEYS);
      insertStmt.setInt(1, collaboration.getMovie().getMovieId());
      insertStmt.setInt(2, collaboration.getActor().getActorId());
      insertStmt.setString(3, collaboration.getActor().getActorName());
      insertStmt.setInt(4, collaboration.getDirector().getDirectorId());
      insertStmt.setString(5, collaboration.getDirector().getName());
      insertStmt.executeUpdate();

      resultKey = insertStmt.getGeneratedKeys();
      int collaborationId = -1;
      if(resultKey.next()) {
        collaborationId = resultKey.getInt(1);
      } else {
        throw new SQLException("Unable to retrieve auto-generated key.");
      }
      collaboration.setCollaborationId(collaborationId);
      return collaboration;
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

  public Collaboration getCollaborationById(int collaborationId) throws SQLException {
    String selectCollaboration =
        "SELECT *" +
            "FROM Collaboration " +
            "WHERE CollaborationId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCollaboration);
      selectStmt.setInt(1, collaborationId);
      results = selectStmt.executeQuery();
      MoviesDao moviesDao = MoviesDao.getInstance();
      ActorsDao actorsDao = ActorsDao.getInstance();
      DirectorsDao directorsDao = DirectorsDao.getInstance();
      if(results.next()) {
        int resultCollaborationId = results.getInt("CollaborationId");
        int movieId = results.getInt("MovieId");
        Movies movie = moviesDao.getMovieByMovieId(movieId);
        int actorId = results.getInt("ActorId");
        Actors actor = actorsDao.getActorByActorId(actorId);
        int directorId = results.getInt("DirectorId");
        Directors director = directorsDao.getDirectorByDirectorId(directorId);
        Collaboration collaboration = new Collaboration(resultCollaborationId, movie, actor, director);
        return collaboration;
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

  public List<Collaboration> getCollaborationByMovie(Movies movie) throws SQLException {
    List<Collaboration> collaborations = new ArrayList<Collaboration>();
    String selectCollaboration =
        "SELECT *" +
            "FROM Collaboration " +
            "WHERE MovieId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCollaboration);
      selectStmt.setInt(1, movie.getMovieId());
      results = selectStmt.executeQuery();
      ActorsDao actorsDao = ActorsDao.getInstance();
      DirectorsDao directorsDao = DirectorsDao.getInstance();
      while(results.next()) {

    	  int resultCollaborationId = results.getInt("CollaborationId");
          int actorId = results.getInt("ActorId");
          Actors actor = actorsDao.getActorByActorId(actorId);
          int directorId = results.getInt("DirectorId");
          Directors director = directorsDao.getDirectorByDirectorId(directorId);
          Collaboration collaboration = new Collaboration(resultCollaborationId, movie, actor, director);
          collaborations.add(collaboration);
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
    return collaborations;
  }

  public List<Collaboration> getCollaborationByActor(Actors actor) throws SQLException {
    List<Collaboration> collaborations = new ArrayList<Collaboration>();
    String selectCollaboration =
        "SELECT *" +
            "FROM Collaboration " +
            "WHERE ActorId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCollaboration);
      selectStmt.setInt(1, actor.getActorId());
      results = selectStmt.executeQuery();
      MoviesDao moviesDao = MoviesDao.getInstance();
      DirectorsDao directorsDao = DirectorsDao.getInstance();
      while(results.next()) {
        int CollaborationId = results.getInt("CollaborationId");
        int movieId = results.getInt("MovieId");
        Movies movie = moviesDao.getMovieByMovieId(movieId);
        int directorId = results.getInt("DirectorId");
        Directors director = directorsDao.getDirectorByDirectorId(directorId);
        Collaboration collaboration = new Collaboration(CollaborationId, movie, actor, director);
        collaborations.add(collaboration);
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
    return collaborations;
  }

  public List<Collaboration> getCollaborationByDirector(Directors director) throws SQLException {
    List<Collaboration> collaborations = new ArrayList<Collaboration>();
    String selectCollaboration =
        "SELECT *" +
            "FROM Collaboration " +
            "WHERE DirectorId=?;";
    Connection connection = null;
    PreparedStatement selectStmt = null;
    ResultSet results = null;
    try {
      connection = connectionManager.getConnection();
      selectStmt = connection.prepareStatement(selectCollaboration);
      selectStmt.setInt(1, director.getDirectorId());
      results = selectStmt.executeQuery();
      MoviesDao moviesDao = MoviesDao.getInstance();
      ActorsDao actorsDao = ActorsDao.getInstance();
      while(results.next()) {
        int CollaborationId = results.getInt("CollaborationId");
        int movieId = results.getInt("MovieId");
        Movies movie = moviesDao.getMovieByMovieId(movieId);
        int actorId = results.getInt("ActorId");
        Actors actor = actorsDao.getActorByActorId(actorId);

        Collaboration collaboration = new Collaboration(CollaborationId, movie, actor, director);
        collaborations.add(collaboration);
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
    return collaborations;
  }

  public Collaboration delete(Collaboration collaboration) throws SQLException {
    String deleteCollaboration = "DELETE FROM Collaboration WHERE CollaborationId=?;";
    Connection connection = null;
    PreparedStatement deleteStmt = null;
    try {
      connection = connectionManager.getConnection();
      deleteStmt = connection.prepareStatement(deleteCollaboration);
      deleteStmt.setInt(1, collaboration.getCollaborationId());
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