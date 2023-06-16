package user.pattern;

import user.type.User;
import user.type.UserDatabase;
import user.type.UserGroup;

public interface UserGroupVisitor {
    void visitUser(User user, String s);
    void visitUserGroup(UserGroup userGroup, String s);
    void visitUserDatabase(UserDatabase userDatabase, String s);
}
