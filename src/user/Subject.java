package user;

import user.type.User;
import window.pattern.Observer;
import window.pattern.SubjectVisitor;
import window.type.UserView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        for (int i = 0; i < observers.size(); i++) {
            ((User) users.get(i)).setLastUpdateTime(((User) this).getLastUpdateTime());
            observers.get(i).update(this, users.get(i));
        }
    }

    public abstract void acceptSubjectVisitor(SubjectVisitor visitor, User user, Map<String, UserView> userViews);
}
