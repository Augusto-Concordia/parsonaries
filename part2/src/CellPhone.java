import java.util.Objects;

public class CellPhone {

    final private long SERIAL_NUM;
    private String brand;
    private int year;
    private double price;

    public CellPhone(long SERIAL_NUM, String brand, double price,  int year) {
        this.SERIAL_NUM = SERIAL_NUM;
        this.brand = brand;
        this.price = price;
        this.year = year;
    }

    public CellPhone(CellPhone cellPhone, long SERIAL_NUM) {
        this(SERIAL_NUM, cellPhone.brand, cellPhone.price, cellPhone.year);
    }

    public long getSERIAL_NUM() {return SERIAL_NUM;}

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

    public CellPhone clone(long SERIAL_NUM) {
        return new CellPhone(SERIAL_NUM, brand, price, year);
    }

    @Override
    public String toString() {
        return "[ " + SERIAL_NUM + ": " + brand + " " + price + "$ " + year + " ]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPhone cellPhone = (CellPhone) o;
        return year == cellPhone.year && Double.compare(cellPhone.price, price) == 0 && Objects.equals(brand, cellPhone.brand);
    }
}
