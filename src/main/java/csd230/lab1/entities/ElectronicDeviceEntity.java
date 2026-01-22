package csd230.lab1.entities;

import jakarta.persistence.Entity;


import java.util.Objects;

@Entity
public abstract class ElectronicDeviceEntity extends ProductEntity {

    private String brand;
    private double price;

    public ElectronicDeviceEntity() {}

    public ElectronicDeviceEntity(String brand, double price) {
        super();
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "ElectronicDevice{" +
                "id=" + getId() +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElectronicDeviceEntity that)) return false;
        return Double.compare(price, that.price) == 0 && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, price);
    }
}
