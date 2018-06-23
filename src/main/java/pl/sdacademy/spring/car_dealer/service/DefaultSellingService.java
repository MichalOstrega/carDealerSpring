package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.CustomerRepository;
import pl.sdacademy.spring.car_dealer.repository.PurchaseRepository;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

import java.util.Date;
import java.util.Optional;


@Service
public class DefaultSellingService implements SellingService {

    private VehicleRepository vehicleRepository;
    private CustomerRepository customerRepository;
    private PurchaseRepository purchaseRepository;

    public DefaultSellingService(
            VehicleRepository vehicleRepository,
            CustomerRepository customerRepository,
            PurchaseRepository purchaseRepository) {
        this.vehicleRepository = vehicleRepository;

        this.customerRepository = customerRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase sell(Long vehicleId, final Customer customer, Long price) {
        Optional<Vehicle> notSoldVehicle = vehicleRepository.findNotSoldVehicle(vehicleId);
        return notSoldVehicle.map(vehicle -> performSell(vehicle,customer,price)).orElse(null);


    }

    private Purchase performSell(Vehicle vehicle, Customer customer, Long price){
        vehicle.setSold(true);
        vehicleRepository.save(vehicle);
        Customer persistedCustomer = customerRepository.save(customer);
        Purchase purchase = new Purchase();
        purchase.setVehicle(vehicle);
        purchase.setCustomer(persistedCustomer);
        purchase.setDate(new Date());
        purchase.setPrice(price);
        return purchaseRepository.save(purchase);
    }
}
