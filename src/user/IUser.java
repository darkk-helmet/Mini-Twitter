package user;

import user.pattern.UserGroupVisitor;
import window.pattern.TreeNodeVisitor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.HashMap;
import java.util.Map;

public interface IUser {
    Map<String, IUser> USERS = new HashMap<>();
    void acceptTreeVisitor(TreeNodeVisitor visitor, DefaultMutableTreeNode node);
    void acceptUserGroupVisitor(UserGroupVisitor visitor, String s);
    default String getID() {
        return null;
    }
}
