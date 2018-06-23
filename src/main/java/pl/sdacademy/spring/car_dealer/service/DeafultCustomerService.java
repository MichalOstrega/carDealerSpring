package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.repository.CustomerFinder;
import pl.sdacademy.spring.car_dealer.repository.CustomerRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DeafultCustomerService implements CustomerService{
    CustomerRepository customerRepository;
    CustomerFinder customerFinder;

    public DeafultCustomerService(CustomerRepository customerRepository, CustomerFinder customerFinder) {
        this.customerRepository = customerRepository;
        this.customerFinder = customerFinder;
    }

    @Override
    public List<Customer> searchByLastName(String lastName) {
        return customerFinder.searchByLastName(lastName);    }

    @Override
    public List<Customer> serachByFirstOrLastName(String name) {
        return customerFinder.searchByFirstOrLastName(name);
    }

    @Override
    public List<Customer> getAll() {
        ArrayList<Customer> customers = new ArrayList<>();

        Iterable<Customer> all = customerRepository.findAll();

        all.forEach(customer -> customers.add(customer));

        return customers;

    }


    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerFinder getCustomerFinder() {
        return customerFinder;
    }

    public void setCustomerFinder(CustomerFinder customerFinder) {
        this.customerFinder = customerFinder;
    }
}
