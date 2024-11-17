package main.java.model;

public class require {
	private String ID;
	private String userName;
	private String request;
	
	public require() {};
	
	public require(String userName) {
		this.userName= userName;
	}
	
	public require(String userName, String request) {
		this.userName = userName;
		this.request = request;
	}
	
	public require(String ID, String userName, String request) {
		this.ID= ID;
		this.userName = userName;
		this.request = request;
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRequest() {
		return request;
	}
	
	public void setRequest(String request) {
		this.request = request;
	}
}
