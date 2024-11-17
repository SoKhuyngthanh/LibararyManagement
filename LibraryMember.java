package LibaryManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryMember extends Person {
    private String membershipType;
    private Date startDate;
    private Date endDate;
    private List<BorrowRecord> borrowHistory;
    private boolean isActive;

    public LibraryMember(String id, String name, String email, String phone, String address,
                        String membershipType, Date startDate, Date endDate) {
        super(id, name, email, phone, address);
        this.membershipType = membershipType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.borrowHistory = new ArrayList<>();
        this.isActive = true;
    }

    public boolean canBorrow() {
        return isActive && new Date().before(endDate);
    }

    @Override
    public String getRole() {
        return "Member";
    }

    // Getters and setters
    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<BorrowRecord> getBorrowHistory() {
        return borrowHistory;
    }

    public void addBorrowRecord(BorrowRecord record) {
        borrowHistory.add(record);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}