package ford.pivotaltracker.report;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.junit.Test;

public class URLBuilderTest {

	@Test
	public void forProjectIdAndEpic_GenerateURL() throws Exception {
		assertThat(baseBuilder().build()).isEqualTo("https://www.pivotaltracker.com/services/v5/projects/1431430/stories?with_label=v1.9&limit=300");
	}
	
	@Test
	public void forProjectIdAndEpic_WithBeforeId_GenerateUrl() throws Exception {
		assertThat(baseBuilder()
							.beforeId("111")
							.build())
					.isEqualTo("https://www.pivotaltracker.com/services/v5/projects/1431430/stories?with_label=v1.9&before_story_id=111&limit=300");
	}
	
	@Test
	public void forProjectIdAndEpic_WithAfterId_GenerateUrl() throws Exception {
		assertThat(baseBuilder()
				.afterId("111")
				.build())
				.isEqualTo("https://www.pivotaltracker.com/services/v5/projects/1431430/stories?with_label=v1.9&after_story_id=111&limit=300");
		
	}
	
	@Test
	public void forProjectIdAndEpic_WithSpecificState_GenerateUrl() throws Exception {
		assertThat(baseBuilder()
					.withState("accepted")
					.build())
					.isEqualTo("https://www.pivotaltracker.com/services/v5/projects/1431430/stories?with_label=v1.9&with_state=accepted&limit=300");
	}
	
	private UrlBuilder baseBuilder() throws URISyntaxException, UnsupportedEncodingException {
		return new UrlBuilder("https://www.pivotaltracker.com/services/v5/projects/")
				.projectId("1431430")
				.forEpic("v1.9")
				.limit(300);
	}
	
}
