package movie.model;

import java.util.Date;

public class CreditCards {
	protected Long cardNumber;
	protected Date expiration;
	protected String userName;
	protected Integer userId;
	
	
	public CreditCards(Long cardNumber, Date expiration, String userName, Integer userId) {
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.userName = userName;
		this.userId = userId;
	}
	
	public CreditCards(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
