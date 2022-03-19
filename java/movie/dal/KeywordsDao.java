package movie.dal;

import movie.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class KeywordsDao {
	protected ConnectionManager connectionManager;
	private static KeywordsDao instance = null;
	protected KeywordsDao() {
		connectionManager = new ConnectionManager();
	}
	public static KeywordsDao getInstance() {
		if(instance == null) {
			instance = new KeywordsDao();
		}
		return instance;
	}
	public Keywords create(Keywords keyword) throws SQLException {
		String insertKeyword = 
			"INSERT INTO Keywords(KeywordName)" + 
			"VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
				connection = connectionManager.getConnection();
				insertStmt = connection.prepareStatement(insertKeyword,
						Statement.RETURN_GENERATED_KEYS);
				insertStmt.setString(1, keyword.getKeywordName());
				insertStmt.executeUpdate();
				resultKey = insertStmt.getGeneratedKeys();
				int keywordId = -1;
				if(resultKey.next()) {
					keywordId = resultKey.getInt(1);
				} else {
					throw new SQLException("Unable to retrieve auto-generated key.");
				}
				keyword.setKeywordId(keywordId);
				return keyword;
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
	public Keywords delete(Keywords keyword) throws SQLException {
		String deleteKeyword = "DELETE FROM Keywords WHERE keywordId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteKeyword);
			deleteStmt.setInt(1, keyword.getKeywordId());
			deleteStmt.executeUpdate();
			return null;
		}catch (SQLException e) {
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
		
	
	public Keywords getKeywordById(int keywordId) throws SQLException {
		String selectKeyword =
			"SELECT KeywordId,KeywordName " +
			"FROM Keywords " +
			"WHERE KeywordId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectKeyword);
			selectStmt.setInt(1, keywordId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultKeywordId = results.getInt("KeywordId");
				String keywordName = results.getString("KeywordName");
				Keywords keyword = new Keywords(resultKeywordId, keywordName);
				return keyword;
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
	
//	public List<Keywords> getKeywordsFromKeywordName(String keywordName) throws SQLException {
//		List<Keywords> keywords = new ArrayList<Keywords>();
//		String selectKeywords =
//			"SELECT KeywordId,KeywordName FROM Keywords WHERE KeywordName=?;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectKeywords);
//			selectStmt.setString(1, keywordName);
//			results = selectStmt.executeQuery();
//			while(results.next()) {
//				Integer keywordId = results.getInt("KeywordId");
//				String resultKeywordName = results.getString("KeywordName");
//				Keywords keyword = new Keywords(keywordId, resultKeywordName);
//				keywords.add(keyword);
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
//		return keywords;
//	}
	
}