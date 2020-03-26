public class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public void savePost(String post) {
        throw new UnsupportedOperationException();
    }

    public String getUsername() {
        return username;
    }
}
