package GUI;

import Garage.*;
import com.formdev.flatlaf.FlatDarculaLaf;

/*import java.swing.*;
import java.swing.table.DefaultTableModel;
import java.swing.table.TableColumn;
import java.swing.ButtonGroup;
import java.swing.table.TableModel;*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

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

        // Gestion du login et logout + reset mdp
        // créer le super admin au lancement ?

        if(Garage.getInstance().getIdConnected() == -1)
        {
            menuItemLogout.setEnabled(false); // car personne n'est connecté au lancement de l'appli
            menuClients.setEnabled(false); // car personne n'est connecté au lancement de l'appli
            //menuEmployes.setEnabled(false); // car personne n'est connecté au lancement de l'appli
            menuVoiture.setEnabled(false); // car personne n'est connecté au lancement de l'appli
            menuItemResetMotDePasse.setEnabled(false); // car personne n'est connecté au lancement de l'appli
            buttonAccorderReduction.setEnabled(false);
            buttonChoisirModele.setEnabled(false);
            buttonChoisirOption.setEnabled(false);
            buttonSupprimerOption.setEnabled(false);
            buttonNouveauProjet.setEnabled(false);
            buttonOuvrirProjet.setEnabled(false);
            buttonEnregistrerProjet.setEnabled(false);
            // ajouter les boutons contrats.
            radioButtonDiesel.setEnabled(false);
            radioButtonElectrique.setEnabled(false);
            radioButtonEssence.setEnabled(false);
            radioButtonHybride.setEnabled(false);
        }


        //login
        menuItemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogLogin dialog = new JDialogLogin(null,true,"Entrée en session...");
                dialog.setVisible(true);

                if(dialog.isOk())
                {
                    for(int i = 0;i<Garage.getInstance().getEmployes().size();i++)
                    {
                        if(Garage.getInstance().getEmployes().get(i).getLogin().equals(dialog.getLogin()) &&  Garage.getInstance().getEmployes().get(i).getMdp().equals(dialog.getMotDePasse()))
                        {
                            setTitle("Application Garage JAVA - "+dialog.getLogin());
                            JDialogMessage dialogMessage = new JDialogMessage("Connexion réussie !");
                            dialogMessage.pack();
                            dialogMessage.setLocationRelativeTo(null);
                            dialogMessage.setVisible(true);
                            if(Garage.getInstance().getEmployes().get(i).getFonction().equals("Administratif"))
                            {
                                menuItemLogin.setEnabled(false);
                                menuItemLogout.setEnabled(true);
                                menuClients.setEnabled(false);
                                menuEmployes.setEnabled(true);
                                menuVoiture.setEnabled(false);
                                menuItemResetMotDePasse.setEnabled(true);
                                buttonAccorderReduction.setEnabled(false);
                                buttonChoisirModele.setEnabled(false);
                                buttonChoisirOption.setEnabled(false);
                                buttonSupprimerOption.setEnabled(false);
                                buttonNouveauProjet.setEnabled(false);
                                buttonOuvrirProjet.setEnabled(false);
                                buttonEnregistrerProjet.setEnabled(false);
                                // ajouter les boutons contrats.
                                radioButtonDiesel.setEnabled(false);
                                radioButtonElectrique.setEnabled(false);
                                radioButtonEssence.setEnabled(false);
                                radioButtonHybride.setEnabled(false);

                            }
                            else
                            {
                                if(Garage.getInstance().getEmployes().get(i).getFonction().equals("Vendeur"))
                                {
                                    menuItemLogin.setEnabled(false);
                                    menuItemLogout.setEnabled(true);
                                    menuClients.setEnabled(true);
                                    menuEmployes.setEnabled(false);
                                    menuVoiture.setEnabled(true);
                                    menuItemResetMotDePasse.setEnabled(true);
                                    buttonAccorderReduction.setEnabled(true);
                                    buttonChoisirModele.setEnabled(true);
                                    buttonChoisirOption.setEnabled(true);
                                    buttonSupprimerOption.setEnabled(true);
                                    buttonNouveauProjet.setEnabled(true);
                                    buttonOuvrirProjet.setEnabled(true);
                                    buttonEnregistrerProjet.setEnabled(true);
                                    // ajouter les boutons contrats.
                                    radioButtonDiesel.setEnabled(true);
                                    radioButtonElectrique.setEnabled(true);
                                    radioButtonEssence.setEnabled(true);
                                    radioButtonHybride.setEnabled(true);
                                }
                                else // super admin
                                {
                                    menuItemLogin.setEnabled(false);
                                    menuItemLogout.setEnabled(true);
                                    menuClients.setEnabled(true);
                                    menuEmployes.setEnabled(true);
                                    menuVoiture.setEnabled(true);
                                    menuItemResetMotDePasse.setEnabled(true);
                                    buttonAccorderReduction.setEnabled(true);
                                    buttonChoisirModele.setEnabled(true);
                                    buttonChoisirOption.setEnabled(true);
                                    buttonSupprimerOption.setEnabled(true);
                                    buttonNouveauProjet.setEnabled(true);
                                    buttonOuvrirProjet.setEnabled(true);
                                    buttonEnregistrerProjet.setEnabled(true);
                                    // ajouter les boutons contrats.
                                    radioButtonDiesel.setEnabled(true);
                                    radioButtonElectrique.setEnabled(true);
                                    radioButtonEssence.setEnabled(true);
                                    radioButtonHybride.setEnabled(true);
                                }

                            }

                            Garage.getInstance().setIdConnected(Garage.getInstance().getEmployes().get(i).getNumero());
                            i = Garage.getInstance().getEmployes().size();
                        }
                    }
                    if(Garage.getInstance().getIdConnected() == -1)
                    {
                        JDialogMessage dialogMessage = new JDialogMessage("Connexion échoué !");
                        dialogMessage.pack();
                        dialogMessage.setLocationRelativeTo(null);
                        dialogMessage.setVisible(true);
                    }
                }
                else
                {
                    menuItemLogin.setEnabled(true);
                    menuItemLogout.setEnabled(false);
                    menuClients.setEnabled(false);
                    menuEmployes.setEnabled(false);
                    menuVoiture.setEnabled(false);
                    menuItemResetMotDePasse.setEnabled(false);
                    buttonAccorderReduction.setEnabled(false);
                    buttonChoisirModele.setEnabled(false);
                    buttonChoisirOption.setEnabled(false);
                    buttonSupprimerOption.setEnabled(false);
                    buttonNouveauProjet.setEnabled(false);
                    buttonOuvrirProjet.setEnabled(false);
                    buttonEnregistrerProjet.setEnabled(false);
                    // ajouter les boutons contrats.
                    radioButtonDiesel.setEnabled(false);
                    radioButtonElectrique.setEnabled(false);
                    radioButtonEssence.setEnabled(false);
                    radioButtonHybride.setEnabled(false);

                }
            }
        });
        // logout
        menuItemLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogConfirmation dialog = new JDialogConfirmation("Voulez-vous vraiment vous déconnecter ?");
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

                if(dialog.isOk())
                {
                    setTitle("Application Garage JAVA");
                    Garage.getInstance().setIdConnected(-1);
                    menuItemLogin.setEnabled(true);
                    menuItemLogout.setEnabled(false);
                    menuClients.setEnabled(false);
                    menuEmployes.setEnabled(false);
                    menuVoiture.setEnabled(false);
                    menuItemResetMotDePasse.setEnabled(false);
                    buttonAccorderReduction.setEnabled(false);
                    buttonChoisirModele.setEnabled(false);
                    buttonChoisirOption.setEnabled(false);
                    buttonSupprimerOption.setEnabled(false);
                    buttonNouveauProjet.setEnabled(false);
                    buttonOuvrirProjet.setEnabled(false);
                    buttonEnregistrerProjet.setEnabled(false);
                    // ajouter les boutons contrats.
                    radioButtonDiesel.setEnabled(false);
                    radioButtonElectrique.setEnabled(false);
                    radioButtonEssence.setEnabled(false);
                    radioButtonHybride.setEnabled(false);
                }
            }
        });
        // reset mdp
        menuItemResetMotDePasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogResetMdp dialogResetMdp = new JDialogResetMdp();
                dialogResetMdp.pack();
                dialogResetMdp.setLocationRelativeTo(null);
                dialogResetMdp.setVisible(true);
                if(dialogResetMdp.isOk())
                {
                    if(dialogResetMdp.getMdp().equals(dialogResetMdp.getMdpConfirmed()))
                    {
                        JDialogConfirmation dialogConfirmation = new JDialogConfirmation("Êtes-vous sûr de vouloir changer votre mot de passe ?");
                        dialogConfirmation.pack();
                        dialogConfirmation.setLocationRelativeTo(null);
                        dialogConfirmation.setVisible(true);
                        if(dialogConfirmation.isOk())
                        {
                            for (int i = 0; i < Garage.getInstance().getEmployes().size(); i++)
                            {
                                if (Garage.getInstance().getEmployes().get(i).getNumero() == Garage.getInstance().getIdConnected())
                                {
                                    Garage.getInstance().getEmployes().get(i).setMdp(dialogResetMdp.getMdp());
                                }
                            }
                        }
                    }
                    else
                    {
                        JDialogMessage dialogMessage = new JDialogMessage("Les mots de passe ne correspondent pas !");
                        dialogMessage.pack();
                        dialogMessage.setLocationRelativeTo(null);
                        dialogMessage.setVisible(true);
                    }
                }
            }
        });


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
        // Permet d'ajouter un client
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
                    Garage.getInstance().ajouteClient(client);
                    afficheClients();
                }
            }
        });

        // Permet de supprimer un client par id
        menuItemSupprimeClientID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogSuppressionById dialog = new JDialogSuppressionById();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                boolean ok = false;
                if(dialog.isOk())
                {
                    int id = dialog.getId();

                    DefaultTableModel tableModel = (DefaultTableModel) tableClients.getModel();
                    // parcour la Jtable
                    for (int i = 0;i<tableModel.getRowCount();i++)
                    {
                        if(tableModel.getValueAt(i,0).equals(id))
                        {
                            ok = true;
                            JDialogConfirmation dialogConfirmation = new JDialogConfirmation("Voulez-vous vraiment supprimer ce client ?");
                            dialogConfirmation.pack();
                            dialogConfirmation.setLocationRelativeTo(null);
                            dialogConfirmation.setVisible(true);
                            if(dialogConfirmation.isOk())
                            {
                                tableModel.removeRow(i);
                                i = tableModel.getRowCount()+1; // met fin à la boucle
                                dialogConfirmation.setVisible(false);
                            }
                            else
                            {
                                dialogConfirmation.setVisible(false);
                            }

                        }

                    }
                    if(!ok)
                    {
                        JDialogMessage dialogMessage = new JDialogMessage("L'id encodé n'existe pas !");
                        dialogMessage.pack();
                        dialogMessage.setLocationRelativeTo(null);
                        dialogMessage.setVisible(true);
                    }

                }
            }
        });

        // Permet de supprimer un client par selection
        menuItemSupprimeClientSelec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableClients.isRowSelected(tableClients.getSelectedRow()))
                {
                    JDialogConfirmation dialogConfirmation = new JDialogConfirmation("Êtes-vous sur de vouloir supprimer ce client ?");
                    dialogConfirmation.pack();
                    dialogConfirmation.setLocationRelativeTo(null);
                    dialogConfirmation.setVisible(true);
                    if(dialogConfirmation.isOk())
                    {
                        DefaultTableModel tableModel = (DefaultTableModel) tableClients.getModel();
                        tableModel.removeRow(tableClients.getSelectedRow());
                        dialogConfirmation.setVisible(false);
                    }
                    else
                    {
                        dialogConfirmation.setVisible(false);
                    }
                }
                else
                {
                    JDialogMessage dialogMessage = new JDialogMessage("Veuillez selectionner une ligne dans la table clients !");
                    dialogMessage.pack();
                    dialogMessage.setLocationRelativeTo(null);
                    dialogMessage.setVisible(true);
                }
            }
        });

        // Permet d'ajouter un employe
        menuItemAjouteEmploye.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogNouvelEmploye dialog = new JDialogNouvelEmploye();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                if(dialog.isOk())
                {
                    Employe employe = new Employe(dialog.getNom(), dialog.getPrenom(), dialog.getLogin(), dialog.getMdp(), dialog.getFonction());
                    Garage.getInstance().ajouteEmploye(employe);
                    afficheEmployes();
                }
            }
        });

        // Permet de supprimer un employee par id
        menuItemSupprimeEmployeID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogSuppressionById dialog = new JDialogSuppressionById();
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                boolean ok = false;
                if(dialog.isOk())
                {
                    int id = dialog.getId();
                    DefaultTableModel tableModel = (DefaultTableModel) tableEmployes.getModel();
                    AtomicBoolean aUnContrat = new AtomicBoolean(false);
                    try
                    {
                        Garage.getInstance().getContrats().forEach(c ->{
                            if(c.getEmployeRef() == id)
                            {
                                aUnContrat.set(true);
                                throw new RuntimeException();
                            }
                        });
                    }
                    catch(Exception exc)
                    {

                    }
                    if(!aUnContrat.get())
                    {
                        // parcour la Jtable
                        for (int i = 0;i<tableModel.getRowCount();i++)
                        {
                            if(tableModel.getValueAt(i,0).equals(id))
                            {
                                ok = true;
                                JDialogConfirmation dialogConfirmation = new JDialogConfirmation("Voulez-vous vraiment supprimer cet employé ?");
                                dialogConfirmation.pack();
                                dialogConfirmation.setLocationRelativeTo(null);
                                dialogConfirmation.setVisible(true);
                                if(dialogConfirmation.isOk())
                                {
                                    Garage.getInstance().getEmployes().remove(tableModel.getValueAt(i, 0));
                                    tableModel.removeRow(i);
                                    i = tableModel.getRowCount()+1; // met fin à la boucle
                                    dialogConfirmation.setVisible(false);
                                }
                                else
                                {
                                    dialogConfirmation.setVisible(false);
                                }

                            }

                        }
                        if(!ok)
                        {
                            JDialogMessage dialogMessage = new JDialogMessage("L'id encodé n'existe pas !");
                            dialogMessage.pack();
                            dialogMessage.setLocationRelativeTo(null);
                            dialogMessage.setVisible(true);
                        }
                    }
                }
            }
        });
        // Permet de supprimer un employé par selection
        menuItemSupprimeEmployeSelec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tableEmployes.getSelectedRow();
                AtomicBoolean aUnContrat = new AtomicBoolean(false);
                if(tableEmployes.isRowSelected(index))
                {
                    try
                    {
                        Garage.getInstance().getContrats().forEach(c -> {
                            if(c.getEmployeRef() == Garage.getInstance().getEmployes().get(index).getNumero())
                            {
                                aUnContrat.set(true);
                                throw new RuntimeException(); // Remplace le break pour sortir de la boucle
                            }
                        });
                    }
                    catch(Exception exc)
                    {

                    }
                    if(!aUnContrat.get())
                    {
                        JDialogConfirmation dialogConfirmation = new JDialogConfirmation("Êtes-vous sur de vouloir supprimer cet employé ?");
                        dialogConfirmation.pack();
                        dialogConfirmation.setLocationRelativeTo(null);
                        dialogConfirmation.setVisible(true);
                        if(dialogConfirmation.isOk())
                        {
                            DefaultTableModel tableModel = (DefaultTableModel) tableEmployes.getModel();
                            Garage.getInstance().getEmployes().remove(index);
                            tableModel.removeRow(tableEmployes.getSelectedRow());
                            dialogConfirmation.setVisible(false);
                        }
                        else
                        {
                            dialogConfirmation.setVisible(false);
                        }
                    }
                    else
                    {
                        JDialogMessage dialogMessage = new JDialogMessage("Vous ne pouvez pas supprimer un employé avec un contrat actif");
                        dialogMessage.pack();
                        dialogMessage.setLocationRelativeTo(null);
                        dialogMessage.setVisible(true);
                    }
                }
                else
                {
                    JDialogMessage dialogMessage = new JDialogMessage("Veuillez selectionner une ligne dans la table employés !");
                    dialogMessage.pack();
                    dialogMessage.setLocationRelativeTo(null);
                    dialogMessage.setVisible(true);
                }
            }
        });
        /*Object[][] data = new Object[][]{
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
        jScrollPaneEmployes.setViewportView(tableEmployes);*/

        // Table des employes
        tableEmployes = new JTable();
        DefaultTableModel tableModel = (DefaultTableModel) tableEmployes.getModel();
        String[] nomsColonnes = { "Num", "Nom", "Prénom", "Fonction"};
        tableModel.setColumnIdentifiers(nomsColonnes);
        /*Vector ligne = new Vector();
        ligne.add(Integer.valueOf(1));
        ligne.add("Wagner");
        ligne.add("Jean-Marc");
        ligne.add("Vendeur");
        tableModel.addRow(ligne);
        ligne = new Vector<>();
        ligne.add(Integer.valueOf(2));
        ligne.add("Charlet");
        ligne.add("Christophe");
        ligne.add("Administratif");
        tableModel.addRow(ligne);*/
        //tableEmployes.setModel(tableModel);
        jScrollPaneEmployes.setViewportView(tableEmployes); // permet de voir le tableauu dans l'interfae

        // Table des clients
        tableClients = new JTable();
        tableModel = (DefaultTableModel) tableClients.getModel();
        String[] nomsColonnes2 = { "Num", "Nom", "Prénom", "GSM"};
        tableModel.setColumnIdentifiers(nomsColonnes2);
        /*Vector ligne = new Vector();
        ligne.add(Integer.valueOf(1));
        ligne.add("Wagner");
        ligne.add("Jean-Marc");
        ligne.add("0478/75.53.36");
        tableModel.addRow(ligne);*/
        //tableClients.setModel(tableModel);
        jScrollPaneClients.setViewportView(tableClients); // permet de voir le tableauu dans l'interfae

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
                if(index != -1)
                {
                    Garage.getProjetEnCours().retireOption(index);
                    afficheOptions();
                    System.out.println(index);
                }
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
//                DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
//                model.setRowCount(0);
//                textFieldModele.setText("");
//                textFieldNomProjet.setText(Garage.getProjetEnCours().getNom());
//                textFieldPrixDeBase.setText("");
//                radioButtonEssence.setSelected(true);
                afficheProjetEnCours();
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
                    afficheProjetEnCours();
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
                String nom = textFieldNomProjet.getText();
                File check = new File("Data/Projets/" + nom + ".car");
                if(indexClient != -1 && indexEmploye != -1 && check.exists()) // Vérifie si on a bien sélectionné et si le projet du contrat existe
                {
                    AtomicBoolean memeContrat = new AtomicBoolean(false);
                    Garage.getProjetEnCours().setNom(nom);
                    Contrat c = new Contrat(Garage.getInstance().getClients().get(indexClient), Garage.getInstance().getEmployes().get(indexEmploye), Garage.getProjetEnCours().getNom());
                    Garage.getInstance().getContrats().forEach(contrat -> {
                        if(contrat.equalsButNum(c))
                        {
                            memeContrat.set(true);
                            System.out.println("Meme contrat !");
                        }
                        else
                        {
                            System.out.println(contrat.toString() + "!=" + c.toString());
                        }
                    });
                    if(!memeContrat.get())
                    {
                        Garage.getInstance().ajouteContrat(c);
                        Vector ligne = new Vector();
                        ligne.add(c.getNumero());
                        ligne.add(c.getClientRef());
                        ligne.add(c.getEmployeRef());
                        ligne.add(c.getNom());
                        tableContratsModel.addRow(ligne);
                    }
                }
                else if(indexClient == -1 && indexEmploye == -1)
                {
                    JDialogMessage dialogMessage = new JDialogMessage("Veuillez choisir un employé et un client");
                    dialogMessage.pack();
                    dialogMessage.setLocationRelativeTo(null);
                    dialogMessage.setVisible(true);
                }
                else
                {
                    JDialogMessage dialogMessage = new JDialogMessage("Veuillez enregistrer un projet pour créer un contrat");
                    dialogMessage.pack();
                    dialogMessage.setLocationRelativeTo(null);
                    dialogMessage.setVisible(true);
                }
            }
        });

        supprimerContratButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) tableContrats.getModel();
                int index = tableContrats.getSelectedRow();
                if(index != -1)
                {
                    model.removeRow(index);
                    Garage.getInstance().getContrats().remove(index);
                    Garage.getInstance().getContrats().forEach(x -> System.out.println(x.toString()));
                }
            }
        });

        visualiserVoitureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = tableContrats.getSelectedRow();
                if(index != -1)
                {
                    Contrat c = Garage.getInstance().getContrats().get(index);
                    Garage.setProjetEnCours(new Voiture(c.getNom(), null));
                    try
                    {
                        Garage.LoadProjetEnCours();
                        afficheProjetEnCours();
                    }
                    catch(Exception exc)
                    {
                        System.out.println(exc.getMessage());
                    }

                }
            }
        });

        Garage.getInstance().ajouteOption(new Option("ABBA", "Vitres teintées", 250));
        comboBoxOptionsDisponibles.addItem(new Option("ABBA", "Vitres teintées", 250));
        Garage.getInstance().ajouteModele(new Modele("Andrew", 100, "Diesel", 3600));
        comboBoxModelesDisponibles.addItem(new Modele("Andrew", 100, "Diesel", 3600));
    }

    private void afficheProjetEnCours()
    {
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
            }
        }
        updatePrixAvecOptions();
    }

    private void afficheOptions()
    {
        DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
        model.setRowCount(0);
        for(int i = 0; i < 5; i++)
        {
            Option o = Garage.getProjetEnCours().getOption(i);
            if(o != null)
            {
                Vector ligne = new Vector();
                ligne.add(o.getCode());
                ligne.add(o.getIntitule());
                ligne.add(o.getPrix());
                model.addRow(ligne);
            }
        }
        updatePrixAvecOptions();
    }

    private void afficheClients()
    {
        DefaultTableModel model = (DefaultTableModel) tableClients.getModel();
        model.setRowCount(0);
        Garage.getInstance().getClients().forEach(e -> {
            Vector ligne = new Vector();
            ligne.add(Integer.valueOf(e.getNumero()));
            ligne.add(e.getNom());
            ligne.add(e.getPrenom());
            ligne.add(e.getGsm());
            model.addRow(ligne);
        });
    }

    private void afficheEmployes()
    {
        DefaultTableModel model = (DefaultTableModel) tableEmployes.getModel();
        model.setRowCount(0);
        Garage.getInstance().getEmployes().forEach(e -> {
            Vector ligne = new Vector();
            ligne.add(Integer.valueOf(e.getNumero()));
            ligne.add(e.getNom());
            ligne.add(e.getPrenom());
            ligne.add(e.getFonction());
            model.addRow(ligne);
        });
    }

    private void updatePrixAvecOptions()
    {
        textFieldPrixAvecOptions.setText(String.valueOf(Garage.getProjetEnCours().getPrix()));
    }
    public static String capitalize(String str)
    {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    public static void main(String[] args) {
        //FlatLightLaf.setup();
        FlatDarculaLaf.setup();
        JFrameGarage frame = new JFrameGarage();
        frame.setVisible(true);
    }
}
