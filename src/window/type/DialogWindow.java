package window.type;

import javax.swing.*;
import java.awt.event.*;

public class DialogWindow extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel label;

    public DialogWindow() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOKOrCancel());

        // call onOKOrCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onOKOrCancel();
            }
        });

        // call onOKOrCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onOKOrCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOKOrCancel() {
        dispose();
    }

    public void setText(String text) {
        label.setText(text);
    }
}
