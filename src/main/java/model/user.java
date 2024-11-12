package main.java.model;

public class user {
	private String userName;
	private String passWord;
	private String fullname;
	private String repeatPass;
	private int ID;
	
	public user() {
		
	}
	
	public user(int ID) {
		this.ID = ID;
	}
	
	public user(String userName) {
		this.userName = userName;
	}
	
	public user(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public user(String userName,String repeatPass, String passWord,String fullname) {
		this.userName = userName;
		this.fullname = fullname;
		this.passWord = passWord;
		this.repeatPass = repeatPass;
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
	
	public void setRepeatPass(String repeatPass) {
		this.repeatPass = repeatPass;
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
	
	public String getReapeatPass() {
		return repeatPass;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
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
