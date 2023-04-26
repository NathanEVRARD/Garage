package Garage;
public class Employe extends Intervenant implements IEstIdentifiable
{
    private String login;
    private String mdp;
    private String fonction;

    public Employe(String n, String p, String l, String m, String f)
    {
        super(n, p);
        setLogin(l);
        setFonction(f);
        setMdp(m);
    }

    public void setLogin(String l)
    {
        this.login = l;
    }

    public void setFonction(String f)
    {
        this.fonction = f;
    }

    public void setMdp(String m)
    {
        this.mdp = m;
    }

    public String getLogin()
    {
        return this.login;
    }

    public String getMdp()
    {
        return this.mdp;
    }

    public String getFonction()
    {
        return this.fonction;
    }

    @Override
    public String toString()
    {
        return super.toString() + ";" + getNumero() + ";" + getFonction();
    }
}
