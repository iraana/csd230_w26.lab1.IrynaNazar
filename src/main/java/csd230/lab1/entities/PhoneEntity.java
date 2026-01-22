package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("PHONE")
public class PhoneEntity extends ElectronicDeviceEntity {

    private int storage; // GB

    public PhoneEntity() {}

    public PhoneEntity(String brand, double price, int storage) {
        super(brand, price);
        this.storage = storage;
    }

    public int getStorage() { return storage; }
    public void setStorage(int storage) { this.storage = storage; }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", storage=" + storage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneEntity that)) return false;
        if (!super.equals(o)) return false;
        return storage == that.storage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), storage);
    }

    @Override
    public void sellItem() {
        System.out.println("Sold Phone: Brand=" + getBrand() + ", Storage=" + storage + "GB, Price=" + getPrice());
    }

}
