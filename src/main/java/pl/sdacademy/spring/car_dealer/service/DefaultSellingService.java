package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.CustomerRepository;
import pl.sdacademy.spring.car_dealer.repository.PurchaseRepository;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

import java.util.Date;
import java.util.List;


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

    @Transactional
    public Purchase sell(Long vehicleId, final Customer customer, Long price) {
        return vehicleRepository.findNotSoldVehicle(vehicleId)
                .map(vehicle -> performSell(vehicle, customer, price))
                .orElse(null);


    }

    @Override
    public Purchase getPurchase(Long purchaseId) {
        return purchaseRepository.findOne(purchaseId);
    }

    private Purchase performSell(Vehicle vehicle, Customer customer, Long price) {
        vehicle.setSold(true);
        vehicleRepository.save(vehicle);

        Customer persistedCustomer = customerRepository
                //z repozytorium pobierz klienta po jego numerze dokumentu
                .findCustomerByDocumentNo(customer.getDocumentNo())
                //tego klienta przypisz do referencji persistedCustomer,
                // jeżeli go nie było to zapisz nowego w bazie i jego przypisz do referencji
                .orElseGet(() -> customerRepository.save(customer));
        Purchase purchase = new Purchase();
        purchase.setVehicle(vehicle);
        purchase.setCustomer(persistedCustomer);
        purchase.setDate(new Date());
        purchase.setPrice(price);


        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> loadHistory(String docNo) {
        return purchaseRepository.findByCustomerDocumentNoEquals(docNo);
    }

    @Override
    public List<Purchase> getHistoryBeetwen(Long price1, Long price2) {
        return purchaseRepository.findByPriceBetween(price1, price2);
    }
}
