package Garage;

import java.io.FileWriter;
import java.io.IOException;

public class MainVoiture {
    public static void main(String[] args)
    {
        /* Test du constructeur d'initialisation */
        Modele m = new Modele("Swift", 70, "Essence", 3000, "");
        Modele m2 = new Modele("Corsa", 75, "Diesel", 4500, "");

        Option o = new Option("AAZE","Vitre teinté",135);
        Option o2 = new Option("AZEE","Toit ouvrant",200);

        Voiture v = new Voiture("Nathan Evrard",m2);
        Voiture v2 = new Voiture("Adrien Feyen",m);

        /* Test de la fonction ToString() */
        System.out.println(v.toString() + "\n");
        System.out.println(v2.toString() + "\n");


        /* Test de la fonction equals() */
        System.out.println("Est-ce que les 2 voitures sont égales ? " + v.equals(v) + "\n");

        /* Test des getters */
        System.out.println("Test des getters : Affichage de la voiture. \n");
        System.out.println(v.getNom());
        System.out.println(v.getModele().toString());

        /* Test des setters */
        System.out.println("\nTest des setters : modification de la voiture avec les setters.");
        v.setModele(m2);
        v.setNom("Wagner Jean-Marc");

        System.out.println("Test des setters : affichage la voiture modifié : \n");
        System.out.println(v.getNom());
        System.out.println(v.getModele().toString());

        /* Ajout d'option à la voiture */
        System.out.println("Test Ajouter option : affichage la voiture avec les options : \n");
        v.ajouteOption(o);
        v.ajouteOption(o2);

        System.out.println(v.toString());
        /* Retirer Option à la voiture*/
        System.out.println("Test Retirer option : affichage la voiture avec les options retirées : \n");
        v.retireOption(1);

        System.out.println(v.toString());


        /* Ecriture dans le fichier du modele (pas fait car chemin définit dans la fonction) */
        //v.Save();


    }
}
