package social_network.follows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class InMemoryFollowRepository implements FollowRepository {
    private HashMap<String, List<String>> follows;

    public InMemoryFollowRepository() {
        follows = new HashMap<>();
    }

    @Override
    public void save(String user, String followee) {
        List<String> userFollows = follows.getOrDefault(user,
                new ArrayList<>(Collections.singleton(user))
        );

        userFollows.add(followee);
        follows.put(user, userFollows);
    }

    @Override
    public List<String> getFollowsByUser(String user) {
        return follows.getOrDefault(user, List.of(user));
    }
}
