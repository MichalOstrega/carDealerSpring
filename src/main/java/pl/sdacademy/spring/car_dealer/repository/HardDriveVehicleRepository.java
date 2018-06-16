package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.util.List;
import java.util.stream.Collectors;


@Repository
public class HardDriveVehicleRepository extends AbstractHardDriveRepository<Vehicle> implements VehicleRepository {

    private final String repositoryLocation;

    @Autowired
    public HardDriveVehicleRepository(@Value("${repository.vehicle.hardDriveLocation}") String repositoryLocation) {
        this.repositoryLocation = repositoryLocation;
    }

    @Override
    public Vehicle byId(Long id) {
        return loadAllElements().stream().filter(vehicle -> vehicle.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public List<Vehicle> getAll() {
        return loadAllElements();
    }

    @Override
    public List<Vehicle> getAvailable() {
        return loadAllElements().stream().filter(vehicle -> !vehicle.isSold()).collect(Collectors.toList());
    }

    @Override
    public Vehicle update(Vehicle newVehicle) {
        List<Vehicle> allVehicles = loadAllElements();
        List<Vehicle> filteredOutVehicles = allVehicles.stream().filter(oldVehicle -> !oldVehicle.getId().equals(newVehicle.getId())).collect(Collectors.toList());
        filteredOutVehicles.add(newVehicle);
        saveAllElements(filteredOutVehicles);
        return newVehicle;
    }

    @Override
    protected String getRepositoryLocation() {
        return repositoryLocation;
    }
}
