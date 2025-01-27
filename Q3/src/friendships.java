import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Manages friendships between people.
public class friendships {
    private Map<String, List<String>> friendships; // Stores each person and their list of friends.

    public friendships() {
        friendships = new HashMap<>();
    }

    // Adds a mutual friendship between two people.
    public void addFriend(String person, String friend) {
        friendships.putIfAbsent(person, new ArrayList<>());
        friendships.get(person).add(friend);

        friendships.putIfAbsent(friend, new ArrayList<>());
        friendships.get(friend).add(person);
    }

    // Returns the list of friends for a person, or an empty list if the person doesn't exist.
    public List<String> getFriendsList(String person) {
        return friendships.getOrDefault(person, new ArrayList<>());
    }

    // Checks if two people are friends.
    public boolean areFriends(String person1, String person2) {
        return friendships.containsKey(person1) && friendships.get(person1).contains(person2);
    }

    // Checks if a person exists in the network.
    public boolean searchPerson(String person) {
        return friendships.containsKey(person);
    }
}
