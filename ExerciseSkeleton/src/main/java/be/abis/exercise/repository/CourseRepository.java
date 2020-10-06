package main.java.be.abis.exercise.repository;

import java.util.List;
import main.java.be.abis.exercise.model.Course;


public interface CourseRepository {

	public List<Course> findAllCourses();
	public Course findCourse(int id);
	public Course findCourse(String shortTitle);
		
}
