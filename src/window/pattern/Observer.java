package window.pattern;

import user.IUser;
import user.Subject;

public interface Observer {
    void update(Subject subject, IUser user);
}
