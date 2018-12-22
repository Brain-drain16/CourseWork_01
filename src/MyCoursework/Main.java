package MyCoursework;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Main {

    Main() {

        JFrame frame = new JFrame();
        frame.setTitle("AUTOSHOP");
        frame.setSize(new Dimension(800, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JButton addJButton = new JButton("Добавить магазин");
        JButton remJButton = new JButton("Удалить магазин");
        JButton editJButton = new JButton("Редактировать магазин");

        JButton removeAllProductsJButton = new JButton("Удалить все продукты магазина");
        JButton infoJButton = new JButton("Показать все товары в магазине");
        JButton priceListJButton = new JButton("Показать прайс-лист");

        JButton saveListJButton = new JButton("Сохранить все");

        JPanel listPanel = new JPanel(new GridLayout());
        JPanel rightButtonPanel = new JPanel(new GridLayout(7, 1));

        TableModel tb = new TableModel();
        JTable autoShopTable = new JTable(tb);
        JScrollPane scroll = new JScrollPane(autoShopTable);
        scroll.setPreferredSize(new Dimension(900, 400));

        addJButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = JOptionPane.showInputDialog("Введите название продукта: ");
                String manufacture = JOptionPane.showInputDialog("Введите производителя от 1 до 5: ");

                int temp1 = Integer.parseInt(manufacture);

                while (temp1 < 1 || temp1 > 5) {
                    manufacture = JOptionPane.showInputDialog("Введите производителя от 1 до 5: ");
                    temp1 = Integer.parseInt(manufacture);
                }

                String unitPrice = JOptionPane.showInputDialog("Введите цену за еденицу товара: ");
                String quantity = JOptionPane.showInputDialog("Введите кол-во постовляемых товаров: ");
                String shopNumber = JOptionPane.showInputDialog("Введите номер магазина: ");

                int temp2 = Integer.parseInt(unitPrice);
                int temp3 = Integer.parseInt(quantity);
                int temp4 = Integer.parseInt(shopNumber);

                AutoShop autoShop = new AutoShop(productName, temp1, temp2, temp3, temp4);

                tb.addShop(autoShop);
                tb.fireTableDataChanged();// Можно не добавлять
            }
        });

        remJButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tb.remData(autoShopTable.getSelectedRow());
                tb.fireTableDataChanged();
            }
        });

        editJButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String productName = JOptionPane.showInputDialog("Введите название продукта: ");
                String manufacture = JOptionPane.showInputDialog("Введите производителя от 1 до 5: ");

                int temp1 = Integer.parseInt(manufacture);

                while (temp1 < 1 || temp1 > 5) {
                    manufacture = JOptionPane.showInputDialog("Введите производителя от 1 до 5: ");
                    temp1 = Integer.parseInt(manufacture);
                }

                String unitPrice = JOptionPane.showInputDialog("Введите цену за еденицу товара: ");
                String quantity = JOptionPane.showInputDialog("Введите кол-во постовляемых товаров: ");
                String shopNumber = JOptionPane.showInputDialog("Введите номер магазина: ");

                int temp2 = Integer.parseInt(unitPrice);
                int temp3 = Integer.parseInt(quantity);
                int temp4 = Integer.parseInt(shopNumber);

                tb.edit(autoShopTable.getSelectedRow() ,productName, temp1, temp2, temp3, temp4);
                tb.fireTableDataChanged();
            }
        });

        removeAllProductsJButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String shopNumber = JOptionPane.showInputDialog("Введите номер магазина: ");
                int temp = Integer.parseInt(shopNumber);
                tb.removeAllProducts(temp);
                tb.fireTableDataChanged();
            }
        });

        infoJButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String shopNumber = JOptionPane.showInputDialog("Введите номер магазина: ");
                int temp = Integer.parseInt(shopNumber);
                JOptionPane.showMessageDialog(null, tb.showProductsOfShop(temp));
                tb.fireTableDataChanged();
            }
        });

        priceListJButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String shopNumber = JOptionPane.showInputDialog("Введите номер магазина: ");
                int temp = Integer.parseInt(shopNumber);
                JOptionPane.showMessageDialog(null, tb.showPriceList(temp));
                tb.fireTableDataChanged();
            }
        });

        saveListJButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                tb.save();
            }
        });


        // add frame table and buttons
        listPanel.add(scroll);
        rightButtonPanel.add(addJButton);
        rightButtonPanel.add(remJButton);
        rightButtonPanel.add(editJButton);
        rightButtonPanel.add(removeAllProductsJButton);
        rightButtonPanel.add(infoJButton);
        rightButtonPanel.add(priceListJButton);
        rightButtonPanel.add(saveListJButton);

        frame.add(listPanel, BorderLayout.WEST);
        frame.add(rightButtonPanel, BorderLayout.EAST);
        frame.setVisible(true);
        frame.pack();
    }

    public static void main(String[] args) {
        new Main();
    }

}