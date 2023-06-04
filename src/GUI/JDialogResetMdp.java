package GUI;

import javax.swing.*;
import java.awt.event.*;

public class JDialogResetMdp extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldmdpconfirmed;
    private JTextField textFieldmdp;

    private String mdp;

    private String mdpConfirmed;

    private boolean ok;

    public JDialogResetMdp() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Chnager de mot de passe !");

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
        ok = true;
        mdp = textFieldmdp.getText();
        mdpConfirmed = textFieldmdpconfirmed.getText();
        dispose();
        setVisible(false);
    }

    private void onCancel() {
        ok = false;
        setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        JDialogResetMdp dialog = new JDialogResetMdp();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public String getMdp()
    {
        return mdp;
    }
    public String getMdpConfirmed()
    {
        return mdpConfirmed;
    }

    public boolean isOk() {
        return ok;
    }
}
