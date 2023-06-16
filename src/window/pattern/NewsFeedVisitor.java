package window.pattern;

import user.type.User;
import user.type.UserGroup;
import window.type.UserView;

import java.util.Map;

public class NewsFeedVisitor implements SubjectVisitor {
    @Override
    public void visitUser(User postingUser, User userToUpdate, Map<String, UserView> userViews) {
        new UserObserver().updateUsersNewsFeed(postingUser, userToUpdate);
        UserView userView = userViews.get(userToUpdate.getID());
        if (userView != null)
            userView.updateNewsFeed(userToUpdate);
    }

    @Override
    public void visitUserGroup(UserGroup userGroup, User userToUpdate, Map<String, UserView> userViews) {}
}
