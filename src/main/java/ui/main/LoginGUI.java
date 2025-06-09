package ui.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import staticProcess.StaticProcess;
import ui.login.Home;
import ui.login.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LoginGUI extends JFrame implements ActionListener {

    public static Home home;

    public static Login loginPanel;

    static {
        try {
            loginPanel = new Login();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    }

    public LoginGUI() throws MalformedURLException, NotBoundException, RemoteException {
        init();
        getRootPane().setDefaultButton(loginPanel.btnLogin);
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(new Dimension(1920, 1080));
        setLocationRelativeTo(null);
        home = new Home();
        setContentPane(home);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    home.initOverlay(StaticProcess.login);
                } catch (MalformedURLException ex) {
                    throw new RuntimeException(ex);
                } catch (NotBoundException ex) {
                    throw new RuntimeException(ex);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                home.play(0);
                home.getHomeOverlay().showLoginForm();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                home.stop();
            }
        });
    }

    public String getUserLogin(){
        return loginPanel.txtUsername.getText();
    }
    public static void main(LoginGUI login) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("ui.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("deflauFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 20));
        EventQueue.invokeLater(() -> {
            login.setVisible(true);
        });

    }
    public void loginSuccessful() {
        dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
