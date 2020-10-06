package be.abis.controller;

import be.abis.model.Course;
import be.abis.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AbisController {

    @Autowired
    CourseService courseService;

    @GetMapping("/")
    public String showCourse(Model model) {
        Course c = courseService.findCourse(7900);
        model.addAttribute("course", c);
        return "course";
    }
}
