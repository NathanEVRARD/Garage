package Garage;

import java.io.IOException;
import java.io.FileWriter;

public class Option
{
    private String code;
    private String intitule;
    private float prix;

    public Option(String code, String intitule, float prix) {
        this.code = code;
        this.intitule = intitule;
        this.prix = prix;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean accorderReduction()
    {
        if(prix - 50 < 0)
            return false;
        else
        {
            prix -= 50;
            return true;
        }
    }

    @Override
    public String toString() { return intitule;}

    public boolean equals(Option o)
    {
        if(o.getCode() == this.getCode() && o.getIntitule() == this.getIntitule() && o.getPrix() == this.getPrix())
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
