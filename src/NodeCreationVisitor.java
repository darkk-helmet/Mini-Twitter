import javax.swing.tree.DefaultMutableTreeNode;

public class NodeCreationVisitor implements TreeNodeVisitor {
    @Override
    public void visitUser(User user, DefaultMutableTreeNode top) {
        AdminControlPanel.createUserNode(user, top);
    }

    @Override
    public void visitUserGroup(UserGroup userGroup, DefaultMutableTreeNode top) {
        AdminControlPanel.createGroupNode(userGroup, top);
    }

    @Override
    public void visitUserDatabase(UserDatabase userDatabase, DefaultMutableTreeNode top) {}
}
