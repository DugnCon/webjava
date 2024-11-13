package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class addNew {
    private SimpleStringProperty nameBook;
    private SimpleStringProperty chapBook;
    private SimpleStringProperty publisher;
    private SimpleStringProperty releaseYear;
    private SimpleStringProperty nameAuthor;
    private SimpleStringProperty styleBook;
    private SimpleStringProperty bookCode;
    private SimpleStringProperty quantity;

    public addNew() {
        this.nameBook = new SimpleStringProperty();
        this.chapBook = new SimpleStringProperty();
        this.publisher = new SimpleStringProperty();
        this.releaseYear = new SimpleStringProperty();
        this.nameAuthor = new SimpleStringProperty();
        this.styleBook = new SimpleStringProperty();
        this.bookCode = new SimpleStringProperty();
        this.quantity = new SimpleStringProperty();
    }

    public addNew(String bookCode, String nameBook, String chapBook, String nameAuthor, 
               String styleBook, String publisher, String releaseYear, String quantity) {
        this();
        setBookCode(bookCode);
        setNameBook(nameBook);
        setChapBook(chapBook);
        setNameAuthor(nameAuthor);
        setStyleBook(styleBook);
        setPublisher(publisher);
        setReleaseYear(releaseYear);
        setQuantity(quantity);
    }
    public void setBookCode(String bookCode) {
        this.bookCode.set(bookCode);
    }

    public void setNameBook(String nameBook) {
        this.nameBook.set(nameBook);
    }

    public void setChapBook(String chapBook) {
        this.chapBook.set(chapBook);
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor.set(nameAuthor);
    }

    public void setStyleBook(String styleBook) {
        this.styleBook.set(styleBook);
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear.set(releaseYear);
    }
    
    public void setQuantity(String quantity) {
        this.quantity.set(quantity);
    }

    public String getBookCode() {
        return bookCode.get();
    }

    public String getNameBook() {
        return nameBook.get();
    }

    public String getChapBook() {
        return chapBook.get();
    }

    public String getNameAuthor() {
        return nameAuthor.get();
    }

    public String getStyleBook() {
        return styleBook.get();
    }

    public String getPublisher() {
        return publisher.get();
    }

    public String getReleaseYear() {
        return releaseYear.get();
    }

    public String getQuantity() {
        return quantity.get();
    }
}