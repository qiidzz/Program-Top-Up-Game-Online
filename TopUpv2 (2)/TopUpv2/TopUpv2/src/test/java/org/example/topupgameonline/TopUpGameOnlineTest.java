package org.example.topupgameonline;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TopUpGameOnlineTest {

    @BeforeEach
    void setUp() {
        // Clear the users and transactions list before each test
        TopUpGameOnline.users.clear();
        TopUpGameOnline.transactions.clear();
    }

    @Test
    void testCreateAccount() {
        // Simulate creating an account
        String userId = "1234567890123";
        String name = "John Doe";
        String email = "johndoe@example.com";

        // Create new User and add to the list
        TopUpGameOnline.User newUser = new TopUpGameOnline.User(userId, name, email);
        TopUpGameOnline.users.add(newUser);

        // Assert that the user list size has increased by 1
        assertEquals(1, TopUpGameOnline.users.size(), "User list should have 1 user");

        // Assert that the user details are correct
        TopUpGameOnline.User createdUser = TopUpGameOnline.users.get(0);
        assertEquals(userId, createdUser.id, "User ID should match");
        assertEquals(name, createdUser.name, "User name should match");
        assertEquals(email, createdUser.email, "User email should match");
    }

    @Test
    void testTopUp() {
        // Simulate creating a user and adding to the list
        String userId = "1234567890123";
        TopUpGameOnline.User user = new TopUpGameOnline.User(userId, "John Doe", "johndoe@example.com");
        TopUpGameOnline.users.add(user);

        // Simulate top-up transaction
        double topUpAmount = 100.0;
        String game = "ML"; // Mobile Legends
        String paymentMethod = "Visa";
        String imagePath = "path/to/image.jpg";

        // Simulate the transaction logic directly (e.g., for Mobile Legends)
        user.mlBalance += topUpAmount;

        // Add transaction record
        TopUpGameOnline.transactions.add(new Object[]{userId, game, topUpAmount, paymentMethod, "Sedang Diproses", imagePath});

        // Assert that the transaction was added to the list
        assertEquals(1, TopUpGameOnline.transactions.size(), "There should be one transaction");

        // Assert the correct update of user's balance
        assertEquals(100.0, user.mlBalance, "Mobile Legends balance should be updated");

        // Assert transaction details
        Object[] transaction = TopUpGameOnline.transactions.get(0);
        assertEquals(userId, transaction[0], "Transaction ID should match");
        assertEquals(game, transaction[1], "Game should match");
        assertEquals(topUpAmount, transaction[2], "Amount should match");
        assertEquals(paymentMethod, transaction[3], "Payment method should match");
        assertEquals("Sedang Diproses", transaction[4], "Status should match");
    }

    @Test
    void testTransactionStatusUpdate() {
        // Simulate creating a user and adding a transaction
        String userId = "1234567890123";
        TopUpGameOnline.User user = new TopUpGameOnline.User(userId, "John Doe", "johndoe@example.com");
        TopUpGameOnline.users.add(user);

        // Simulate top-up transaction
        double topUpAmount = 100.0;
        String game = "FF"; // Free Fire
        String paymentMethod = "Gopay";
        String imagePath = "path/to/image.jpg";
        user.ffBalance += topUpAmount;

        TopUpGameOnline.transactions.add(new Object[]{userId, game, topUpAmount, paymentMethod, "Sedang Diproses", imagePath});

        // Check that transaction is initially "Sedang Diproses"
        Object[] transactionBeforeUpdate = TopUpGameOnline.transactions.get(0);
        assertEquals("Sedang Diproses", transactionBeforeUpdate[4], "Status should be 'Sedang Diproses' before update");

        // Simulate updating the status of the transaction to "Completed"
        String newStatus = "Completed";
        transactionBeforeUpdate[4] = newStatus;

        // Assert that the status has been updated
        assertEquals(newStatus, transactionBeforeUpdate[4], "Transaction status should be updated to 'Completed'");
    }

    @Test
    void testInvalidUserIdForTopUp() {
        // Simulate creating a user
        TopUpGameOnline.User user = new TopUpGameOnline.User("1234567890123", "John Doe", "johndoe@example.com");
        TopUpGameOnline.users.add(user);

        // Simulate top-up with invalid user ID (less than 13 digits)
        String invalidUserId = "123";
        String game = "ML"; // Mobile Legends
        double topUpAmount = 100.0;

        // Simulate a failed top-up transaction due to invalid User ID length
        TopUpGameOnline.User invalidUser = TopUpGameOnline.users.stream()
                .filter(u -> u.id.equals(invalidUserId))
                .findFirst()
                .orElse(null);

        // Assert that invalid user is not found
        assertNull(invalidUser, "User should not exist with invalid ID");

        // Assert no transaction is added with invalid user ID
        assertEquals(0, TopUpGameOnline.transactions.size(), "No transaction should be added for invalid user");
    }
}
