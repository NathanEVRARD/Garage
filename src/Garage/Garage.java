package Garage;

import java.io.*;
import java.util.*;

public class Garage
{
    ArrayList<Employe> employes;
    ArrayList<Client> clients;
    Vector<Modele> modeles;
    Vector<Option> options;
    Vector<Contrat> contrats;

    int idConnected;
    private Garage()
    {
        options = new Vector<Option>();
        modeles = new Vector<Modele>();
        contrats = new Vector<Contrat>();
        clients = new ArrayList<Client>();
        employes = new ArrayList<Employe>();
        setIdConnected(-1);
    }

    private Garage(Garage g)
    {

    }
    private static Garage instance = new Garage();

    public static Garage getInstance()
    {
        return instance;
    }

    private static Voiture projetEnCours = new Voiture("", null);

    public static Voiture getProjetEnCours(){return projetEnCours;}

    public static void setProjetEnCours(Voiture v){projetEnCours = v;}

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

    public void SaveGarage()
    {
        SaveClients();
        SaveEmployes();
        SaveContrats();
    }

    public void LoadGarage()
    {
        try
        {
            LoadClientsSer();
            LoadEmployesSer();
            LoadContratsSer();
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void SaveClients()
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("Data" + File.separator + "GarageSerialized" + File.separator + "clients.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(getClients());
            out.close();
            fileOut.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }
    public void SaveEmployes()
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("Data" + File.separator + "GarageSerialized" + File.separator + "employes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(getEmployes());
            out.close();
            fileOut.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    public void SaveContrats()
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("Data" + File.separator + "GarageSerialized" + File.separator + "contrats.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(getContrats());
            out.close();
            fileOut.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    public void SaveProjetEnCoursSer()
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("Data" + File.separator + "GarageSerialized" + File.separator + "projetEnCours.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(getProjetEnCours());
            out.close();
            fileOut.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    public void LoadContratsSer() throws ClassNotFoundException
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("Data" + File.separator + "GarageSerialized" + File.separator + "contrats.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            contrats = (Vector<Contrat>)in.readObject();
            in.close();
            fileIn.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    public void LoadClientsSer() throws ClassNotFoundException
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("Data" + File.separator + "GarageSerialized" + File.separator + "clients.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            clients = (ArrayList<Client>)in.readObject();
            in.close();
            fileIn.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    public void LoadEmployesSer() throws ClassNotFoundException
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("Data" + File.separator + "GarageSerialized" + File.separator + "employes.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            employes = (ArrayList<Employe>)in.readObject();
            in.close();
            fileIn.close();
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }

    public void LoadProjetEnCoursSer()
    {
        try
        {
            FileInputStream fileIn = new FileInputStream("Data" + File.separator + "GarageSerialized" + File.separator + "projetEnCours.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            setProjetEnCours((Voiture)in.readObject());
            in.close();
            fileIn.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException ec)
        {
            System.out.println(ec.getMessage());
        }
    }

    public static void SaveProjetEnCours() throws IOException
    {
        String nom = getProjetEnCours().getNom();
        if(nom == "")
            return;
        String extension = ".car";
        String path = "Data/Projets/" + nom + extension;
        FileOutputStream fileOut = new FileOutputStream(path);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(getProjetEnCours());
        out.close();
        fileOut.close();
    }

    public static void LoadProjetEnCours() throws IOException, ClassNotFoundException
    {
        String nom = getProjetEnCours().getNom();
        String extension = ".car";
        String path = "Data/Projets/" + nom + extension;
        FileInputStream fileIn = new FileInputStream(path);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        setProjetEnCours((Voiture)in.readObject());
        in.close();
        fileIn.close();
    }
    public void LoadModeles() throws FileNotFoundException
    {
        File fileModeles = new File("Data" + File.separator + "CSV" + File.separator + "modeles.csv");
        Scanner scModeles = new Scanner(fileModeles);
        scModeles.useDelimiter(";|\n");
        while(scModeles.hasNext())
        {
            String nom = scModeles.next();
            int puissance = Integer.parseInt(scModeles.next());
            String moteur = scModeles.next();
            String image = scModeles.next();
            float prix = Float.parseFloat(scModeles.next());

            Modele m = new Modele(nom, puissance, moteur, prix, image);
            ajouteModele(m);
        }
        scModeles.close();
    }

    public void LoadOptions() throws FileNotFoundException
    {
        File fileOptions = new File("Data" + File.separator + "CSV" + File.separator+ "options.csv");
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

    public void SaveModeles() throws IOException
    {
        FileWriter writer = new FileWriter("Data" + File.separator + "CSV" + File.separator + "modeles.csv");
        Garage.getInstance().getModeles().forEach(m -> {
            try {
                writer.write(m.getNom() + ";" + m.getPuissance() + ";" + m.getMoteur() + ";" + m.getImage() + ";" + m.getPrixDeBase()+ "\n");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        writer.close();
    }

    public void SaveOptions() throws IOException
    {
        FileWriter writer = new FileWriter("Data" + File.separator + "CSV" + File.separator + "options.csv");
        Garage.getInstance().getOptions().forEach(o -> {
            try
            {
                writer.write(o.getCode() + ";" + o.getIntitule() + ";" + o.getPrix() + "\n");
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }
        });
        writer.close();
    }

    public void SaveInumeros()
    {
        try
        {
            FileWriter writer = new FileWriter("Data" + File.separator + "GarageSerialized" + File.separator + "inumeros.data");
            writer.write(Intervenant.getInumero());
            writer.write(Contrat.getInumero());
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void LoadInumeros()
    {
        try
        {
            FileReader reader = new FileReader("Data" + File.separator + "GarageSerialized" + File.separator + "inumeros.data");
            Intervenant.setInumero(reader.read());
            Contrat.setInumero(reader.read());
            reader.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
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
        Contrat.IncrementINumero();
    }

    public void ajouteClient(Client c)
    {
        clients.add(c);
        Collections.sort(clients);
    }

    public void ajouteEmploye(Employe e)
    {
        employes.add(e);
        Collections.sort(employes);
    }
    public Vector<Option> getOptions()
    {
        return options;
    }
    public List<Client> getClients(){return clients;}
    public List<Employe> getEmployes(){return employes;}
    public Vector<Contrat> getContrats(){return contrats;}
    public Vector<Modele> getModeles(){return modeles;}
    public int getIdConnected(){return idConnected;}
    public void setIdConnected(int id) {idConnected = id;}
}
