package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JDialogNouveauClient extends JDialog
{
    private JPanel contentPane;
    private JButton buttonCreer;
    private JButton buttonAnnuler;
    private JTextField textFieldNom;
    private JTextField textFieldPrenom;
    private JTextField textFieldGsm;

    private String nom;
    private String prenom;
    private String gsm;
    private boolean ok;

    public JDialogNouveauClient()
    {
        super();
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setModal(true);
        setTitle("Nouveau Client...");

        buttonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok = false;
                setVisible(false);
            }
        });
        buttonCreer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nom = textFieldNom.getText();
                prenom = textFieldPrenom.getText();
                gsm = textFieldGsm.getText();
                ok = true;
                setVisible(false);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Perform the action you want when the dialog is closed
                System.out.println("Dialog is being closed");
                // You can close the dialog or perform any other action here
                dispose(); // Close the dialog
            }
        });
    }

    public static void main(String[] args) {
        JDialogNouveauClient dialog = new JDialogNouveauClient();
        dialog.pack();
        dialog.setVisible(true);

        if (dialog.isOk())
        {

        }
        dialog.dispose();
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getGsm() {
        return gsm;
    }

    public boolean isOk() {
        return ok;
    }
}
