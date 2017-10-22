package ford.pivotaltracker.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Criteria {
	private String story_state;
	private String contains_tag;
	private String before_story;
	private String does_not_contain_tag;
	private String after_story;
}
