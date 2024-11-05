package main.java.model;

public class Return {
    private String userID;
    private String borrowerID;
    private String bookCode;
    private String returnDate;
    private String userName;

    public Return() {}

    public Return(String userID, String borrowerID, String bookCode, String returnDate, String userName) {
        this.userID = userID;
        this.borrowerID = borrowerID;
        this.bookCode = bookCode;
        this.returnDate = returnDate;
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
