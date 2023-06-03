package GUI;

import Garage.*;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.ButtonGroup;
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
    private JTextField textFieldNomProjet;
    private JTextField textFieldModele;
    private JTextField textFieldPuissance;
    private JTextField textFieldPrixDeBase;
    private JRadioButton radioButtonEssence;
    private JRadioButton radioButtonDiesel;
    private JRadioButton radioButtonElectrique;
    private JRadioButton radioButtonHybride;
    private JButton buttonSupprimerOption;
    private JButton buttonAccorderReduction;
    private JTextField textFieldPrixAvecOptions;
    private JButton buttonNouveauProjet;
    private JButton buttonOuvrirProjet;
    private JButton buttonEnregistrerProjet;

    private JTable tableEmployes;

    private JTable tableClients;

    public JFrameGarage()
    {
        setTitle("Application Garage JAVA");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonDiesel);
        buttonGroup.add(radioButtonElectrique);
        buttonGroup.add(radioButtonEssence);
        buttonGroup.add(radioButtonHybride);
        radioButtonEssence.setSelected(true);

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

        JMenu menuEmployes = new JMenu("Employés");
        menuBar.add(menuEmployes);
        JMenuItem menuItemAjouteEmploye = new JMenuItem("Ajouter un employé");
        menuEmployes.add(menuItemAjouteEmploye);
        menuEmployes.addSeparator();
        JMenuItem menuItemSupprimeEmployeID = new JMenuItem("Supprimer un employé par id");
        menuEmployes.add(menuItemSupprimeEmployeID);
        JMenuItem menuItemSupprimeEmployeSelec = new JMenuItem("Supprimer un employé par sélection");
        menuEmployes.add(menuItemSupprimeEmployeSelec);

        JMenu menuClients = new JMenu("Clients");
        menuBar.add(menuClients);
        JMenuItem menuItemAjouteClient = new JMenuItem("Ajouter un client");
        menuClients.add(menuItemAjouteClient);
        menuClients.addSeparator();
        JMenuItem menuItemSupprimeClientID = new JMenuItem("Supprimer un client par id");
        menuClients.add(menuItemSupprimeClientID);
        JMenuItem menuItemSupprimeClientSelec = new JMenuItem("Supprimer un client par sélection");
        menuClients.add(menuItemSupprimeClientSelec);

        JMenu menuVoiture = new JMenu("Voiture");
        menuBar.add(menuVoiture);
        JMenuItem menuItemNouveauModele = new JMenuItem("Nouveau modèle");
        menuVoiture.add(menuItemNouveauModele);
        JMenuItem menuItemNouvelleOption = new JMenuItem("Nouvelle Option");
        menuVoiture.add(menuItemNouvelleOption);

        menuItemNouveauModele.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JDialogNouveauModele dialog = new JDialogNouveauModele();
                        dialog.pack();
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                        if (dialog.isOk())
                        {
                            System.out.println("Choix : " + dialog.getNom() + "-" + dialog.getMoteur() + "-" + dialog.getPuissance() + "-" + dialog.getPrixDeBase());
                            Modele modele = new Modele(dialog.getNom(),dialog.getPuissance(),dialog.getMoteur(),dialog.getPrixDeBase());
                            comboBoxModelesDisponibles.addItem(modele);
                            Garage.getInstance().ajouteModele(modele);
                        }
                        dialog.dispose();
                    }
                }
        );

        menuItemNouvelleOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogNouvelleOption dialog = new JDialogNouvelleOption();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                if (dialog.isOk())
                {
                    System.out.println("Code = " + dialog.getCode());
                    System.out.println("Intitule = " + dialog.getIntitule());
                    System.out.println("Prix = " + dialog.getPrix());
                    Option option = new Option(dialog.getCode(),dialog.getIntitule(),dialog.getPrix());
                    comboBoxOptionsDisponibles.addItem(option);
                    Garage.getInstance().ajouteOption(option);
                }
                dialog.dispose();
            }
        });

        menuItemAjouteClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogNouveauClient dialog = new JDialogNouveauClient();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                if(dialog.isOk())
                {
                    Client client = new Client(dialog.getNom(), dialog.getPrenom(), dialog.getGsm());
                    DefaultTableModel tableModel = (DefaultTableModel) tableClients.getModel();
                    Vector ligne = new Vector();
                    ligne.add(Integer.valueOf(client.getNumero()));
                    ligne.add(dialog.getNom());
                    ligne.add(dialog.getPrenom());
                    ligne.add(dialog.getGsm());
                    tableModel.addRow(ligne);
                    Garage.getInstance().ajouteClient(client);
                }
            }
        });

        // Table des employes
        Object[][] data = new Object[][]{
                {Integer.valueOf(1), "Wagner", "Jean-Marc", "Vendeur"},
                {Integer.valueOf(2), "Charlet", "Christophe", "Administratif"}
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
        ligne.add(Integer.valueOf(1));
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

        // Table des options
        tableModel = (DefaultTableModel) tableOptionsChoisies.getModel();
        String[] nomsColonnes4 = { "Code", "Intitule", "Prix"};
        tableModel.setColumnIdentifiers(nomsColonnes4);

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
        buttonChoisirModele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Modele modele = (Modele) comboBoxModelesDisponibles.getSelectedItem();
                if (modele == null) return;
                textFieldModele.setText(modele.getNom());
                textFieldPuissance.setText(String.valueOf(modele.getPuissance()));
                textFieldPrixDeBase.setText(String.valueOf(modele.getPrixDeBase()));
                if (modele.getMoteur().equals("Essence")) radioButtonEssence.setSelected(true);
                if (modele.getMoteur().equals("Diesel")) radioButtonDiesel.setSelected(true);
                if (modele.getMoteur().equals("Electrique")) radioButtonElectrique.setSelected(true);
                if (modele.getMoteur().equals("Hybride")) radioButtonHybride.setSelected(true);
                DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
                model.setRowCount(0);
                Voiture v = new Voiture("nom", modele);
                Garage.setProjetEnCours(v);
                updatePrixAvecOptions();
            }
        });
        buttonChoisirOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Option option = (Option) comboBoxOptionsDisponibles.getSelectedItem();
                Option o = new Option(option.getCode(), option.getIntitule(), option.getPrix());
                if (option == null || Garage.getProjetEnCours().getModele() == null) return;
                DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
                if(Garage.getProjetEnCours().ajouteOption(o))
                {
                    Vector ligne = new Vector();
                    ligne.add(o.getCode());
                    ligne.add(o.getIntitule());
                    ligne.add(o.getPrix());
                    model.addRow(ligne);
                    updatePrixAvecOptions();
                }

            }
        });
        buttonSupprimerOption.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int index =  tableOptionsChoisies.getSelectedRow();
                if(index == -1)
                    return;
                Option option = Garage.getInstance().getOptions().get(index);
                DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
                Garage.getProjetEnCours().retireOption(index);
                System.out.println("index : " + index);
                Garage.getProjetEnCours().afficheOptions();
                model.removeRow(index);
                updatePrixAvecOptions();
            }
        });

        buttonAccorderReduction.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int index = tableOptionsChoisies.getSelectedRow();
                if(index == -1)
                    return;
                Option o = Garage.getProjetEnCours().getOption(index);
                if(o != null)
                {
                    o.accorderReduction();
                    DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
                    model.getDataVector().get(index).remove(2);
                    model.getDataVector().get(index).add(o.getPrix());
                    updatePrixAvecOptions();
                    tableOptionsChoisies.requestFocus();
                }
            }
        });

        buttonNouveauProjet.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Garage.setProjetEnCours(new Voiture("nom", null));
                DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
                model.setRowCount(0);
                textFieldModele.setText("");
                textFieldNomProjet.setText(Garage.getProjetEnCours().getNom());
                textFieldPrixDeBase.setText("");
                radioButtonEssence.setSelected(true);
            }
        });

        buttonEnregistrerProjet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Garage.getProjetEnCours().setNom(textFieldNomProjet.getText());
                    Garage.SaveProjetEnCours();
                }
                catch(Exception exc)
                {
                    System.out.println(exc.getMessage());
                }
            }
        });

        buttonOuvrirProjet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Garage.getProjetEnCours().setNom(textFieldNomProjet.getText());
                    Garage.LoadProjetEnCours();
                    Voiture v = Garage.getProjetEnCours();
                    textFieldModele.setText(v.getModele().getNom());
                    textFieldPrixDeBase.setText(String.valueOf(v.getModele().getPrixDeBase()));
                    textFieldPuissance.setText(String.valueOf(v.getModele().getPuissance()));
                    if (v.getModele().getMoteur().equals("Essence")) radioButtonEssence.setSelected(true);
                    if (v.getModele().getMoteur().equals("Diesel")) radioButtonDiesel.setSelected(true);
                    if (v.getModele().getMoteur().equals("Electrique")) radioButtonElectrique.setSelected(true);
                    if (v.getModele().getMoteur().equals("Hybride")) radioButtonHybride.setSelected(true);
                    DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
                    model.setRowCount(0);
                    for(int i = 0; i < 5; i++)
                    {
                        Option o = v.getOption(i);
                        if(o != null) {
                            Vector ligne = new Vector();
                            ligne.add(o.getCode());
                            ligne.add(o.getIntitule());
                            ligne.add(o.getPrix());
                            model.addRow(ligne);
                            updatePrixAvecOptions();
                        }
                    }
                    updatePrixAvecOptions();
                }
                catch(Exception exc)
                {
                    System.out.println(exc.getMessage());
                }
            }
        });

        nouveauContratButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableContratsModel = (DefaultTableModel) tableContrats.getModel();
                int indexClient = tableClients.getSelectedRow();
                int indexEmploye = tableEmployes.getSelectedRow();
                if(indexClient != -1 && indexEmploye != -1)
                {
                    Garage.getProjetEnCours().setNom(textFieldNomProjet.getText());
                    Contrat c = new Contrat(Garage.getInstance().getClients().get(indexClient), Garage.getInstance().getEmployes().get(indexEmploye), Garage.getProjetEnCours().getNom());
                    Garage.getInstance().ajouteContrat(c);
                    Vector ligne = new Vector();
                    ligne.add(c.getNumero());
                    ligne.add(c.getClientRef());
                    ligne.add(c.getEmployeRef());
                    ligne.add(c.getNom());
                    tableContratsModel.addRow(ligne);
                }
            }
        });

        supprimerContratButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tableEmployes.getModel();
                Vector ligne = new Vector();
                Employe emp = new Employe("Evrard", "Nathan", "wesh", "zebi", "cool");
                ligne.add(emp.getNumero());
                ligne.add(emp.getNom());
                ligne.add(emp.getPrenom());
                ligne.add(emp.getFonction());
                model.addRow(ligne);
            }
        });

        Garage.getInstance().ajouteOption(new Option("ABBA", "Vitres teintées", 250));
        comboBoxOptionsDisponibles.addItem(new Option("ABBA", "Vitres teintées", 250));
        Garage.getInstance().ajouteModele(new Modele("Andrew", 100, "Diesel", 3600));
        comboBoxModelesDisponibles.addItem(new Modele("Andrew", 100, "Diesel", 3600));
    }

    private void updatePrixAvecOptions()
    {
        textFieldPrixAvecOptions.setText(String.valueOf(Garage.getProjetEnCours().getPrix()));
    }
    public static void main(String[] args) {
        //FlatLightLaf.setup();
        FlatDarculaLaf.setup();
        JFrameGarage frame = new JFrameGarage();
        frame.setVisible(true);
    }
}
