package ford.pivotaltracker.service2;

import java.util.List;

import lombok.Data;

@Data
public class Story {

	private String kind;
    private Long id;
    private int estimate;
    private String created_at;
    private String updated_at;
    private String accepted_at;
    private String story_type;
    private String name;
    private String description;
	private String current_state;
	private Long requested_by_id;
	private String url;
	private String project_id;
	private List<Long> owner_ids;
    private List<Label> labels;
    private String owned_by_id;
    
	public boolean hasTag(String tag) {
		for(Label label: labels) {
			if(label.hasTag(tag))
				return true;
		}
		return false;
	}

}


