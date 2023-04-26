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

    @Override
    public String toString() {
        return code + ";" + intitule + ";" + prix;
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
