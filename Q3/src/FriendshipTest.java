import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

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
        assertEquals(2, friendships.getFriendsList("Joe").size());
    }

    @Test
    void testAreFriends() {
        assertTrue(friendships.areFriends("Joe", "Audrey"));
    }

    @Test
    void testSearchPerson() {
        assertTrue(friendships.searchPerson("Joe"));
        assertFalse(friendships.searchPerson("Alice"));
    }
}
