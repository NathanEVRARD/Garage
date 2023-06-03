package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialogMessage extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel Message;


    public JDialogMessage(String message) {


        super();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        Message.setText(message);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        JDialogMessage dialog = new JDialogMessage("Default message");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
