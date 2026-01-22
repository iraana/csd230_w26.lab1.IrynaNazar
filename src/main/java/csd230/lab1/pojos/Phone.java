package csd230.lab1.pojos;

import java.util.Objects;

/**
 * DTO for {@link csd230.lab1.entities.PhoneEntity}
 */
public class Phone extends ElectronicDevice {
    private int storage;

    public Phone() {}

    public Phone(String brand, double price, int storage) {
        super(brand, price);
        this.storage = storage;
    }

    public int getStorage() { return storage; }
    public void setStorage(int storage) { this.storage = storage; }

    @Override
    public void initialize() {
        System.out.println("Initializing Phone:");
        super.initialize();
        this.storage = getInput(this.storage);
    }

    @Override
    public void edit() {
        System.out.println("Editing Phone:");
        super.edit();
        this.storage = getInput(this.storage);
    }

    @Override
    public String toString() {
        return "Phone{" +
                "productId='" + getProductId() + '\'' +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", storage=" + storage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        if (!super.equals(o)) return false;
        Phone phone = (Phone) o;
        return storage == phone.storage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), storage);
    }

    @Override
    public void sellItem() {
        System.out.println("Sold Phone: " + getBrand() + " with " + storage + "GB storage for $" + getPrice());
    }
}
