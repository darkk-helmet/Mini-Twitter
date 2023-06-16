package user.pattern;

import user.type.User;
import user.type.UserDatabase;
import user.type.UserGroup;

public class AddUserVisitor implements UserGroupVisitor {
    @Override
    public void visitUser(User user, String groupID) {
        if (user.isInGroup())
            throw new RuntimeException("User " + user.getID() + " cannot be in multiple groups!");
        else
            user.setIsInGroup();
        UserGroup.getGroupUserList().get(groupID).add(user);
    }

    @Override
    public void visitUserGroup(UserGroup userGroup, String groupID) {
        UserGroup.getGroupUserList().get(groupID).add(userGroup);
    }

    @Override
    public void visitUserDatabase(UserDatabase userDatabase, String groupID) {}
}
