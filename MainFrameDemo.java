package LibaryManagement;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// Lớp chính cho giao diện quản lý thư viện
public class MainFrameDemo extends JFrame {
    private JPanel contentPane; // Pane chính
    private JPanel sideBar; // Thanh bên
    private CardLayout cardLayout; // Layout cho các trang
    private JPanel mainContent; // Nội dung chính
    private DefaultTableModel memberTableModel; // Để quản lý bảng thành viên
    private JTable membersTable; // Bảng hiển thị thành viên
    private List<Member> memberList = new ArrayList<>(); // Danh sách thành viên

    // Constructor
    public MainFrameDemo() {
        setTitle("Hệ Thống Quản Lý Thư Viện");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Đặt giao diện hệ thống
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        initComponents(); // Khởi tạo các thành phần
    }
    
    private void initComponents() {
        contentPane = new JPanel(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        // Tạo thanh bên
        createSidebar();
        
        // Tạo nội dung chính
        createMainContent();
        
        // Hiển thị trang chính mặc định
        showHome();
    }

    private void createSidebar() {
        sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(250, 0));
        sideBar.setBackground(new Color(33, 33, 33));
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        
        // Panel logo
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(33, 33, 33));
        logoPanel.setMaximumSize(new Dimension(250, 100));
        JLabel logoLabel = new JLabel("HỆ THỐNG THƯ VIỆN");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logoPanel.add(logoLabel);
        sideBar.add(logoPanel);
        
        // Các mục menu
        addMenuItem("Trang Chủ", e -> showHome());
        addMenuItem("Quản Lý Thành Viên", e -> showMembers());
        
