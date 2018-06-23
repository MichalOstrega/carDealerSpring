package pl.sdacademy.spring.car_dealer.repository;

import java.util.Map;

public interface CustomerSumFinder {
    Map<String, Long> purchasesSumByCustomer();
}
