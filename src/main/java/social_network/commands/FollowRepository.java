package social_network.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FollowRepository {
    private HashMap<String, List<String>> follows;

    public FollowRepository() {
        follows = new HashMap<>();
    }

    public void save(String user, String followee) {
        List<String> userFollows = follows.getOrDefault(user,
                new ArrayList<>(Collections.singleton(user))
        );

        userFollows.add(followee);
        follows.put(user, userFollows);
    }

    public List<String> getFollowsByUser(String user) {
        return follows.getOrDefault(user, List.of(user));
    }
}
