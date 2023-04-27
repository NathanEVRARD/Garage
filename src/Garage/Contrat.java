package Garage;

import javax.imageio.IIOException;
import java.io.FileWriter;
import java.io.IOException;

public class Contrat implements IEstIdentifiable
{
    private static int Inumero = 1;
    private int numero;
    private int clientRef;
    private int employeRef;
    private String nom;

    public Contrat(String n, Client c, Employe e)
    {
        setNom(n);
        setClientRef(c.getNumero());
        setEmployeRef(e.getNumero());
        setNumero(Inumero++);
    }

    public Contrat(String n, int c, int e)
    {
        setNom(n);
        setClientRef(c);
        setEmployeRef(e);
        setNumero(Inumero++);
    }

    public Contrat(int i, String n, int c, int e)
    {
        setNumero(i);
        setNom(n);
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
        return getNumero() + ";" + getNom() + ";" + getClientRef() + ";" + getEmployeRef();
    }

    public boolean equals(Contrat c)
    {
        if(c.getEmployeRef() == this.getEmployeRef() && c.getClientRef() == this.getClientRef() && c.getNom() == this.getNom() && c.getNumero() == this.getNumero())
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
