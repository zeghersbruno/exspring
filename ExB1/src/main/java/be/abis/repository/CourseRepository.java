package be.abis.repository;


import be.abis.model.Course;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface CourseRepository {

	public List<Course> findAllCourses();
	public Course findCourse(int id);
	public Course findCourse(String shortTitle);
		
}
