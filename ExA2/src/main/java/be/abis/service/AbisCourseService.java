package be.abis.service;

import be.abis.model.Course;
import be.abis.repository.CourseRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbisCourseService implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        List<Course> courses = new ArrayList<>();
        courses = courseRepository.findAllCourses();
        return courses;
    }

    @Override
    public Course findCourse(int id) {
        Course course = new Course();
        course = courseRepository.findCourse(id);
        return course;
    }

    @Override
    public Course findCourse(String shortTitle) {
        Course course = new Course();
        course = courseRepository.findCourse(shortTitle);
        return course;
    }
}
