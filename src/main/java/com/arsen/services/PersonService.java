package com.arsen.services;

import com.arsen.models.Person;
import com.arsen.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public void newPerson(Person person) {
        personRepository.save(person);
    }

    public void update(long id, Person updatedPerson) {
        Person person = personRepository.findById(id).orElse(null);
        if(person == null) return;
        person.setFirstName(updatedPerson.getFirstName());
        person.setLastName(updatedPerson.getLastName());
        person.setAge(updatedPerson.getAge());
        personRepository.save(person);
    }

    public void delete(long id) {
        personRepository.deleteById(id);
    }
}
