import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JPanel contentPane;
    private JPanel sideBar;
    private CardLayout cardLayout;
    private JPanel mainContent;
    
    // Constructor
    public MainFrame() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        initComponents();
    }
    
    private void initComponents() {
        contentPane = new JPanel(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        // Create sidebar
        createSidebar();
        
        // Create main content
        createMainContent();
        
        // Show home page by default
        showHome();
    }
    
    private void createSidebar() {
        sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(250, 0));
        sideBar.setBackground(new Color(33, 33, 33));
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        
        // Logo panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(new Color(33, 33, 33));
        logoPanel.setMaximumSize(new Dimension(250, 100));
        JLabel logoLabel = new JLabel("LIBRARY SYSTEM");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logoPanel.add(logoLabel);
        sideBar.add(logoPanel);
        
        // Menu items
        addMenuItem("Home", "home.png", e -> showHome());
        addMenuItem("Books", "books.png", e -> showBooks());
        addMenuItem("Members", "members.png", e -> showMembers());
        addMenuItem("Borrowing", "borrow.png", e -> showBorrowing());
        addMenuItem("Reports", "reports.png", e -> showReports());
        
        contentPane.add(sideBar, BorderLayout.WEST);
    }
    
    private void addMenuItem(String text, String iconPath, ActionListener action) {
        JPanel menuItem = new JPanel();
        menuItem.setLayout(new BoxLayout(menuItem, BoxLayout.X_AXIS));
        menuItem.setBackground(new Color(33, 33, 33));
        menuItem.setMaximumSize(new Dimension(250, 50));
        
        // Add padding
        menuItem.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Icon (you would need to add actual icons)
        JLabel iconLabel = new JLabel(" ");
        iconLabel.setForeground(Color.WHITE);
        menuItem.add(iconLabel);
        menuItem.add(Box.createRigidArea(new Dimension(20, 0)));
        
        // Text
        JLabel textLabel = new JLabel(text);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        menuItem.add(textLabel);
        
        // Hover effect
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuItem.setBackground(new Color(66, 66, 66));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                menuItem.setBackground(new Color(33, 33, 33));
            }
        });
        
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
        
        // Add pages
        mainContent.add(createHomePage(), "HOME");
        mainContent.add(createBooksPage(), "BOOKS");
        mainContent.add(createMembersPage(), "MEMBERS");
        mainContent.add(createBorrowingPage(), "BORROWING");
        mainContent.add(createReportsPage(), "REPORTS");
        
        contentPane.add(mainContent, BorderLayout.CENTER);
    }
    
    private JPanel createHomePage() {
        JPanel homePanel = new JPanel(new BorderLayout());
        homePanel.setBackground(Color.WHITE);
        
        // Welcome header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel welcomeLabel = new JLabel("Welcome to Library Management System");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(welcomeLabel, BorderLayout.WEST);
        
        // Search bar
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(300, 35));
        headerPanel.add(searchField, BorderLayout.EAST);
        
        homePanel.add(headerPanel, BorderLayout.NORTH);
        
        // Featured books grid
        JPanel booksGrid = new JPanel(new GridLayout(2, 4, 20, 20));
        booksGrid.setBackground(Color.WHITE);
        booksGrid.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Sample books
        String[][] sampleBooks = {
            {"The Great Gatsby", "F. Scott Fitzgerald", "/api/placeholder/150/200"},
            {"To Kill a Mockingbird", "Harper Lee", "/api/placeholder/150/200"},
            {"1984", "George Orwell", "/api/placeholder/150/200"},
            {"Pride and Prejudice", "Jane Austen", "/api/placeholder/150/200"},
            {"The Catcher in the Rye", "J.D. Salinger", "/api/placeholder/150/200"},
            {"Lord of the Flies", "William Golding", "/api/placeholder/150/200"},
            {"Animal Farm", "George Orwell", "/api/placeholder/150/200"},
            {"Brave New World", "Aldous Huxley", "/api/placeholder/150/200"}
        };
        
        for (String[] bookInfo : sampleBooks) {
            booksGrid.add(createBookCard(bookInfo[0], bookInfo[1], bookInfo[2]));
        }
        
        homePanel.add(new JScrollPane(booksGrid), BorderLayout.CENTER);
        
        return homePanel;
    }
    
    private JPanel createBookCard(String title, String author, String imagePath) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        // Book cover image
        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(imageLabel);
        
        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);
        
        // Author
        JLabel authorLabel = new JLabel(author);
        authorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        authorLabel.setForeground(Color.GRAY);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(authorLabel);
        
        return card;
    }
    
    private JPanel createBooksPage() {
        JPanel booksPanel = new JPanel(new BorderLayout());
        booksPanel.setBackground(Color.WHITE);
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("Books Management");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        JButton addButton = new JButton("Add New Book");
        headerPanel.add(addButton, BorderLayout.EAST);
        
        booksPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Books table
        String[] columns = {"ISBN", "Title", "Author", "Publisher", "Year", "Category", "Available"};
        Object[][] data = {
            {"978-0743273565", "The Great Gatsby", "F. Scott Fitzgerald", "Scribner", "1925", "Fiction", "Yes"},
            {"978-0446310789", "To Kill a Mockingbird", "Harper Lee", "Grand Central", "1960", "Fiction", "No"}
        };
        
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        booksPanel.add(scrollPane, BorderLayout.CENTER);
        
        return booksPanel;
    }
    
    private JPanel createMembersPage() {
        JPanel membersPanel = new JPanel(new BorderLayout());
        // Similar structure to books page
        return membersPanel;
    }
    
    private JPanel createBorrowingPage() {
        JPanel borrowingPanel = new JPanel(new BorderLayout());
        // Implementation for borrowing management
        return borrowingPanel;
    }
    
    private JPanel createReportsPage() {
        JPanel reportsPanel = new JPanel(new BorderLayout());
        // Implementation for reports
        return reportsPanel;
    }
    
    private void showHome() {
        cardLayout.show(mainContent, "HOME");
    }
    
    private void showBooks() {
        cardLayout.show(mainContent, "BOOKS");
    }
    
    private void showMembers() {
        cardLayout.show(mainContent, "MEMBERS");
    }
    
    private void showBorrowing() {
        cardLayout.show(mainContent, "BORROWING");
    }
    
    private void showReports() {
        cardLayout.show(mainContent, "REPORTS");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}