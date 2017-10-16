package ford.pivotaltracker.service2;

import java.io.IOException;
import java.util.List;

public class StoryGroups {

	private List<Story> all;
	private List<Story> qcReady;
	private List<Story> buildReady;
	private List<Story> mergeReady;

	public void populateStories(Service service, StoryConfig config, String tag) throws IOException {
		all = service.fetchForTag(tag);
		qcReady = config.fetchQCReady(service, tag);
		buildReady = config.fetchBuildReady(service, tag);
		mergeReady = config.fetchMergeReady(service, tag);
	}

	@Override
	public String toString() {
		return String.format("All: %d, qcReady: %d, buildReady:%d, mergeReady: %d", all.size(), qcReady.size(), buildReady.size(), mergeReady.size());
	}

}
