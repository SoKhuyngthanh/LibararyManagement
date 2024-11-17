package LibaryManagement;

import java.util.Date;

public class BorrowRecord {
    private String borrowId;
    private LibraryMember member;
    private Book book;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private double fine;

    public BorrowRecord(String borrowId, LibraryMember member, Book book,
                       Date borrowDate, Date dueDate) {
        this.borrowId = borrowId;
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public double calculateFine() {
        if (returnDate == null || !returnDate.after(dueDate)) {
            return 0;
        }
        long daysLate = (returnDate.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
        return daysLate * 1000; // 1000 VND per day
    }

    // Getters and setters
    public String getBorrowId() {
        return borrowId;
    }

    public LibraryMember getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
        this.fine = calculateFine();
    }

    public double getFine() {
        return fine;
    }
}