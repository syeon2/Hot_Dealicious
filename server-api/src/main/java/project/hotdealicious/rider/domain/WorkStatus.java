package project.hotdealicious.rider.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum WorkStatus {
	WAIT, PROCESS, OFFLINE;

	@JsonCreator
	public static WorkStatus from(String s) {
		return WorkStatus.valueOf(s.toUpperCase());
	}
}
