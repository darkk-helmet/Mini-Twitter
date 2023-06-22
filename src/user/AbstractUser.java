package user;

public abstract class AbstractUser extends Subject implements IUser{
    protected final String ID;
    public static boolean isValidIDs = true;

    protected AbstractUser(String id) {
        if (isValidIDs && (USERS.containsKey(id) || id.contains(" "))) {
            isValidIDs = false;
        }
        ID = id;
        USERS.put(id, this);
    }

    @Override
    public String getID() {
        return ID;
    }
}
