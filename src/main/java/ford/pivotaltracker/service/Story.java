package ford.pivotaltracker.service;

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

    public Report updateReport(Report report) {
    	switch(current_state) {
    		case "accepted": report.incrementAcceptedWithPoints(estimate); break; 
    		case "unstarted":report.incrementUnstartedWithPoints(estimate); break;
    		case "unscheduled":report.incrementIceboxed(); break;
    		default:report.incrementInProgressWithPoints(estimate, this); break;
    	}
		return report;
	}

	public boolean isQCPass() {
		for(Label label: labels) {
			if(label.isQCPass())
				return true;
		}
		return false;
	}

	public boolean isBlocked() {
		
		return false;
	}

}


