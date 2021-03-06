package pl.sdacademy.spring.car_dealer.service;

import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.util.List;

public interface CarDataService {
    List<Vehicle> loadCarsThatCanBeSold();
    List<Vehicle> loadAllCars();
    Vehicle addVehicle(Vehicle vehicle);

    Vehicle getById(Long vehicleId);
}
