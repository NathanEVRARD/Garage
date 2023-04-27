package Garage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Garage
{
    List<Employe> employes;
    List<Client> clients;
    Vector<Modele> modeles;
    Vector<Option> options;
    Vector<Contrat> contrats;

    private Garage()
    {
        options = new Vector<Option>();
        modeles = new Vector<Modele>();
        contrats = new Vector<Contrat>();
        clients = new ArrayList<Client>();
        employes = new ArrayList<Employe>();
    }

    private Garage(Garage g)
    {

    }
    private static Garage instance = new Garage();

    public static Garage getInstance()
    {
        return instance;
    }

    public void afficheClients()
    {
        clients.forEach(client -> System.out.println(client.toString() + "\n"));
    }

    public void afficheModeles()
    {
        modeles.forEach(modele -> System.out.println(modele.toString()));
    }

    public void afficheContrats()
    {
        contrats.forEach(contrat -> System.out.println(contrat.toString()));
    }

    public void afficheEmployes()
    {
        employes.forEach(employe -> System.out.println(employe.toString()));
    }

    public void afficheOptions()
    {
        options.forEach(option -> System.out.println(option.toString()));
    }

    public void Save()
    {
        try
        {
            String modelesPath = "CSV\\modeles.csv";
            String optionsPath = "CSV\\options.csv";
            String contratsPath = "CSV\\contrats.csv";
            String clientsPath = "CSV\\clients.csv";
            String employesPath = "CSV\\employes.csv";

            FileWriter modelesWriter = new FileWriter(modelesPath);
            FileWriter optionsWriter = new FileWriter(optionsPath);
            FileWriter contratsWriter = new FileWriter(contratsPath);
            FileWriter clientsWriter = new FileWriter(clientsPath);
            FileWriter employesWriter = new FileWriter(employesPath);

            contratsWriter.write(Contrat.getInumero() + "\n");

            modeles.forEach(modele -> modele.Save(modelesWriter));
            options.forEach(option -> option.Save(optionsWriter));
            contrats.forEach(contrat -> contrat.Save(contratsWriter));
            clients.forEach(client -> client.Save(clientsWriter));
            employes.forEach(employe -> employe.Save(employesWriter));

            modelesWriter.close();
            optionsWriter.close();
            contratsWriter.close();
            clientsWriter.close();
            employesWriter.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    public void Load()
    {
        try
        {
            LoadClients();
            LoadModeles();
            LoadContrats();
            LoadEmployes();
            LoadOptions();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void LoadClients() throws FileNotFoundException
    {
        File fileClients = new File("CSV\\clients.csv");
        Scanner scClients = new Scanner(fileClients);
        scClients.useDelimiter(";|\n");
        while(scClients.hasNext())
        {
            String nom = scClients.next();
            String prenom = scClients.next();
            int num = Integer.parseInt(scClients.next());
            String gsm = scClients.next();
            Client c = new Client(nom, prenom, gsm);
            c.setNumero(num);
            ajouteClient(c);
        }
        scClients.close();
    }

    public void LoadModeles() throws FileNotFoundException
    {
        File fileModeles = new File("CSV\\modeles.csv");
        Scanner scModeles = new Scanner(fileModeles);
        scModeles.useDelimiter(";|\n");
        while(scModeles.hasNext())
        {
            String nom = scModeles.next();
            int puissance = Integer.parseInt(scModeles.next());
            String moteur = scModeles.next();
            float prix = Float.parseFloat(scModeles.next());

            Modele m = new Modele(nom, puissance, moteur, prix);
            ajouteModele(m);
        }
        scModeles.close();
    }

    public void LoadOptions() throws FileNotFoundException
    {
        File fileOptions = new File("CSV\\options.csv");
        Scanner scOptions = new Scanner(fileOptions);
        scOptions.useDelimiter(";|\n");
        while(scOptions.hasNext())
        {
            String code = scOptions.next();
            String intitule = scOptions.next();
            float prix = Float.parseFloat(scOptions.next());

            Option o = new Option(code, intitule, prix);
            ajouteOption(o);
        }
        scOptions.close();
    }

    public void LoadContrats() throws FileNotFoundException
    {
        File fileContrats = new File("CSV\\contrats.csv");
        Scanner scContrats = new Scanner(fileContrats);
        int Inumero = Integer.parseInt(scContrats.nextLine());
        Contrat.setInumero(Inumero);
        scContrats.useDelimiter(";|\n");

        while(scContrats.hasNext())
        {
            int num = Integer.parseInt(scContrats.next());
            String nom = scContrats.next();
            int clientRef = Integer.parseInt(scContrats.next());
             int employeRef = Integer.parseInt(scContrats.next());

            Contrat c = new Contrat(num, nom, clientRef, employeRef);
            ajouteContrat(c);
        }
        scContrats.close();
    }

    public void LoadEmployes() throws FileNotFoundException
    {
        File fileEmployes = new File("CSV\\employes.csv");
        Scanner scEmployes = new Scanner(fileEmployes);
        scEmployes.useDelimiter(";|\n");

        while(scEmployes.hasNext())
        {
            String nom = scEmployes.next();
            String prenom = scEmployes.next();
            int num = Integer.parseInt(scEmployes.next());
            String login = scEmployes.next();
            String fonction = scEmployes.next();
            String mdp = scEmployes.next();

            Employe e = new Employe(nom, prenom, login, mdp, fonction, num);
            ajouteEmploye(e);
        }
        scEmployes.close();
    }

    public void ajouteOption(Option o)
    {
        options.add(o);
    }

    public void ajouteModele(Modele m)
    {
        modeles.add(m);
    }

    public void ajouteContrat(Contrat c)
    {
        contrats.add(c);
    }

    public void ajouteClient(Client c)
    {
        clients.add(c);
    }

    public void ajouteEmploye(Employe e)
    {
        employes.add(e);
    }
}