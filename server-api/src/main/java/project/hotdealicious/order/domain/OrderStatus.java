package project.hotdealicious.order.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OrderStatus {
	WAIT, PROGRESS, SEARCH_RIDER, COMPLETE, CANCEL;

	@JsonCreator
	public static OrderStatus from(String s) {
		return OrderStatus.valueOf(s.toUpperCase());
	}
}
