
package main.java.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class borrowNew {
    private SimpleStringProperty borrowerID;
    private SimpleStringProperty bookCode;
    private SimpleStringProperty userName;
    private SimpleIntegerProperty userID;
    private SimpleStringProperty borrowDate;
    private SimpleStringProperty returnDate;
    private SimpleStringProperty status;
    private SimpleStringProperty phonenum;
    private SimpleStringProperty title;

    public borrowNew() {
        this.borrowerID = new SimpleStringProperty();
        this.bookCode = new SimpleStringProperty();
        this.userName = new SimpleStringProperty();
        this.userID = new SimpleIntegerProperty();
        this.borrowDate = new SimpleStringProperty();
        this.returnDate = new SimpleStringProperty();
        this.status = new SimpleStringProperty();
        this.phonenum = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
    }

    public borrowNew(String borrowerID, String bookCode, 
            String userName, int userID, String borrowDate, String returnDate, String status, String phonenum) {
        this();
        setBorrowerID(borrowerID);
        setBookCode(bookCode);
        setUserName(userName);
        setUserID(userID);
        setBorrowDate(borrowDate);
        setReturnDate(returnDate);
        setStatus(status);
        setPhonenum(phonenum);
    }
    
    public borrowNew(int userID, String bookCode, String title) {
    	this();
    	setUserID(userID);
    	setBookCode(bookCode);
    	setTitle(title);
    }
    
    public borrowNew(String bookCode, String userName, String borrowDate, String returnDate, String status) {
    	this();
        setBookCode(bookCode);
        setUserName(userName);
        setBorrowDate(borrowDate);
        setReturnDate(returnDate);
        setStatus(status);
    }

    public borrowNew(String borrowerID, String bookCode, String userName, 
            String borrowDate, String returnDate, String phonenum) {
    	this();
        setBorrowerID(borrowerID);
        setBookCode(bookCode);
        setUserName(userName);
        setBorrowDate(borrowDate);
        setReturnDate(returnDate);
        setPhonenum(phonenum);
    }

    public String getBorrowerID() {
        return borrowerID.get();
    }

    public void setBorrowerID(String borrowerID) {
        this.borrowerID.set(borrowerID);
    }

    public String getBookCode() {
        return bookCode.get();
    }

    public void setBookCode(String bookCode) {
        this.bookCode.set(bookCode);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public int getUserID() {
        return userID.get();
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public String getBorrowDate() {
        return borrowDate.get();
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate.set(borrowDate);
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public void setReturnDate(String returnDate) {
        this.returnDate.set(returnDate);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getPhonenum() {
        return phonenum.get();
    }

    public void setPhonenum(String phonenum) {
        this.phonenum.set(phonenum);
    }
    
    public String getTitle() {
    	return title.get();
    }
    
    public void setTitle(String title) {
    	this.title.set(title);
    }
}
