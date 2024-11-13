package model;

import javafx.beans.property.SimpleStringProperty;

public class ReturnNew {
    private SimpleStringProperty borrowerID;
    private SimpleStringProperty bookCode;
    private SimpleStringProperty userName;
    private SimpleStringProperty userID;
    private SimpleStringProperty returnDate;

    public ReturnNew() {
        this.borrowerID = new SimpleStringProperty();
        this.bookCode = new SimpleStringProperty();
        this.userName = new SimpleStringProperty();
        this.userID = new SimpleStringProperty();
        this.returnDate = new SimpleStringProperty();
    }

    public ReturnNew(String userID, String borrowerID, String bookCode, String returnDate, String userName) {
        setUserID(userID);
        setBorrowerID(borrowerID);
        setBookCode(bookCode);
        setUserName(userName);
        setReturnDate(returnDate);
        setUserName(userName);
    }

    public ReturnNew(String borrowerID,String userName, String bookCode, String returnDate) {
    	this();
        setBorrowerID(borrowerID);
        setUserName(userName);
        setBookCode(bookCode);
        setReturnDate(returnDate);
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

    public String getUserID() {
        return userID.get();
    }

    public void setUserID(String userID) {
        this.userID.set(userID);
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public void setReturnDate(String returnDate) {
        this.returnDate.set(returnDate);
    }
}
