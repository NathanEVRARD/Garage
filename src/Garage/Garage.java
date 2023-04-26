package Garage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;
import java.io.FileWriter;

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

    public void Save()
    {
        try
        {
            String modelesPath = "C:\\Users\\natha\\Documents\\Mes Cours\\B2\\Q2\\Labo\\Java\\ApplicGarage\\CSV\\modeles.csv";
            String optionsPath = "C:\\Users\\natha\\Documents\\Mes Cours\\B2\\Q2\\Labo\\Java\\ApplicGarage\\CSV\\options.csv";
            String contratsPath = "C:\\Users\\natha\\Documents\\Mes Cours\\B2\\Q2\\Labo\\Java\\ApplicGarage\\CSV\\contrats.csv";
            String clientsPath = "C:\\Users\\natha\\Documents\\Mes Cours\\B2\\Q2\\Labo\\Java\\ApplicGarage\\CSV\\clients.csv";
            String employesPath = "C:\\Users\\natha\\Documents\\Mes Cours\\B2\\Q2\\Labo\\Java\\ApplicGarage\\CSV\\employes.csv";

            FileWriter modelesWriter = new FileWriter(modelesPath);
            FileWriter optionsWriter = new FileWriter(optionsPath);
            FileWriter contratsWriter = new FileWriter(contratsPath);
            FileWriter clientsWriter = new FileWriter(clientsPath);
            FileWriter employesWriter = new FileWriter(employesPath);

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
