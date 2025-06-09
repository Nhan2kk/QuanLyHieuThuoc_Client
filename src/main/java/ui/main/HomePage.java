package ui.main;

import model.Employee;
import model.Manager;
import service.*;
import staticProcess.StaticProcess;
import ui.dialog.Confirm;
import ui.forms.TempOrderForm;
import ui.glasspanepupup.CreateOrderWithPres;
import ui.glasspanepupup.GlassPanePopup;
import ui.glasspanepupup.Message;
import ui.glasspanepupup.Notification;
import ui.menu.MenuEvent;
import ui.scroll.win11.ScrollBarWin11UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static staticProcess.StaticProcess.homePage;
import static staticProcess.StaticProcess.userlogin;
import static staticProcess.StaticProcess.userlogin;
@SuppressWarnings("all")

public class HomePage extends JFrame implements ActionListener{
    ServerService serverService = (ServerService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/serverService");
    EmployeeService employeeService = (EmployeeService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/employeeService");
    ManagerService managerService = (ManagerService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/managerService");
    AccountService accountService = (AccountService) Naming.lookup("rmi://"+ StaticProcess.properties.get("ServerName") +":" + StaticProcess.properties.get("Port") + "/accountService");
    ProductService productService = (ProductService) Naming.lookup("rmi://"+ StaticProcess.properties.get("ServerName") +":" + StaticProcess.properties.get("Port") + "/productService");
    private JPanel currentPanel;

    private final HomeSlide homeSlide = new HomeSlide();
    private final CreateOrder createOrder = new CreateOrder(this);
    private final OrderHistory orderHistory = new OrderHistory(this);
    private final RevenueStatistic revenueStatistic = new RevenueStatistic();
    private CategorySearch category = new CategorySearch(this);
    private final AddProduct addProduct = new AddProduct();
    private final UpdateProduct updateProduct = new UpdateProduct();
    private final ProductStatistics productStatistics = new ProductStatistics(this);
    private final AddCustomer addCustomer = new AddCustomer();
    private final CustomerSearch customerSearch = new CustomerSearch();
    private final VendorSearch vendorSearch = new VendorSearch();
    private final AddVendor addVendor = new AddVendor();
    private final EmployeeSearch employeeSearch = new EmployeeSearch();
    private final AddEmployee addEmployee = new AddEmployee();
    private final PromotionSearch promotionSearch = new PromotionSearch();
    private final AddPromotion addPromotion = new AddPromotion();
    private final TodayRevenueStatistic todayRevenueStatistic = new TodayRevenueStatistic();
    private final ProcessOrder processOrder = new ProcessOrder();
    private static String accLoginID;

    public HomePage() throws Exception {
        initComponents();
        setFullScreen();
        updateDateLable();
        initHomeSlide();
        GlassPanePopup.install(this);
        Notification notification;
        GlassPanePopup.install(this);
        menu.setEvent(new MenuEvent() {
            @Override
            public void selected(int index, int subIndex) throws Exception {
                openFrame(index, subIndex);
            }
        });

        btnGuide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    URI uri = new URI("https://pphiep.github.io/HTCNWeb/User_manual/HTML/User_manual.html");
                    Desktop.getDesktop().browse(uri);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        pCenter.requestFocusInWindow();
        btnAvatar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Message message = new Message();

                if(userlogin.startsWith("MN")){
                    Manager emp = null;
                    try {
                        emp = managerService.findById(userlogin);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                    message.lblEmpID_show.setText(emp.getManagerID());
                    message.lblEmpName_show.setText(emp.getManagerName());
                    message.lblPhoneNumber_show.setText(emp.getPhoneNumber());
                    message.lblDOB_show.setText(emp.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    message.lblGender_show.setText((""));
                    message.lblDegree_show.setText("");
                    message.lblEmail_show.setText("");
                }else {
                    Employee emp = null;
                    try {
                        emp = employeeService.getListEmployeeByAccountID(userlogin);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                    message.lblEmpID_show.setText(emp.getEmployeeID());
                    message.lblEmpName_show.setText(emp.getEmployeeName());
                    message.lblPhoneNumber_show.setText(emp.getPhoneNumber());
                    message.lblDOB_show.setText(emp.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    message.lblGender_show.setText((emp.isGender()) ? "Nữ" : "Nam");
                    message.lblDegree_show.setText(emp.getDegree());
                    message.lblEmail_show.setText(emp.getEmail());
                }
                GlassPanePopup.showPopup(message);
            }
        });
        btnNotification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notification notification = new Notification();
                GlassPanePopup.showPopup(notification);
                notification.myList1.addItem("Đăng nhập thành công tài khoản " + userlogin);
                try {
                    if(!productService.getLowStockProducts(25).isEmpty()){
                        notification.myList1.addItem("Có sản phẩm tồn kho dưới 25%!");
                    }
                    if (!productService.getProductListNearExpire().isEmpty()){
                        notification.myList1.addItem("Có sản phẩm gần hết hạn sử dụng!");
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void setRole(String s){
        accLoginID = s;
    }

    private void openFrame(int index,int subIndex) throws Exception {
        //Trang chủ
        if(index == 0 && subIndex == 0){
            replacePanel(homeSlide);
        }
        //Hóa đơn
        if(index == 1 && subIndex == 1){
            replacePanel(createOrder);
        } else if(index == 1 && subIndex == 2){
            replacePanel(new OrderHistory(this));
        } else if(index == 1 && subIndex == 3){
            replacePanel(processOrder.getPnlProcessPanel());
        } else if(index == 1 && subIndex == 4){
            replacePanel(revenueStatistic);
            revenueStatistic.startAnimation();
        } else if(index == 1 && subIndex == 5){
            replacePanel(new TodayRevenueStatistic());
        }
        //Sản phẩm
        else if(index == 2 && subIndex == 1){
            replacePanel(new CategorySearch(this));
        } else if(index == 2 && subIndex == 2){
            replacePanel(addProduct);
        } else if(index == 2 && subIndex == 3){
            replacePanel(productStatistics);
            productStatistics.startAnimation();
        }
        //Khách hàng
        else if(index == 3 && subIndex == 1){
            replacePanel(customerSearch);
        } else if(index == 3 && subIndex == 2){
            replacePanel(addCustomer);
        }
        //Nhà cung cấp
        else if(index == 4 && subIndex == 1){
            replacePanel(vendorSearch);
        } else if(index == 4 && subIndex == 2){
            replacePanel(addVendor);
        }
        //Nhân viên
        else if(index == 5 && subIndex == 1){
            replacePanel(employeeSearch);
        } else if(index == 5 && subIndex == 2){
            replacePanel(addEmployee);
        }
        //Khuyến mãi
        else if(index == 6 && subIndex == 1){
            replacePanel(promotionSearch);
        } else if(index == 6 && subIndex == 2){
            replacePanel(addPromotion);
        }
    }

    public void initHomeSlide(){
        currentPanel = homeSlide;
        replacePanel(homeSlide);
    }

    public void replacePanel(JPanel panel){
        if (currentPanel != null && currentPanel.getClass().equals(panel.getClass())) {
            return;
        }

        currentPanel = panel;
        pCenter.removeAll();

        pCenter.setLayout(new BorderLayout());
        pCenter.add(panel, BorderLayout.CENTER);

        pCenter.revalidate();
        pCenter.repaint();
    }

    private void setFullScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle bounds = ge.getMaximumWindowBounds();

        this.setBounds(bounds);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void updateDateLable(){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        lbHeaderDate.setText(sdf.format(now));
    }

    public void updateCurretPanel(JPanel panel){
        currentPanel = panel;
    }

    public void showPres(TempOrderForm tempOrderForm) throws MalformedURLException, NotBoundException, RemoteException {
        GlassPanePopup.showPopup(new CreateOrderWithPres(this, tempOrderForm));
    }

    public void closePres(){
        GlassPanePopup.closePopup(0);
    }

    public CreateOrder getCreateOrder(){
        return createOrder;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws RemoteException {

        pNorth = new JPanel();
        lbHeaderDate = new JLabel();
        btnGuide = new ui.button.Button();
        btnNotification = new ui.button.Button();
        btnAvatar = new ui.button.Button();
        pWest = new JPanel();
        lbLogo = new JLabel();
        lblEmplName = new JLabel();
        scrollPaneWin = new ui.scroll.win11.ScrollPaneWin11();
        menu = new ui.menu.Menu();
        btnLogout = new ui.button.Button();
        pCenter = new JPanel();
        homeSlide1 = new HomeSlide();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pNorth.setBackground(new Color(102, 204, 255));
        pNorth.setPreferredSize(new Dimension(756, 75));

        lbHeaderDate.setFont(new Font("Segoe UI", 1, 20)); // NOI18N
        lbHeaderDate.setForeground(new Color(255, 255, 255));

        btnGuide.setBackground(new Color(102, 204, 255));
        btnGuide.setIcon(new ImageIcon("src/main/java/ui/button/information_32.png")); // NOI18N
        btnGuide.setToolTipText("");
        btnGuide.setRound(50);
        btnGuide.setShadowColor(new Color(102, 204, 255));

        btnNotification.setBackground(new Color(102, 204, 255));
        btnNotification.setIcon(new ImageIcon("src/main/java/ui/button/notification_32.png")); // NOI18N
        btnNotification.setRound(50);
        btnNotification.setShadowColor(new Color(102, 204, 255));

        btnAvatar.setBackground(new Color(102, 204, 255));
        btnAvatar.setIcon(new ImageIcon("src/main/java/ui/button/user_32_white.png")); // NOI18N
        btnAvatar.setRound(50);
        btnAvatar.setShadowColor(new Color(102, 204, 255));

        lblEmplName.setBackground(new Color(255, 255, 255));
        lblEmplName.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        lblEmplName.setForeground(new Color(255, 255, 255));
        lblEmplName.setHorizontalAlignment(SwingConstants.RIGHT);
        String userName = userlogin.startsWith("MN")?managerService.findById(userlogin).getManagerName():employeeService.findById(userlogin).getEmployeeName();
        lblEmplName.setText(userName);

        GroupLayout pNorthLayout = new GroupLayout(pNorth);
        pNorth.setLayout(pNorthLayout);
        pNorthLayout.setHorizontalGroup(
                pNorthLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pNorthLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(lbHeaderDate, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblEmplName, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAvatar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNotification, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnGuide, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
        );
        pNorthLayout.setVerticalGroup(
                pNorthLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pNorthLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pNorthLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btnNotification, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnAvatar, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnGuide, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(pNorthLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pNorthLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbHeaderDate, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                        .addComponent(lblEmplName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pWest.setBackground(new Color(102, 204, 255));

        lbLogo.setIcon(new ImageIcon("src/main/java/ui/menu/logoleft.png")); // NOI18N
        lbLogo.setPreferredSize(new Dimension(329, 200));

        scrollPaneWin.setBackground(new Color(102, 204, 255));
        scrollPaneWin.setBorder(null);
        scrollPaneWin.setViewportView(menu);

        btnLogout.setBackground(new Color(102, 204, 255));
        btnLogout.setIcon(new ImageIcon("src/main/java/ui/button/logout.png")); // NOI18N
        btnLogout.setShadowColor(new Color(102, 204, 255));

        GroupLayout pWestLayout = new GroupLayout(pWest);
        pWest.setLayout(pWestLayout);
        pWestLayout.setHorizontalGroup(
            pWestLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneWin, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(lbLogo, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(pWestLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pWestLayout.setVerticalGroup(
            pWestLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pWestLayout.createSequentialGroup()
                .addComponent(lbLogo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(scrollPaneWin, GroupLayout.PREFERRED_SIZE, 780, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pCenter.setBackground(new Color(255, 255, 255));

        GroupLayout pCenterLayout = new GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
            pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pCenterLayout.createSequentialGroup()
                .addComponent(homeSlide1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        pCenterLayout.setVerticalGroup(
            pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pCenterLayout.createSequentialGroup()
                .addComponent(homeSlide1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 225, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pWest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(pNorth, GroupLayout.DEFAULT_SIZE, 1632, Short.MAX_VALUE)
                    .addComponent(pCenter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
            .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pWest, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pNorth, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
        btnLogout.addActionListener(this);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException |
                 InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateOrder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        UIDefaults ui = UIManager.getDefaults();
        ui.put("ScrollBarUI", ScrollBarWin11UI.class.getCanonicalName());

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StaticProcess.homePage = new HomePage();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (NotBoundException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                homePage.setVisible(true);
                StaticProcess.login.setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ui.button.Button btnAvatar;
    private ui.button.Button btnGuide;
    private ui.button.Button btnLogout;
    private ui.button.Button btnNotification;
    private HomeSlide homeSlide1;
    private JLabel lbHeaderDate;
    private JLabel lbLogo;
    private JLabel lblEmplName;
    private ui.menu.Menu menu;
    private JPanel pCenter;
    private JPanel pNorth;
    private JPanel pWest;
    private ui.scroll.win11.ScrollPaneWin11 scrollPaneWin;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if((o.equals(btnLogout))){

            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    Confirm dialog = new Confirm(HomePage.this, true, "Thông báo", "Bạn có chắc chắn muốn đăng xuất?", "src/main/java/ui/dialog/logout.jpg", "Đăng xuất", "Hủy bỏ");
                    dialog.showAlert();

                    int response = dialog.getResponse();
                    if(response == 1) {
                        try {
                            serverService.setAwaiKey(false);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                        StaticProcess.loginSuccess = false;
                        try {
                            serverService.setAwaiKey(false);
                            accountService.logout(userlogin);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            WelcomeMyApp.main(null);
                        } catch (MalformedURLException ex) {
                            throw new RuntimeException(ex);
                        } catch (NotBoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        HomePage.this.dispose();
//                        new ui.dialog.Message(HomePage.this, true, "Thông báo", "Đăng xuất thành công", "src/main/java/ui/dialog/done.png").showAlert();
                    }
                }
            });
        }
        }
}
    // End of variables declaration//GEN-END:variables

