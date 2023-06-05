package GUI;

import Garage.*;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public class JFrameGarage extends JFrame implements PropertyChangeListener
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

    private JFrameGarage frameGarage = this;

    public JFrameGarage()
    {
        setTitle("Application Garage JAVA" + " - " + getDate());
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        //LECTURE CSV


        LoadModelesCSV();
        LoadOptionsCSV();
        Garage.getInstance().LoadGarage();
        Garage.getInstance().LoadInumeros();

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

        JMenu menuParametres = new JMenu("Paramètres");
        menuBar.add(menuParametres);
        JMenuItem menuItemParametre = new JMenuItem("Paramètres de date");
        menuParametres.add(menuItemParametre);

        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try
                {
                    Garage.getInstance().SaveModeles();
                    Garage.getInstance().SaveOptions();
                    Garage.getInstance().SaveGarage();
                    Garage.getInstance().SaveInumeros();
                    if(Garage.getProjetEnCours().getModele() != null)
                    {
                        Garage.getProjetEnCours().setNom(textFieldNomProjet.getText());
                        Garage.getInstance().SaveProjetEnCoursSer();
                    }
                }
                catch(Exception exc)
                {
                    System.out.println(exc.getMessage());
                }
                System.out.println("Sauvegarde des modèles");
                System.out.println("Sauvegarde des options");
                System.out.println("Sérialisation du garage");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });


        // Gestion du login et logout + reset mdp
        File f = new File("Admin.properties");
        if(f.isFile())
        {
            // besoin de rien faire on vérifie juste si le fichier admin.properties existe
        }
        else
        {
            Properties p=new Properties();

            p.setProperty("Id","0");
            p.setProperty("Login","SUPERADMIN");
            p.setProperty("Mdp","ADMIN1");

            try {
                p.store(new FileWriter("Admin.properties"),"Enregistrement du super administrateur");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

        if(Garage.getInstance().getIdConnected() == -1)
        {
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
            nouveauContratButton.setEnabled(false);
            supprimerContratButton.setEnabled(false);
            visualiserVoitureButton.setEnabled(false);
            radioButtonDiesel.setEnabled(false);
            radioButtonElectrique.setEnabled(false);
            radioButtonEssence.setEnabled(false);
            radioButtonHybride.setEnabled(false);
            comboBoxOptionsDisponibles.setEnabled(false);
            comboBoxModelesDisponibles.setEnabled(false);
        }
        //login
        menuItemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogLogin dialog = new JDialogLogin(null,true,"Entrée en session...", frameGarage);
                dialog.setVisible(true);

                if(dialog.isOk())
                {
                    if(dialog.getLogin().equals("SUPERADMIN"))
                    {
                        // récupère les données dans Admin.properties
                        FileReader reader= null;
                        try {
                            reader = new FileReader("Admin.properties");
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        Properties p=new Properties();
                        try {
                            p.load(reader);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                        if(p.getProperty("Mdp").equals(dialog.getMotDePasse()))
                        {
                            afficheEmployes();
                            afficheClients();
                            afficheContrats();
                            Garage.getInstance().LoadProjetEnCoursSer();
                            afficheProjetEnCours();
                            comboBoxOptionsDisponibles.setEnabled(true);
                            comboBoxModelesDisponibles.setEnabled(true);
                            setTitle("Application Garage JAVA - "+dialog.getLogin() + " - " + getDate());
                            JDialogMessage dialogMessage = new JDialogMessage("Connexion réussie !");
                            dialogMessage.pack();
                            dialogMessage.setLocationRelativeTo(null);
                            dialogMessage.setVisible(true);
                            Garage.getInstance().setIdConnected(0);
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
                            nouveauContratButton.setEnabled(true);
                            supprimerContratButton.setEnabled(true);
                            visualiserVoitureButton.setEnabled(true);
                            radioButtonDiesel.setEnabled(true);
                            radioButtonElectrique.setEnabled(true);
                            radioButtonEssence.setEnabled(true);
                            radioButtonHybride.setEnabled(true);
                        }
                        else
                        {
                            JDialogMessage dialogMessage = new JDialogMessage("Connexion échoué !");
                            dialogMessage.pack();
                            dialogMessage.setLocationRelativeTo(null);
                            dialogMessage.setVisible(true);
                        }
                    }
                    else
                    {
                        for(int i = 0;i<Garage.getInstance().getEmployes().size();i++)
                        {
                            if(Garage.getInstance().getEmployes().get(i).getLogin().equals(dialog.getLogin()) &&  Garage.getInstance().getEmployes().get(i).getMdp().equals(dialog.getMotDePasse()))
                            {
                                setTitle("Application Garage JAVA - "+dialog.getLogin() + " - " + getDate());
                                JDialogMessage dialogMessage = new JDialogMessage("Connexion réussie !");
                                dialogMessage.pack();
                                dialogMessage.setLocationRelativeTo(null);
                                dialogMessage.setVisible(true);
                                afficheContrats();
                                afficheClients();
                                afficheEmployes();
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
                                    nouveauContratButton.setEnabled(false);
                                    supprimerContratButton.setEnabled(false);
                                    visualiserVoitureButton.setEnabled(false);
                                    radioButtonDiesel.setEnabled(false);
                                    radioButtonElectrique.setEnabled(false);
                                    radioButtonEssence.setEnabled(false);
                                    radioButtonHybride.setEnabled(false);
                                }
                                else
                                {
                                    if(Garage.getInstance().getEmployes().get(i).getFonction().equals("Vendeur"))
                                    {
                                        comboBoxOptionsDisponibles.setEnabled(true);
                                        comboBoxModelesDisponibles.setEnabled(true);
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
                                        nouveauContratButton.setEnabled(true);
                                        supprimerContratButton.setEnabled(true);
                                        visualiserVoitureButton.setEnabled(true);
                                        radioButtonDiesel.setEnabled(true);
                                        radioButtonElectrique.setEnabled(true);
                                        radioButtonEssence.setEnabled(true);
                                        radioButtonHybride.setEnabled(true);
                                        Garage.getInstance().LoadProjetEnCoursSer();
                                        afficheProjetEnCours();
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
                    setTitle("Application Garage JAVA" + " - " + getDate());
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
                    buttonEnregistrerProjet.setEnabled(false);
                    nouveauContratButton.setEnabled(false);
                    supprimerContratButton.setEnabled(false);
                    visualiserVoitureButton.setEnabled(false);
                    radioButtonDiesel.setEnabled(false);
                    radioButtonElectrique.setEnabled(false);
                    radioButtonEssence.setEnabled(false);
                    radioButtonHybride.setEnabled(false);
                    comboBoxOptionsDisponibles.setEnabled(false);
                    comboBoxModelesDisponibles.setEnabled(false);
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
                            if(Garage.getInstance().getIdConnected() == 0)
                            {
                                // récupère les données dans Admin.properties
                                FileReader reader= null;
                                try {
                                    reader = new FileReader("Admin.properties");
                                } catch (FileNotFoundException ex) {
                                    throw new RuntimeException(ex);
                                }
                                Properties p=new Properties();
                                try {
                                    p.load(reader);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                p.setProperty("Mdp",dialogResetMdp.getMdp());
                                // enregistre le nouveau mdp
                                try {
                                    p.store(new FileWriter("Admin.properties"),"Enregistrement du super administrateur");
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                            }
                            else
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
                            Modele modele = new Modele(dialog.getNom(),dialog.getPuissance(),dialog.getMoteur(),dialog.getPrixDeBase(), dialog.getImage());
                            comboBoxModelesDisponibles.addItem(modele);
                            Garage.getInstance().ajouteModele(modele);
                            Garage.getInstance().getModeles().forEach(m -> {
                                System.out.println(m.toString());
                            });
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
                    AtomicBoolean aUnContrat = new AtomicBoolean(false);
                    try
                    {
                        Garage.getInstance().getContrats().forEach(c ->{ // cherche les employes qui ont un contrat
                        if(c.getClientRef() == id)
                        {
                            aUnContrat.set(true);
                            throw new RuntimeException(); // permet de quitter la boucle
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
                                JDialogConfirmation dialogConfirmation = new JDialogConfirmation("Voulez-vous vraiment supprimer ce client ?");
                                dialogConfirmation.pack();
                                dialogConfirmation.setLocationRelativeTo(null);
                                dialogConfirmation.setVisible(true);
                                if(dialogConfirmation.isOk())
                                {
                                    Garage.getInstance().getClients().remove(tableModel.getValueAt(i, 0));
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
                    else
                    {
                        JDialogMessage dialogMessage = new JDialogMessage("Vous ne pouvez pas supprimer un client avec un contrat actif");
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
                int index = tableClients.getSelectedRow();
                AtomicBoolean aUnContrat = new AtomicBoolean(false);
                if(tableClients.isRowSelected(index))
                {
                    try
                    {
                        Garage.getInstance().getContrats().forEach(c -> {
                            if(c.getClientRef() == Garage.getInstance().getClients().get(index).getNumero())
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
                        JDialogConfirmation dialogConfirmation = new JDialogConfirmation("Êtes-vous sur de vouloir supprimer ce client ?");
                        dialogConfirmation.pack();
                        dialogConfirmation.setLocationRelativeTo(null);
                        dialogConfirmation.setVisible(true);
                        if(dialogConfirmation.isOk())
                        {
                            DefaultTableModel tableModel = (DefaultTableModel) tableClients.getModel();
                            tableModel.removeRow(tableClients.getSelectedRow());
                            Garage.getInstance().getClients().remove(index);
                            dialogConfirmation.setVisible(false);
                        }
                        else
                        {
                            dialogConfirmation.setVisible(false);
                        }
                    }
                    else
                    {
                        JDialogMessage dialogMessage = new JDialogMessage("Vous ne pouvez pas supprimer un client avec un contrat actif");
                        dialogMessage.pack();
                        dialogMessage.setLocationRelativeTo(null);
                        dialogMessage.setVisible(true);
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
                    if(dialog.getLogin().equals("SUPERADMIN"))
                    {
                        JDialogMessage dialogMessage = new JDialogMessage("Ce login est réservé !");
                        dialogMessage.pack();
                        dialogMessage.setLocationRelativeTo(null);
                        dialogMessage.setVisible(true);
                    }
                    else
                    {
                        boolean login = false;
                        for(int i = 0;i<Garage.getInstance().getEmployes().size();i++)
                        {
                            if(Garage.getInstance().getEmployes().get(i).getLogin().equals(dialog.getLogin()))
                            {
                                JDialogMessage dialogMessage = new JDialogMessage("Ce login déjà utilisé !");
                                dialogMessage.pack();
                                dialogMessage.setLocationRelativeTo(null);
                                dialogMessage.setVisible(true);
                                login = true;
                            }
                        }
                        if(login == false)
                        {
                            Employe employe = new Employe(dialog.getNom(), dialog.getPrenom(), dialog.getLogin(), dialog.getMdp(), dialog.getFonction());
                            Garage.getInstance().ajouteEmploye(employe);
                            afficheEmployes();
                        }

                    }

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
                        Garage.getInstance().getContrats().forEach(c ->{ // cherche les employes qui ont un contrat
                            if(c.getEmployeRef() == id)
                            {
                                aUnContrat.set(true);
                                throw new RuntimeException(); // permet de quitter la boucle
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
                    else
                    {
                        JDialogMessage dialogMessage = new JDialogMessage("Vous ne pouvez pas supprimer un employé avec un contrat actif");
                        dialogMessage.pack();
                        dialogMessage.setLocationRelativeTo(null);
                        dialogMessage.setVisible(true);
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
                try
                {
                    Garage.getInstance().SaveModeles();
                    Garage.getInstance().SaveOptions();
                    Garage.getInstance().SaveGarage();
                    Garage.getInstance().SaveInumeros();
                    if(Garage.getProjetEnCours().getModele() != null)
                    {
                        Garage.getProjetEnCours().setNom(textFieldNomProjet.getText());
                        Garage.getInstance().SaveProjetEnCoursSer();
                    }
                }
                catch(Exception exc)
                {
                    System.out.println(exc.getMessage());
                }
                System.out.println("Sauvegarde des modèles");
                System.out.println("Sauvegarde des modèles");
                System.out.println("Sérialisation du garage");
                System.exit(0);
            }
        });



        pack();
        setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
        buttonChoisirModele.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Modele modele = (Modele) comboBoxModelesDisponibles.getSelectedItem();
                Voiture v = new Voiture("nom", modele);
                Garage.setProjetEnCours(v);
                afficheProjetEnCours();
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
                    JDialogMessage dialogMessage = new JDialogMessage("Aucun projet à ce nom");
                    dialogMessage.pack();
                    dialogMessage.setLocationRelativeTo(null);
                    dialogMessage.setVisible(true);
                }
            }
        });

        nouveauContratButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel tableContratsModel = (DefaultTableModel) tableContrats.getModel();
                int indexClient = tableClients.getSelectedRow();
                String nom = textFieldNomProjet.getText();
                File check = new File("Data/Projets/" + nom + ".car");
                if(indexClient != -1 && check.exists()) // Vérifie si on a bien sélectionné et si le projet du contrat existe
                {
                    AtomicBoolean memeContrat = new AtomicBoolean(false);
                    Garage.getProjetEnCours().setNom(nom);
                    Contrat c = new Contrat(Garage.getInstance().getClients().get(indexClient).getNumero(), Garage.getInstance().getIdConnected(), Garage.getProjetEnCours().getNom());
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
                        ligne.add(c.getEmployeRef());
                        ligne.add(c.getClientRef());
                        ligne.add(c.getNom());
                        tableContratsModel.addRow(ligne);
                    }
                }
                else if(indexClient == -1)
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

        menuItemParametre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogParametres dialogParametres = new JDialogParametres();
                dialogParametres.pack();
                dialogParametres.setLocationRelativeTo(null);
                dialogParametres.setVisible(true);
            }
        });
    }
    private void afficheProjetEnCours()
    {

        Voiture v = Garage.getProjetEnCours();
        if(v.getModele() != null)
        {
            textFieldNomProjet.setText(v.getNom());
            textFieldModele.setText(v.getModele().getNom());
            textFieldPrixDeBase.setText(String.valueOf(v.getModele().getPrixDeBase()));
            textFieldPuissance.setText(String.valueOf(v.getModele().getPuissance()));
            if (capitalize(v.getModele().getMoteur()).equals("Essence")) radioButtonEssence.setSelected(true);
            if (capitalize(v.getModele().getMoteur()).equals("Diesel")) radioButtonDiesel.setSelected(true);
            if (capitalize(v.getModele().getMoteur()).equals("Electrique")) radioButtonElectrique.setSelected(true);
            if (capitalize(v.getModele().getMoteur()).equals("Hybride")) radioButtonHybride.setSelected(true);
            DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
            ImageIcon icon = new ImageIcon("src" + File.separator + "GUI" + File.separator + "images" + File.separator + v.getModele().getImage());
            labelImage.setIcon(icon);
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
        else
        {
            textFieldModele.setText("");
            textFieldPrixDeBase.setText("");
            textFieldPuissance.setText("");
            radioButtonEssence.setSelected(true);
            DefaultTableModel model = (DefaultTableModel) tableOptionsChoisies.getModel();
            model.setRowCount(0);
            labelImage.setIcon(null);
        }
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
            String gsm = e.getGsm().substring(0, 4);
            gsm += '/';
            gsm += e.getGsm().substring(4, 6);
            gsm += '.';
            gsm += e.getGsm().substring(6, 8);
            gsm += '.';
            gsm += e.getGsm().substring(8, 10);
            ligne.add(gsm);
            ligne.add("");
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

    private void afficheContrats()
    {
        DefaultTableModel model = (DefaultTableModel) tableContrats.getModel();
        model.setRowCount(0);
        Garage.getInstance().getContrats().forEach(e -> {
            Vector ligne = new Vector();
            ligne.add(Integer.valueOf(e.getNumero()));
            ligne.add(e.getClientRef());
            ligne.add(e.getEmployeRef());
            ligne.add(e.getNom());
            model.addRow(ligne);
        });
    }

    private void LoadModelesCSV()
    {
        try
        {
            Garage.getInstance().LoadModeles();
            Garage.getInstance().getModeles().forEach(m -> {
                comboBoxModelesDisponibles.addItem(m);
            });
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    private void LoadOptionsCSV()
    {
        try
        {
            Garage.getInstance().LoadOptions();
            Garage.getInstance().getOptions().forEach(o -> {
                comboBoxOptionsDisponibles.addItem(o);
            });
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JDialogMessage dialogMessage = new JDialogMessage("Bienvenue " + evt.getNewValue() + " !");
        dialogMessage.pack();
        dialogMessage.setLocationRelativeTo(null);
        dialogMessage.setVisible(true);
    }

    public String getDate()
    {
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
        Locale locale = new Locale(p.getProperty("Langue"), p.getProperty("Pays"));
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        String date = dateFormat.format(new Date());

        return date;
    }
}
