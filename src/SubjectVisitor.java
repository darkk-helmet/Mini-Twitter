import java.util.Map;

public interface SubjectVisitor {
    void visitUser(User user, User user2, Map<String, UserView> userViews);
    void visitUserGroup(UserGroup userGroup, User user2, Map<String, UserView> userViews);
}
