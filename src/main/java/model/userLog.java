package model;

public class userLog {
	private String username;
	private String password;
	private String ID;
	private String Fullname;
	private String createAc;
	public userLog() {};
	public userLog(String ID) {
		this.ID = ID;
	}
	public userLog(String ID, String username, String Fullname) {
		this.ID = ID;
		this.username = username;
		this.Fullname = Fullname;
	}
	public userLog(String username,String password) {
		this.username = username;
		this.password = password;
	}
	public userLog(String ID,String username, String password, String createAc, String Fullname) {
		this.ID = ID;
		this.username = username;
		this.password = password;
		this.createAc = createAc;
		this.Fullname = Fullname;
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
	public String getFullname() {
		return Fullname;
	}
	public void setFullname(String Fullname) {
		this.Fullname = Fullname;
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getCreateAc() {
		return createAc;
	}
	public void setCreateAc(String createAc) {
		this.createAc = createAc;
		
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
