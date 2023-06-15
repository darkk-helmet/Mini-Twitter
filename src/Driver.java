import javax.swing.*;
import java.util.Arrays;

public class Driver {

    public static void main(String[] args) {
        IUser users = UserDatabase.getInstance();
        IUser john = new User("john");

        IUser bob = new User("bob");
        IUser steve = new User("steve");
        IUser cs356 = new UserGroup("CS356");
        IUser stu1 = new User("stu1");
        IUser stu2 = new User("stu2");
        IUser stu3 = new User("stu3");
        IUser cs356Session01 = new UserGroup("CS356Session01");
        IUser stu8 = new User("stu8");
        IUser stu9 = new User("stu9");
        IUser stu10 = new User("stu10");
        IUser stu4 = new User("stu4");
        IUser oostu = new User("oostu");
        IUser ppstu2 = new User("ppstu2");

        ((User) john).addFollowing("bob");
        ((User) john).addFollowing("steve");

        ((UserGroup) cs356).addUsers(Arrays.asList(stu1, stu2, stu3, cs356Session01, stu4));
        ((UserGroup) cs356Session01).addUsers(Arrays.asList(stu8, stu9, stu10));
        ((UserDatabase) users).addUsers(Arrays.asList(john, bob, steve, cs356, oostu, ppstu2));

        JFrame frame = new JFrame("Admin Control Panel");
        frame.setContentPane(AdminControlPanel.getInstance().getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}