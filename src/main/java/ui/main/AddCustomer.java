package ui.main;

import static staticProcess.StaticProcess.*;
import model.Customer;

import service.CustomerService;
import staticProcess.StaticProcess;
import ui.dialog.Message;
import ui.table.TableCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
@SuppressWarnings("all")

public class AddCustomer extends JPanel implements ActionListener, MouseListener {
    CustomerService customerService = (CustomerService) Naming.lookup("rmi://" + properties.get("ServerName") + ":" + properties.get("Port") + "/customerService");
    public AddCustomer() throws MalformedURLException, NotBoundException, RemoteException {
        initComponents();
        customer =new Customer();
        setupTable();
//        testData(tableCustomer);
        TableCustom.apply(scrollPane_tableCustomer, TableCustom.TableType.MULTI_LINE);
//        pCenter.requestFocusInWindow();
        btnAdd.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnUpdate.addActionListener(this);
        tableCustomer.addMouseListener(this);
        buttonGroup.clearSelection();

        loadTable();
    }

    private void setupTable() {
        JTableHeader theader = tableCustomer.getTableHeader();
        theader.setFont(new java.awt.Font("Segoe UI", 0, 18));
        tableCustomer.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        if (tableCustomer.getColumnModel().getColumnCount() > 0) {
            tableCustomer.getColumnModel().getColumn(0).setPreferredWidth(150);
            tableCustomer.getColumnModel().getColumn(1).setPreferredWidth(200);
            tableCustomer.getColumnModel().getColumn(2).setPreferredWidth(130);
            tableCustomer.getColumnModel().getColumn(3).setPreferredWidth(90);
            tableCustomer.getColumnModel().getColumn(4).setPreferredWidth(160);
            tableCustomer.getColumnModel().getColumn(5).setPreferredWidth(314);
            tableCustomer.getColumnModel().getColumn(6).setPreferredWidth(70);
            tableCustomer.getColumnModel().getColumn(7).setPreferredWidth(344);
        }
    }



    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new ButtonGroup();
        dateChooser1 = new ui.datechooser.DateChooser();
        pCenter = new JPanel();
        scrollPane_tableCustomer = new JScrollPane();
        tableCustomer = new JTable();
        lbTitlePane = new JLabel();
        lbTitleTable = new JLabel();
        lbCustName = new JLabel();
        lbPhoneNumber = new JLabel();
        jLabel5 = new JLabel();
        lbBirthDate = new JLabel();
        jLabel7 = new JLabel();
        lbAddress = new JLabel();
        radioButtonCustom1 = new ui.radio_button.RadioButtonCustom();
        radioButtonCustom2 = new ui.radio_button.RadioButtonCustom();
        btnAdd = new ui.button.Button();
        btnUpdate = new ui.button.Button();
        btnRefresh = new ui.button.Button();
        txtCustName = new ui.textfield.TextField();
        txtPhoneNumber = new ui.textfield.TextField();
        txtEmail = new ui.textfield.TextField();
        txtAddress = new ui.textfield.TextField();
        txtBirthDate = new ui.textfield.TextField();

        dateChooser1.setForeground(new java.awt.Color(51, 204, 255));
        dateChooser1.setTextRefernce(txtBirthDate);

        setPreferredSize(new java.awt.Dimension(1620, 1000));
        setLayout(new java.awt.BorderLayout());

        pCenter.setBackground(new java.awt.Color(242, 249, 255));
        pCenter.setPreferredSize(new java.awt.Dimension(1600, 1000));

        scrollPane_tableCustomer.setBackground(new java.awt.Color(221, 221, 221));
        scrollPane_tableCustomer.setBorder(null);

