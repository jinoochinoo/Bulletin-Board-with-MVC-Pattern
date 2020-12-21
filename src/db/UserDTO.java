package db;

public class UserDTO {
	
	private String userID;
	private String firstPassword;
	private String secondPassword;
	private String email;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFirstPassword() {
		return firstPassword;
	}
	public void setFirstPassword(String firstPassword) {
		this.firstPassword = firstPassword;
	}
	public String getSecondPassword() {
		return secondPassword;
	}
	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
