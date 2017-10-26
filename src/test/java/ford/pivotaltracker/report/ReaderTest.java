package ford.pivotaltracker.report;

import static org.assertj.core.api.Assertions.assertThat;

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

import ford.pivotaltracker.data.Story;

public class ReaderTest {

	@Before
	public void setup() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("test-basic.yml");
		ConfigManager.init(new File(resource.toURI()));
	}
	
	@Test
	public void reader_readsForProject_UsingConfigManagerSingleton() throws Exception {
		Reader reader = new Reader();
		Map<String, Map<String, Map<String, List<Story>>>> processed = reader.process();
		assertThat(processed.get("Torque_CN_Android")).containsKeys("v1.9","new sms flow","extended warranty");
		assertThat(processed.get("Torque_CN_Android").get("v1.9").get("all")).hasSize(126);
		assertThat((processed.get("Torque_CN_Android").get("v1.9").get("qc_pass"))).hasSize(2);
	}
}
