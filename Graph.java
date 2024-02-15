import java.util.*;

public class Graph {
    private Map<String, User> nodes; // Maps userId to Node object

    public Graph() {
        this.nodes = new HashMap<>();
    }

    // Method to add a node to the graph
    public void addUser(String userId) {
        nodes.putIfAbsent(userId, new User(userId));
    }

    // Method to add a "follows" relationship
    public void addFollowRelationship(String userId1, String userId2) {
        User user1 = nodes.get(userId1);
        User user2 = nodes.get(userId2);
        if (user1 != null && user2 != null && user1.notFollowing(user2)) {
            user1.addFollowing(user2);
            user2.addFollower(user1);
        }
    }

    public Map<String, User> getNodes(){
        return nodes;
    }

    // Method to find the distance or path between two users
    // Implement search algorithms like BFS here

    // Additional methods as needed for your application
}
