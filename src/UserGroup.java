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

    public Map<String, ArrayList<IUser>> getGroupUserList() {
        return groupUserList;
    }

    public void addUsers(List<IUser> users) {
        for (IUser user : users) {
            if (user instanceof User) {
                if (((User) user).isInGroup()) {
                    throw new RuntimeException("User " + user.getID() + " cannot be in multiple groups!");
                } else
                    ((User) user).setIsInGroup();
            }
            groupUserList.get(ID).add(user);
        }
    }

    public static int getNumGroups() {
        return groupUserList.size();
    }

    @Override
    public void acceptTreeVisitor(TreeNodeVisitor visitor, DefaultMutableTreeNode top) {
        visitor.visitUserGroup(this, top);
    }
}
