package org.example.topupgameonline;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TopUpGameOnline {

    static final List<User> users = new ArrayList<>();
    static final List<Object[]> transactions = new ArrayList<>();

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("MyItem - Welcome");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        // Set background color of the main frame to black
        mainFrame.getContentPane().setBackground(Color.BLACK);

        JLabel welcomeLabel = new JLabel("Welcome to MyItem", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE); // Set text color to white
        mainFrame.add(welcomeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.setBackground(Color.BLACK); // Set background of button panel to black

        JButton topUpButton = new JButton("Top Up");
        JButton checkTopUpButton = new JButton("Check TopUp");
        JButton adminButton = new JButton("Admin Only");
        JButton createAccountButton = new JButton("Buat Akun");

        // Set font and text color for buttons
        Font buttonFont = new Font("Arial", Font.PLAIN, 18);
        topUpButton.setFont(buttonFont);
        checkTopUpButton.setFont(buttonFont);
        adminButton.setFont(buttonFont);
        createAccountButton.setFont(buttonFont);

        // Set background color for buttons
        topUpButton.setBackground(Color.BLUE);
        checkTopUpButton.setBackground(Color.CYAN);
        adminButton.setBackground(Color.RED);
        createAccountButton.setBackground(Color.ORANGE);

        // Set text color for buttons to white
        topUpButton.setForeground(Color.WHITE);
        checkTopUpButton.setForeground(Color.WHITE);
        adminButton.setForeground(Color.WHITE);
        createAccountButton.setForeground(Color.WHITE);

        buttonPanel.add(topUpButton);
        buttonPanel.add(checkTopUpButton);
        buttonPanel.add(adminButton);
        buttonPanel.add(createAccountButton);

        mainFrame.add(buttonPanel, BorderLayout.SOUTH);

        topUpButton.addActionListener(_ -> showTopUpMenu());
        checkTopUpButton.addActionListener(_ -> showCheckTopUpMenu());
        adminButton.addActionListener(_ -> showAdminMenu());
        createAccountButton.addActionListener(_ -> showCreateAccountMenu());

        mainFrame.setVisible(true);
    }

    private static void showTopUpMenu() {
        JFrame topUpFrame = new JFrame("Top Up Menu");
        topUpFrame.setSize(400, 300);
        topUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        topUpFrame.setLayout(new GridLayout(4, 1));

        // Set background color of TopUp frame to black
        topUpFrame.getContentPane().setBackground(Color.BLACK);

        JLabel menuLabel = new JLabel("Select a game for top-up:", SwingConstants.CENTER);
        menuLabel.setFont(new Font("Arial", Font.BOLD, 18));
        menuLabel.setForeground(Color.WHITE); // Set text color to white
        topUpFrame.add(menuLabel);

        JButton mlButton = new JButton("Mobile Legends");
        JButton ffButton = new JButton("Free Fire");
        JButton robuxButton = new JButton("Robux");

        // Set button styles
        mlButton.setBackground(Color.BLUE);
        ffButton.setBackground(Color.CYAN);
        robuxButton.setBackground(Color.RED);

        mlButton.setForeground(Color.WHITE);
        ffButton.setForeground(Color.WHITE);
        robuxButton.setForeground(Color.WHITE);

        mlButton.addActionListener(_ -> showTopUpForm("ML"));
        ffButton.addActionListener(_ -> showTopUpForm("FF"));
        robuxButton.addActionListener(_ -> showTopUpForm("Robux"));

        topUpFrame.add(mlButton);
        topUpFrame.add(ffButton);
        topUpFrame.add(robuxButton);

        topUpFrame.setVisible(true);
    }

    private static void showCheckTopUpMenu() {
        JFrame checkTopUpFrame = new JFrame("Check TopUp");
        checkTopUpFrame.setSize(600, 400);
        checkTopUpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set background color of CheckTopUp frame to black
        checkTopUpFrame.getContentPane().setBackground(Color.BLACK);

        String[] columnNames = {"ID", "Game", "Amount", "Payment Method", "Status", "Image"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable topUpTable = new JTable(tableModel);
        topUpTable.setRowHeight(60); // Adjust row height for images
        topUpTable.setDefaultRenderer(Object.class, new TableCellRenderer());
        JScrollPane scrollPane = new JScrollPane(topUpTable);

        for (Object[] transaction : transactions) {
            tableModel.addRow(transaction);
        }

        checkTopUpFrame.add(scrollPane);
        checkTopUpFrame.setVisible(true);
    }

    private static void showAdminMenu() {
        JFrame adminFrame = new JFrame("Admin Only");
        adminFrame.setSize(600, 400);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set background color of Admin frame to black
        adminFrame.getContentPane().setBackground(Color.BLACK);

        String[] columnNames = {"ID", "Game", "Amount", "Payment Method", "Status", "Image"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable transactionTable = new JTable(tableModel);
        transactionTable.setRowHeight(60); // Adjust row height for images
        transactionTable.setDefaultRenderer(Object.class, new TableCellRenderer());
        JScrollPane scrollPane = new JScrollPane(transactionTable);

        for (Object[] transaction : transactions) {
            tableModel.addRow(transaction);
        }

        JButton updateStatusButton = new JButton("Update Status");

        updateStatusButton.addActionListener(_ -> {
            int selectedRow = transactionTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(adminFrame, "Please select a transaction to update.");
                return;
            }

            String[] statusOptions = {"Sedang Diproses", "Completed"};
            String newStatus = (String) JOptionPane.showInputDialog(adminFrame, "Select new status:", "Update Status",
                    JOptionPane.PLAIN_MESSAGE, null, statusOptions, statusOptions[0]);

            if (newStatus != null) {
                tableModel.setValueAt(newStatus, selectedRow, 4);
                transactions.get(selectedRow)[4] = newStatus;
                JOptionPane.showMessageDialog(adminFrame, "Status updated successfully.");
            }
        });

        adminFrame.setLayout(new BorderLayout());
        adminFrame.add(scrollPane, BorderLayout.CENTER);
        adminFrame.add(updateStatusButton, BorderLayout.SOUTH);
        adminFrame.setVisible(true);
    }

    private static void showTopUpForm(String game) {
        JFrame formFrame = new JFrame("Top Up - " + game);
        formFrame.setSize(400, 500);
        formFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formFrame.setLayout(new GridLayout(6, 2));

        // Set background color of form frame to black
        formFrame.getContentPane().setBackground(Color.BLACK);

        JLabel userIdLabel = new JLabel("Enter User ID (13 digits):");
        userIdLabel.setForeground(Color.WHITE); // Set text color to white
        JTextField userIdField = new JTextField();

        userIdField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (userIdField.getText().length() >= 13 || !Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.WHITE); // Set text color to white
        JTextField amountField = new JTextField();

        JLabel paymentLabel = new JLabel("Payment Method:");
        paymentLabel.setForeground(Color.WHITE); // Set text color to white
        String[] paymentMethods = {"Visa", "Dana", "Gopay"};
        JComboBox<String> paymentComboBox = new JComboBox<>(paymentMethods);

        JLabel imageLabel = new JLabel("Upload Image:");
        imageLabel.setForeground(Color.WHITE); // Set text color to white
        JButton uploadButton = new JButton("Upload");
        JLabel uploadedImageLabel = new JLabel();
        final String[] uploadedImagePath = {null};

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(formFrame);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                uploadedImagePath[0] = fileChooser.getSelectedFile().getAbsolutePath();
                uploadedImageLabel.setText("Image Uploaded");
            }
        });

        JButton payButton = new JButton("Pay");

        formFrame.add(userIdLabel);
        formFrame.add(userIdField);
        formFrame.add(amountLabel);
        formFrame.add(amountField);
        formFrame.add(paymentLabel);
        formFrame.add(paymentComboBox);
        formFrame.add(imageLabel);
        formFrame.add(uploadButton);
        formFrame.add(new JLabel()); // Spacer
        formFrame.add(uploadedImageLabel);
        formFrame.add(new JLabel()); // Spacer
        formFrame.add(payButton);

        payButton.addActionListener(_ -> {
            try {
                String userId = userIdField.getText();
                if (userId.length() != 13) {
                    JOptionPane.showMessageDialog(formFrame, "User ID must be exactly 13 digits.");
                    return;
                }

                double amount = Double.parseDouble(amountField.getText());
                String paymentMethod = (String) paymentComboBox.getSelectedItem();

                User user = users.stream().filter(u -> u.id.equals(userId)).findFirst().orElse(null);
                if (user != null) {
                    switch (game.toLowerCase()) {
                        case "ml":
                            user.mlBalance += amount;
                            break;
                        case "ff":
                            user.ffBalance += amount;
                            break;
                        case "robux":
                            user.robuxBalance += amount;
                            break;
                        default:
                            JOptionPane.showMessageDialog(formFrame, "Invalid game selection.");
                            return;
                    }
                    transactions.add(new Object[]{userId, game, amount, paymentMethod, "Sedang Diproses", uploadedImagePath[0]});
                    JOptionPane.showMessageDialog(formFrame, "Payment successful! " + game + " balance updated.");
                } else {
                    JOptionPane.showMessageDialog(formFrame, "User not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(formFrame, "Invalid input. Make sure Amount is a valid number.");
            }
        });

        formFrame.setVisible(true);
    }

    private static void showCreateAccountMenu() {
        JFrame createAccountFrame = new JFrame("Buat Akun");
        createAccountFrame.setSize(400, 300);
        createAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createAccountFrame.setLayout(new GridLayout(5, 2));

        // Set background color of Create Account frame to black
        createAccountFrame.getContentPane().setBackground(Color.BLACK);

        JLabel idLabel = new JLabel("User ID (13 digits):");
        idLabel.setForeground(Color.WHITE); // Set text color to white
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Nama:");
        nameLabel.setForeground(Color.WHITE); // Set text color to white
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE); // Set text color to white
        JTextField emailField = new JTextField();
        JButton createButton = new JButton("Buat Akun");

        createAccountFrame.add(idLabel);
        createAccountFrame.add(idField);
        createAccountFrame.add(nameLabel);
        createAccountFrame.add(nameField);
        createAccountFrame.add(emailLabel);
        createAccountFrame.add(emailField);
        createAccountFrame.add(new JLabel()); // Spacer
        createAccountFrame.add(createButton);

        createButton.addActionListener(_ -> {
            String id = idField.getText();
            if (id.length() != 13) {
                JOptionPane.showMessageDialog(createAccountFrame, "User ID harus 13 digit.");
                return;
            }
            String name = nameField.getText();
            String email = emailField.getText();
            users.add(new User(id, name, email, 0, 0, 0));
            JOptionPane.showMessageDialog(createAccountFrame, "Akun berhasil dibuat!");
        });

        createAccountFrame.setVisible(true);
    }
}

class User {
    String id;
    String name;
    String email;
    double mlBalance;
    double ffBalance;
    double robuxBalance;

    public User(String id, String name, String email, double mlBalance, double ffBalance, double robuxBalance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mlBalance = mlBalance;
        this.ffBalance = ffBalance;
        this.robuxBalance = robuxBalance;
    }
}

class TableCellRenderer extends DefaultTableCellRenderer implements javax.swing.table.TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (column == 5 && value != null) {
            ImageIcon imageIcon = new ImageIcon((String) value);
            Image scaledImage = imageIcon.getImage().getScaledInstance(100, 50, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}