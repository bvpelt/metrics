package nl.bsoft.metrics;

import nl.bsoft.metrics.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetricsApplicationTests {
	private Logger logger = Logger.getLogger(MetricsApplicationTests.class.getName());

	TestRestTemplate template = new TestRestTemplate();
	Random r = new Random();

	@Test
	public void contextLoads() {
	}

	@Test
	public void addData() {
		for (int j = 0; j < 10; j++) {
			int ix = new Random().nextInt(100000);
			Person p = new Person();
			p.setFirstName("Jan" + ix);
			p.setLastName("Testowy" + ix);
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
}
