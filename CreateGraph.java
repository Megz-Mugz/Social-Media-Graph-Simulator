import java.util.Random;

public class CreateGraph {

    private static Graph graph = new Graph();

    public static void main(String[] args) {
        // populateGraph(); // Ensure this is called to populate the graph
    }

    public static void populateGraph() {
        Random rand = new Random(); // create instance of Random class

        // Random number of users between 30 and 100
        int randomNum = 30 + rand.nextInt(71 - 1);

        // Add users
        for (int i = 1; i <= randomNum; i++) {
            String id = "User" + i;
            graph.addUser(id);
        }

        // Add follows relationship
        for (int i = 1; i <= randomNum; i++) {
            for (int j = 1; j <= randomNum; j++) {
                if (i != j) {
                    String user1 = "User" + i;
                    String user2 = "User" + j;
                    // 20% chance that user1 will be following user2
                    int x = 0 + rand.nextInt(5 - 1);
                    if (x == 1) {
                        graph.addFollowRelationship(user1, user2);
                    }
                }
            }
        }

        System.out.println("Graph has been randomly populated with " + randomNum + " users.");
    }

    public static Graph getGraph() {
        // Removed the populateGraph call from here to prevent multiple initializations
        populateGraph();
        return graph;
    }
}
