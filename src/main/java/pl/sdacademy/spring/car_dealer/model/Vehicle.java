package pl.sdacademy.spring.car_dealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Vehicle extends BaseModel implements Serializable {
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column (name = "model")
    private String model;
    @Column (name = "production_year")
    private Long productionYear;
    @Column (name = "mileage")
    private Long mileage;
    @Column (name = "fuel")
    private String fuel;
    @Column (name = "price")
    private Long price;
    @Column (name = "sold")
    private boolean sold;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Long productionYear) {
        this.productionYear = productionYear;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }
}