        contentPane.add(sideBar, BorderLayout.WEST);
    }
    
    private void addMenuItem(String text, ActionListener action) {
        JPanel menuItem = new JPanel();
        menuItem.setLayout(new BoxLayout(menuItem, BoxLayout.X_AXIS));
        menuItem.setBackground(new Color(33, 33, 33));
        menuItem.setMaximumSize(new Dimension(250, 50));
        
        // Thêm văn bản
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        menuItem.add(textLabel);
        
        // Xử lý sự kiện khi nhấp chuột
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        });
        
        sideBar.add(menuItem);
    }
    
    private void createMainContent() {
        mainContent = new JPanel();
        cardLayout = new CardLayout();
        mainContent.setLayout(cardLayout);
        
        // Thêm các trang
        mainContent.add(createHomePage(), "HOME");
        mainContent.add(createMembersPage(), "MEMBERS");
        
        contentPane.add(mainContent, BorderLayout.CENTER);
    }

    private JPanel createHomePage() {
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(Color.WHITE);
        JLabel welcomeLabel = new JLabel("Chào mừng đến với Hệ Thống Quản Lý Thư Viện");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        homePanel.add(welcomeLabel, BorderLayout.NORTH);
        return homePanel;
    }

    private JPanel createMembersPage() {
        JPanel membersPanel = new JPanel(new BorderLayout());
        membersPanel.setBackground(Color.WHITE);
        
        // Panel tiêu đề
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Quản Lý Thành Viên");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        // Nút Thêm Thành Viên Mới
        JButton addButton = new JButton("Thêm Thành Viên");
        addButton.addActionListener(e -> showAddMemberDialog());
        buttonPanel.add(addButton);

        // Nút Chỉnh Sửa Thành Viên
        JButton editButton = new JButton("Chỉnh Sửa Thành Viên");
        editButton.addActionListener(e -> showEditMemberDialog());
        buttonPanel.add(editButton);

        // Nút Xóa Thành Viên
        JButton deleteButton = new JButton("Xóa Thành Viên");
        deleteButton.addActionListener(e -> deleteSelectedMember());
        buttonPanel.add(deleteButton);

        // Thêm buttonPanel vào headerPanel
        headerPanel.add(buttonPanel, BorderLayout.EAST);

        // Thêm headerPanel vào membersPanel
        membersPanel.add(headerPanel, BorderLayout.NORTH);

        // Tạo bảng với DefaultTableModel
        String[] columns = {"Mã Thành Viên", "Tên", "Email", "Số Điện Thoại", "Địa Chỉ"};
        memberTableModel = new DefaultTableModel(columns, 0);
        membersTable = new JTable(memberTableModel);
        
        // Tải dữ liệu mẫu
        loadMemberData();

        JScrollPane scrollPane = new JScrollPane(membersTable);
        membersPanel.add(scrollPane, BorderLayout.CENTER);

        return membersPanel; // Trả về panel quản lý thành viên
    }

    private void loadMemberData() {
        // Thêm dữ liệu mẫu vào danh sách thành viên
        memberList.add(new Member("1", "Nguyễn Văn A", "a@example.com", "0123456789", "123 Đường A"));
        memberList.add(new Member("2", "Trần Thị B", "b@example.com", "0987654321", "456 Đường B"));
        
        // Cập nhật bảng với dữ liệu từ danh sách
        for (Member member : memberList) {
            memberTableModel.addRow(new Object[]{
                member.getMemberId(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getAddress()
            });
        }
    }

    private void showAddMemberDialog() {
        JDialog addDialog = new JDialog(this, "Thêm Thành Viên Mới", true);
        addDialog.setSize(400, 400);
        addDialog.setLocationRelativeTo(this);
        addDialog.setLayout(new BorderLayout());

        // Panel chính
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Các trường nhập liệu
        JTextField memberIdField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();

        // Thêm các components vào panel
        mainPanel.add(new JLabel("Mã Thành Viên:"));
        mainPanel.add(memberIdField);
        
        mainPanel.add(new JLabel("Tên:"));
        mainPanel.add(nameField);
        
        mainPanel.add(new JLabel("Email:"));
        mainPanel.add(emailField);
        
        mainPanel.add(new JLabel("Số Điện Thoại:"));
        mainPanel.add(phoneField);
        
        mainPanel.add(new JLabel("Địa Chỉ:"));
        mainPanel.add(addressField);

        // Panel cho các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Lưu");
        JButton cancelButton = new JButton("Hủy");

        // Xử lý sự kiện nút Lưu
        saveButton.addActionListener(e -> {
            // Kiểm tra dữ liệu đầu vào
            if (memberIdField.getText().isEmpty() || nameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(addDialog, 
                    "Mã Thành Viên và Tên không được để trống!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tạo thành viên mới và thêm vào danh sách
            Member newMember = new Member(
                memberIdField.getText(),
                nameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                addressField.getText()
            );

            memberList.add(newMember); // Thêm thành viên vào danh sách
            memberTableModel.addRow(new Object[]{
                newMember.getMemberId(),
                newMember.getName(),
                newMember.getEmail(),
                newMember.getPhone(),
                newMember.getAddress()
            });

            addDialog.dispose(); // Đóng hộp thoại sau khi lưu
        });

        // Xử lý sự kiện nút Hủy
        cancelButton.addActionListener(e -> addDialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Thêm các panel vào dialog
        addDialog.add(mainPanel, BorderLayout.CENTER);
        addDialog.add(buttonPanel, BorderLayout.SOUTH);

        addDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        addDialog.setVisible(true); // Hiển thị hộp thoại
    }

    private void showEditMemberDialog() {
        // Kiểm tra xem có thành viên nào được chọn không
        int selectedRow = membersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thành viên để chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy thông tin thành viên đã chọn
        Member selectedMember = memberList.get(selectedRow);
        
        // Hiển thị hộp thoại chỉnh sửa
        JDialog editDialog = new JDialog(this, "Chỉnh Sửa Thành Viên", true);
        editDialog.setSize(400, 400);
        editDialog.setLocationRelativeTo(this);
        editDialog.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Các trường nhập liệu với thông tin hiện tại
        JTextField memberIdField = new JTextField(selectedMember.getMemberId());
        JTextField nameField = new JTextField(selectedMember.getName());
        JTextField emailField = new JTextField(selectedMember.getEmail());
        JTextField phoneField = new JTextField(selectedMember.getPhone());
        JTextField addressField = new JTextField(selectedMember.getAddress());

        // Thêm các components vào panel
        mainPanel.add(new JLabel("Mã Thành Viên:"));
        mainPanel.add(memberIdField);
        
        mainPanel.add(new JLabel("Tên:"));
        mainPanel.add(nameField);
        
        mainPanel.add(new JLabel("Email:"));
        mainPanel.add(emailField);
        
        mainPanel.add(new JLabel("Số Điện Thoại:"));
        mainPanel.add(phoneField);
        
        mainPanel.add(new JLabel("Địa Chỉ:"));
        mainPanel.add(addressField);

        // Panel cho các nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Lưu");
        JButton cancelButton = new JButton("Hủy");

        // Xử lý sự kiện nút Lưu
        saveButton.addActionListener(e -> {
            // Cập nhật thông tin thành viên
            selectedMember.setName(nameField.getText());
            selectedMember.setEmail(emailField.getText());
            selectedMember.setPhone(phoneField.getText());
            selectedMember.setAddress(addressField.getText());

            // Cập nhật bảng
            memberTableModel.setValueAt(selectedMember.getName(), selectedRow, 1);
            memberTableModel.setValueAt(selectedMember.getEmail(), selectedRow, 2);
            memberTableModel.setValueAt(selectedMember.getPhone(), selectedRow, 3);
            memberTableModel.setValueAt(selectedMember.getAddress(), selectedRow, 4);

            editDialog.dispose(); // Đóng hộp thoại sau khi lưu
        });

        // Xử lý sự kiện nút Hủy
        cancelButton.addActionListener(e -> editDialog.dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Thêm các panel vào dialog
        editDialog.add(mainPanel, BorderLayout.CENTER);
        editDialog.add(buttonPanel, BorderLayout.SOUTH);

        editDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        editDialog.setVisible(true); // Hiển thị hộp thoại
    }

    private void deleteSelectedMember() {
        // Kiểm tra xem có thành viên nào được chọn không
        int selectedRow = membersTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thành viên để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Xác nhận xóa thành viên
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa thành viên này?", 
            "Xác Nhận", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // Xóa thành viên khỏi danh sách
            memberList.remove(selectedRow);
            memberTableModel.removeRow(selectedRow); // Cập nhật bảng
        }
    }

    private void showHome() {
        cardLayout.show(mainContent, "HOME"); // Hiển thị trang chính
    }

    private void showMembers() {
        cardLayout.show(mainContent, "MEMBERS"); // Hiển thị trang quản lý thành viên
    }

    // Phương thức main để chạy ứng dụng
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrameDemo frame = new MainFrameDemo();
            frame.setVisible(true); // Hiển thị khung chính
        });
    }
}
