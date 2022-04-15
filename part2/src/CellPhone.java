//COMP 249 - Assignment #4
//Due Date: April 15th
//Written by: Augusto Mota Pinheiro (40208080) & Michaël Gugliandolo (40213419)

import java.util.Objects;

/**
 * Class containing the data of a cell phone.
 */
public class CellPhone {
    /**
     * Unique identifier of the cell phone.
     */
    final private long SERIAL_NUM;
    /**
     * Brand of the cell phone.
     */
    private String brand;

    /**
     * Release year of the cell phone.
     */
    private int year;

    /**
     * Price of the cell phone.
     */
    private double price;

    /**
     * Parametrized constructor for a cell phone.
     * @param SERIAL_NUM the serial number of the phone
     * @param brand the brand of the phone
     * @param year the release year of the phone
     * @param price the price of the phone
     */
    public CellPhone(long SERIAL_NUM, String brand, double price,  int year) {
        this.SERIAL_NUM = SERIAL_NUM;
        this.brand = brand;
        this.price = price;
        this.year = year;
    }

    /**
     * Copy constructor for a cell phone.
     * @param cellPhone the cell phone to be copied
     * @param SERIAL_NUM the serial number of the new cell phone
     */
    public CellPhone(CellPhone cellPhone, long SERIAL_NUM) {
        this(SERIAL_NUM, cellPhone.brand, cellPhone.price, cellPhone.year);
    }

    /* GETTERS AND SETTERS */

    /**
     * Getter for the serial number of the phone.
     * @return the serial number of the phone
     */
    public long getSERIAL_NUM() {return SERIAL_NUM;}

    /**
     * Getter for the brand of the phone.
     * @return the brand of the phone
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter for the brand of the phone.
     * @param brand the brand of the phone
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Getter for the release year of the phone.
     * @return the release year of the phone
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for the release year of the phone.
     * @param year the release year of the phone
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Getter for the price of the phone.
     * @return the price of the phone
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the price of the phone.
     * @param price the price of the phone
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Copies the cell phone object with the given serial number.
     * @param SERIAL_NUM the serial number of the new cell phone
     * @return the copied cell phone object
     */
    public CellPhone clone(long SERIAL_NUM) {
        return new CellPhone(SERIAL_NUM, brand, price, year);
    }

    /**
     * Returns a string representation of the cell phone.
     * @return a string representation of the cell phone
     */
    @Override
    public String toString() {
        return "[ " + SERIAL_NUM + ": " + brand + " " + price + "$ " + year + " ]";
    }

    /**
     * Compares two cell phones.
     * @param o the cell phone to be compared
     * @return true if the cell phones are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPhone cellPhone = (CellPhone) o;
        return year == cellPhone.year && Double.compare(cellPhone.price, price) == 0 && Objects.equals(brand, cellPhone.brand);
    }
}
