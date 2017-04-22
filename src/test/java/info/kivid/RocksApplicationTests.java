package info.kivid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RocksApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void exampleTest() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).contains("eesmärgid");
	}

	@Test
	public void exampleLanguageTest() {
		String body = this.restTemplate.getForObject("/resources", String.class);
		assertThat(body).containsIgnoringCase("Maavarad");
		body = this.restTemplate.getForObject("/resources?lang=en", String.class);
		assertThat(body).containsIgnoringCase("Natural resources are resources that exist without actions of humankind. This includes all valued characteristics such as magnetic, gravitational, and electrical properties and forces. On earth it includes: sunlight, atmosphere, water, land (includes all minerals) along with all vegetation and animal life that naturally subsists upon or within the heretofore identified characteristics and substances.");
	}
}
