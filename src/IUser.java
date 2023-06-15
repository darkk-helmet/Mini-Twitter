import java.util.HashMap;
import java.util.Map;

public interface IUser {
    Map<String, IUser> USERS = new HashMap<>();
    default String getID() {
        return null;
    }
}
