package be.abis.controller;

import be.abis.model.Course;
import be.abis.model.Person;
import be.abis.service.CourseService;
import be.abis.service.TrainingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
