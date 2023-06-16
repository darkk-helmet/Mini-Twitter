package window.pattern;

import user.type.User;
import user.type.UserGroup;
import window.type.UserView;

import java.util.Map;

public interface SubjectVisitor {
    void visitUser(User user, User user2, Map<String, UserView> userViews);
    void visitUserGroup(UserGroup userGroup, User user2, Map<String, UserView> userViews);
}
