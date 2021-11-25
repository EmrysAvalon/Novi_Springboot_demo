package com.example.demo.service;

import com.example.demo.dto.BookRequestDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.model.Person;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person getPerson(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        return optionalPerson.get();
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }

    public int addPerson(Person person) {
        Person newPerson = personRepository.save(person);
        return newPerson.getId();
    }

}
