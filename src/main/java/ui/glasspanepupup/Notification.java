package ui.glasspanepupup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class Notification extends JPanel {

    public Notification() {
        initComponents();
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); // Gọi phương thức của lớp cha trước
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Bật chống răng cưa

        // Đặt màu nền của component
        g2.setColor(getBackground());

        // Vẽ hình chữ nhật bo góc
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15)); // 15 là bán kính bo góc

        g2.dispose();
    }


    @SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane1 = new JScrollPane();
        myList1 = new ui.jlist.MyList<>();

        setBackground(new Color(242, 249, 255));
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        myList1.setModel(new AbstractListModel<String>() {
            String[] strings = {};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });

        myList1.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        myList1.setPreferredSize(new Dimension(250, 300)); // Tăng chiều rộng của myList1
        myList1.setBackground(new Color(242, 249, 255));
        jScrollPane1.setViewportView(myList1);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        // Căn giữa jScrollPane1 trong layout
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE) // Kích thước cho jScrollPane
                        .addContainerGap()
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(10)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE) // Kích thước cho jScrollPane
                        .addContainerGap()
        );
    }


    public void eventOK(ActionListener event) {
        // Thêm chức năng xử lý sự kiện OK
    }


    public void addNotification(String message) {
        DefaultListModel<String> model = (DefaultListModel<String>) myList1.getModel();

        // Thêm thông báo vào mô hình
        model.addElement(message);

        // In ra các thông báo hiện tại trong myList1
        System.out.println("Notification added: " + myList1.getModel().getSize());

        // Duyệt qua tất cả các phần tử trong model và in ra
        for (int i = 0; i < model.getSize(); i++) {
            System.out.println("Message " + (i + 1) + ": " + model.getElementAt(i));
        }
    }


    // Biến khai báo - không sửa đổi//GEN-BEGIN:variables
    private JScrollPane jScrollPane1;
    public ui.jlist.MyList<String> myList1;


    // Kết thúc khai báo biến//GEN-END:variables

}
