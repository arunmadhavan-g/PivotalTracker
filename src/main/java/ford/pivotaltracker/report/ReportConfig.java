package ford.pivotaltracker.report;

import java.util.List;

import lombok.Data;

@Data
public class ReportConfig {

	private String name;
	private String project_id;
	private int max_limit;
	private List<Field> fields;
	
}
