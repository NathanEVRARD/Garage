package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class JDialogSuppressionById extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldId;

    private int Id;

    private boolean Ok;

    public JDialogSuppressionById() {
        super();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Suppression par Id");

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        Id = Integer.parseInt(textFieldId.getText());
        Ok = true;
        dispose();
    }

    private void onCancel() {
        Ok = false;
        setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        JDialogSuppressionById dialog = new JDialogSuppressionById();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public int getId() {
        return Id;
    }

    public boolean isOk() {
        return Ok;
    }
}
