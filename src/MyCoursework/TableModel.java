package MyCoursework;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private int columnCount = 1;

    private ArrayList<AutoShop> autoShops;

    public TableModel() {
        autoShops = new ArrayList<AutoShop>();
        load();
    }

    @SuppressWarnings("unchecked")
    public void load() {
        try {
            FileInputStream fileIn = new FileInputStream("./autoShop.ser");
            ObjectInputStream inStream = new ObjectInputStream(fileIn);
            this.autoShops = (ArrayList<AutoShop>) inStream.readObject();
            inStream.close();
            fileIn.close();

            FileOutputStream fileOutCopy = new FileOutputStream("./backup/copyFile" + (new Date().getTime()) + ".ser");
            ObjectOutputStream outStreamCopy = new ObjectOutputStream(fileOutCopy);

            outStreamCopy.writeObject(autoShops);
            outStreamCopy.flush();
            outStreamCopy.close();
            fileOutCopy.flush();
            fileOutCopy.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }

    public void save() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./autoShop.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(autoShops);
            outStream.flush();
            outStream.close();
            fileOut.flush();
            fileOut.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public int getRowCount() {
        return autoShops.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Номер автомагазина";
        }
        return "";
    }

    @Override
    public AutoShop getValueAt(int rowIndex, int colomnIndex) {
        AutoShop rows = autoShops.get(rowIndex);
        return rows;
    }

    public void remData(int row) {
        autoShops.remove(row);
    }

    public void addShop(AutoShop autoShop) {
        autoShops.add(autoShop);
    }

    public void edit(int i, String productName, int manufacture, int unitPrice, int quatntity, int shopNumber) {
        autoShops.get(i).setProductName(productName);
        autoShops.get(i).setManufacture(manufacture);
        autoShops.get(i).setUnitPrice(unitPrice);
        autoShops.get(i).setQuantity(quatntity);
        autoShops.get(i).setShopNumber(shopNumber);
    }

    public void removeAllProducts(int shopNumber) {
        for (AutoShop autoShop : autoShops) {
            if (shopNumber == autoShop.getShopNumber()) {
                autoShop.setProductName("Пусто");
                autoShop.setManufacture(0);
                autoShop.setQuantity(0);
                autoShop.setUnitPrice(0);
            }
        }
    }

    public String showProductsOfShop(int shopNumber) {
        String result = "";
        for (AutoShop autoShop : autoShops) {
            if (shopNumber == autoShop.getShopNumber())
            {
                result += "\nИмя продукта: " + autoShop.getProductName() + "\nКол-во товара: " + autoShop.getQuantity();
            }

        }
        return result.equals("") ? "Неизвестная ошибка" : result;
    }

    public String showPriceList(int shopNumber) {
        for (AutoShop autoShop : autoShops) {
            if (shopNumber == autoShop.getShopNumber()) {
                return "\nВ магазине №" + autoShop.getShopNumber() + "\nТовар: " + autoShop.getProductName() + "\nЦена: " + autoShop.getUnitPrice() + "$";
            }
        }
        return "Неизвестная ошибка";
    }
}
