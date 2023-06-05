package Garage;

import javax.imageio.IIOException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Contrat implements IEstIdentifiable, Serializable
{
    private static int Inumero = 1;
    private int numero;
    private int clientRef;
    private int employeRef;
    private String nom;

    public Contrat(Client c, Employe e, String n)
    {
        setClientRef(c.getNumero());
        setEmployeRef(e.getNumero());
        setNumero(Inumero);
        setNom(n);
    }

    public Contrat(int c, int e, String n)
    {
        setClientRef(c);
        setEmployeRef(e);
        setNom(n);
        setNumero(Inumero);
    }

    public Contrat(int i, int c, int e)
    {
        setNumero(i);
        setClientRef(c);
        setEmployeRef(e);
    }
    @Override
    public int getNumero()
    {
        return this.numero;
    }

    public void setNom(String n)
    {
        this.nom = n;
    }

    public void setClientRef(int c)
    {
        this.clientRef = c;
    }

    public void setEmployeRef(int e)
    {
        this.employeRef = e;
    }

    public void setNumero(int n)
    {
        this.numero = n;
    }

    public String getNom()
    {
        return this.nom;
    }

    public int getEmployeRef()
    {
        return this.employeRef;
    }

    public int getClientRef()
    {
        return this.clientRef;
    }

    public static void setInumero(int n)
    {
        Inumero = n;
    }

    public static int getInumero()
    {
        return Inumero;
    }


    @Override
    public String toString()
    {
        return getNom() + ";" + getClientRef() + ";" + getEmployeRef();
    }

    public boolean equals(Contrat c)
    {
        if(c.getEmployeRef() == this.getEmployeRef() && c.getClientRef() == this.getClientRef() && c.getNom() == this.getNom() && c.getNumero() == this.getNumero())
            return true;
        else
            return false;
    }

    public boolean equalsButNum(Contrat c)
    {
        if(c.getEmployeRef() == this.getEmployeRef() && c.getClientRef() == this.getClientRef() && c.getNom().equals(this.getNom()))
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

    public static void IncrementINumero()
    {
        Inumero++;
    }
}
