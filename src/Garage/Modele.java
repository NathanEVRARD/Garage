package Garage;

import java.io.FileWriter;
import java.io.IOException;
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
        return nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public String getMoteur() {
        return moteur;
    }

    public void setMoteur(String moteur) {
        this.moteur = moteur;
    }

    public float getPrixDeBase() {
        return prixDeBase;
    }

    public void setPrixDeBase(float prixDeBase) {
        this.prixDeBase = prixDeBase;
    }

    public boolean equals(Modele m)
    {
        if(m.getNom() == this.getNom() && m.getMoteur() == this.getMoteur() && m.getPuissance() == this.getPuissance() && m.getPrixDeBase() == this.getPrixDeBase())
            return true;
        else
            return false;
    }

    public void Save(FileWriter writer)
    {
        try
        {
            writer.write(this.toString() + "\n");
        }
        catch(IOException e)
        {
            System.out.print(e.getMessage());
        }
    }
}
