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

import ford.pivotaltracker.analyze.Analyzer;
import ford.pivotaltracker.analyze.Report;
import ford.pivotaltracker.beans.tracker.Story;
import ford.pivotaltracker.read.ConfigManager;
import ford.pivotaltracker.read.Reader;

public class ReportGeneratorTest {

	@Before
	public void setup() throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("test-basic.yml");
		ConfigManager.init(new File(resource.toURI()));
	}
	
	@Test
	public void createAggregateReportForGivenConfiguration() throws Exception {
		Reader reader = new Reader();
		Map<String, Map<String, Map<String, List<Story>>>> data = reader.process();
		
		Analyzer analyzer = new Analyzer();
		Report analyzedData = analyzer.aggregateStories(data.get("Torque_CN_Android"));
		System.out.println(analyzedData);
		//assertThat(analyzedData).extracting("total").isEqualTo(new Integer[] {10, 20, 30});
	}
}
