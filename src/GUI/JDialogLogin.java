package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JDialogLogin extends JDialog
{
    private JTextField textFieldLogin;
    private JPasswordField textFieldMotDePasse;
    private JPanel mainPanel;
    private JButton buttonOk;
    private JButton buttonAnnuler;

    private String login;
    private String motDePasse;
    private boolean ok;

    BeanEmetteur be;
    JFrameGarage br;

    public JDialogLogin(JFrame parent,boolean modal,String titre, JFrameGarage frameGarage)
    {
        super(parent,modal);
        setTitle(titre);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        pack();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
        ok = false;

        be = new BeanEmetteur("login");
        br = frameGarage;
        be.addPropertyChangeListener(br);

        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login = textFieldLogin.getText();
                motDePasse = textFieldMotDePasse.getText();
                ok = true;
                setVisible(false);
                be.setLogin(login);
            }
        });
        buttonAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok = false;
                setVisible(false);
            }
        });
    }

    public String getLogin() {
        return login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public boolean isOk() {
        return ok;
    }
}
