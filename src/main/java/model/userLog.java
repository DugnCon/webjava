package main.java.model;

public class userLog {
	private String username;
	private String password;
	public userLog() {};
	public userLog(String username,String password) {
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUserVallid() {
		if(username.length() < 6) return false;
		return true;
	}
	public boolean isPassWordValid() {
		if(password.length() < 6) return false;
		return true;
	}
}
