package LibaryManagement;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int publishYear;
    private String category;
    private String location;
    private int totalQuantity;
    private int availableQuantity;
    private boolean isAvailable;

    public Book(String isbn, String title, String author, String publisher, int publishYear,
                String category, String location, int totalQuantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.category = category;
        this.location = location;
        this.totalQuantity = totalQuantity;
        this.availableQuantity = totalQuantity;
        this.isAvailable = totalQuantity > 0;
    }

    // Getters and setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
        this.isAvailable = availableQuantity > 0;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
        this.isAvailable = availableQuantity > 0;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}