package window.pattern;

import user.type.User;
import user.type.UserDatabase;
import user.type.UserGroup;

import javax.swing.tree.DefaultMutableTreeNode;

public interface TreeNodeVisitor {
    void visitUser(User user, DefaultMutableTreeNode top);
    void visitUserGroup(UserGroup userGroup, DefaultMutableTreeNode top);
    void visitUserDatabase(UserDatabase userDatabase, DefaultMutableTreeNode top);
}
