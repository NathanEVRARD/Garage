package Garage;

public class Client extends Intervenant
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
}
