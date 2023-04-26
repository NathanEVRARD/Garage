package Garage;

public class MainModele {
    public static void main(String[] args)
    {
        Modele m = new Modele("Swift", 70, "Essence", 3000);
        Modele m2 = new Modele("Corsa", 75, "Diesel", 4500);

        System.out.println(m.toString() + "\n");
        System.out.println(m2.toString() + "\n");

        System.out.println("Est-ce que les 2 modèles sont égaux ? " + m.equals(m2));
    }
}
