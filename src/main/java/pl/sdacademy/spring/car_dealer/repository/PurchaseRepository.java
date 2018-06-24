package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.spring.car_dealer.model.Purchase;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {


    List<Purchase> findByCustomerDocumentNoEquals(String DocNo);

    List<Purchase> findByPriceBetween(Long minPrice, Long maxPrice);

}
