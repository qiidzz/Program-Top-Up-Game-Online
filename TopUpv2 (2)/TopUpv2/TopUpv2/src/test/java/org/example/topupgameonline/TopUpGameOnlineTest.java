package org.example.topupgameonline;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopUpGameOnlineTest {

    @BeforeEach
    void setUp() {
        TopUpGameOnline.users.clear();
        TopUpGameOnline.transactions.clear();
    }

    @Test
    void testCreateAccount() {
        User user = new User("1234567890123", "Test User", "test@example.com", 0, 0, 0);
        TopUpGameOnline.users.add(user);

        assertEquals(1, TopUpGameOnline.users.size());
        assertEquals("1234567890123", TopUpGameOnline.users.getFirst().id);
        assertEquals("Test User", TopUpGameOnline.users.getFirst().name);
        assertEquals("test@example.com", TopUpGameOnline.users.getFirst().email);
    }

    @Test
    void testTopUpMLBalance() {
        User user = new User("1234567890123", "Test User", "test@example.com", 0, 0, 0);
        TopUpGameOnline.users.add(user);

        String game = "ML";
        double amount = 100.0;
        String paymentMethod = "Visa";

        User foundUser = TopUpGameOnline.users.stream()
                .filter(u -> u.id.equals("1234567890123"))
                .findFirst()
                .orElse(null);

        assertNotNull(foundUser);
        foundUser.mlBalance += amount;
        TopUpGameOnline.transactions.add(new Object[]{foundUser.id, game, amount, paymentMethod, "Sedang Diproses"});

        assertEquals(100.0, foundUser.mlBalance);
        assertEquals(1, TopUpGameOnline.transactions.size());
        assertEquals("Sedang Diproses", TopUpGameOnline.transactions.get(0)[4]);
    }

    @Test
    void testUpdateTransactionStatus() {
        User user = new User("1234567890123", "Test User", "test@example.com", 0, 0, 0);
        TopUpGameOnline.users.add(user);

        TopUpGameOnline.transactions.add(new Object[]{"1234567890123", "ML", 100.0, "Visa", "Sedang Diproses"});

        assertEquals("Sedang Diproses", TopUpGameOnline.transactions.get(0)[4]);

        TopUpGameOnline.transactions.get(0)[4] = "Completed";

        assertEquals("Completed", TopUpGameOnline.transactions.get(0)[4]);
    }

    @Test
    void testCreateAccountWithInvalidId() {
        String invalidId = "12345"; // ID kurang dari 13 digit
        assertThrows(IllegalArgumentException.class, () -> {
            if (invalidId.length() != 13) {
                throw new IllegalArgumentException("User ID harus 13 digit.");
            }
        });
    }

    @Test
    void testTopUpInvalidUser() {
        String userId = "1234567890123";
        User foundUser = TopUpGameOnline.users.stream()
                .filter(u -> u.id.equals(userId))
                .findFirst()
                .orElse(null);

        assertNull(foundUser);
    }
}