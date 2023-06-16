package user;

public abstract class AbstractUser extends Subject implements IUser{
    protected final String ID;

    protected AbstractUser(String id) {
        if (USERS.containsKey(id)) {
            throw new RuntimeException("ID is not available.");
        }
        ID = id;
        USERS.put(id, this);
    }

    @Override
    public String getID() {
        return ID;
    }
}
