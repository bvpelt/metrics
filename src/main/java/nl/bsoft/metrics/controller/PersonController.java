package nl.bsoft.metrics.controller;

import io.micrometer.core.annotation.Timed;
import nl.bsoft.metrics.model.Person;
import nl.bsoft.metrics.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Timed
public class PersonController {
    private Logger logger = Logger.getLogger(PersonController.class.getName());

    @Autowired
    PersonRepository repository;

    @GetMapping("/persons/geslacht/{geslacht}")
    public List findByGeslacht(@PathVariable("geslacht") String geslacht) {
        logger.info(String.format("Person.findByGeslacht(%s)", geslacht));
        return repository.findByGeslacht(geslacht);
    }

    @GetMapping("/persons/{id}")
    public Person findById(@PathVariable("id") Integer id) {
        logger.info(String.format("Person.findById(%d)", id));
        return repository.findById(id).get();
    }

    @GetMapping("/persons")
    public List findAll() {
        logger.info(String.format("Person.findAll()"));
        return (List) repository.findAll();
    }

    @PostMapping("/persons")
    public Person add(@RequestBody Person person) {
        logger.info(String.format("Person.add(%s)", person));
        return repository.save(person);
    }

    @PutMapping("/persons")
    public Person update(@RequestBody Person person) {
        logger.info(String.format("Person.update(%s)", person));
        return repository.save(person);
    }

    @DeleteMapping("/persons/{id}")
    public void remove(@PathVariable("id") Integer id) {
        logger.info(String.format("Person.remove(%d)", id));
        repository.deleteById(id);
    }

}
