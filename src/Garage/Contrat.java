package Garage;

import javax.imageio.IIOException;
import java.io.FileWriter;
import java.io.IOException;

public class Contrat implements IEstIdentifiable
{
    private static int Inumero = 1;
    private int numero;
    private Client clientRef;
    private Employe employeRef;
    private String nom;

    Contrat(String n, Client c, Employe e)
    {
        setNom(n);
        setClientRef(c);
        setEmployeRef(e);
        setNumero(Inumero++);
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

    public void setClientRef(Client c)
    {
        this.clientRef = c;
    }

    public void setEmployeRef(Employe e)
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

    public Employe getEmployeRef()
    {
        return this.employeRef;
    }

    public Client getClientRef()
    {
        return this.clientRef;
    }

    @Override
    public String toString()
    {
        return getNom() + ";" + getClientRef().getNumero() + ";" + getEmployeRef().getNumero();
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
