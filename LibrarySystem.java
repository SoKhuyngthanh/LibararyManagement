package LibaryManagement;

import java.util.*;
import java.util.stream.Collectors;

public class LibrarySystem {
    private List<Book> books;
    private List<LibraryMember> members;
    private List<Librarian> librarians;
    private List<BorrowRecord> borrowRecords;

    public LibrarySystem() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        librarians = new ArrayList<>();
        borrowRecords = new ArrayList<>();
    }

    // Book management
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public Book findBook(String isbn) {
        return books.stream()
                   .filter(book -> book.getIsbn().equals(isbn))
                   .findFirst()
                   .orElse(null);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // Member management
    public void addMember(LibraryMember member) {
        members.add(member);
    }

    public void removeMember(String id) {
        members.removeIf(member -> member.getId().equals(id));
    }

    public LibraryMember findMember(String id) {
        return members.stream()
                     .filter(member -> member.getId().equals(id))
                     .findFirst()
                     .orElse(null);
    }

    public List<LibraryMember> getAllMembers() {
        return new ArrayList<>(members);
    }

    // Borrowing management
    public BorrowRecord createBorrowRecord(LibraryMember member, Book book) {
        if (!member.canBorrow() || !book.isAvailable()) {
            return null;
        }

        Date borrowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(borrowDate);
        calendar.add(Calendar.DAY_OF_MONTH, 14); // 2 weeks borrowing period
        Date dueDate = calendar.getTime();

        BorrowRecord record = new BorrowRecord(
            UUID.randomUUID().toString(),
            member,
            book,
            borrowDate,
            dueDate
        );

        borrowRecords.add(record);
        book.setAvailableQuantity(book.getAvailableQuantity() - 1);
        member.addBorrowRecord(record);
        return record;
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return new ArrayList<>(borrowRecords);
    }

    // Report generation
    public List<BorrowRecord> getOverdueBooks() {
        Date currentDate = new Date();
        return borrowRecords.stream()
                          .filter(record -> record.getReturnDate() == null && 
                                          currentDate.after(record.getDueDate()))
                          .collect(Collectors.toList());
    }

    public Map<Book, Integer> getPopularBooks() {
        Map<Book, Integer> bookFrequency = new HashMap<>();
        for (BorrowRecord record : borrowRecords) {
            bookFrequency.merge(record.getBook(), 1, Integer::sum);
        }
        return bookFrequency;
    }

    public double getTotalFines() {
        return borrowRecords.stream()
                          .mapToDouble(BorrowRecord::calculateFine)
                          .sum();
    }
}
}