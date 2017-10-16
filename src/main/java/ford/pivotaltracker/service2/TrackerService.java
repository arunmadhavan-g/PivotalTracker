package ford.pivotaltracker.service2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrackerService {

	@GET("/services/v5/projects/{id}/stories/")
	@Headers("X-TrackerToken:4f39020862d53a30161a3e63f48e4c46")
	Call<List<Story>> fetchStoriesForEpic(@Path("id") String projectId, @Query("with_label") String epicName, @Query("limit") int limit);

	@GET("/services/v5/projects/{id}/stories/")
	@Headers("X-TrackerToken:4f39020862d53a30161a3e63f48e4c46")
	Call<List<Story>> fetchStoriesForEpicBeforeStory(@Path("id") String projectId, @Query("with_label") String epicName, @Query("limit") int limit, @Query("before_story_id")String beforeStoryId);

	
	@GET("/services/v5/projects/{id}/stories/")
	@Headers("X-TrackerToken:4f39020862d53a30161a3e63f48e4c46")
	Call<List<Story>> fetchStoriesForEpicBetweenStories(@Path("id") String projectId, @Query("with_label") String epicName, @Query("limit") int limit, @Query("after_story_id")String afterStoryId, @Query("before_story_id")String beforeStoryId);
	
}
