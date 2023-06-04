package Garage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Client extends Intervenant implements Serializable
{
    private String gsm;

    public Client(String n, String p, String g)
    {
        super(n, p);
        setGsm(g);
    }

    public void setGsm(String g)
    {
        this.gsm = g;
    }

    public String getGsm()
    {
        return this.gsm;
    }

    @Override
    public String toString()
    {
        return super.toString() + ";" + getGsm();
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
