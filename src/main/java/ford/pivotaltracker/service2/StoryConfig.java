package ford.pivotaltracker.service2;

import java.io.IOException;
import java.util.List;

public class StoryConfig {

	private String releaseQCMarker;
	private String mergedMarker;
	private String awaitingMergeMarker;
	
	public StoryConfig(String releaseQCMarker, String mergedMarker, String awaitingMergeMarker) {
		this.releaseQCMarker = releaseQCMarker;
		this.mergedMarker = mergedMarker;
		this.awaitingMergeMarker = awaitingMergeMarker;
	}

	public List<Story> fetchQCReady(Service service, String tag) throws IOException {
		return service.fetchForTagBeforeStory(tag, releaseQCMarker);
	}

	public List<Story> fetchBuildReady(Service service, String tag) throws IOException {
		return service.fetchForTagBetweenStories(tag, releaseQCMarker, mergedMarker);
	}

	public List<Story> fetchMergeReady(Service service, String tag) throws IOException {
		return service.fetchForTagBetweenStories(tag, mergedMarker, awaitingMergeMarker);
	}
	
}
