package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    @Query("Select v from Vehicle v where v.id= :vehId AND v.sold = false")
    Optional<Vehicle> findNotSoldVehicle(
            @Param("vehId") Long Id);

    @Query("Select v from Vehicle  v where v.manufacturer =:vehMan")
    Optional<Vehicle> findVehicleByManufacturer(
            @Param("vehMan") String Manufacturer);

    @Query("select v from Vehicle v where v.price between :minPrice and :maxPrice")
    Optional<Vehicle> findVehicleByPriceBetween(
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice);
}
