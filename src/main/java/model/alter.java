package main.java.model;
import main.java.dao.addbook;
public class alter {
	private Integer ID;
	private Integer Year;
	private String nameBook;
    private String chapBook;
    private String publisher;
    private String releaseYear;
    private String nameAuthor;
    private String styleBook;
    private String bookCode;
    private String quantity;
    
    public alter() {
        super();
    }

    public alter(String bookCode, String nameBook, String chapBook,String nameAuthor, 
    		String styleBook, String publisher,String releaseYear, String quantity) {
        this.bookCode = bookCode;
        this.nameBook = nameBook;
        this.chapBook = chapBook;
        this.nameAuthor = nameAuthor;
        this.styleBook = styleBook;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
        this.quantity = quantity;
    }
    
    public void setID(int ID) {
    	this.ID = ID;
    }
    public void setbookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public void setnameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public void setchapBook(String chapBook) {
        this.chapBook = chapBook;
    }

    public void setnameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public void setstyleBook(String styleBook) {
        this.styleBook = styleBook;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setreleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
    
    public void setYear(String releaseYear) {
    	this.Year = Integer.parseInt(releaseYear);
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    public Integer getYear() {
    	return Year;
    }
    
    public Integer getID() {
    	return ID;
    }

    public String getbookCode() {
        return bookCode;
    }

    public String getnameBook() {
        return nameBook;
    }

    public String getchapBook() {
        return chapBook;
    }

    public String getnameAuthor() {
        return nameAuthor;
    }

    public String getstyleBook() {
        return styleBook;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getreleaseYear() {
        return releaseYear;
    }

    public String getQuantity() {
        return quantity;
    }
}
