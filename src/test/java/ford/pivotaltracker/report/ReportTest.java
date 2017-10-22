package ford.pivotaltracker.report;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

import ford.pivotaltracker.service2.Service;
import ford.pivotaltracker.service2.Story;

public class ReportTest {

	private File file;
	
	public void setup() throws URISyntaxException {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("report.yml");
		file = new File(resource.toURI());
	}
	
	@Test
	public void fetchResultsForMainTag() throws Exception {
		String mainTag =  "v1.9";
		Service service = new Service("1431430");
		List<Story> stories = service.fetchForTag(mainTag);
		assertThat(stories).hasSize(126);
	}
	
	@Test
	public void parseYamlConfig() throws Exception {
		ConfigManager.init(file);
		assertThat(ConfigManager.configs()).extracting("name").isEqualTo(Lists.newArrayList("Torque_CN_Android","Torque CN iOS"));
	}

	@Test
	public void parsedConfig_TransformsTo_URLMap() throws Exception {
		ConfigManager.init(file);
		ConfigManager.transform();
		assertThat(ConfigManager.transformed()).containsKeys("Torque_CN_Android","Torque CN iOS");
	}
	
	
	
}
