import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
    }

    public User findOrCreateByUsername(String username) {
        if (users.containsKey(username)) {
            return users.get(username);
        }

        User user = new User(username);
        users.put(username, user);
        return user;
    }
}
