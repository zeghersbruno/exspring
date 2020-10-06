package be.abis.service;

import be.abis.exception.EnrollException;
import be.abis.model.Course;
import be.abis.model.Person;
import be.abis.repository.FilePersonRepository;
import be.abis.repository.PersonRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbisTrainingService implements TrainingService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person findPerson(int id) {
        return personRepository.findPerson(id);
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

    @Override
    public Person findPerson(String emailAddress, String passWord) {
        return personRepository.findPerson(emailAddress, passWord);
    }

    @Override
    public void addPerson(Person p) throws IOException {
        personRepository.addPerson(p);
    }

    @Override
    public void deletePerson(int id) {
        personRepository.deletePerson(id);
    }

    @Override
    public void changePassword(Person p, String newPswd) throws IOException {
        personRepository.changePassword(p, newPswd);
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
