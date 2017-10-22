package ford.pivotaltracker.report;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ford.pivotaltracker.service2.Story;

public class ReaderTest {

	@Before
	public void setup() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("test-basic.yml");
		ConfigManager.init(new File(resource.toURI()));
	}
	
	@Test
	public void reader_readsForProject_UsingConfigManagerSingleton() throws Exception {
		Reader reader = new Reader();
		Map<String, Map<String, List<Story>>> processed = reader.process();
		System.out.println(processed.keySet());
		System.out.println(processed.get("Torque_CN_Android").keySet());
		System.out.println(processed.get("Torque_CN_Android").get("all").size());
		System.out.println(processed.get("Torque_CN_Android").get("accepted").size());
		
	}
}
