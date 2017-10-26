package ford.pivotaltracker.report;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import ford.pivotaltracker.analyze.Analyzer;
import ford.pivotaltracker.analyze.Report;
import ford.pivotaltracker.beans.tracker.Story;
import ford.pivotaltracker.read.Reader;

public class ReportGeneratorTest {

	@Test
	public void createAggregateReportForGivenConfiguration() throws Exception {
		Reader reader = new Reader();
		Map<String, Map<String, Map<String, List<Story>>>> data = reader.process();
		
		Analyzer analyzer = new Analyzer();
		List<Report> analyzedData = analyzer.aggregateStories(data);
		assertThat(analyzedData).extracting("total").isEqualTo(new Integer[] {10, 20, 30});
	}
}
