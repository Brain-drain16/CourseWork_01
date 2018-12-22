package MyCoursework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class AutoShopArrayList {
    //объявление списка типа AutoShop
    private ArrayList<AutoShop> autoShops = new ArrayList<>();

    public void save(String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(autoShops);
            outStream.flush();
            outStream.close();
            fileOut.flush();
            fileOut.close();
            System.out.println("Внесенные данные сохранены в файле: " + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void load(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
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
            System.out.println("Список загружен из файла " + fileName);
            System.out.println("Копия успешно создана.");
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Класс с таким именем не найден.");
            c.printStackTrace();
            return;
        }
    }
    //добавления магазина в список
    public void addShop(AutoShop autoShop) {
        this.autoShops.add(autoShop);
    }
    //удаление всех продуктов в выбраном магазине
    public void removeAllProducts(int shopNumber) {
        for (AutoShop autoShop : autoShops) {
            if(shopNumber == autoShop.getShopNumber()) {
                autoShop.setProductName("Пусто");
                autoShop.setManufacture(0);
                autoShop.setQuantity(0);
                autoShop.setUnitPrice(0);
            }
        }
    }
    //вывод магазинов с минимальной связкой 6 и более
    public void showBrach() {
        for (AutoShop autoShop : autoShops) {
            if(autoShop.getMinimumBarch() > 5) {
                System.out.println(autoShop);
            }
        }
    }
    //вывод названия продукта и его кол-ва в магазине
    public void showProductsOfShop(int shopNumber) {
        for (AutoShop autoShop : autoShops) {
            if(shopNumber == autoShop.getShopNumber()) {
                System.out.println("\nИмя продукта: " + autoShop.getProductName() + "\nКол-во товара: " + autoShop.getQuantity());
            }
        }
    }
    //метод для вывода всех данных
    public void showAll() {
        for (AutoShop autoShop : autoShops) {
            System.out.println(autoShop);
        }
    }
    //очистка всего списка
    public void clearAll() {
        autoShops.clear();
        System.out.println("Список очищен.");
    }
    //вывод прайс-листа, название продукта и номер магазина
    public void showPriceList() {
        for (AutoShop autoShop : autoShops) {
            System.out.println("Товар: " + autoShop.getProductName() + " в магазине №" + autoShop.getShopNumber());
        }
    }
}
