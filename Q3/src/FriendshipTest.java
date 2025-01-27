import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

// Unit tests for the `friendships` class.
public class FriendshipTest {
    private friendships friendships;

    @BeforeEach
    void setUp() throws Exception {
        friendships = new friendships();
        friendships.addFriend("Joe", "Audrey");
        friendships.addFriend("Joe", "Tom");
    }

    @AfterEach
    void tearDown() throws Exception {
        friendships = null;
    }

    @Test
    void testGetFriendsList() {
        // Verifies that "Joe" has 2 friends.
        assertEquals(2, friendships.getFriendsList("Joe").size());
    }

    @Test
    void testAreFriends() {
        // Checks that "Joe" and "Audrey" are friends.
        assertTrue(friendships.areFriends("Joe", "Audrey"));
    }

    @Test
    void testSearchPerson() {
        // Confirms "Joe" exists, and "Alice" does not.
        assertTrue(friendships.searchPerson("Joe"));
        assertFalse(friendships.searchPerson("Alice"));
    }
}
