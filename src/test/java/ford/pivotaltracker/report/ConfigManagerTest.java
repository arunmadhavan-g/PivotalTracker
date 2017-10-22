package ford.pivotaltracker.report;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ConfigManagerTest {

	private File getFile(String fileName) throws URISyntaxException {
		URL resource = Thread.currentThread().getContextClassLoader().getResource(fileName);
		return new File(resource.toURI());
	}
	
	@Test
	public void parseYamlConfig() throws Exception {
		ConfigManager.init(getFile("report.yml"));
		assertThat(ConfigManager.configs()).extracting("name").isEqualTo(Lists.newArrayList("Torque_CN_Android","Torque CN iOS"));
	}

	@Test
	public void parsedConfig_TransformsTo_URLMap() throws Exception {
		ConfigManager.init(getFile("test-basic.yml"));
		Map<String, Map<String, UrlBuilder>> transformed = ConfigManager.transformed();
		Map<String, UrlBuilder> transformedMap = transformed.get("Torque_CN_Android");
		assertThat(transformedMap).containsKeys("all","accepted");
	}
	
	
}
