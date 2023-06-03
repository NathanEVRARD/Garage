package Garage;

public class MainPersonne {
    public static void main(String[] args)
    {
        /* Test du constructeur par défaut */
        Personne p1 = new Personne();

        System.out.println(p1.toString());

        /* Test du constructeur d'initialisation */

        Personne p = new Personne("Nathan", "Evrard");
        Personne p2 = new Personne("Adrien","Feyen");

        /* Test de la fonction ToString() */
        System.out.println(p.toString() + "\n");
        System.out.println(p2.toString() + "\n");


        /* Test de la fonction equals() */
        System.out.println("Est-ce que les 2 personnes sont égales ? " + p.equals(p2) + "\n");

        /* Test des getters */
        System.out.println("Test des getters : Affichage de la personne. \n");
        System.out.println(p.getNom());
        System.out.println(p.getPrenom());

        /* Test des setters */
        System.out.println("\nTest des setters : modification de la personne avec les setters.");
        p.setPrenom("Jean-Marc");
        p.setNom("Wagner");

        System.out.println("Test des setters : affichage la personne modifié : \n");
        System.out.println(p.getNom());
        System.out.println(p.getPrenom());
    }
}
