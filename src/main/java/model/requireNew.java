package main.java.model;

import javafx.beans.property.SimpleStringProperty;

public class requireNew {
	 private SimpleStringProperty ID;
	 private SimpleStringProperty request;
	 private SimpleStringProperty userName;
	 private SimpleStringProperty timeRequire;
	 
	 public requireNew() {
		 this.ID = new SimpleStringProperty();
	     this.userName = new SimpleStringProperty();
	     this.request = new SimpleStringProperty();
	     this.timeRequire = new SimpleStringProperty();
	 };
	 
	 public requireNew(String ID, String userName, String request, String timeRequire) {
		 this();
		 setID(ID);
		 setUserName(userName);
		 setRequest(request);
		 setTimeRequire(timeRequire);
	 }
	 
	 public String getID() {
		 return this.ID.get();
	 }
	 
	 public void setID(String ID) {
		 this.ID.set(ID);
	 }
	 
	 public String getUserName() {
		 return this.userName.get();
	 }
	 
	 public void setUserName(String userName) {
		 this.userName.set(userName);
	 }
	 
	 public String getRequest() {
		 return this.request.get();
	 }
	 
	 public void setRequest(String request) {
		 this.request.set(request);
	 }
	 
	 public String getTimeRequire() {
		 return this.timeRequire.get();
	 }
	 
	 public void setTimeRequire(String timeRequire) {
		 this.timeRequire.set(timeRequire);
	 }
}
