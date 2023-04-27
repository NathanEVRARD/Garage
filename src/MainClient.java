package Garage;

import java.io.FileWriter;
import java.io.IOException;

public class MainClient {
    public static void main(String[] args)
    {
        /* Test du constructeur d'initialisation */

        Client c = new Client("Nathan", "Evrard","0438445483");
        Client c2 = new Client("Adrien","Feyen","0458542949");

        /* Test de la fonction ToString() */
        System.out.println(c.toString() + "\n");
        System.out.println(c2.toString() + "\n");


        /* Test de la fonction equals() */
        System.out.println("Est-ce que les 2 client sont égaux ? " + c.equals(c2) + "\n");

        /* Test des getters */
        System.out.println("Test des getters : Affichage du client. \n");
        System.out.println(c.getNom());
        System.out.println(c.getPrenom());
        System.out.println(c.getGsm());
        System.out.println(c.getNumero());

        /* Test des setters */
        System.out.println("\nTest des setters : modification du client avec les setters.");
        c.setPrenom("Jean-Marc");
        c.setNom("Wagner");
        c.setGsm("0498201202");
        c.setNumero(10);

        System.out.println("Test des setters : affichage du client modifié : \n");
        System.out.println(c.getNom());
        System.out.println(c.getPrenom());
        System.out.println(c.getGsm());
        System.out.println(c.getNumero());


        /* Test de la fonction Save() */

        try
        {
            FileWriter fw = new FileWriter(".\\FichierTest\\C"+c.getNom()+c.getPrenom()+".txt");
            c.Save(fw);
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
