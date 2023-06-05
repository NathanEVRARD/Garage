package GUI;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class BeanEmetteur implements Serializable {
    private String login;

    private PropertyChangeSupport propertySupport;
    public BeanEmetteur()
    {
        propertySupport = new PropertyChangeSupport(this);
    }
    public BeanEmetteur(String l)
    {
        login = l;
        propertySupport = new PropertyChangeSupport(this);
    }
    public String getLogin()
    {
        return login;
    }

    public void setLogin(String value)
    {
        String oldValue = login;
        login = value;
        propertySupport.firePropertyChange("login", oldValue, login);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        propertySupport.addPropertyChangeListener(listener);
    }
}
