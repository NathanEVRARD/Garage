package Garage;

public class Personne
{
    private String nom;
    private String prenom;

    Personne(String n, String p)
    {
        setNom(n);
        setPrenom(p);
    }

    Personne()
    {
        setNom("Nom");
        setPrenom("Prénom");
    }

    public void setNom(String n)
    {
        this.nom = n;
    }

    public void setPrenom(String p)
    {
        this.prenom = p;
    }

    public String getNom()
    {
        return this.nom;
    }

    public String getPrenom()
    {
        return this.prenom;
    }

    @Override
    public String toString()
    {
        return nom + ";" + prenom;
    }

    public boolean equals(Personne p)
    {
        if(p.getPrenom() == this.getPrenom() && p.getNom() == this.getNom())
            return true;
        else
            return false;
    }
}
