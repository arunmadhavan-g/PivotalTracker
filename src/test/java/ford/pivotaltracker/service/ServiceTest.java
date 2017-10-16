package ford.pivotaltracker.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;
import org.junit.Test;

public class ServiceTest {
	private static final String NEW_SMS_FLOW = "new sms flow";
	private static final String[] TAGS = { NEW_SMS_FLOW, "extended warranty", "fuel-poi favorites", "cmax", "recall",
			"defects 1.9", "appreciation 1.9", "global 1.9", "credit 1.9", "v1.9" };
	private static final String[] PROJECTS = { "1431430", "1431428" };

	String projectId = "1431430";
	Service service = new Service(projectId);
	Analyzer analyzer = new Analyzer();

	@Test
	public void fetchStories_ForAnEpic() throws Exception {
		assertThat(service.fetchStoriesForEpic("v1.9")).isNotEmpty();
	}

	@Test
	public void createMapOfStoriesForEachLabel() throws Exception {
		Map<String, List<Story>> consolidatedList = service.fetchForEpicsList(Lists.newArrayList(TAGS));
		assertThat(consolidatedList.get(NEW_SMS_FLOW).size()).isEqualTo(5);
	}

	@Test
	public void convertStoryToReport() throws Exception {
		List<Story> stories = service.fetchStoriesForEpic(NEW_SMS_FLOW);
		Report report = analyzer.consolidateStories(NEW_SMS_FLOW, stories);
		assertThat(report.hasTotal(5)).isTrue();
	}

	@Test
	public void convertStoryMaptoReportList() throws Exception {
		Map<String, List<Story>> consolidatedList = service.fetchForEpicsList(Lists.newArrayList(TAGS));
		List<Report> reportList = analyzer.consolidateReport(consolidatedList);
		assertThat(reportList).hasSize(TAGS.length);
	}

	@Test
	public void writeToExcelReport() throws Exception {
		Writer writer = new Writer("C:\\Arun\\Report.xls");
		for (String projectId : PROJECTS) {
			Service service = new Service(projectId);
			Map<String, List<Story>> consolidatedList = service.fetchForEpicsList(Lists.newArrayList(TAGS));
			List<Report> reportList = analyzer.consolidateReport(consolidatedList);
			writer.writeReport(projectId, reportList);
		}
		writer.flush();
	}

}
