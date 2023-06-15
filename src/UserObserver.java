import java.util.HashMap;
import java.util.Map;

public class UserObserver implements Observer {
    private static final Map<String, UserView> userViews = new HashMap<>();

    @Override
    public void update(Subject subject, IUser user) {
        if (subject instanceof User) {
            updateUsersNewsFeed(subject, user);
            UserView userView = userViews.get(user.getID());
            if (userView != null)
                userView.updateNewsFeed((User) user);
        }
    }

    private void updateUsersNewsFeed(Subject subject, IUser user) {
        ((User) user).updateNewsFeed("\n-   " + ((User) subject).getID() + ": " +
                ((User) subject).getMessages().get(((User) subject).getMessages().size() - 1));
        ((User) subject).updateNewsFeed("\n-   " + ((User) subject).getID() + ": " +
                ((User) subject).getMessages().get(((User) subject).getMessages().size() - 1));
    }

    public static void addUserView(UserView userView, String id) {
        userViews.put(id, userView);
    }

    public static void removeUserView(UserView userView, String id) {
        userViews.remove(id, userView);
    }
}
