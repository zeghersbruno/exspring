package be.abis.controller;

import be.abis.model.Person;
import be.abis.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/")
    public  String showPerson(Model model) {
        Person p = helloService.findperson(5);
        model.addAttribute("person", p);
        return "HelloSpring";
    }

}
