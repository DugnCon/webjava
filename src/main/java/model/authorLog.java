package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dao.loginAccount;
public class authorLog {
	private String userName;
	private String passWord;
	public authorLog() {
		super();
	}
	public authorLog(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getUserName() {
		return this.userName;
	}
	public String getPassWord() {
		return this.passWord;
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