        tableCustomer.setBackground(new java.awt.Color(242, 249, 255));
        tableCustomer.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tableCustomer.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Giới tính", "Ngày sinh", "Email", "ĐTL", "Địa chỉ"
                }
        ) {
            final boolean[] canEdit = new boolean [] {
                    true, true, true, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCustomer.setGridColor(new java.awt.Color(218, 247, 249));
        scrollPane_tableCustomer.setViewportView(tableCustomer);

        lbTitlePane.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbTitlePane.setForeground(new java.awt.Color(102, 204, 255));
        lbTitlePane.setText("THÔNG TIN KHÁCH HÀNG");

        lbTitleTable.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTitleTable.setForeground(new java.awt.Color(102, 204, 255));
        lbTitleTable.setText("DANH SÁCH KHÁCH HÀNG");

        lbCustName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbCustName.setText("Tên khách hàng");

        lbPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbPhoneNumber.setText("Số điện thoại ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Giới tính :");

        lbBirthDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbBirthDate.setText("Ngày sinh");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Địa chỉ mail :");

        lbAddress.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbAddress.setText("Địa chi thường trú");

        radioButtonCustom1.setBackground(new java.awt.Color(102, 204, 255));
        buttonGroup.add(radioButtonCustom1);
        radioButtonCustom1.setText("Nam");
        radioButtonCustom1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        radioButtonCustom1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                radioButtonCustom1ActionPerformed(evt);
            }
        });

        radioButtonCustom2.setBackground(new java.awt.Color(102, 204, 255));
        buttonGroup.add(radioButtonCustom2);
        radioButtonCustom2.setText("Nữ");
        radioButtonCustom2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnAdd.setBackground(new java.awt.Color(102, 204, 255));
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm");
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(102, 204, 255));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Cập nhật");
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUpdate.setPreferredSize(new java.awt.Dimension(72, 50));
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(102, 204, 255));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Làm mới");
        btnRefresh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N


        txtCustName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N


        txtPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N


        txtEmail.setText("");
        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N


        txtAddress.setText("");
        txtAddress.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N


        txtBirthDate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        GroupLayout pCenterLayout = new GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
                pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pCenterLayout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPane_tableCustomer, GroupLayout.PREFERRED_SIZE, 1458, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbCustName)
                                                        .addComponent(lbPhoneNumber)
                                                        .addComponent(lbBirthDate)
                                                        .addComponent(lbAddress))
                                                .addGap(33, 33, 33)
                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtCustName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(txtAddress, GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                        .addComponent(txtBirthDate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(txtPhoneNumber, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE))
                                                                .addGap(51, 51, 51)
                                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel7)
                                                                        .addComponent(jLabel5))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                                                .addComponent(radioButtonCustom1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(radioButtonCustom2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addGap(63, 63, 63)
                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, pCenterLayout.createSequentialGroup()
                                .addContainerGap(607, Short.MAX_VALUE)
                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, pCenterLayout.createSequentialGroup()
                                                .addComponent(lbTitlePane, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE)
                                                .addGap(535, 535, 535))
                                        .addGroup(GroupLayout.Alignment.TRAILING, pCenterLayout.createSequentialGroup()
                                                .addComponent(lbTitleTable)
                                                .addGap(632, 632, 632))))
        );
        pCenterLayout.setVerticalGroup(
                pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pCenterLayout.createSequentialGroup()
                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(lbTitlePane, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbCustName)
                                                        .addComponent(txtCustName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(22, 22, 22)
                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbPhoneNumber)
                                                        .addComponent(txtPhoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(radioButtonCustom1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(radioButtonCustom2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(25, 25, 25)
                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbBirthDate)
                                                        .addComponent(txtBirthDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel7)
                                                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(27, 27, 27)
                                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lbAddress)
                                                        .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
                                                .addGap(17, 17, 17)
                                                .addComponent(lbTitleTable)
                                                .addGap(39, 39, 39))
                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                .addGap(180, 180, 180)
                                                .addComponent(btnRefresh, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(52, 52, 52)
                                                .addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(scrollPane_tableCustomer, GroupLayout.PREFERRED_SIZE, 417, GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37))
        );

        add(pCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void radioButtonCustom1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_radioButtonCustom1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonCustom1ActionPerformed

    private void btnUpdateActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ui.button.Button btnAdd;
    private ui.button.Button btnRefresh;
    private ui.button.Button btnUpdate;
    private ButtonGroup buttonGroup;
    private ui.datechooser.DateChooser dateChooser1;
    private JLabel jLabel5;
    private JLabel jLabel7;
    private JLabel lbAddress;
    private JLabel lbBirthDate;
    private JLabel lbCustName;
    private JLabel lbPhoneNumber;
    private JLabel lbTitlePane;
    private JLabel lbTitleTable;
    private JPanel pCenter;
    private ui.radio_button.RadioButtonCustom radioButtonCustom1;
    private ui.radio_button.RadioButtonCustom radioButtonCustom2;
    private JScrollPane scrollPane_tableCustomer;
    private JTable tableCustomer;
    private ui.textfield.TextField txtEmail;
    private ui.textfield.TextField txtAddress;
    private ui.textfield.TextField txtBirthDate;
    private ui.textfield.TextField txtCustName;
    private ui.textfield.TextField txtPhoneNumber;
    private final Customer customer;
    private DefaultTableModel model;


    private boolean checkData() {
        if (txtCustName.getText().isEmpty() || txtPhoneNumber.getText().isEmpty() ||
                (!radioButtonCustom1.isSelected() && !radioButtonCustom2.isSelected())) {
            new Message(StaticProcess.homePage, true, "Thông báo", "Vui lòng nhập đầy đủ thông tin khách hàng !!!", "src/main/java/ui/dialog/warning.png").showAlert();

            return false;
        }

        // Kiểm tra tên khách hàng
        if (txtCustName.getText().isEmpty()) {
            new Message(StaticProcess.homePage, true, "Thông báo", "Vui lòng nhập tên khách hàng !!!", "src/main/java/ui/dialog/warning.png").showAlert();

            return false;
        }

        // Kiểm tra giới tính
        if (!radioButtonCustom1.isSelected() && !radioButtonCustom2.isSelected()) {
            new Message(StaticProcess.homePage, true, "Thông báo", "Vui lòng chọn giới tính khách hàng !!!", "src/main/java/ui/dialog/warning.png").showAlert();

            return false;
        }


        // Kiểm tra tuổi từ ngày sinh
        if (!txtBirthDate.getText().isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate birthDate = LocalDate.parse(txtBirthDate.getText().trim(), formatter);
                int age = Period.between(birthDate, LocalDate.now()).getYears();
                if (age < 18) {
                    new Message(StaticProcess.homePage, true, "Thông báo", "Khách hàng phải đủ 18 tuổi !!!", "src/main/java/ui/dialog/warning.png").showAlert();

                    return false;
                }
            } catch (Exception e) {
                new Message(StaticProcess.homePage, true, "Thông báo", "Ngày sinh không hợp lệ !!!", "src/main/java/ui/dialog/warning.png").showAlert();

                return false;
            }
        } else {
            new Message(StaticProcess.homePage, true, "Thông báo", "Vui lòng nhập ngày sinh khách hàng !!!", "src/main/java/ui/dialog/warning.png").showAlert();

            return false;
        }

        return true;
    }

    private void loadTable() throws RemoteException {
        ArrayList<Customer> customerList = (ArrayList<Customer>) customerService.getAll();
        DefaultTableModel model = (DefaultTableModel) tableCustomer.getModel();
        for (Customer customer : customerList) {
            model.addRow(new Object[]{
                    customer.getCustomerID(),
                    customer.getCustomerName(),
                    customer.getPhoneNumber(),
                    customer.isGender()?"Nữ":"Nam",
                    customer.getBrithDate(),
                    customer.getEmail(),
                    customer.getPoint(),
                    customer.getAddr()
            });
        }
    }

    private void clearData(){
        txtCustName.setText("");
        txtPhoneNumber.setText("");
        txtBirthDate.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        buttonGroup.clearSelection();
        tableCustomer.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        model = (DefaultTableModel) tableCustomer.getModel();
        if(obj.equals(btnAdd)){
            if(checkData()){
                // Kiểm tra số điện thoại
                if (!txtPhoneNumber.getText().isEmpty()) {
                    String sdt = txtPhoneNumber.getText().trim();
                    try {
                        if (customerService.checkPhoneNumber(sdt)) {
                            new Message(StaticProcess.homePage, true, "Thông báo", "Số điện thoại đã được sử dụng!!!", "src/main/java/ui/dialog/warning.png").showAlert();

                            txtPhoneNumber.requestFocus();
                            return;
                        }
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                String name = txtCustName.getText().trim();
                String phone = txtPhoneNumber.getText().trim();
                boolean sex = radioButtonCustom1.isSelected();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(txtBirthDate.getText().trim(),formatter);
                String mail = txtEmail.getText().trim();
                String add = txtAddress.getText().trim();
                String id = null;
                try {
                    id = customerService.createCustomerID();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                Customer c = new Customer();
                c.setCustomerID(id);
                c.setPhoneNumber(phone);
                c.setCustomerName(name);
                c.setEmail(mail);
                c.setGender(sex);
                c.setAddr(add);
                c.setBrithDate(date);
                try {
                    if(customerService.create(c)){
                            Object[] row = {id, name, phone, sex?"Nam":"Nữ", date, mail,customer.getPoint(), add};
                        model.addRow(row);
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } else if (obj.equals(btnRefresh)) {
            clearData();
        } else if (obj.equals(btnUpdate)) {
            int row = tableCustomer.getSelectedRow();
            String id = null;
            if (row >= 0) {
                if (checkData()) {
                    String name = txtCustName.getText().trim();
                    String phone = txtPhoneNumber.getText().trim();
                    boolean sex = radioButtonCustom1.isSelected();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate date = LocalDate.parse(txtBirthDate.getText().trim(),formatter);
                    String mail = txtEmail.getText().trim();
                    String add = txtAddress.getText().trim();
                    id = tableCustomer.getValueAt(row, 0).toString();

                    Customer c = new Customer();
                    c.setCustomerID(id);
                    c.setPhoneNumber(phone);
                    c.setCustomerName(name);
                    c.setEmail(mail);
                    c.setGender(false? true: false);
                    c.setAddr(add);
                    c.setBrithDate(date);
                    try {
                        if (customerService.update(c)) {
                            new Message(StaticProcess.homePage, true, "Thông báo", "Cập nhật khách hàng thành công !!!", "src/main/java/ui/dialog/checked.png").showAlert();

                            model.setValueAt(c.getCustomerName(),row, 1);
                            model.setValueAt(c.getPhoneNumber(),row, 2);
                            model.setValueAt(c.isGender()?"Nữ":"Nam",row, 3);
                            model.setValueAt(c.getBrithDate(),row, 4 );
                            model.setValueAt(c.getEmail(),row, 5);
                            model.setValueAt(c.getAddr(),row, 7);
                            clearData();
                        }
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            } else {
                new Message(StaticProcess.homePage, true, "Thông báo", "Vui lòng chọn khách hàng cần cập nhật", "src/main/java/ui/dialog/warning.png").showAlert();

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int row = tableCustomer.getSelectedRow();
        if (row >= 0) { // Ensure a row is selected
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            txtCustName.setText(tableCustomer.getValueAt(row, 1).toString());
            txtPhoneNumber.setText(tableCustomer.getValueAt(row, 2).toString());
            String gender = tableCustomer.getValueAt(row, 3).toString();
            if (gender.equals("Nam")) {
                radioButtonCustom1.setSelected(true);
            } else {
                radioButtonCustom2.setSelected(true);
            }

            String birthDateString = tableCustomer.getValueAt(row, 4).toString();
            LocalDate birthDate = LocalDate.parse(birthDateString,inputFormatter);
            txtBirthDate.setText(birthDate.format(outputFormatter));

            txtEmail.setText(tableCustomer.getValueAt(row, 5).toString());
            txtAddress.setText(tableCustomer.getValueAt(row, 7).toString());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    // End of variables declaration//GEN-END:variables
}
