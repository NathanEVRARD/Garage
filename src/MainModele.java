package Garage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainModele {
    public static void main(String[] args)
    {
        /* Test du constructeur d'initialisation */

        Modele m = new Modele("Swift", 70, "Essence", 3000);
        Modele m2 = new Modele("Corsa", 75, "Diesel", 4500);

        /* Test de la fonction ToString() */
        System.out.println(m.toString() + "\n");
        System.out.println(m2.toString() + "\n");


        /* Test de la fonction equals() */
        System.out.println("Est-ce que les 2 modèles sont égaux ? " + m.equals(m2) + "\n");

        /* Test des getters */
        System.out.println("Test des getters : Affichage du modele. \n");
        System.out.println(m.getNom());
        System.out.println(m.getPuissance());
        System.out.println(m.getMoteur());
        System.out.println(m.getPrixDeBase());

        /* Test des setters */
        System.out.println("\nTest des setters : modification du modèle avec les setters.");
        m.setNom("Arteon");
        m.setPuissance(220);
        m.setPrixDeBase(70000);
        m.setMoteur("Diesel");

        System.out.println("Test des setters : affichage du modèle modifié : \n");
        System.out.println(m.getNom());
        System.out.println(m.getPuissance());
        System.out.println(m.getMoteur());
        System.out.println(m.getPrixDeBase());

        /* Ecriture dans le fichier du modele */
        try
        {
            FileWriter fw = new FileWriter(".\\FichierTest\\"+m.getNom()+".txt");
            m.Save(fw);
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
