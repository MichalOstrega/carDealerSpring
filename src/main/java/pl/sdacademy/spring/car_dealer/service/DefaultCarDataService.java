package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.VehicleFinder;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class DefaultCarDataService implements CarDataService {
    private VehicleRepository vehicleRepository;
    private final VehicleFinder vehicleFinder;

    public DefaultCarDataService(VehicleRepository vehicleRepository, VehicleFinder vehicleFinder) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleFinder = vehicleFinder;
    }



    public List<Vehicle> loadCarsThatCanBeSold() {
        return vehicleFinder.getAvailable();
    }

    public List<Vehicle> loadAllCars() {
        Iterable<Vehicle> all = vehicleRepository.findAll();
        List<Vehicle> vehicles = new ArrayList<>();
        all.forEach(vehicles::add);
        return vehicles;
    }


    @Override
    public Vehicle addVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);
    }


}
