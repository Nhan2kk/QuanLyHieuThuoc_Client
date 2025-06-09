package ui.main;

import model.*;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import service.AdministrationRouteService;
import service.CategoryService;
import service.ProductService;
import service.VendorService;
import staticProcess.StaticProcess;
import ui.dialog.Message;
import ui.table.TableCustom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@SuppressWarnings("all")

public class CategorySearch extends JPanel {
    AdministrationRouteService administrationRouteService = (AdministrationRouteService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/administrationRouteService");
    ProductService productService = (ProductService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/productService");
    VendorService vendorService = (VendorService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/vendorService");
    CategoryService categoryService = (CategoryService) Naming.lookup("rmi://" + StaticProcess.properties.get("ServerName") + ":" + StaticProcess.properties.get("Port") + "/categoryService");

    private HomePage homePage;
    private ArrayList<Product> productsListTemp;

    public CategorySearch(HomePage homePage) throws MalformedURLException, NotBoundException, RemoteException {
        this.homePage = homePage;
        initComponents();
        JTableHeader theader = tableProduct.getTableHeader();
        theader.setFont(new Font("Segoe UI", 0, 18));
        TableCustom.apply(jScrollPane_tableProduct, TableCustom.TableType.MULTI_LINE);
        tableProduct.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setupTable();
        showDataComboBoxVendor();
        showDataComboBoxCategory();
        showDataComboBoxAdmintrationRoute();
    }


    private void setupTable() {
        JTableHeader theader = tableProduct.getTableHeader();
        theader.setFont(new Font("Segoe UI", 0, 18));
        tableProduct.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        if (tableProduct.getColumnModel().getColumnCount() > 0) {
            tableProduct.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableProduct.getColumnModel().getColumn(1).setPreferredWidth(400);
            tableProduct.getColumnModel().getColumn(2).setPreferredWidth(300);
            tableProduct.getColumnModel().getColumn(3).setPreferredWidth(200);
            tableProduct.getColumnModel().getColumn(4).setPreferredWidth(200);
            tableProduct.getColumnModel().getColumn(5).setPreferredWidth(300);
            tableProduct.getColumnModel().getColumn(6).setPreferredWidth(300);
            tableProduct.getColumnModel().getColumn(7).setPreferredWidth(300);
            tableProduct.getColumnModel().getColumn(8).setPreferredWidth(300);
            tableProduct.getColumnModel().getColumn(9).setPreferredWidth(300);
            tableProduct.getColumnModel().getColumn(10).setPreferredWidth(300);
            tableProduct.getColumnModel().getColumn(11).setPreferredWidth(300);
            tableProduct.getColumnModel().getColumn(12).setPreferredWidth(400);
            tableProduct.getColumnModel().getColumn(13).setPreferredWidth(400);
        }
    }

    private void initComponents() {
        date = new ui.datechooser.DateChooser();
        tableCustom = new TableCustom();
        pCenter = new JPanel();
        txtDate = new ui.textfield.TextField();
        btnCalendar = new ui.button.Button();
        btnAdd = new ui.button.Button();
        cbbCategory = new ui.combobox.Combobox();
        cbbVendor = new ui.combobox.Combobox();
        cbbMethod = new ui.combobox.Combobox();
        tableScrollButton_Product = new ui.table.TableScrollButton();
        jScrollPane_tableProduct = new JScrollPane();
        tableProduct = new JTable();
        cbbAdministration = new ui.combobox.Combobox();
        txtSearch = new ui.textfield.TextField();
        btnSearch = new ui.button.Button();
        lbDate = new JLabel();

        date.setForeground(new Color(102, 204, 255));

        date.setTextRefernce(txtDate);


        setPreferredSize(new Dimension(1620, 1000));

        pCenter.setBackground(new Color(242, 249, 255));
        pCenter.setPreferredSize(new Dimension(1600, 1000));


        txtDate.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        txtDate.setMargin(new Insets(3, 6, 3, 6));
        txtDate.setName(""); // NOI18N
        txtDate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    txtDateActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnCalendar.setForeground(new Color(255, 255, 255));
        btnCalendar.setIcon(new ImageIcon("src/main/java/ui/button/calendar.png")); // NOI18N
        btnCalendar.setPreferredSize(new Dimension(64, 64));
        btnCalendar.setRound(20);
        btnCalendar.setShadowColor(new Color(255, 255, 255));
        btnCalendar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnCalendarActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btnAdd.setBackground(new Color(102, 204, 255));
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setText("Thêm sản phẩm");
        btnAdd.setFont(new Font("Segoe UI", 1, 18)); // NOI18N
        btnAdd.setIconTextGap(2);
        btnAdd.setPreferredSize(new Dimension(64, 64));
        btnAdd.setRound(30);
        btnAdd.setShadowColor(new Color(0, 0, 0));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btnAddActionPerformed(evt);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (NotBoundException e) {
                    throw new RuntimeException(e);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        cbbCategory.setBackground(new Color(242, 249, 255));
        cbbCategory.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbCategory.setForeground(new Color(102, 102, 102));
        cbbCategory.setModel(new DefaultComboBoxModel(new String[]{""}));
        cbbCategory.setSelectedIndex(-1);
        cbbCategory.setFont(new Font("Segoe UI", 0, 16)); // NOI18N
        cbbCategory.setLabeText("Danh mục");
        cbbCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    cbbCategoryActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        cbbVendor.setBackground(new Color(242, 249, 255));
        cbbVendor.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbVendor.setForeground(new Color(102, 102, 102));
        cbbVendor.setModel(new DefaultComboBoxModel(new String[]{""}));
        cbbVendor.setSelectedIndex(-1);
        cbbVendor.setFont(new Font("Segoe UI", 0, 16)); // NOI18N
        cbbVendor.setLabeText("Nhà cung cấp");
        cbbVendor.setPreferredSize(new Dimension(350, 30));
        cbbVendor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    cbbVendorActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        cbbMethod.setBackground(new Color(242, 249, 255));
        cbbMethod.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbMethod.setForeground(new Color(102, 102, 102));
        cbbMethod.setModel(new DefaultComboBoxModel(new String[]{"", "Sản phẩm sắp hết hạn", "Sản phẩm tồn kho thấp"}));
        cbbMethod.setSelectedIndex(-1);
        cbbMethod.setFont(new Font("Segoe UI", 0, 16)); // NOI18N
        cbbMethod.setLabeText("Khác");
        cbbMethod.setName(""); // NOI18N
        cbbMethod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    cbbMethodActionPerformed(evt);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

        });

        tableScrollButton_Product.setMinimumSize(new Dimension(200, 15));
        tableScrollButton_Product.setPreferredSize(new Dimension(1190, 400));

        jScrollPane_tableProduct.setBackground(new Color(221, 221, 221));
        jScrollPane_tableProduct.setBorder(null);
        jScrollPane_tableProduct.setPreferredSize(new Dimension(950, 400));

        tableProduct.setBackground(new Color(242, 249, 255));
        tableProduct.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        tableProduct.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Mã sản phẩm", "Tên sản phẩm", "Số đăng ký", "Ngày hết hạn", "Đơn vị", "Số lượng tồn", "Giá bán", "Hoạt chất", "Đường dùng", "Đơn vị nhập", "Dưỡng chất chính", "Loại vật tư y tế", "Danh mục", "Nhà cung cấp"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tableProduct.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        tableProduct.setGridColor(new Color(218, 247, 249));
        tableProduct.setRequestFocusEnabled(false);
        tableProduct.setShowVerticalLines(true);
        jScrollPane_tableProduct.setViewportView(tableProduct);

        tableScrollButton_Product.add(jScrollPane_tableProduct, BorderLayout.CENTER);

        cbbAdministration.setBackground(new Color(242, 249, 255));
        cbbAdministration.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        cbbAdministration.setForeground(new Color(102, 102, 102));
        cbbAdministration.setModel(new DefaultComboBoxModel(new String[]{""}));
        cbbAdministration.setSelectedIndex(-1);
        cbbAdministration.setFont(new Font("Segoe UI", 0, 16)); // NOI18N
        cbbAdministration.setLabeText("Đường dùng");
        cbbAdministration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                                   try {
                        cbbAdministrationActionPerformed(evt);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                            }
        });

        txtSearch.setForeground(new Color(153, 153, 153));
        txtSearch.setText("Nhập tiêu chí tìm kiếm ...");
        txtSearch.setFont(new Font("Segoe UI", 0, 18)); // NOI18N

        txtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    List<model.Product> result = null;
                    try {
                        result = searchProducts(
                                cbbCategory.getSelectedItem(),
                                cbbVendor.getSelectedItem(),
                                cbbAdministration.getSelectedItem(),
                                txtSearch.getText()
                        );
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }

                    showTable(result);


                    if (result.isEmpty()) {
//                            new Message(homePage, true, "Thông báo", "Hết hàng", "src/main/java/ui/dialog/warning.png").showAlert();
                        new Message(homePage, true, "Thông báo", "Không tìm thấy", "src/main/java/ui/dialog/warning.png").showAlert();
                    } else {
                        tableProduct.setVisible(true);
                        showTable(result);


                }
                pCenter.revalidate(); // Cập nhật lại giao diện
                pCenter.repaint();
            }
        });
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });

        btnSearch.setBackground(new Color(102, 204, 255));
        btnSearch.setForeground(new Color(0, 0, 0));
        btnSearch.setIcon(new ImageIcon("src/main/java/ui/button/magnifying-glass_32.png")); // NOI18N
        btnSearch.setRound(20);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (countSelectedComboBox() >= 1) {
                        List<model.Product> result = searchProducts(
                                cbbCategory.getSelectedItem(),
                                cbbVendor.getSelectedItem(),
                                cbbAdministration.getSelectedItem(),
                                txtSearch.getText()
                        );
                        showTable(result);

                        if (result.isEmpty()) {
                            new Message(homePage, true, "Thông báo", "Không tìm thấy sản phẩm phù hợp", "src/main/java/ui/dialog/warning.png").showAlert();
                        }
                    } else {
                        new Message(homePage, true, "Thông báo", "Vui lòng chọn tiêu chí tìm kiếm", "src/main/java/ui/dialog/warning.png").showAlert();
                    }
                    pCenter.revalidate();
                    pCenter.repaint();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                }
            }
        });


        lbDate.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        lbDate.setText("Lọc theo ngày");

        GroupLayout pCenterLayout = new GroupLayout(pCenter);
        pCenter.setLayout(pCenterLayout);
        pCenterLayout.setHorizontalGroup(
                pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, pCenterLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                .addComponent(cbbCategory, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(cbbVendor, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                .addComponent(txtDate, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCalendar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(221, 221, 221)
                                                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(cbbAdministration, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30)
                                                .addComponent(cbbMethod, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)))
                                .addGap(123, 123, 123))
                        .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, pCenterLayout.createSequentialGroup()
                                        .addContainerGap(28, Short.MAX_VALUE)
                                        .addComponent(tableScrollButton_Product, GroupLayout.PREFERRED_SIZE, 1572, GroupLayout.PREFERRED_SIZE)
                                        .addGap(115, 115, 115)))
        );
        pCenterLayout.setVerticalGroup(
                pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(pCenterLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(btnCalendar, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtDate, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pCenterLayout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(cbbVendor, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbCategory, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbAdministration, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbMethod, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pCenterLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(pCenterLayout.createSequentialGroup()
                                        .addGap(226, 226, 226)
                                        .addComponent(tableScrollButton_Product, GroupLayout.PREFERRED_SIZE, 690, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(84, Short.MAX_VALUE)))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pCenter, GroupLayout.PREFERRED_SIZE, 1620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(pCenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

    }// </editor-fold>

    private void txtDateActionPerformed(ActionEvent evt) throws RemoteException {
        List<model.Product> proListByDate = (List<model.Product>) productService.searchByMultipleCriteria("product", convertDateFormat(txtDate.getText()));
        showTable(proListByDate);

    }

    private void btnCalendarActionPerformed(ActionEvent evt) throws RemoteException {
//        date.showPopup();
        List<model.Product> proListByDate = (List<model.Product>) productService.searchByMultipleCriteria("product", convertDateFormat(txtDate.getText()));
        showTable(proListByDate);
    }

    private void btnAddActionPerformed(ActionEvent evt) throws MalformedURLException, NotBoundException, RemoteException {
        AddProduct addProduct = new AddProduct();
        homePage.updateCurretPanel(addProduct);

        pCenter.removeAll();

        pCenter.setLayout(new BorderLayout());
        pCenter.add(addProduct, BorderLayout.CENTER);

        pCenter.revalidate();
        pCenter.repaint();
    }

    private void cbbCategoryActionPerformed(ActionEvent evt) throws RemoteException {
        if (countSelectedComboBox() == 1) {
            List<model.Product> result = searchProducts(
                    cbbCategory.getSelectedItem(),
                    null,
                    null,
                    ""
            );
            showTable(result);
        }
    }


    private void cbbVendorActionPerformed(ActionEvent evt) throws RemoteException {
        if (countSelectedComboBox() == 1) {
            List<model.Product> result = searchProducts(
                    null,
                    cbbVendor.getSelectedItem(),
                    null,
                    ""
            );
            showTable(result);
        }
    }


    private void cbbMethodActionPerformed(ActionEvent evt) throws RemoteException {
        searchByOtherCriterious();
    }

    private void cbbAdministrationActionPerformed(ActionEvent evt) throws RemoteException {
        if (countSelectedComboBox() == 1) {
            List<model.Product> result = searchProducts(
                    null,
                    null,
                    cbbAdministration.getSelectedItem(),
                    ""
            );
            showTable(result);
        }
    }


    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {
        if (txtSearch.getText().equals("Nhập tiêu chí tìm kiếm ...")) {
            txtSearch.setText("");
        }
    }

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {
        if (txtSearch.getText().equals("")) {
            txtSearch.setText("Nhập tiêu chí tìm kiếm ...");
        }
    }


    // Variables declaration - do not modify
    private ui.button.Button btnAdd;
    private ui.button.Button btnCalendar;
    private ui.button.Button btnSearch;
    private ui.combobox.Combobox cbbAdministration;
    private ui.combobox.Combobox cbbCategory;
    private ui.combobox.Combobox cbbMethod;
    private ui.combobox.Combobox cbbVendor;
    private ui.datechooser.DateChooser date;
    private JScrollPane jScrollPane_tableProduct;
    private JLabel lbDate;
    private JPanel pCenter;
    private TableCustom tableCustom;
    private JTable tableProduct;
    private ui.table.TableScrollButton tableScrollButton_Product;
    private ui.textfield.TextField txtDate;
    private ui.textfield.TextField txtSearch;


    public void showTable(List<model.Product> arrayList) {
        DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();
        model.setRowCount(0); // Clear dữ liệu cũ

        for (model.Product product : arrayList) {
            if (product instanceof Medicine medicine) {
                medicine.getUnitDetails().forEach((unit, detail) -> model.addRow(new Object[]{
                        medicine.getProductID(),
                        medicine.getProductName(),
                        medicine.getRegistrationNumber(),
                        medicine.getEndDate(),
                        unit.name(),
                        detail.getInStock(),
                        medicine.getSellPrice(unit),
                        medicine.getActiveIngredient(),
                        medicine.getAdministrationRoute().getAdministrationRouteID(),
                        medicine.getConversionUnit(),
                        "", "",
                        medicine.getCategory().getCategoryName(),
                        medicine.getVendor().getVendorName()
                }));
            } else if (product instanceof MedicalSupply supply) {
                supply.getUnitDetails().forEach((unit, detail) -> model.addRow(new Object[]{
                        supply.getProductID(),
                        supply.getProductName(),
                        supply.getRegistrationNumber(),
                        supply.getEndDate(),
                        unit.name(),
                        detail.getInStock(),
                        supply.getSellPrice(unit),
                        "", "", "", "",
                        supply.getMedicalSupplyType(),
                        supply.getCategory().getCategoryName(),
                        supply.getVendor().getVendorName()
                }));
            } else if (product instanceof FunctionalFood food) {
                food.getUnitDetails().forEach((unit, detail) -> model.addRow(new Object[]{
                        food.getProductID(),
                        food.getProductName(),
                        food.getRegistrationNumber(),
                        food.getEndDate(),
                        unit.name(),
                        detail.getInStock(),
                        food.getSellPrice(unit),
                        "", "", "",
                        food.getMainNutrients(),
                        "",
                        food.getCategory().getCategoryName(),
                        food.getVendor().getVendorName()
                }));
            }
        }
    }


    public void showDataComboBoxVendor() throws RemoteException {

        List<Vendor> list = vendorService.getAll();

        Set<Vendor> uniqueValues = new LinkedHashSet<>(list);
        List<Vendor> uniqueList = new ArrayList<>(uniqueValues);

        for (Vendor vendor : uniqueList) {
            cbbVendor.addItem(vendor.getVendorName());
        }

    }

    public void showDataComboBoxCategory() throws RemoteException {
        List<Category> list = categoryService.getAll();

        Set<Category> uniqueValues = new LinkedHashSet<>(list);
        List<Category> uniqueList = new ArrayList<>(uniqueValues);

        for (Category category : uniqueList) {
            cbbCategory.addItem(category.getCategoryName());
        }
    }

    public void showDataComboBoxAdmintrationRoute() throws RemoteException {
        List<AdministrationRoute> list = administrationRouteService.getAll();

        Set<AdministrationRoute> uniqueValues = new LinkedHashSet<>(list);
        List<AdministrationRoute> uniqueList = new ArrayList<>(uniqueValues);

        for (AdministrationRoute administrationRoute : uniqueList) {
            cbbAdministration.addItem(administrationRoute.getAdministrationRouteID());
        }
    }

    private static String convertDateFormat(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        try {
            date = inputFormat.parse(inputDate);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void searchByOtherCriterious() throws RemoteException {
        String searchByOther = (String) cbbMethod.getSelectedItem();

        if (searchByOther != null) {
            DefaultTableModel model = (DefaultTableModel) tableProduct.getModel();

            if (searchByOther.equals("Sản phẩm sắp hết hạn")) {
                // Lấy danh sách sản phẩm gần hết hạn
                List<model.Product> proNearExpire = productService.getProductListNearExpire();
                if (proNearExpire.isEmpty()) {
                    model.setRowCount(0);  // Clear existing rows
                    model.addRow(new Object[]{"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."});
                } else {
                    showTable(proNearExpire);
                }

            } else if (searchByOther.equals("Sản phẩm tồn kho thấp")) {
                // Lấy danh sách sản phẩm tồn kho thấp
                List<model.Product> lowStockProductsList = productService.getLowStockProducts(25);
                if (lowStockProductsList.isEmpty()) {
                    model.setRowCount(0);  // Clear existing rows
                    model.addRow(new Object[]{"...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "...", "..."});
                } else {
                    showTable(lowStockProductsList);
                }

            } else if (searchByOther.equals("Tất cả")) {
                // Lấy tất cả sản phẩm
                List<model.Product> productArrayList = productService.fetchProducts();
                showTable(productArrayList);
            }
        } else {
            System.out.println("Giá trị tìm kiếm không hợp lệ (null).");
        }
    }


    public List<model.Product> searchProducts(Object selectedCategory, Object selectedVendor, Object selectedAdmin, String searchText) throws RemoteException {
        List<model.Product> allProducts = productService.getAll();
        List<model.Product> filteredProducts = new ArrayList<>();

        boolean hasSearchText = searchText != null && !searchText.trim().isEmpty() && !searchText.equals("Nhập tiêu chí tìm kiếm ...");

        for (model.Product p : allProducts) {
            boolean match = true;

            if (selectedCategory != null && !selectedCategory.toString().isEmpty()) {
                if (p.getCategory() == null || !p.getCategory().getCategoryName().equals(selectedCategory.toString())) {
                    match = false;
                }
            }

            if (selectedVendor != null && !selectedVendor.toString().isEmpty()) {
                if (p.getVendor() == null || !p.getVendor().getVendorName().equals(selectedVendor.toString())) {
                    match = false;
                }
            }

            if (selectedAdmin != null && !selectedAdmin.toString().isEmpty()) {
                if (p instanceof Medicine) {
                    Medicine m = (Medicine) p;
                    if (m.getAdministrationRoute() == null || !m.getAdministrationRoute().getAdministrationRouteID().equals(selectedAdmin.toString())) {
                        match = false;
                    }
                } else {
                    match = false;
                }
            }

            if (hasSearchText) {
                boolean textMatch = p.getProductID().contains(searchText) ||
                        p.getProductName().toLowerCase().contains(searchText.toLowerCase()) ||
                        (p.getRegistrationNumber() != null && p.getRegistrationNumber().contains(searchText));
                if (!textMatch) {
                    match = false;
                }
            }

            if (match) {
                filteredProducts.add(p);
            }
        }

        return filteredProducts;
    }
    private int countSelectedComboBox() {
        int count = 0;
        if (cbbCategory.getSelectedItem() != null && !cbbCategory.getSelectedItem().toString().isEmpty())
            count++;
        if (cbbVendor.getSelectedItem() != null && !cbbVendor.getSelectedItem().toString().isEmpty())
            count++;
        if (cbbAdministration.getSelectedItem() != null && !cbbAdministration.getSelectedItem().toString().isEmpty())
            count++;
        if (txtSearch.getText() != null && !txtSearch.getText().trim().isEmpty() && !txtSearch.getText().equals("Nhập tiêu chí tìm kiếm ..."))
            count++;
        return count;
    }


}

