package movie.model;

public class Users {
	protected int userId;
	protected String password;
	protected String userName;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected int phone;
	
	public Users(int userId, String password, String userName, String firstName, String lastName, String email, int phone) {
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		
	}
	
	public Users(String password, String userName, String firstName, String lastName, String email, int phone) {
		this.password = password;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		
	}
	
	public Users(int userId) {
		this.userId = userId;
		
	}
	
	public Users(String userName) {
		this.userName = userName;
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
}
