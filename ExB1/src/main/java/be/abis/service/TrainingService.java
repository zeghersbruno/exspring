package be.abis.service;

import be.abis.exception.EnrollException;
import be.abis.model.Course;
import be.abis.model.Person;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface TrainingService {

    // Person
    public Person findPerson(int id);
    public List<Person> getAllPersons();
    public Person findPerson(String emailAddress, String passWord);
    void addPerson(Person p) throws IOException;
    public void deletePerson(int id);
    void changePassword(Person p, String newPswd) throws IOException;


    // Course
    public List<Course> showFollowedCourses(Person person);
    public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException;
    public Course getCourse(int id);
    public List<Course> getAllCourses();

}
