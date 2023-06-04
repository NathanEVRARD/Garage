package Garage;

public class Personne implements Comparable<Personne>
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
        this.nom = n.toUpperCase();
    }

    public void setPrenom(String p)
    {
        this.prenom = capitalize(p);
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

    public static String capitalize(String str)
    {
        if (str == null || str.length() == 0) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    @Override
    public int compareTo(Personne p) {
        if(this.getNom() != p.getNom())
            return this.getNom().compareTo(p.getNom());
        else
            return this.getPrenom().compareTo(p.getPrenom());
    }
}
