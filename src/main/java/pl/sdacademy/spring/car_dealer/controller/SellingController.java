package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.service.SellingService;

import java.util.List;
import java.util.Scanner;


@Controller
public class SellingController {

    private final SellingService sellingService;

    public SellingController(SellingService sellingService) {
        this.sellingService = sellingService;
    }

    public void buyVehicle(Long vehicleId) {
        Customer customer = getCustomerData();
        Long customerPrice = getCustomerPrice();
        sellingService.sell(vehicleId, customer, customerPrice);
    }

    public void showPurchaseHistory(){
        System.out.println("Provide customer Document No:");
        String docNo = readInput();
        List<Purchase> history = sellingService.loadHistory(docNo);
        history.forEach(System.out::println);

    }

    public void showPurchaseHistoryBetweenPrices(){
        System.out.println("Provide lowest Price:");
        Long lowPrice = getPrice();

        System.out.println("Provide highest Price:");
        Long highPrice = getPrice();
        List<Purchase> history = sellingService.getHistoryBeetwen(lowPrice,highPrice);
        history.forEach(System.out::println);

    }

    private Long getPrice() {
        while(true){
            try{
                return Long.valueOf(readInput());
            }
            catch (NumberFormatException ex){
                System.out.println("Invalid price. Try again");
            }
        }

    }


    private Customer getCustomerData() {
        Customer customer = new Customer();
        System.out.println("Provide customer data:");
        System.out.print("   Name: ");
        customer.setName(readInput());
        System.out.print("   Last name: ");
        customer.setSurname(readInput());
        System.out.print("   Document number: ");
        customer.setDocumentNo(readInput());
        System.out.print("   Telephone: ");
        customer.setTelephone(readInput());
        return customer;
    }

    private Long getCustomerPrice() {
        while (true) {
            try {
                System.out.print("Selling price for this customer: ");
                return Long.parseLong(readInput());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Provide again.");
            }
        }
    }

    private String readInput() {
        return new Scanner(System.in).nextLine();
    }
}
