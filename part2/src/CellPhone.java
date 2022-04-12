import java.util.Objects;

public class CellPhone {

    final private long SERIAL_NUM;
    private String brand;
    private int year;
    private double price;

    public CellPhone(long SERIAL_NUM, String brand, int year, double price) {
        this.SERIAL_NUM = SERIAL_NUM;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    public CellPhone(CellPhone cellPhone, long SERIAL_NUM) {
        this.SERIAL_NUM = SERIAL_NUM;
        this.brand = cellPhone.brand;
        this.year = cellPhone.year;
        this.price = cellPhone.price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CellPhone clone(CellPhone cellPhone, long SERIAL_NUM) {
        return new CellPhone(SERIAL_NUM, cellPhone.brand, cellPhone.year, cellPhone.price);
    }

    @Override
    public String toString() {
        return "This cellphone's serial number is " + SERIAL_NUM +
                ", its brand is " + brand +
                ", its manufacturing year is " + year +
                ", and it has a price of " + price + "$.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPhone cellPhone = (CellPhone) o;
        return year == cellPhone.year && Double.compare(cellPhone.price, price) == 0 && Objects.equals(brand, cellPhone.brand);
    }
}
