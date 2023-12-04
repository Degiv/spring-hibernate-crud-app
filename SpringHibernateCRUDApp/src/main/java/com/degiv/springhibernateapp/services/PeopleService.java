package com.degiv.springhibernateapp.services;

import com.degiv.springhibernateapp.models.Person;
import com.degiv.springhibernateapp.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public List<Person> findByName(String name) {
        return peopleRepository.findByName(name);
    }

    public List<Person> findByNameOrderByAge(String name) {
        return peopleRepository.findByNameOrderByAge(name);
    }

    public List<Person> findByNameStartingWith(String startingWith) {
        return peopleRepository.findByNameStartingWith(startingWith);
    }

    public List<Person> findByNameOrEmail(String name, String email) {
        return peopleRepository.findByNameOrEmail(name, email);
    }

    public void test() {
        System.out.println("testing while in transaction");
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person); //repository updates person because person with given id already exists
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
