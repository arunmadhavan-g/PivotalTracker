package ford.pivotaltracker.report;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import ford.pivotaltracker.service2.Story;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Reader {

	public Map<String, Map<String, List<Story>>> process() throws URISyntaxException, IOException {
		Map<String, Map<String, UrlBuilder>> transformed = ConfigManager.transformed();
		Map<String, Map<String, List<Story>>> result = new HashMap<>();
		for(String projectName: transformed.keySet()) {
			result.put(projectName, processProject(transformed.get(projectName)));
		}
		return result;
	}

	@SuppressWarnings("serial")
	private Map<String, List<Story>> processProject(Map<String, UrlBuilder> projectCriteria) throws URISyntaxException, IOException {
		Map<String, List<Story>> result = new HashMap<>();
		Request.Builder requestBuilder = new Request.Builder()
									.header("X-TrackerToken", "4f39020862d53a30161a3e63f48e4c46");
		OkHttpClient client = new OkHttpClient();				
		Request request = null;
		for(String fieldName: projectCriteria.keySet()) {
			request = projectCriteria.get(fieldName).buildRequest(requestBuilder);
			String response = client.newCall(request).execute().body().string();
			Gson gson = new Gson();
			List<Story> fromJson = gson.fromJson(response, new TypeToken<List<Story>>(){}.getType() );
			result.put(fieldName, fromJson);
		}
		return result;
	}

}
