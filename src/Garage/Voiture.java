package Garage;

import GUI.JDialogMessage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Voiture implements Serializable {
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

    public String getNom()
    {
        return this.nomProjet;
    }

    public Modele getModele()
    {
        return this.modele;
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
    public boolean equals(Voiture v)
    {
        if(v.getNom() == this.getNom() && v.getModele().equals(this.getModele()))
            return true;
        else
            return false;
    }

    public boolean ajouteOption(Option op)
    {
        int i;
        for(i = 0; i < 5 && options[i] != null && options[i].getCode() != op.getCode(); i++);
        if(i < 5 && options[i] == null)
        {
            System.out.println("Option ajoutée à l'index : " + i);
            options[i] = op;
            return true;
        }
        else
        {
            JDialogMessage dialogMessage = new JDialogMessage("Vous ne pouvez pas avoir plus de 5 options");
            dialogMessage.pack();
            dialogMessage.setLocationRelativeTo(null);
            dialogMessage.setVisible(true);
        }
        return false;
    }

    public void retireOption(int i)
    {
        if(i >= 0 && i <= 4 && options[i] != null)
        {
            System.out.println("Option supprimée");
            options[i] = null;
        }
        while(i < 4)
        {
            options[i] = options[i+1];
            i++;
        }
        options[i] = null;
    }

    public float getPrix()
    {
        float prix = modele.getPrixDeBase();
        for(int i = 0; i < 5; i++)
        {
            if(options[i] != null)
                prix += options[i].getPrix();
        }
        return prix;
    }

    public Option getOption(int index)
    {
        if(options[index] != null)
            return options[index];
        else
            return null;
    }

    public void afficheOptions()
    {
        for(int i = 0; i < 5; i++)
        {
            if(options[i] != null)
                System.out.println(options[i].toString() + " : " + i);
        }
    }

    public void Save()
    {
        try
        {
            FileWriter writer = new FileWriter("CSV\\" + this.getNom() + ".car");
        }
        catch (IOException e)
        {
            // Print the exception
            System.out.print(e.getMessage());
        }
    }
}
