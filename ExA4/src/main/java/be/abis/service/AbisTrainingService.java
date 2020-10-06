package be.abis.service;

import be.abis.exception.EnrollException;
import be.abis.model.Course;
import be.abis.model.Person;
import be.abis.repository.FilePersonRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class AbisTrainingService implements TrainingService {

    @Autowired
    FilePersonRepository filePersonRepository;

    @Override
    public Person findPerson(int id) {
        return filePersonRepository.findPerson(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return filePersonRepository.getAllPersons();
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) {
        return filePersonRepository.findPerson(emailAddress, passWord);
    }

    @Override
    public void addPerson(Person p) throws IOException {
        filePersonRepository.addPerson(p);
    }

    @Override
    public void deletePerson(int id) {
        filePersonRepository.deletePerson(id);
    }

    @Override
    public void changePassword(Person p, String newPswd) throws IOException {
        filePersonRepository.changePassword(p, newPswd);
    }

    @Override
    public List<Course> showFollowedCourses(Person person) {

        return null;
    }

    @Override
    public void enrollForSession(Person person, Course course, LocalDate date)
        throws EnrollException {

    }
}
