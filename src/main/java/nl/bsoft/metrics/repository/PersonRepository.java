package nl.bsoft.metrics.repository;

import nl.bsoft.metrics.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    public List<Person> findByGeslacht(String geslacht);
}
