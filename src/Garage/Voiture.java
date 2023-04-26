package Garage;
public class Voiture {
    private String nomProjet;
    private Modele modele;

    private Option[] options = new Option[5];

    public Voiture(String nom, Modele m)
    {
        setNom(nom);
        setModele(m);
    }

    public void setNom(String n)
    {
        this.nomProjet = n;
    }

    public void setModele(Modele m)
    {
        this.modele = m;
    }

    @Override
    public String toString()
    {
        int i;
        String s = nomProjet + ";" + modele.toString();
        s += "\n";
        for(i = 0; i < 5; i++)
        {
            if(options[i] != null)
                s += "(" + options[i].toString() + ")\n";
        }
        return s;
    }

    public void ajouteOption(Option op)
    {
        int i;
        for(i = 0; i < 5 && options[i] != null; i++);
        if(i < 5)
            options[i] = op;
    }

    public void retireOption(int i)
    {
        if(i-1 >= 0 && i-1 <= 4 && options[i-1] != null)
            options[i-1] = null;
    }
}
