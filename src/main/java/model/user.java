package main.java.model;

public class user {
	private String userName;
	private String passWord;
	private String fullname;
	
	public user() {
		
	}
	
	public user(String username) {
		this.userName = userName;
	}
	
	public user(String userName, String passWord, String fullname) {
		this.userName = userName;
		this.passWord = passWord;
		this.fullname = fullname;
	}
	
	public void setUsername(String userName) {
		this.userName = userName;
	}
	public void setPassword(String passWord) {
		this.passWord = passWord;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUsername() {
		return userName;
	}
	public String getPassword() {
		return passWord;
	}
	public String getFullname() {
		return fullname;
	}
	public boolean isUserVallid() {
		if(userName.length() < 6) return false;
		return true;
	}
	public boolean isPassWordValid() {
		if(passWord.length() < 6) return false;
		return true;
	}
}
