import javax.swing.tree.DefaultMutableTreeNode;
import java.util.HashMap;
import java.util.Map;

public interface IUser {
    Map<String, IUser> USERS = new HashMap<>();
    void acceptTreeVisitor(TreeNodeVisitor visitor, DefaultMutableTreeNode top);
    default String getID() {
        return null;
    }
}
