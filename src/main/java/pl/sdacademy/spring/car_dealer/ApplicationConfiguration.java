package pl.sdacademy.spring.car_dealer;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sdacademy.spring.car_dealer.controller.CarDataController;
import pl.sdacademy.spring.car_dealer.controller.SellingController;
import pl.sdacademy.spring.car_dealer.repository.*;
import pl.sdacademy.spring.car_dealer.service.CarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultCarDataService;
import pl.sdacademy.spring.car_dealer.service.DefaultSellingService;
import pl.sdacademy.spring.car_dealer.service.SellingService;

@Configuration
public class ApplicationConfiguration {


    // Repositories
    @Bean (name = "hardDriveCustomerRepository", initMethod = "initialize", destroyMethod = "cleanUp")
    public HardDriveCustomerRepository hardDriveCustomerRepository() {
        return new HardDriveCustomerRepository("customers.ser");
    }

    @Bean (name = "hardDrivePurchaseRepository", initMethod = "initialize", destroyMethod = "cleanUp")
    public HardDrivePurchaseRepository hardDrivePurchaseRepository() {
        return new HardDrivePurchaseRepository("purchases.ser");
    }

    @Bean (name = "hardDriveVehicleRepository", initMethod = "initialize", destroyMethod = "cleanUp")
    public HardDriveVehicleRepository hardDriveVehicleRepository() {
        return new HardDriveVehicleRepository("vehicles.ser");
    }


    //Services
    @Bean (name = "defaultCarDataService" )
    public DefaultCarDataService defaultCarDataService(VehicleRepository vehicleRepository) {
        return new DefaultCarDataService(vehicleRepository);
    }

    @Bean (name = "defaultSellingService")
    public DefaultSellingService defaultSellingService(VehicleRepository vehicleRepository, CustomerRepository customerRepository, PurchaseRepository purchaseRepository){
        return new DefaultSellingService(vehicleRepository, customerRepository, purchaseRepository);
    }


    //Controllers
    @Bean (name = "carDataController")
    public CarDataController carDataController(CarDataService carDataService) {
        return new CarDataController(carDataService);
    }

    @Bean (name = "sellingController")
    public SellingController sellingController(SellingService sellingService) {
        return new SellingController(sellingService);
    }

    @Bean(name = "application", autowire = Autowire.BY_TYPE)
    public Application application() {
        return new Application();
    }
}
