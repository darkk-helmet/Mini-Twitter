package window.type;

import user.IUser;
import user.type.User;
import user.type.UserDatabase;
import window.Window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserView implements Window {
    private JButton followButton;
    private JTextPane followingsText;
    private JTextField messageText;
    private JButton tweetButton;
    private JTextPane feedText;
    private JPanel userPanel;
    private JTextField userIDText;
    private JScrollPane followingsScroll;
    private JScrollPane feedScroll;

    protected UserView(String userID) {
        IUser user = UserDatabase.getInstance().getUser(userID);
        List<String> followings = ((User) user).getFollowings();
        List<String> messages = ((User) user).getMessages();
        List<String> newsFeed = ((User) user).getNewsFeed();
        for(String followingUser : followings) {
            followingsText.setText(followingsText.getText() + "\n-   " + followingUser);
        }
        for (String text : newsFeed)
            feedText.setText(feedText.getText() + text);
        followButton.addActionListener(e -> {
            if (((User) user).addFollowing(userIDText.getText())) {
                followingsText.setText(followingsText.getText() + "\n-   " + followings.get(followings.size() - 1));
                userIDText.setText("");
            }
            else {
                DialogWindow dialog = new DialogWindow();
                dialog.setText("User " + userIDText.getText() + " not found.");
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        tweetButton.addActionListener(e -> {
            if (!messageText.getText().isBlank()) {
                ((User) user).addMessage(messageText.getText());
                feedText.setText(feedText.getText() + "\n-   " + userID + ": " + messages.get(messages.size() - 1));
                messageText.setText("");
            }
        });
    }

    @Override
    public JPanel getPanel() {
        return userPanel;
    }

    public void updateNewsFeed(User user) {
        feedText.setText(feedText.getText() + user.getNewsFeed().get(user.getNewsFeed().size() - 1));
    }
}
