package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {


    @RequestMapping("/hi")
    public String Welcome(Model model){
        model.addAttribute("name", "Michal");
        return "hello";
    }
}
