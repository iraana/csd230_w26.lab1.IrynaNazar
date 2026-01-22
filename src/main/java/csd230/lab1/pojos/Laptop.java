package csd230.lab1.pojos;

import java.util.Objects;

/**
 * DTO for {@link csd230.lab1.entities.LaptopEntity}
 */
public class Laptop extends ElectronicDevice {
    private int ramSize;

    public Laptop() {}

    public Laptop(String brand, double price, int ramSize) {
        super(brand, price);
        this.ramSize = ramSize;
    }

    public int getRamSize() { return ramSize; }
    public void setRamSize(int ramSize) { this.ramSize = ramSize; }

    @Override
    public void initialize() {
        System.out.println("Initializing Laptop:");
        super.initialize();
        this.ramSize = getInput(this.ramSize);
    }

    @Override
    public void edit() {
        System.out.println("Editing Laptop:");
        super.edit();
        this.ramSize = getInput(this.ramSize);
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "productId='" + getProductId() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", ramSize=" + ramSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop)) return false;
        if (!super.equals(o)) return false;
        Laptop laptop = (Laptop) o;
        return ramSize == laptop.ramSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ramSize);
    }

    @Override
    public void sellItem() {
        System.out.println("Sold Laptop: " + getBrand() + " with " + ramSize + "GB RAM for $" + getPrice());
    }
}
