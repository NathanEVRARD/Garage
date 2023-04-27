package Garage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class MainOption {
    public static void main(String[] args)
    {
        /* Test du constructeur d'initialisation */

        Option o = new Option("AAZE","Vitre teinté",135);
        Option o2 = new Option("AZEE","Toit ouvrant",200);

        /* Test de la fonction ToString() */
        System.out.println(o.toString() + "\n");
        System.out.println(o2.toString() + "\n");


        /* Test de la fonction equals() */
        System.out.println("Est-ce que les 2 options sont égales ? " + o.equals(o2) + "\n");

        /* Test des getters */
        System.out.println("Test des getters : Affichage de l'option. \n");
        System.out.println(o.getCode());
        System.out.println(o.getIntitule());
        System.out.println(o.getPrix());

        /* Test des setters */
        System.out.println("\nTest des setters : modification d'une option avec les setters.");
        o.setCode("PERZ");
        o.setIntitule("Coffre automatique");
        o.setPrix(100);

        System.out.println("Test des setters : affichage de l'option modifié : \n");
        System.out.println(o.getCode());
        System.out.println(o.getIntitule());
        System.out.println(o.getPrix());

        /* Ecriture dans le fichier de l'option */
        try
        {
            FileWriter fw = new FileWriter(".\\FichierTest\\"+o.getIntitule()+".txt");
            o.Save(fw);
            fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
