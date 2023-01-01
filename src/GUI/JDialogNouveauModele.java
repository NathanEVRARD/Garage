package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialogNouveauModele extends JDialog
{
    private JPanel contentPane;
    private JButton buttonCreer;
    private JButton buttonAnnuler;
    private JTextField textFieldNom;
    private JSpinner spinnerPuissance;
    private JComboBox comboBoxMoteur;
    private JTextField textFieldPrixDeBase;

    public JDialogNouveauModele()
    {
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setModal(true);
        setTitle("Nouveau Modèle...");

        buttonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        JDialogNouveauModele dialog = new JDialogNouveauModele();
        dialog.pack();
        dialog.setVisible(true);
    }
}
