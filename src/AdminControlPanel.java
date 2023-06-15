import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class AdminControlPanel implements Window {
    private JTree userTree;
    private JPanel controlPanel;
    private JButton addUser;
    private JButton addGroup;
    private JButton userView;
    private JButton userTotal;
    private JButton groupTotal;
    private JButton messagesTotal;
    private JButton positivePercentage;
    private JLabel userIDLabel;
    private JLabel groupIDLabel;
    private JScrollPane treeScrollPane;
    private JTextField userID;
    private JTextField groupID;
    private static AdminControlPanel instance;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;
    private String selectedID;
    private boolean userIsSelected = false;

    private AdminControlPanel() {
        userTree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) userTree.getLastSelectedPathComponent();

                if (node == null)
                    return;

                Object nodeInfo = node.getUserObject();
                if (node.isLeaf()) {
                    selectedID = (String) nodeInfo;
                    userIsSelected = true;
                }
                else {
                    selectedID = (String) nodeInfo;
                    userIsSelected = false;
                }
            }
        });
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userID.getText().isBlank())
                    return;
                String newUserID = userID.getText();
                userID.setText("");
                UserDatabase.getInstance().addUser(newUserID);
                DefaultMutableTreeNode newUser = new DefaultMutableTreeNode(newUserID);
                newUser.setAllowsChildren(false);
                root.add(newUser);
                treeModel.reload();
            }
        });
        addGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (groupID.getText().isBlank())
                    return;
                String newGroupID = groupID.getText();
                groupID.setText("");
                new UserGroup(newGroupID);
                DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode(newGroupID);
                root.add(newGroup);
                treeModel.reload();
            }
        });
        userView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userIsSelected) {
                    UserView view = new UserView(selectedID);
                    UserObserver.addUserView(view, selectedID);
                    JFrame frame = new JFrame("User View");
                    frame.setContentPane(view.getPanel());
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            UserObserver.removeUserView(view, selectedID);
                            frame.dispose();
                        }
                    });
                }
                else {
                    DialogWindow dialog = new DialogWindow();
                    dialog.setText("A user must be selected from the tree to open the user view!");
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }
        });
        userTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogWindow dialog = new DialogWindow();
                dialog.setText("There is currently a total of " + UserDatabase.getInstance().getUserCount() + " users.");
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        groupTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogWindow dialog = new DialogWindow();
                dialog.setText("There is currently a total of " + UserGroup.getNumGroups() + " groups.");
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        messagesTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogWindow dialog = new DialogWindow();
                dialog.setText("There has been a total of " + UserDatabase.getInstance().getMessagesCount() + " messages posted.");
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        positivePercentage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogWindow dialog = new DialogWindow();
                dialog.setText("There has been a total of " + UserDatabase.getInstance().getPositiveMessagesCount() +
                        " positive messages posted.");
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

    public static AdminControlPanel getInstance() {
        if (instance == null) {
            synchronized (AdminControlPanel.class) {
                if (instance == null)
                    instance = new AdminControlPanel();
            }
        }
        return instance;
    }

    @Override
    public JPanel getPanel() {
        return controlPanel;
    }

    private void createNodes(DefaultMutableTreeNode top, List<IUser> users) {
        DefaultMutableTreeNode subCategory;
        DefaultMutableTreeNode user;
        String groupID;
        List<IUser> groupUsers;

        for(IUser iUser : users) {
            if (iUser instanceof UserGroup) {
                groupID = iUser.getID();
                groupUsers = (((UserGroup) iUser).getGroupUserList().get(groupID));
                subCategory = new DefaultMutableTreeNode(groupID);
                top.add(subCategory);
                createNodes(subCategory, groupUsers);
            } else {
                user = new DefaultMutableTreeNode(iUser.getID());
                user.setAllowsChildren(false);
                top.add(user);
            }
        }
    }

    private void createUIComponents() {
        root = new DefaultMutableTreeNode("Root");
        createNodes(root, UserDatabase.getInstance().getUserList());
        treeModel = new DefaultTreeModel(root);
        treeModel.setAsksAllowsChildren(true);
        userTree = new JTree(treeModel);
        userTree.setEditable(true);
        userTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }
}
