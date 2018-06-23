package pl.sdacademy.spring.car_dealer.controller;


import org.springframework.stereotype.Controller;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.service.CustomerService;

import java.util.List;
import java.util.Scanner;

@Controller
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void searchInDB(){
        Long choice = -1L;
        while (choice != 9L) {
            printMenu();
            choice = readInput();
            switch (choice.intValue()) {
                case 1:
                    System.out.println("Input LastName:");
                    String lastName = readLine();
                    List<Customer> customers = customerService.searchByLastName(lastName);
                    if (!customers.isEmpty()) {
                        customers.forEach(System.out::println);
                    }
                    break;
                case 2:
                    System.out.println("Input name:");
                    String name = readLine();
                    List<Customer> customers2 = customerService.serachByFirstOrLastName(name);
                    if (!customers2.isEmpty()) {
                        customers2.forEach(System.out::println);
                    }
                    break;

                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println();
        System.out.println("How do you want to search");
        System.out.println("1) By LastName");
        System.out.println("2) By Last or FirstName");
        System.out.print("What is your choice? ");
    }
    private Long readInput() {
        try {
            return Long.parseLong(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            return -1L;
        }
    }

    public void showCustomers(){
        List<Customer> customers = customerService.getAll();
        customers.forEach(System.out::println);
    }


    public CustomerService getCustomerService() {
        return customerService;
    }

    private String readLine() {
        return new Scanner(System.in).nextLine();
    }
}
