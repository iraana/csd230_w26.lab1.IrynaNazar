package csd230.lab1.pojos;

import java.util.Objects;

/**
 * DTO for {@link csd230.lab1.entities.ElectronicDeviceEntity}
 */
public abstract class ElectronicDevice extends Product {
    private String brand;
    private double price;

    public ElectronicDevice() {}

    public ElectronicDevice(String brand, double price) {
        super();
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public void initialize() {
        this.brand = getInput("Enter brand: ");
        this.price = getInput(0.0);
    }

    @Override
    public void edit() {
        System.out.println("Editing Electronic Device:");
        this.brand = getInput("Edit brand [" + this.brand + "]: ");
        this.price = getInput(this.price);
    }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                "productId='" + getProductId() + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectronicDevice)) return false;
        ElectronicDevice that = (ElectronicDevice) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, price);
    }
}