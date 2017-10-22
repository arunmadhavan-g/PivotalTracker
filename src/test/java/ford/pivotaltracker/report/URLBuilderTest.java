package ford.pivotaltracker.report;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class URLBuilderTest {

	private UrlBuilder builder = new UrlBuilder("https://www.pivotaltracker.com/services/v5/projects/");
	
	@Test
	public void forProjectIdAndMainTag_GenerateURL() throws Exception {
		String url= builder.projectId("1431430")
						   .addLabel("v1.9")
						   .build();

		assertThat(url).isEqualTo("https://www.pivotaltracker.com/services/v5/projects/1431430/search?query=label%3Av1.9+includedone%3Atrue");
	}

	@Test
	public void withExtraLabel_queryIsAppendedWithAndLabelCondition() throws Exception {
		String url= builder.projectId("1431430")
				   .addLabel("v1.9")
				   .addLabel("qc pass")
				   .build();
		
		assertThat(url).isEqualTo("https://www.pivotaltracker.com/services/v5/projects/1431430/search?query=label%3A%22v1.9%22%20AND%20label%3A%22qc%20pass%22%20includedone%3Atrue%26envelope%3Dtrue");
	}
	
}
