package GUI;

import javax.swing.*;
import java.awt.event.*;

public class JDialogNouvelEmploye extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldNom;
    private JTextField textFieldPrenom;
    private JRadioButton vendeurRadioButton;
    private JRadioButton administrateurRadioButton;
    private JTextField textFieldLogin;
    private JPasswordField passwordFieldMdp;

    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    private String fonction;
    private boolean ok;

    public JDialogNouvelEmploye() {

        super();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Nouvel employé ...");

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
        nom = textFieldNom.getText();
        prenom = textFieldPrenom.getText();
        login = textFieldLogin.getText();
        mdp = passwordFieldMdp.getText();

        if(vendeurRadioButton.isSelected())
        {
            fonction = "VENDEUR";
        }
        else
        {
            if(administrateurRadioButton.isSelected())
            {
                fonction = "ADMINISTRATEUR";
            }
        }

        ok = true;
        setVisible(false);
        dispose();
    }

    private void onCancel() {
        ok = false;
        setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        JDialogNouvelEmploye dialog = new JDialogNouvelEmploye();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public String getLogin() {
        return login;
    }

    public String getFonction() {
        return fonction;
    }

    public boolean isOk() {
        return ok;
    }
}
