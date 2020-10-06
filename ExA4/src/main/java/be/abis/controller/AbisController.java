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
    CourseService courseService;

    @Autowired
    TrainingService trainingService;


    @GetMapping("/")
    public String showCourse(Model model) {
        Course c = courseService.findCourse(7900);
        model.addAttribute("course", c);
        return "course";
    }

    @GetMapping("/training")
    public String showTraining(Model model) {
        Person p = trainingService.findPerson(2);
        List<Course> courses = trainingService.showFollowedCourses(p);
        model.addAttribute("courses", courses);
        return "course";
    }
}
