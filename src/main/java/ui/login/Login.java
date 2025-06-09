package ui.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;

import model.Employee;
import model.Manager;
import service.AccountService;
import service.EmployeeService;
import service.ManagerService;
import staticProcess.StaticProcess;
import net.miginfocom.swing.MigLayout;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class Login extends JPanel implements ActionListener, KeyListener {
    static EmployeeService employeeService;
    static ManagerService managerService;

    static {
        try {
            employeeService = (EmployeeService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/employeeService");
            managerService = (ManagerService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/managerService");
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    AccountService accountService = (AccountService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/accountService");
    Font f = new Font("Times New Romam", Font.PLAIN, 20);
    Font f1 = new Font("Times New Romam", Font.ITALIC, 15);
    public JButton btnLogin;
    public static JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblErrorUser;
    private JLabel lblErrorPass;
    private String tenDN;
    private JCheckBox checkBoxForgotPW;
    private List<ModelLocation> locations;
    private ForgotPassword panelForgot = new ForgotPassword();
    private static String currentAccount ;

    public Login() throws MalformedURLException, NotBoundException, RemoteException {
        init();

        txtUsername.addKeyListener(this);
        txtPassword.addKeyListener(this);
        btnLogin.addKeyListener(this);
        btnLogin.addActionListener(this);
        checkBoxForgotPW.addActionListener(this);
    }


    private void init() {
        setOpaque(false);
        addMouseListener(new MouseAdapter() {

        });
        setLayout(new MigLayout("wrap,fillx,insets 90 80 90 80", "[fill]"));

        JLabel title = new JLabel("Login to your account", SwingConstants.CENTER);

        lblErrorUser = new JLabel("", SwingConstants.LEFT);
        lblErrorUser.setFont(f1);
        lblErrorUser.setForeground(Color.red);
        lblErrorPass = new JLabel("", SwingConstants.LEFT);
        lblErrorPass.setFont(f1);
        lblErrorPass.setForeground(Color.red);
        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        btnLogin = new JButton("Login");
        btnLogin.setFont(f);
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +30");
        txtUsername.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:15,15,15,15;" +
                "focusWidth:1;" +
                "innerFocusWidth:0");
        txtUsername.setFont(new Font("Arial (Body)", Font.PLAIN, 15));

        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:15,15,15,15;" +
                "focusWidth:1;" +
                "innerFocusWidth:0;" +
                "showRevealButton:true");
        txtPassword.setFont(new Font("Arial (Body)", Font.PLAIN, 15));

        btnLogin.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:10,15,10,15;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");
        btnLogin.setBackground(new Color(5, 149, 243));
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");

        checkBoxForgotPW = new JCheckBox("Forgot password");
        checkBoxForgotPW.setFont(new Font("Arial (Body)", Font.PLAIN, 15));

        add(title);
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(f);
        add(lblUsername, "gapy 20");
        add(txtUsername, "gapy 10");
        add(lblErrorUser, "gapy 15");

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(f);
        add(lblPassword, "gapy 20");
        add(txtPassword, "gapy 10");
        add(lblErrorPass, "gapy 15");
        add(checkBoxForgotPW, "gapy 15");
        add(btnLogin, "gapy 20");

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int arc = UIScale.scale(50);
        g2.setColor(getBackground());
        g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc));
        g2.dispose();
        super.paintComponent(g);
    }

    public JTextField getTxtUsername() {
        return txtUsername;
    }

    /**
     * xử lý checkbox forgot password
     */
    private void handleForgotPasswordCheckbox() throws MalformedURLException, NotBoundException, RemoteException {
        if (checkBoxForgotPW.isSelected()) {
            // Tạo và hiển thị dialog cho quên mật khẩu
            panelForgot = new ForgotPassword();
            JDialog dialog = new JDialog((Frame) null, "Forgot Password", true);

            panelForgot.setPreferredSize(new Dimension(700, 550));
            // Bỏ trang trí cho dialog trước khi thiết lập màu nền
            dialog.setUndecorated(true); // Bỏ trang trí
            dialog.setBackground(new Color(0, 0, 0, 0)); // Màu trong suốt


            // Đặt màu nền cho panelForgot
            panelForgot.setBackground(new Color(32, 32, 32, 240)); // Màu trắng với độ trong suốt

            // Tạo nút "Đóng"
            JButton btnClose = new JButton("Hủy");
            btnClose.setFont(new Font("Times New Romam", Font.PLAIN, 25));
            btnClose.setBackground(new Color(129, 129, 129));
            btnClose.setForeground(Color.white);
            btnClose.addActionListener((ActionEvent e) -> {
                dialog.dispose(); // Đóng dialog
                checkBoxForgotPW.setSelected(false); // Bỏ chọn checkbox
            });

            panelForgot.add(btnClose);

            dialog.getContentPane().add(panelForgot);
            dialog.pack();
            dialog.setLocationRelativeTo(null);


            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    checkBoxForgotPW.setSelected(false);
                }
            });

            dialog.setVisible(true);
        } else {

            if (panelForgot != null) {
                remove(panelForgot);
                panelForgot = null;
                revalidate();
                repaint();
            }
            checkBoxForgotPW.setSelected(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLogin)) {
            boolean kqCheck = false;
            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();

            if (username.isEmpty() && password.isEmpty()) {
                txtUsername.requestFocus();
                JOptionPane.showMessageDialog(this, "Vui lòng điền thông tin");
                lblErrorUser.setText("Vui lòng nhập tên tài khoản");

            } else {
                try {
                    if(accountService.containUserName(username) == null){
                        JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại");

                    }else{
                        tenDN = accountService.containUserName(username);

                    }

                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                ArrayList<String> duLieu = null;
                try {
                    duLieu = (ArrayList<String>) accountService.login(username, password);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                if (duLieu.isEmpty()) {
                    if (tenDN != null) {
                        if (tenDN.equals(txtUsername.getText()) == true) {
                            txtPassword.requestFocus();
                            lblErrorPass.setText("Mật khẩu không đúng");
                        }
                    }
//                    else {
//                        JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại");
//                    }
                }
                else {
                    if (!(duLieu.get(0) == null)) {

                        if (txtUsername.getText().equals(duLieu.get(0))) {
                            if (txtPassword.getText().equals(duLieu.get(1))) {
                                kqCheck = true;
                            } else {
                                kqCheck = false;
                                txtPassword.requestFocus();
                                lblErrorUser.setText("");
                                lblErrorPass.setText("Mật khẩu không đúng");

                            }
                        } else {
                            kqCheck = false;
                            JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại");
                        }

                        try {
                            if(!accountService.isAccountLoggedIn(tenDN)){
                                if (kqCheck) {
                                    currentAccount = txtUsername.getText().trim();
                                    lblErrorUser.setText("");
                                    lblErrorPass.setText("");
                                    StaticProcess.userlogin = txtUsername.getText();
                                    try {
                                        StaticProcess.empLogin = employeeService.findById(txtUsername.getText());
                                    } catch (RemoteException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                    accountService.loginCheck(tenDN, password);
                                    StaticProcess.loginSuccess = true;
                                    closeLoginWindow();

                                }

                            }else {
                                JOptionPane.showMessageDialog(this, "Tài khoản đã đăng nhập trên thiết bị khác");
                            }
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }

                    } else {
                        JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại");
                    }
                }



            }
        } else if (o.equals(checkBoxForgotPW)) {
            if (checkBoxForgotPW.isSelected()) {
                try {
                    handleForgotPasswordCheckbox();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.getSource() == txtUsername) {
                txtPassword.requestFocus();
            } else if (e.getSource() == txtPassword) {
                btnLogin.doClick();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void closeLoginWindow() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }

    public static Employee getEmployeeLogin() throws RemoteException {
        return employeeService.findById(currentAccount);
    }

    public static Manager getManagerLogin() throws RemoteException {
        return managerService.findById(currentAccount);
    }

    public static boolean checkRole() throws RemoteException {
        if (employeeService.findById(currentAccount) != null){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        AccountService accountService = (AccountService) Naming.lookup("rmi://localhost:7281/accountService");
        System.out.println(accountService.containUserName("EP0302"));
        employeeService = (EmployeeService) Naming.lookup("rmi://localhost:7281/employeeService");
        System.out.println(employeeService.findById("EP0302"));
    }
}
