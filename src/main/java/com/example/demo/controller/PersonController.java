package com.example.demo.controller;

import com.example.demo.dto.BookRequestDto;
import com.example.demo.model.Book;
import com.example.demo.model.Person;
import com.example.demo.service.BookService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable int id) {
        return ResponseEntity.ok(personService.getPerson(id));
    }

    @DeleteMapping(value = "/persons/{id}")
    public ResponseEntity<Object> removePerson(@PathVariable int id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/persons")
    public ResponseEntity<Object> addPerson(@RequestBody Person person) {
        int newPersonId = personService.addPerson(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPersonId).toUri();

        return ResponseEntity.created(location).build();
    }

//    @PutMapping(value = "/persons/{id}")
//    public ResponseEntity<Object> updatePerson(@PathVariable int id, @RequestBody Person person) {
//        personService.updatePerson(id, person);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @PatchMapping(value = "/persons/{id}")
//    public ResponseEntity<Object> updatePersonPartially(@PathVariable int id, @RequestBody Person person) {
//        personService.partialUpdatePerson(id, person);
//
//        return ResponseEntity.noContent().build();
//    }
}
