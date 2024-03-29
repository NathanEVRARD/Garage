package Garage;

import java.io.Serializable;

public abstract class Intervenant extends Personne implements IEstIdentifiable, Serializable
{
    private static int Inumero = 1;
    public int numero;

    public Intervenant(String n, String p)
    {
        super(n ,p);
        setNumero(Inumero++);
    }

    public Intervenant(String n, String p, int i)
    {
        super(n, p);
        setNumero(i);
    }
    @Override
    public int getNumero()
    {
        return this.numero;
    }
    public static int getInumero(){return Inumero;}
    public static void setInumero(int i){Inumero = i;}
    public void setNumero(int n)
    {
        this.numero = n;
    }

    @Override
    public String toString()
    {
        return super.toString() + ";" + getNumero();
    }

    public boolean equals(Intervenant i)
    {
        if(i.getNumero() == this.getNumero() && super.equals(i))
            return true;
        else
            return false;
    }
}
