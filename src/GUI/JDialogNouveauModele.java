package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JDialogNouveauModele extends JDialog
{
    private JPanel contentPane;
    private JButton buttonCreer;
    private JButton buttonAnnuler;
    private JTextField textFieldNom;
    private JSpinner spinnerPuissance;
    private JComboBox comboBoxMoteur;
    private JTextField textFieldPrixDeBase;
    private JTextField textFieldImage;

    private String nom;
    private int puissance;
    private String moteur;
    private float prixDeBase;
    private String image;
    private boolean ok;

    public JDialogNouveauModele()
    {
        super();
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setModal(true);
        setTitle("Nouveau Modèle...");

        comboBoxMoteur.addItem("Essence");
        comboBoxMoteur.addItem("Diesel");
        comboBoxMoteur.addItem("Hybride");
        comboBoxMoteur.addItem("Electrique");

        SpinnerModel model = new SpinnerNumberModel(100,30,300,5);
        spinnerPuissance.setModel(model);

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
                puissance = (int) spinnerPuissance.getValue();
                moteur = (String) comboBoxMoteur.getSelectedItem();
                prixDeBase = Float.parseFloat(textFieldPrixDeBase.getText());
                image = textFieldImage.getText();
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
        JDialogNouveauModele dialog = new JDialogNouveauModele();
        dialog.pack();
        dialog.setVisible(true);
        if (dialog.isOk())
        {
            System.out.println("Choix : " + dialog.getNom() + "-" + dialog.getMoteur() + "-" + dialog.getPuissance() + "-" + dialog.getPrixDeBase() + "-" + dialog.getImage());
        }
        dialog.dispose();
    }

    public String getNom() {
        return nom;
    }

    public int getPuissance() {
        return puissance;
    }

    public String getMoteur() {
        return moteur;
    }

    public float getPrixDeBase() {
        return prixDeBase;
    }
    public String getImage(){return image;}

    public boolean isOk() {
        return ok;
    }
}
