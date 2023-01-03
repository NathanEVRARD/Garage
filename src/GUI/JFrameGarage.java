package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class JFrameGarage extends JFrame
{
    private JPanel mainPanel;
    private JButton nouveauContratButton;
    private JButton supprimerContratButton;
    private JButton visualiserVoitureButton;
    private JTable tableContrats;
    private JScrollPane jScrollPaneEmployes;
    private JScrollPane jScrollPaneClients;
    private JLabel labelImage;
    private JComboBox comboBoxModelesDisponibles;
    private JButton buttonChoisirModele;
    private JComboBox comboBoxOptionsDisponibles;
    private JButton buttonChoisirOption;
    private JScrollPane jScrollPaneOptionsChoisies;
    private JTable tableOptionsChoisies;

    private JTable tableEmployes;

    private JTable tableClients;

    public JFrameGarage()
    {
        //setSize(800,600);
        setTitle("Application Garage JAVA");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuConnexion = new JMenu("Connexion");
        menuBar.add(menuConnexion);
        JMenuItem menuItemLogin = new JMenuItem("Login");
        menuConnexion.add(menuItemLogin);
        JMenuItem menuItemLogout = new JMenuItem("Logout");
        menuConnexion.add(menuItemLogout);
        menuConnexion.addSeparator();
        JMenuItem menuItemResetMotDePasse = new JMenuItem("Reset mot de passe");
        menuConnexion.add(menuItemResetMotDePasse);
        menuConnexion.addSeparator();
        JMenuItem menuItemQuitter = new JMenuItem("Quitter");
        menuConnexion.add(menuItemQuitter);

        // Table des employes
        Object[][] data = {
                {new Integer(1), "Wagner","Jean-Marc","Vendeur"},
                {new Integer(2), "Charlet","Christophe","Administratif"}
        };
        String[] nomsColonnes = { "Num", "Nom", "Prénom", "Fonction"};
        tableEmployes = new JTable(data, nomsColonnes);
        int[] taillesColonnes = {30,60,60,60};
        TableColumn col = null;
        for (int i=0; i<taillesColonnes.length; i++)
        {
            col = tableEmployes.getColumnModel().getColumn(i);
            col.setPreferredWidth(taillesColonnes[i]);
        }
        jScrollPaneEmployes.setViewportView(tableEmployes);

        // Table des clients
        tableClients = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) tableClients.getModel();
        String[] nomsColonnes2 = { "Num", "Nom", "Prénom", "GSM"};
        tableModel.setColumnIdentifiers(nomsColonnes2);
        Vector ligne = new Vector();
        ligne.add(new Integer(1));
        ligne.add("Wagner");
        ligne.add("Jean-Marc");
        ligne.add("0478/75.53.36");
        tableModel.addRow(ligne);
        //tableClients.setModel(tableModel);
        jScrollPaneClients.setViewportView(tableClients);

        // Table des contrats
        tableModel = (DefaultTableModel) tableContrats.getModel();
        String[] nomsColonnes3 = { "Num", "Vendeur", "Client", "Voiture"};
        tableModel.setColumnIdentifiers(nomsColonnes3);


        menuItemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuItemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogLogin dialog = new JDialogLogin(null,true,"Entrée en session...");
                dialog.setVisible(true);
            }
        });

        pack();
        setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
    }

    public static void main(String[] args) {
        JFrameGarage frame = new JFrameGarage();
        frame.setVisible(true);
    }
}
