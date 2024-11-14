
package main.java.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import main.java.dao.signUpAccount;

public class author {
	private String userName;
	private String passWord;
	private String repeatPassWord;
	private String fullname;
	
	public author() {
		super();
	}
	
	public author(String userName, String passWord, String repeatPassWord, String fullname) {
		this.userName = userName;
		this.passWord = passWord;
		this.repeatPassWord = repeatPassWord;
		this.fullname = fullname;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setFullName(String fullname) {
		this.fullname = fullname;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setRepeatPassWord(String repeatPassWord) {
		this.repeatPassWord = repeatPassWord;
	}
	public String getUserName() {
		return userName;
	}
	public String getFullName() {
		return fullname;
	}
	public String getPassWord() {
		return passWord;
	}
	public String getRepeatPassWord() {
		return repeatPassWord;
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
