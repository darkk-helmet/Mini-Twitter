import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDatabase implements IUser {
    private final List<IUser> userList = new ArrayList<>();
    private static UserDatabase instance;
    private int userCount = 0;
    private int messagesCount = 0;
    private int positiveMessagesCount = 0;
    private final List<String> positiveKeywords = Arrays.asList("good", "great", "excellent", "nice");

    private UserDatabase() {}

    public static UserDatabase getInstance() {
        if (instance == null) {
            synchronized (AdminControlPanel.class) {
                if (instance == null)
                    instance = new UserDatabase();
            }
        }
        return instance;
    }

    public List<IUser> getUserList() {
        return userList;
    }

    public void addUsers(List<IUser> users) {
        userList.addAll(users);
    }

    public void addUser(String id) {
        userList.add(new User(id));
    }

    public IUser getUser(String id) {
        return USERS.get(id);
    }

    public int getUserCount() {
        return userCount;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public int getPositiveMessagesCount() {
        return positiveMessagesCount;
    }

    public void incrementUserCount() {
        userCount++;
    }

    public void incrementMessagesCount(String message) {
        messagesCount++;
        checkIfPositiveMessage(message);
    }

    private void checkIfPositiveMessage(String message) {
        message = message.toLowerCase();
        for (String keyword : positiveKeywords) {
            if (message.contains(keyword))
                positiveMessagesCount++;
        }
    }
}
