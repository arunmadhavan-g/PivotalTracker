package ford.pivotaltracker.service2;

import java.io.IOException;
import java.util.List;

import lombok.Data;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Data
public class Service {
	private static final int LIMIT = 500;
	private String projectId;
	private TrackerService service;

	public Service(String projectId) {
		this.projectId = projectId;
		this.service = new Retrofit.Builder()
				.baseUrl("https://www.pivotaltracker.com/")
				.addConverterFactory(GsonConverterFactory.create())
				.build()
				.create(TrackerService.class);
	}
	
	public List<Story> fetchForTag(String tag) throws IOException{
		return service.fetchStoriesForEpic(projectId, tag, LIMIT)
						.execute()
						.body();
	}
	
	public List<Story> fetchForTagBeforeStory(String tag, String storyId) throws IOException{
		return service.fetchStoriesForEpicBeforeStory(projectId, tag, LIMIT, storyId)
				.execute()
				.body();
	}
	
	public List<Story> fetchForTagBetweenStories(String tag, String fromId, String toId) throws IOException{
		return service.fetchStoriesForEpicBetweenStories(projectId, tag, LIMIT, fromId, toId)
				.execute()
				.body();
	}
}
