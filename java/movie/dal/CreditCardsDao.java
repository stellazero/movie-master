package movie.dal;


import movie.model.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


/**
 * Data access object (DAO) class to interact with the underlying Persons table in your MySQL
 * instance. This is used to store {@link Persons} into your MySQL instance and retrieve 
 * {@link Persons} from MySQL instance.
 */
public class CreditCardsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CreditCardsDao instance = null;
	protected CreditCardsDao() {
		connectionManager = new ConnectionManager();
	}
	public static CreditCardsDao getInstance() {
		if(instance == null) {
			instance = new CreditCardsDao();
		}
		return instance;
	}

	public CreditCards create(CreditCards creditCard) throws SQLException {
		String insertCreditCard = "INSERT INTO CreditCards(CardNumber, Expiration, UserName, UserId) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCreditCard);
			
			insertStmt.setLong(1, creditCard.getCardNumber());
			insertStmt.setTimestamp(2, new Timestamp(creditCard.getExpiration().getTime()));
			insertStmt.setString(3, creditCard.getUserName());
			insertStmt.setInt(4, creditCard.getUserId());
			
			insertStmt.executeUpdate();
			
			return creditCard;
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

	public CreditCards updateExpiration(CreditCards creditCard, Date date) throws SQLException {
		String updateCreditCard = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCreditCard);
			updateStmt.setTimestamp(1, new Timestamp(date.getTime()));
			updateStmt.setLong(2, creditCard.getCardNumber());
			updateStmt.executeUpdate();
			
			creditCard.setExpiration(date);
			return creditCard;
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

	public CreditCards delete(CreditCards creditCard) throws SQLException {
		String deleteCreditCard = "DELETE FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCreditCard);
			
			deleteStmt.setLong(1, creditCard.getCardNumber());
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

	public CreditCards getCreditCardByCardNumber(Long cardNumber) throws SQLException {
		String selectCreditCard = "SELECT * FROM CreditCards WHERE CardNumber=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCreditCard);
			selectStmt.setString(1, Long.toString(cardNumber));
			
			results = selectStmt.executeQuery();

			if(results.next()) {
				Long resultCardNumber = results.getLong("CardNumber");
				Date expiration = new Date(results.getTimestamp("Expiration").getTime());
				String userName = results.getString("UserName");
				Integer userId = results.getInt("UserId");
				
				CreditCards creditCard = new CreditCards(resultCardNumber, expiration, userName, userId);
				return creditCard;
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

	public List<CreditCards> getCreditCardsByUserId(Integer userId) throws SQLException {
		List<CreditCards> creditCards = new ArrayList<CreditCards>();
		String selectCards = "SELECT * FROM CreditCards WHERE UserId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCards);
			selectStmt.setInt(1, userId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Long cardNumber = results.getLong("CardNumber");
				Date expiration = new Date(results.getTimestamp("Expiration").getTime());
				String userName = results.getString("UserName");
				Integer resultUserId = results.getInt("UserId");
				CreditCards creditCard = new CreditCards(cardNumber, expiration, userName, resultUserId);
				creditCards.add(creditCard);
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
		return creditCards;
	}
}
