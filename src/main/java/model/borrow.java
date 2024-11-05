package main.java.model;

public class borrow {
    private String borrowerID;
    private String bookCode;
    private String userName;
    private String userID;
    private String borrowDate;
    private String returnDate;
    private String status;
    private String phonenum;

    public borrow() {
        super();
    }

    public borrow(String borrowerID, String userID,String bookCode
            , String borrowDate, String returnDate, String userName,String status, String phonenum) {
        this.borrowerID = borrowerID;
        this.bookCode = bookCode;
        this.userName = userName;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
        this.phonenum = phonenum;
    }
    
    public borrow(String bookCode, String userName, String borrowDate, String returnDate, String status) {
    	this.bookCode = bookCode;
    	this.userName = userName;
    	this.borrowDate = borrowDate;
    	this.returnDate = returnDate;
    	this.status = status;
    }
    
    public borrow(String userID,String bookCode, String borrowDate, String returnDate, String status, String phonenum, String userName) {
    	this.userID = userID;
        this.bookCode = bookCode;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
        this.phonenum = phonenum;
        this.userName = userName;
    }

    public String getBorrowerID() {
        return borrowerID;
    }

    public void setBorrowerID(String borrowerID) {
        this.borrowerID = borrowerID;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}
