import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class friendships {
    private Map<String, List<String>> friendships;

    public friendships() {
        friendships = new HashMap<>();
    }

    public void addFriend(String person, String friend) {
        friendships.putIfAbsent(person, new ArrayList<>());
        friendships.get(person).add(friend);

        // Ensure mutual friendship
        friendships.putIfAbsent(friend, new ArrayList<>());
        friendships.get(friend).add(person);
    }

    public List<String> getFriendsList(String person) {
        return friendships.getOrDefault(person, new ArrayList<>());
    }

    public boolean areFriends(String person1, String person2) {
        return friendships.containsKey(person1) &&
                friendships.get(person1).contains(person2);
    }

    public boolean searchPerson(String person) {
        return friendships.containsKey(person);
    }
}
