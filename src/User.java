import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User extends AbstractUser {
    private final List<String> followers = new ArrayList<>();
    private final List<String> followings = new ArrayList<>();
    private final List<String> messages = new ArrayList<>();
    private final List<String> newsFeed = new ArrayList<>();
    private final UserObserver USER_OBSERVER = new UserObserver();
    private boolean isInGroup = false;

    public User(String id) {
        super(id);
        UserDatabase.getInstance().incrementUserCount();
    }

    public void setIsInGroup() {
        isInGroup = true;
    }

    public boolean addFollowing(String id) {
        if (USERS.containsKey(id)) {
            followings.add(id);
            addFollower(id);
            return true;
        }
        return false;
    }

    private void addFollower(String id) {
        IUser followingUser = UserDatabase.getInstance().getUser(id);
        ((User) followingUser).getFollowers().add(this.ID);
        attachObserver(followingUser);
    }

    private void attachObserver(IUser user) {
        ((User) user).attach(this);
    }

    public void addMessage(String message) {
        messages.add(message);
        UserDatabase.getInstance().incrementMessagesCount(message);
        notifyObservers();
    }

    public void updateNewsFeed(String message) {
        newsFeed.add(message);
    }

    public List<String> getFollowings() {
        return followings;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public List<String> getNewsFeed() {
        return newsFeed;
    }

    public List<String> getMessages() {
        return messages;
    }

    public boolean isInGroup() {
        return isInGroup;
    }

    public UserObserver getUserObserver() {
        return USER_OBSERVER;
    }

    @Override
    public void acceptTreeVisitor(TreeNodeVisitor visitor, DefaultMutableTreeNode top) {
        visitor.visitUser(this, top);
    }

    @Override
    public void acceptSubjectVisitor(SubjectVisitor visitor, User user, Map<String, UserView> userViews) {
        visitor.visitUser(this, user, userViews);
    }
}
