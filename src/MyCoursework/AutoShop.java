package MyCoursework;

import java.io.Serializable;

public class AutoShop implements Serializable {
    // объявления полей класса, private поля нужны для механизма ООП - инкапсуляции,
    // это типо для того, чтобы пользоветель твоей программы не мог полиять на
    // данные напрямую
    private String productName; // наименование товара
    private Manufacture manufacture; // фирма-производитель
    private int unitPrice; // цена за единицу
    private int quantity; // количество
    private int shopNumber; // номер магазина
    private int minimumBarch; // минимальная партия

    // Константное перечисление поставщиков
    enum Manufacture {
        LMI, BOGE, TRW, LUCAS, HELLA, NULL;
    }

    // Конструктор для создания экземпляра класса AutoShop, в поторый мы передаем
    // поля нашего класса, для того, чтобы переопределить их.
    public AutoShop(String productName, int manufacture, int unitPrice, int quantity, int shopNumber) {
        this.setProductName(productName);// инициализируем данные с помощью методов
        this.setManufacture(manufacture);
        this.setUnitPrice(unitPrice);
        this.setQuantity(quantity);
        this.setShopNumber(shopNumber);
    }

    // все методы с приставкой "get" нужны для вызова значений полей класса.
    // публичные методы нужны для работы с приватными полями, то есть, для того,
    // чтобы как-то с ними взаимодействовать, не принося полям метода вреда или же
    // краша всех программы в целом

    public String getProductName() {
        return productName; // тобиж, просто вернуть значения, в основном используется для сравнения с
        // чем-либо или с выводом на экран значения поля класса
    }

    public void setProductName(String productName) {
        this.productName = productName;// задаем имя продукта магазина
    }

    public Manufacture getManufacture() {
        return manufacture;
    }

    // метод для опредения производителя по индексу, передаваемый в метод
    public void setManufacture(int manufactureNumber) {
        // нужен для определения минимальной поставляемой связки с завода производителя
        switch (manufactureNumber) {
            case 0:
                this.manufacture = Manufacture.NULL;// обнуляющая итерация и метод, нужен для удаления всех товаров в
                // магазине(по заданию)
                setMinimumBarch(0);
                break;
            case 1:
                this.manufacture = Manufacture.LMI;
                setMinimumBarch(2);
                break;
            case 2:
                this.manufacture = Manufacture.BOGE;
                setMinimumBarch(8);
                break;
            case 3:
                this.manufacture = Manufacture.TRW;
                setMinimumBarch(6);
                break;
            case 4:
                this.manufacture = Manufacture.LUCAS;
                setMinimumBarch(4);
                break;
            case 5:
                this.manufacture = Manufacture.HELLA;
                setMinimumBarch(10);
                break;

            default:
                break;
        }
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(int shopNumber) {
        this.shopNumber = shopNumber;
    }

    public int getMinimumBarch() {
        return minimumBarch;
    }

    public void setMinimumBarch(int minBranch) {
        this.minimumBarch = minBranch;
    }

    // Хорошая практика использованя метода toString для вывода либо полной, либо
    // частичной информации о полях класса
    public String toString() {
        return "\nИмя продукта: " + productName + "\nПроизводитель: " + manufacture + "\nЦена за еденицу товара: "
                + unitPrice + "$" + "\nКол-во товара: " + quantity + "\nНомер магазина: " + shopNumber
                + "\nМинимальная связка товара: " + minimumBarch;
    }
}
