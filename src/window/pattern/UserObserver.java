package window.pattern;

import user.IUser;
import user.Subject;
import user.type.User;
import window.type.UserView;

import java.util.HashMap;
import java.util.Map;

public class UserObserver implements Observer {
    private static final Map<String, UserView> userViews = new HashMap<>();

    @Override
    public void update(Subject subject, IUser user) {
        subject.acceptSubjectVisitor(new NewsFeedVisitor(), (User) user, userViews);
    }

    protected void updateUsersNewsFeed(User postingUser, User userToUpdate) {
        userToUpdate.updateNewsFeed("\n-   " + postingUser.getID() + ": " +
                postingUser.getMessages().get(postingUser.getMessages().size() - 1));
        postingUser.updateNewsFeed("\n-   " + postingUser.getID() + ": " +
                postingUser.getMessages().get(postingUser.getMessages().size() - 1));
    }

    public static void addUserView(UserView userView, String id) {
        userViews.put(id, userView);
    }

    public static void removeUserView(UserView userView, String id) {
        userViews.remove(id, userView);
    }
}
