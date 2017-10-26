package ford.pivotaltracker.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ford.pivotaltracker.read.UrlBuilder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Criteria {
	private String story_state;
	private String contains_tag;
	private String before_story;
	private String does_not_contain_tag;
	private String after_story;
	
	public UrlBuilder urlBuilder(UrlBuilder builder) {
		return builder.afterId(after_story)
						.beforeId(before_story)
						.withState(story_state)
						.containTag(contains_tag)
						.doesNotContainTag(does_not_contain_tag);
	}
}
