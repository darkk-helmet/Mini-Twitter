import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGroup extends AbstractUser {
    private static final Map<String, ArrayList<IUser>> groupUserList = new HashMap<>();

    public UserGroup(String id) {
        super(id);
        groupUserList.put(id, new ArrayList<>());
    }

    public static Map<String, ArrayList<IUser>> getGroupUserList() {
        return groupUserList;
    }

    public void addUsers(List<IUser> users) {
        for (IUser user : users)
            user.acceptUserGroupVisitor(new AddUserVisitor(), ID);
    }

    public static int getNumGroups() {
        return groupUserList.size();
    }

    @Override
    public void acceptTreeVisitor(TreeNodeVisitor visitor, DefaultMutableTreeNode top) {
        visitor.visitUserGroup(this, top);
    }

    @Override
    public void acceptUserGroupVisitor(UserGroupVisitor visitor, String groupID) {
        visitor.visitUserGroup(this, groupID);
    }

    @Override
    public void acceptSubjectVisitor(SubjectVisitor visitor, User user, Map<String, UserView> userViews) {
        visitor.visitUserGroup(this, user, userViews);
    }
}
