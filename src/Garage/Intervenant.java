package Garage;

public abstract class Intervenant extends Personne implements IEstIdentifiable
{
    private static int Inumero = 1;
    public int numero;

    Intervenant(String n, String p)
    {
        super(n ,p);
        this.numero = Inumero++;
    }
    @Override
    public int getNumero()
    {
        return this.numero;
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
