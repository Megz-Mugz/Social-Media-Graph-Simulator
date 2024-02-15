import java.util.*;

public class User {
    private String userId; // Unique identifier for the user
    private List<User> followers; // List of users that follow this user
    private List<User> following; // List of users this user follows


    public User(String userId) {
        this.userId = userId;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    // Methods to add and remove followers/following, get followers/following, etc.
    public void addFollower(User user) {
        followers.add(user);
    }

    public void addFollowing(User user) {
        following.add(user);
    }

    // Include getters as needed
    public String getUserId() {
        return userId;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public int getNumFollowers(){
        return followers.size();
    }

    public int getNumFollowing(){
        return following.size();
    }

    public boolean notFollowing(User user){
        return !following.contains(user);
    }

}
