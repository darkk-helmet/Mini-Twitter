package window.type;

import user.type.User;
import user.type.UserDatabase;
import window.Window;

import javax.swing.*;
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
    private JTextField creationTimeText;
    private JTextField lastUpdateText;

    protected UserView(String userID) {
        User user = (User) UserDatabase.getInstance().getUser(userID);
        List<String> followings = user.getFollowings();
        List<String> messages = user.getMessages();
        List<String> newsFeed = user.getNewsFeed();

        lastUpdateText.setText("Last News Feed Update at: " + user.getLastUpdateTime());
        creationTimeText.setText(creationTimeText.getText() + user.getCreationTime());

        for(String followingUser : followings) {
            followingsText.setText(followingsText.getText() + "\n-   " + followingUser);
        }
        for (String text : newsFeed)
            feedText.setText(feedText.getText() + text);
        followButton.addActionListener(e -> {
            if (user.addFollowing(userIDText.getText())) {
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
                user.addMessage(messageText.getText());
                feedText.setText(feedText.getText() + "\n-   " + userID + ": " + messages.get(messages.size() - 1));
                lastUpdateText.setText("Last News Feed Update at: " + user.getLastUpdateTime());
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
        lastUpdateText.setText("Last News Feed Update at: " + user.getLastUpdateTime());
    }
}
