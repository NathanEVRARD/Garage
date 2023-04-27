package Garage;

import java.io.FileWriter;
import java.io.IOException;

public class MainEmploye {
    public static void main(String[] args)
    {

        /* Test du constructeur d'initialisation sans le numéro */

        Employe e1 = new Employe("Nathan", "Evrard","NatNat","","ADMIN");

        /* Test des getters */
        System.out.println("Test des getters : Affichage du client. \n");
        System.out.println(e1.getNom());
        System.out.println(e1.getPrenom());
        System.out.println(e1.getLogin());
        System.out.println(e1.getMdp());
        System.out.println(e1.getFonction());
        System.out.println(e1.getNumero());

        /* Test du constructeur d'initialisation avec le numéro */

        Employe e3 = new Employe("Nathan", "Evrard","NatNat","","ADMIN",5);
        Employe e2 = new Employe("Adrien","Feyen","Adri","LOLO111","VENDEUR",11);

        /* Test des getters */
        System.out.println("Test des getters : Affichage de l'employé. \n");
        System.out.println(e2.getNom());
        System.out.println(e2.getPrenom());
        System.out.println(e2.getLogin());
        System.out.println(e2.getMdp());
        System.out.println(e2.getFonction());
        System.out.println(e2.getNumero());

        /* Test de la fonction ToString() */
        System.out.println(e3.toString() + "\n");
        System.out.println(e2.toString() + "\n");


        /* Test de la fonction equals() */
        System.out.println("Est-ce que les 2 client sont égaux ? " + e3.equals(e2) + "\n");


        /* Test des setters */
        System.out.println("\nTest des setters : modification de l'employé avec les setters.");
        e3.setPrenom("Jean-Marc");
        e3.setNom("Wagner");
        e3.setLogin("JeanJean");
        e3.setFonction("VENDEUR");
        e3.setMdp("Wagner123");
        e3.setNumero(10);

        System.out.println("Test des setters : affichage de l'employé modifié : \n");
        System.out.println(e3.getNom());
        System.out.println(e3.getPrenom());
        System.out.println(e3.getLogin());
        System.out.println(e3.getMdp());
        System.out.println(e3.getFonction());
        System.out.println(e3.getNumero());

        /* Test de la fonction Save() */

        try
        {
            FileWriter fw = new FileWriter(".\\FichierTest\\E"+e2.getNom()+e2.getPrenom()+".txt");
            e2.Save(fw);
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
