package ford.pivotaltracker.beans.tracker;

import lombok.Data;

@Data
public class Label {

	private Long id;
	private Long project_id;
	private String kind;
	private String name;
	private String created_at;
	private String updated_at;
	
	public boolean isQCPass() {
		return "qc pass".equals(name);
	}
	public boolean hasTag(String tag) {
		return name.equals(tag);
	}
	
}
