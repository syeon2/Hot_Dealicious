package project.hotdealicious.rider.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum WorkStatus {
	INIT, WAIT, PROCESS, COMPLETE;

	@JsonCreator
	public static WorkStatus from(String s) {
		return WorkStatus.valueOf(s.toUpperCase());
	}
}
