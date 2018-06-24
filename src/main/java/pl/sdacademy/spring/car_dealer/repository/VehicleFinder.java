package pl.sdacademy.spring.car_dealer.repository;

import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.util.List;

public interface VehicleFinder  {
    List<Vehicle> getAvailable();

    List<Vehicle> getAll();

    Long totalCount();

    Long totalCountForAvailable();
}
