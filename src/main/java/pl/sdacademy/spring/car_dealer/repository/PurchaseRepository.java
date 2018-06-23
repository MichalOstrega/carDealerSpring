package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sdacademy.spring.car_dealer.model.Purchase;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase,Long> {

}
