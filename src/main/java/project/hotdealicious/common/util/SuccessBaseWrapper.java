package project.hotdealicious.common.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SuccessBaseWrapper<T> {

	private final T data;

	@Builder
	private SuccessBaseWrapper(T data) {
		this.data = data;
	}

	public static <T> SuccessBaseWrapper<T> onSuccess(T data) {
		return SuccessBaseWrapper.<T>builder()
			.data(data)
			.build();
	}
}
