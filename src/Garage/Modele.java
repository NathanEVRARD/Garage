package Garage;

public class Modele
{
    public Modele(String nom, int puissance, String moteur, float prixDeBase) {
        this.nom = nom;
        this.puissance = puissance;
        this.moteur = moteur;
        this.prixDeBase = prixDeBase;
    }

    private String nom;
    private int puissance;
    private String moteur;
    private float prixDeBase;

    @Override
    public String toString() {
        String s = "" + nom + ", " + puissance + "ch, " + moteur + ", " + prixDeBase;
        return s;
    }
}
