package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.service.CarDataService;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/vehicles")
public class CarDataController {

    private final CarDataService carDataService;

    public CarDataController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }

    @RequestMapping("/{id}")
    public String getCar(@PathVariable("id") Long vehicleId, Model model){
        Vehicle vehicle = carDataService.getById(vehicleId);
        if (vehicle != null) {
            model.addAttribute("vehicle", vehicle);
        }


        return "vehicleDetails";
    }

    public void printAvailableCars() {
        List<Vehicle> vehicles = carDataService.loadCarsThatCanBeSold();
        vehicles.sort(Comparator.comparing(Vehicle::getId));
        vehicles.forEach(vehicle -> {
            printVehicle(vehicle);
        });
    }


    public void printAllCars(){
        List<Vehicle> vehicles = carDataService.loadAllCars();
        vehicles.forEach(vehicle -> {
            printField(vehicle.getManufacturer());
            printField(vehicle.getModel());
            printField(vehicle.getPrice().toString());
            printField(String.valueOf(vehicle.isSold()));
            System.out.println();
        });
    }

    private void printField(String field) {
        System.out.print("{" + field + "}");
    }

    public void createCar(){
        Vehicle vehicle = new Vehicle();
        System.out.println("Provide Vehicle data:");
        System.out.print("Manufacturer:");
        vehicle.setManufacturer(readLine());

        System.out.print("Model:");
        vehicle.setModel(readLine());
        System.out.print("Fuel:");
        vehicle.setFuel(readLine());
        vehicle.setProductionYear(getProductionYear());
        vehicle.setMileage(getMileage());
        vehicle.setPrice(getPrice());
        vehicle.setSold(false);


        vehicle = carDataService.addVehicle(vehicle);

        System.out.println("Added vehicle: ");
        printVehicle(vehicle);
    }

    private String readLine() {
        return new Scanner(System.in).nextLine();
    }

    private Long getPrice() {
        while (true) {
            try {
                System.out.print("Price: ");
                return Long.parseLong(readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price. Provide again.");
            }
        }
    }

    private Long getProductionYear() {
        while (true) {
            try {
                System.out.print("Production year: ");
                return Long.parseLong(readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid year. Provide again.");
            }
        }
    }

    private Long getMileage() {
        while (true) {
            try {
                System.out.print("Mileage: ");
                return Long.parseLong(readLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid mileage. Provide again.");
            }
        }
    }

    private void printVehicle(Vehicle vehicle) {
        System.out.println(String.format("%d: %s %s from %d with %s powered engine and total mileage of %d for only %d!",
                vehicle.getId(),
                vehicle.getManufacturer(),
                vehicle.getModel(),
                vehicle.getProductionYear(),
                vehicle.getFuel(),
                vehicle.getMileage(),
                vehicle.getPrice()));
    }

}
