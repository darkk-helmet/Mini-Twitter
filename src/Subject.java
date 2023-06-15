import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private final List<Observer> observers = new ArrayList<>();
    private final List<IUser> users = new ArrayList<>();

    public void attach(IUser user) {
        observers.add(((User) user).getUserObserver());
        users.add(user);
    }

    public void detach(IUser user) {
        observers.remove(((User) user).getUserObserver());
        users.remove(user);
    }

    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++)
            observers.get(i).update(this, users.get(i));
    }
}
