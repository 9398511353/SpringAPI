package com.example.demo.api;

import com.example.demo.service.PersonService;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonResource {

    private final PersonService personService;

    @Autowired
    public PersonResource(PersonService personService){
        this.personService = personService ;

    }
    @PostMapping
    public void addPerson(@Validated @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public static List<Person> getAllPeople() {
        return PersonService.getAllPeople();

    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") UUID id) {

        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}" )
    public void updatePerson(@PathVariable("id") UUID id , @Validated @NonNull @RequestBody Person personToUpdate){
        personService.updatePerson(id,personToUpdate);
    }
}
