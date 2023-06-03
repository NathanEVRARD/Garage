package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JDialogNouvelleOption extends JDialog
{
    private JTextField textFieldIntitule;
    private JTextField textFieldCode;
    private JTextField textFieldPrix;
    private JButton buttonCreerOption;
    private JButton buttonAnnuler;
    private JPanel mainPanel;

    private String code;
    private String intitule;
    private float prix;
    private boolean ok;

    public JDialogNouvelleOption()
    {
        super();
        setContentPane(mainPanel);
        setTitle("Nouvelle Option...");
        pack();
        setModal(true);
        ok = false;
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        buttonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        buttonCreerOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                code = textFieldCode.getText();
                intitule = textFieldIntitule.getText();
                prix = Float.parseFloat(textFieldPrix.getText());
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

    public String getCode() {
        return code;
    }

    public String getIntitule() {
        return intitule;
    }

    public float getPrix() {
        return prix;
    }

    public boolean isOk() {
        return ok;
    }

    public static void main(String[] args) {
        JDialogNouvelleOption dialog = new JDialogNouvelleOption();
        dialog.setVisible(true);
        if (dialog.isOk())
        {
            System.out.println("Code = " + dialog.getCode());
            System.out.println("Intitule = " + dialog.getIntitule());
            System.out.println("Prix = " + dialog.getPrix());
        }
        dialog.dispose();
    }
}
