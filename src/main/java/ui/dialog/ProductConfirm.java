package ui.dialog;


import model.*;
import service.ProductService;
import service.ServerService;
import staticProcess.StaticProcess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

public class ProductConfirm extends SweetAlert {
    public static Product product;
    public static DecimalFormat df = new DecimalFormat("#,##0.00 VND");
    public static NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());
    public boolean flag = false;
    ServerService serverService = (ServerService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/serverService");
    ProductService productService = (ProductService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/productService");

    public ProductConfirm(java.awt.Frame parent, Product product, boolean modal) throws MalformedURLException, NotBoundException, RemoteException {
        super(parent, modal);
        this.product = product;
        initComponents();

        setInformationProduct();
        updateComboboxUnit();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        lblProductID = new JLabel();
        lblProductName = new JLabel();
        lblUnit = new JLabel();
        lblQuantity = new JLabel();
        lblSellPprice = new JLabel();
        lblLineTotal = new JLabel();
        lblInStock = new JLabel();
        txtProductID = new ui.textfield.TextField();
        txtProductName = new ui.textfield.TextField();
        txtInStock = new ui.textfield.TextField();
        txtQuantity = new ui.textfield.TextField();
        txtSellPrice = new ui.textfield.TextField();
        txtLineTotal = new ui.textfield.TextField();
        lblTitle = new JLabel();
        cbbUnit = new ui.combo_suggestion.ComboBoxSuggestion();
        btnConfirm = new ui.button.Button();
        btnCancel = new ui.button.Button();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        lblProductID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblProductID.setText("Mã sản phẩm:");

        lblProductName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblProductName.setText("Tên sản phẩm:");

        lblUnit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblUnit.setText("Đơn vị:");

        lblQuantity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblQuantity.setText("Số lượng:");

        lblSellPprice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblSellPprice.setText("Giá bán:");

        lblLineTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblLineTotal.setText("Thành tiền:");

        lblInStock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblInStock.setText("Tồn kho:");

        txtProductID.setEnabled(false);
        txtProductID.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtProductName.setEnabled(false);
        txtProductName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtInStock.setEnabled(false);
        txtInStock.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtQuantity.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtQuantity.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String quantity = txtQuantity.getText().trim();
                PackagingUnit unitEnum = PackagingUnit.convertToEnum(cbbUnit.getSelectedItem().toString());
                int inStock = 0;
                try {
                    inStock = productService.findById(product.getProductID()).getInstockQuantity(unitEnum);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

                if(!quantity.isEmpty()){
                    if(Integer.parseInt(quantity) > inStock) {
                        txtQuantity.setText("");
                        txtLineTotal.setText("");
                        new Message(StaticProcess.homePage, true, "Thông báo", "Số lượng bán không lớn hơn tồn kho", "src/main/java/ui/dialog/warning.png").showAlert();
                    } else {

                        double sellPrice = 0;
                        try {
                            sellPrice = nf.parse(txtSellPrice.getText().trim()).doubleValue();
                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        txtLineTotal.setText(df.format(Double.parseDouble(txtQuantity.getText().trim()) * sellPrice));
                    }
                } else {
                    txtLineTotal.setText("");
                }

            }
        });

        txtSellPrice.setEnabled(false);
        txtSellPrice.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        txtLineTotal.setEnabled(false);
        txtLineTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        lblTitle.setBackground(new java.awt.Color(102, 204, 255));
        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(102, 204, 255));
        lblTitle.setText("THÔNG TIN CHI TIẾT SẢN PHẨM");

        cbbUnit.setModel(new DefaultComboBoxModel(new String[] { "Viên", "Vỉ", "Chai", "Lọ", "Tuýp", "Túi", "Ống", "Chai xịt", "Lọ xịt", "Bộ kit", "Thùng", "Hộp" }));
        cbbUnit.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbbUnit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cbbUnitActionPerformed(e);
            }
        });

        btnConfirm.setBackground(new java.awt.Color(102, 204, 255));
        btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirm.setText("Xác nhận");
        btnConfirm.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnConfirm.setRound(15);
        btnConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(255, 101, 101));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Hủy bỏ");
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCancel.setRound(15);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtProductID, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitle)
                    .addComponent(txtProductName, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbUnit, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInStock, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSellPrice, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLineTotal, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                .addGap(0, 188, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblProductID)
                    .addComponent(lblInStock)
                    .addComponent(lblLineTotal)
                    .addComponent(lblSellPprice)
                    .addComponent(lblQuantity)
                    .addComponent(lblUnit)
                    .addComponent(lblProductName))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductID)
                    .addComponent(txtProductID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProductName)
                    .addComponent(txtProductName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUnit)
                    .addComponent(cbbUnit, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInStock)
                    .addComponent(txtInStock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(txtQuantity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSellPprice)
                    .addComponent(txtSellPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLineTotal)
                    .addComponent(txtLineTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirm, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbUnitActionPerformed(ActionEvent e) {
        if(cbbUnit.getSelectedItem() != null) {
            try {
                String description = String.valueOf(cbbUnit.getSelectedItem());

                PackagingUnit unit = PackagingUnit.convertToEnum(description);

                txtInStock.setText(String.valueOf(product.getInstockQuantity(unit)));
                txtQuantity.setText(String.valueOf(1));
                txtSellPrice.setText(df.format(product.getSellPrice(unit)));

                double sellPrice = nf.parse(txtSellPrice.getText().trim()).doubleValue();

                txtLineTotal.setText(df.format(Double.parseDouble(txtQuantity.getText().trim()) * sellPrice));
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void btnConfirmActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        if(cbbUnit.getSelectedItem() != null){
            if(!txtQuantity.getText().isEmpty() && (Integer.parseInt(txtQuantity.getText().trim()) > 0) && (Integer.parseInt(txtInStock.getText().trim())) > 0) {
                flag = true;
                closeAlert();
            } else if(!((Integer.parseInt(txtInStock.getText().trim())) > 0)) {
                new Message(StaticProcess.homePage, true, "Thông báo", "Số lượng tồn kho không đủ!", "src/main/java/ui/dialog/warning.png").showAlert();
            } else if(!(Integer.parseInt(txtQuantity.getText().trim()) > 0)) {
                new Message(StaticProcess.homePage, true, "Thông báo", "Số lượng bán phải lớn hơn 0!", "src/main/java/ui/dialog/warning.png").showAlert();
            }
        } else {
            new Message(StaticProcess.homePage, true, "Thông báo", "Chưa chọn đơn vị cho sản phẩm!", "src/main/java/ui/dialog/warning.png").showAlert();
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnCancelActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        flag = false;
        closeAlert();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductConfirm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProductConfirm dialog = null;
                try {
                    dialog = new ProductConfirm(new JFrame(), product, true);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (NotBoundException e) {
                    throw new RuntimeException(e);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void setInformationProduct(){
        txtProductID.setText(product.getProductID());
        txtProductName.setText(product.getProductName());
    }

    public OrderDetail getOrderDetails(Order or, String orderIDNew) throws RemoteException {
        Order order = new Order(orderIDNew,
                LocalDateTime.now(), or.getShipToAddress(),
                or.getPaymentMethod(), or.getDiscount(), StaticProcess.empLogin,
                or.getCustomer(), or.getPrescription());
        return new OrderDetail(order, productService.findById(txtProductID.getText()), PackagingUnit.convertToEnum(cbbUnit.getSelectedItem().toString()), Integer.parseInt(txtQuantity.getText().trim()));
    }

    public void updateComboboxUnit(){
        ArrayList<PackagingUnit> unitList = new ArrayList<>(product.getUnitDetails().keySet());
        String[] items = new String[unitList.size() + 1];
        int i = 0;

        for(PackagingUnit u : unitList) {
            i++;
            items[i] = u.convertUnit(u);
        }
        cbbUnit.setModel(new DefaultComboBoxModel<String>(items));
    }

    public String getProductID(){
        return txtProductID.getText().trim();
    }

    public int getQuantity(){
        return Integer.parseInt(txtQuantity.getText().trim());
    }

    public double getSellPrice(){
        try {
            return nf.parse(txtSellPrice.getText().trim()).doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public PackagingUnit getEnumUnit(){
        return PackagingUnit.convertToEnum(cbbUnit.getSelectedItem().toString());
    }

    public void setSelectedComboboxUnit(String desUnit, int qty) {
        cbbUnit.setSelectedItem(desUnit);
        txtQuantity.setText(String.valueOf(qty));
        try {
            txtLineTotal.setText(df.format(qty * nf.parse(txtSellPrice.getText().trim()).doubleValue()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ui.button.Button btnCancel;
    private ui.button.Button btnConfirm;
    private ui.combo_suggestion.ComboBoxSuggestion cbbUnit;
    private JLabel lblInStock;
    private JLabel lblLineTotal;
    private JLabel lblProductID;
    private JLabel lblProductName;
    private JLabel lblQuantity;
    private JLabel lblSellPprice;
    private JLabel lblTitle;
    private JLabel lblUnit;
    private ui.textfield.TextField txtInStock;
    private ui.textfield.TextField txtLineTotal;
    private ui.textfield.TextField txtProductID;
    private ui.textfield.TextField txtProductName;
    private ui.textfield.TextField txtQuantity;
    private ui.textfield.TextField txtSellPrice;
    // End of variables declaration//GEN-END:variables
}
