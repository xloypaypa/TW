package hello;

import bookshelf.Main;
import bookshelf.repository.RepositoryTest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class PageControllerTest extends RepositoryTest {

    @Value("${local.server.port}")
    private int port;

	@Test
	public void should_get_3_books_when_get_all_books() throws Exception {
		URL base = new URL("http://localhost:" + port + "/getAllBooks");
		RestTemplate template = new TestRestTemplate();

		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String body = response.getBody();
		JSONArray jsonArray = JSONArray.fromObject(body);

		assertEquals(3, jsonArray.size());
	}

	@Test
	public void should_get_Refactoring_when_get_book_with_isbn_9780201485677() throws Exception {
		URL base = new URL("http://localhost:" + port + "/getBook?isbn=9780201485677");
		RestTemplate template = new TestRestTemplate();

		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String body = response.getBody();
		JSONObject jsonObject = JSONObject.fromObject(body);

		assertEquals("Refactoring", jsonObject.getString("name"));
	}

	@Test
	public void should_get_other_2_books_when_remove_book_with_isbn_9780201485677() throws Exception {
		URL base = new URL("http://localhost:" + port + "/removeBook?isbn=9780201485677");
		RestTemplate template = new TestRestTemplate();

		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String body = response.getBody();
		JSONArray jsonArray = JSONArray.fromObject(body);

		assertEquals(2, jsonArray.size());
	}

	@Test
	public void should_get_other_4_books_when_remove_book_with_isbn_9780201485677() throws Exception {
		URL base = new URL("http://localhost:" + port + "/addBook?isbn=9781931859455&name=History of the Russian Revolution&author=Leon Trotsky&price=32.00");
		RestTemplate template = new TestRestTemplate();

		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String body = response.getBody();
		JSONArray jsonArray = JSONArray.fromObject(body);

		assertEquals(4, jsonArray.size());
	}

	@Test
	public void should_get_changed_book_data_when_update_book_with_isbn_9780201485677() throws Exception {
		URL base = new URL("http://localhost:" + port + "/updateBook?isbn=9780201485677&name=Refactoring&author=Martin Fowler&price=65.00");
		RestTemplate template = new TestRestTemplate();

		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		String body = response.getBody();
		JSONArray jsonArray = JSONArray.fromObject(body);

		for (Object now : jsonArray) {
			JSONObject jsonObject = (JSONObject) now;
			if (jsonObject.getString("isbn").equals("9780201485677")) {
				assertEquals(65, jsonObject.getDouble("price"), 1e-3);
			}
		}
	}

}
