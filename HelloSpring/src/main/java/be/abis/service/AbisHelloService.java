package be.abis.service;

import be.abis.model.Person;
import org.springframework.stereotype.Service;

@Service
public class AbisHelloService implements HelloService {

    @Override
    public Person findperson(int id) {
        Person person = new Person("Bruno");
        return person;
    }
}
