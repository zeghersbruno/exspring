package be.abis.controller;

import be.abis.model.Course;
import be.abis.model.Person;
import be.abis.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AbisController {

    @Autowired
    TrainingService trainingService;

    @GetMapping("/")
    public String showCourse(Model model) {
        Course c = trainingService.getCourse(7900);
        model.addAttribute("course", c);
        return "course";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        Person p = new Person();
        model.addAttribute("person", p);
        return "login";
    }

    @PostMapping("/login")
    public String submitLogin(Model model, Person p) {
        Person person = trainingService.findPerson(p.getEmailAddress(), p.getPassword());
        model.addAttribute("name", person.getFirstName());
        return "login";
    }
}