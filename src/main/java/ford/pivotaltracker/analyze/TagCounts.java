package ford.pivotaltracker.analyze;

import java.util.List;

import lombok.Data;

@Data
public class TagCounts {
	private List<FieldCount> fieldCounts;
	private String name;
}
