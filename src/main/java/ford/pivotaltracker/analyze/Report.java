package ford.pivotaltracker.analyze;

import java.util.List;

import lombok.Data;

@Data
public class Report {
	private List<TagCounts> aggregated;
}
