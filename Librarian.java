package LibaryManagement;

public class Librarian extends Person {
    private String employeeId;
    private Date joinDate;
    private String department;
    private boolean isAdmin;

    public Librarian(String id, String name, String email, String phone, String address,
                    String employeeId, Date joinDate, String department, boolean isAdmin) {
        super(id, name, email, phone, address);
        this.employeeId = employeeId;
        this.joinDate = joinDate;
        this.department = department;
        this.isAdmin = isAdmin;
    }

    @Override
    public String getRole() {
        return "Librarian";
    }

    // Getters and setters
}