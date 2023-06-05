package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class JDialogParametres extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldLangue;
    private JTextField textFieldPays;
    private JLabel labelLangue;
    private JLabel labelPays;
    private String langue;
    private String pays;

    public JDialogParametres() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // récupère les données dans Admin.properties
        FileReader reader= null;
        try {
            reader = new FileReader("Date.properties");
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        Properties p=new Properties();
        try {
            p.load(reader);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        langue = p.getProperty("Langue");
        pays = p.getProperty("Pays");
        textFieldLangue.setText(langue);
        textFieldPays.setText(pays);
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
        langue = textFieldLangue.getText();
        pays = textFieldPays.getText();

        Properties p=new Properties();
        p.setProperty("Langue", langue);
        p.setProperty("Pays", pays);
        try {
            p.store(new FileWriter("Date.properties"),"Mise à jour du format de date");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
