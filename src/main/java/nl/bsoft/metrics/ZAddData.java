package nl.bsoft.metrics;

import nl.bsoft.metrics.model.Person;

import java.util.Random;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.web.client.RestTemplate;

public class ZAddData {
    private Logger logger = Logger.getLogger(ZAddData.class.getName());

    private RestTemplate template = new RestTemplate();
    private Random r = new Random();

    public void addData() {
        for (int j = 0; j < 1000; j++) {
            int ix = new Random().nextInt(100000);
            Person p = new Person();
            p.setFirstName("Jan" + ix);
            p.setLastName("Test" + ix);
            p.setGeslacht("M");
            p.setAge(ix % 100);
            p = template.postForObject("http://localhost:8080/persons", p, Person.class);
            logger.info("New person: " + p);

            p = template.getForObject("http://localhost:8080/persons/{id}", Person.class, p.getId());
            p.setAge(ix % 100);
            template.put("http://localhost:8080/persons", p);
            logger.info("Person updated: " + p + " with age=" + ix % 100);

            template.delete("http://localhost:8080/persons/{id}", p.getId());
        }
    }

    public static void main(String[] args) {

        ZAddData za = new ZAddData();

        za.addData();
    }
}
