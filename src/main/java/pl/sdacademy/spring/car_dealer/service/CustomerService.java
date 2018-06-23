package pl.sdacademy.spring.car_dealer.service;

import pl.sdacademy.spring.car_dealer.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> searchByLastName(String lastName);
    List<Customer> serachByFirstOrLastName(String name);
}
