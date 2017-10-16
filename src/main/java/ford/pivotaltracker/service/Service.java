package ford.pivotaltracker.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

	private String projectId;
	private Retrofit request;

	public Service(String projectId) {
		this.projectId = projectId;

		this.request = new Retrofit.Builder()
				.baseUrl("https://www.pivotaltracker.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
	
	public List<Story> fetchStoriesForEpic(String epicName) throws IOException {
		return this.request.create(TrackerService.class)
				.fetchStoriesForEpic(this.projectId, epicName, 500)
				.execute()
				.body();
	}

	public Map<String, List<Story>> fetchForEpicsList(ArrayList<String> epicList) throws IOException {
		Map<String, List<Story>> consolidatedMap = new LinkedHashMap<>();
		for(String epic: epicList) {
			consolidatedMap.put(epic, fetchStoriesForEpic(epic));
		}
		return consolidatedMap;
	}

}
