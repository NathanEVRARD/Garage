package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameGarage extends JFrame
{
    private JPanel mainPanel;

    public JFrameGarage()
    {
        //setSize(800,600);
        setTitle("Application Garage JAVA");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuConnexion = new JMenu("Connexion");
        menuBar.add(menuConnexion);
        JMenuItem menuItemLogin = new JMenuItem("Login");
        menuConnexion.add(menuItemLogin);
        JMenuItem menuItemLogout = new JMenuItem("Logout");
        menuConnexion.add(menuItemLogout);
        menuConnexion.addSeparator();
        JMenuItem menuItemResetMotDePasse = new JMenuItem("Reset mot de passe");
        menuConnexion.add(menuItemResetMotDePasse);
        menuConnexion.addSeparator();
        JMenuItem menuItemQuitter = new JMenuItem("Quitter");
        menuConnexion.add(menuItemQuitter);

        menuItemQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menuItemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialogLogin dialog = new JDialogLogin(null,true,"Entrée en session...");
                dialog.setVisible(true);
            }
        });

        pack();
        setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
    }

    public static void main(String[] args) {
        JFrameGarage frame = new JFrameGarage();
        frame.setVisible(true);
    }
}
