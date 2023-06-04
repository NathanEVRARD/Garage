package Garage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Modele implements Serializable
{
    public Modele(String nom, int puissance, String moteur, float prixDeBase, String image) {
        this.nom = nom;
        this.puissance = puissance;
        this.moteur = moteur;
        this.prixDeBase = prixDeBase;
        this.image = image;
    }

    private String nom;
    private int puissance;
    private String moteur;
    private float prixDeBase;
    private String image;

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
    public void setImage(String image){this.image = image;}

    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public String getMoteur() {
        return moteur;
    }
    public String getImage(){return image;}

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
