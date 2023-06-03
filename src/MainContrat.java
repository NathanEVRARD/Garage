package Garage;

import Garage.Contrat;

import java.io.FileWriter;
import java.io.IOException;

public class MainContrat {
    public static void main(String[] args)
    {
        /* Test du constructeur d'initialisation */

        Client c = new Client("Nathan", "Evrard","0438445483");
        Client c2 = new Client("Adrien","Feyen","0458542949");

        Employe e3 = new Employe("Hugo", "Donateli","Hugogo","","ADMIN",5);
        Employe e2 = new Employe("Pierre","Menes","Pierre","LOLO111","VENDEUR",11);

        Contrat co = new Contrat(c,e3, "coucou");
        Contrat co2 = new Contrat(c2.getNumero(),e2.getNumero());
        Contrat co3 = new Contrat(8,c2.getNumero(),e2.getNumero());

        /* Test de la fonction ToString() */
        System.out.println(co.toString() + "\n");
        System.out.println(co2.toString() + "\n");
        System.out.println(co3.toString() + "\n");


        /* Test de la fonction equals() */
        System.out.println("Est-ce que les 2 client sont égaux ? " + co.equals(co2) + "\n");

        /* Test des getters */
        System.out.println("Test des getters : Affichage du client. \n");
        System.out.println(co.getNom());
        System.out.println(co.getClientRef());
        System.out.println(co.getEmployeRef());
        System.out.println(co.getNumero());
        System.out.println(co.getInumero());


        /* Test des setters */
        System.out.println("\nTest des setters : modification du client avec les setters.");
        co.setNom(e3.getNom()+c2.getNom());
        co.setNumero(30);
        co.setClientRef(c2.getNumero());
        co.setEmployeRef(e3.getNumero());

        System.out.println("Test des setters : affichage du client modifié : \n");
        System.out.println(co.toString() + "\n");


        /* Test de la fonction Save() */

        try
        {
            FileWriter fw = new FileWriter(".\\FichierTest\\Contrat"+co.getNom()+".txt");
            co.Save(fw);
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
