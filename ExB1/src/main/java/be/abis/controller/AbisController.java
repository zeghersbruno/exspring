package be.abis.controller;

import be.abis.model.Course;
import be.abis.model.Login;
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

    Person person;

    @GetMapping("/course")
    public String showCourse(Model model) {
        Course c = trainingService.getCourse(7900);
        model.addAttribute("course", c);
        return "course";
    }

    @GetMapping("/")
    public String showLogin(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "login";
    }

    @GetMapping("/login")
    public String showLoginAgain(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "login";
    }


    @GetMapping("/welcome")
    public String showWelcome(Model model) {
        model.addAttribute("person", person);
        return "welcome";
    }


    @PostMapping("/")
    public String submitLogin(Model model, Login login) {
        System.out.println("person à logger " + login.getEmail() + " " + login.getPassword());
        person = trainingService.findPerson(login.getEmail(), login.getPassword());
        if (person==null) {
            System.out.println("person not found");
            return "redirect:/login";
        } else {
            model.addAttribute("person", person.getFirstName());
            return "redirect:/welcome";
        }
    }
}
