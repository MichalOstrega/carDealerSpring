package pl.sdacademy.spring.car_dealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Purchase extends BaseModel implements Serializable {


    @ManyToOne
    @JoinColumn (name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column (name = "date")
    private Date date;
    @Column (name = "price")
    private Long price;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "vehicle=" + vehicle.getManufacturer() + " " + vehicle.getModel() +
                ", customer=" + customer.getName() + " " + customer.getSurname() +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
