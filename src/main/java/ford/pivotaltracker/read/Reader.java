package ford.pivotaltracker.read;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ford.pivotaltracker.beans.tracker.Story;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Reader {

	private static final String API_KEY = "4f39020862d53a30161a3e63f48e4c46";
	private final OkHttpClient client = new OkHttpClient();
	
	public Map<String , Map<String, Map<String, List<Story>>>> process() throws URISyntaxException, IOException {
		Map<String, Map<String, Map<String, UrlBuilder>>> allProjectsMap = ConfigManager.transformed();
		Map<String, Map<String, Map<String, List<Story>>>> allProjectBuilderMap = new HashMap<>();
		for(String projectName: allProjectsMap.keySet()) {
			Map<String, Map<String, UrlBuilder>> projectMap = allProjectsMap.get(projectName);  
			Map<String, Map<String, List<Story>>> projectBuilderMap = new HashMap<>();
			for(String tagName: projectMap.keySet()) {
				projectBuilderMap.put(tagName, processProject(projectMap.get(tagName)));
			}
			allProjectBuilderMap.put(projectName, projectBuilderMap);
		}
		return allProjectBuilderMap;
	}

	private Map<String, List<Story>> processProject(Map<String, UrlBuilder> projectCriteria) throws URISyntaxException, IOException {
		Map<String, List<Story>> result = new HashMap<>();
		for(String fieldName: projectCriteria.keySet()) {
			result.put(fieldName, readStories(projectCriteria.get(fieldName)));
		}
		return result;
	}

	@SuppressWarnings("serial")
	private List<Story> readStories(UrlBuilder urlBuilder) throws URISyntaxException, IOException {
		Request request = urlBuilder.requestBuilder()
									.header("X-TrackerToken", API_KEY)
									.build();

		String response = client.newCall(request)
								.execute()
								.body()
								.string();
		return urlBuilder.filter(
								new Gson()
								.fromJson(response, new TypeToken<List<Story>>(){}.getType())
								);
	}


}
