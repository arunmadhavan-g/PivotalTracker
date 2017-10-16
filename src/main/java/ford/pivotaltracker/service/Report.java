package ford.pivotaltracker.service;

import lombok.Data;

@Data
public class Report {

	private String epicName;

	private int accepted;
	private int delivered;
	private int qcPass;
	private int qcPending;
	private int pendingMerge;
	private int inProgress;
	private int unstarted;
	private int iceboxed;

	private int acceptedPoints;
	private int deliveredPoints;
	private int qcPassPoints;
	private int qcPendingPoints;
	private int pendingMergePoints;
	private int inProgressPoints;
	private int unstartedPoints;

	public Report(String epicName) {
		this.epicName = epicName;
	}

	public void incrementAcceptedWithPoints(int estimate) {
		accepted++;
		acceptedPoints += estimate;
	}

	public void incrementUnstartedWithPoints(int estimate) {
		unstarted++;
		unstartedPoints += estimate;
	}

	public void incrementIceboxed() {
		iceboxed++;
	}

	public void incrementDeliveredWithPoints(int estimate) {
		delivered++;
		deliveredPoints += estimate;
	}

	public void incrementInProgressWithPoints(int estimate, Story story) {

		if (story.isQCPass()) {
			qcPass++;
			qcPassPoints += estimate;
		} else {
			inProgress++;
			inProgressPoints += estimate;
		}
	}

	public boolean hasTotal(int val) {
		return (accepted + inProgress + unstarted + iceboxed) == val;
	}

	public int getTotalPoints() {
		return acceptedPoints + inProgressPoints + unstartedPoints;
	}

	public int getTotal() {
		return accepted + inProgress + unstarted + iceboxed;
	}

}
