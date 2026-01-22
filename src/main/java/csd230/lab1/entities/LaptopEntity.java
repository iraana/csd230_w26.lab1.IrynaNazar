package csd230.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("LAPTOP")
public class LaptopEntity extends ElectronicDeviceEntity {

    private int ramSize;

    public LaptopEntity() {}

    public LaptopEntity(String brand, double price, int ramSize) {
        super(brand, price);
        this.ramSize = ramSize;
    }

    public int getRamSize() { return ramSize; }
    public void setRamSize(int ramSize) { this.ramSize = ramSize; }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + getId() +
                ", brand='" + getBrand() + '\'' +
                ", price=" + getPrice() +
                ", ramSize=" + ramSize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LaptopEntity that)) return false;
        if (!super.equals(o)) return false;
        return ramSize == that.ramSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ramSize);
    }

    @Override
    public void sellItem() {
        System.out.println("Sold Laptop: Brand=" + getBrand() + ", RAM=" + ramSize + "GB, Price=" + getPrice());
    }
}
