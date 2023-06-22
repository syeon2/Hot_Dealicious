package project.hotdealicious.rider.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum WorkStatus {
	OFFLINE, ONLINE;

	public static WorkStatus changedStatus(WorkStatus workStatus) {
		if (workStatus == OFFLINE) {
			return OFFLINE;
		} else {
			return ONLINE;
		}
	}

	@JsonCreator
	public static WorkStatus from(String s) {
		return WorkStatus.valueOf(s.toUpperCase());
	}
}
