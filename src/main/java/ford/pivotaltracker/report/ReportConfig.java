package ford.pivotaltracker.report;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class ReportConfig {
	private String name;
	private String project_id;
	@JsonIgnoreProperties(ignoreUnknown=true)
	private int max_limit;
	@JsonIgnoreProperties(ignoreUnknown=true)
	private String main_tag;
	private List<Field> fields;
}
