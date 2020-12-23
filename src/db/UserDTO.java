package db;

public class UserDTO {
	
	private String userID;
	private String firstPassword;
	private String secondPassword;
	private String emailID;
	private String emailAddress;
	private String gender;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
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
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String email) {
		this.emailID = email;
	}

}
