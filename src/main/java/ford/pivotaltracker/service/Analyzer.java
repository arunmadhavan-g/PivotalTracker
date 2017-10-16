package ford.pivotaltracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Analyzer {

	public Report consolidateStories(String epicName, List<Story> stories) {
		Report report = new Report(epicName);
		for(Story story: stories) {
			report = story.updateReport(report);
		}
		return report;
	}

	public List<Report> consolidateReport(Map<String, List<Story>> consolidatedMap) {
		List<Report> reportList = new ArrayList<>();
		for(String epicName: consolidatedMap.keySet()) {
			reportList.add(consolidateStories(epicName, consolidatedMap.get(epicName)));
		}
		return reportList;
	}

}
