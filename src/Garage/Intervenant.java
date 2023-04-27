package Garage;

public abstract class Intervenant extends Personne implements IEstIdentifiable
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
