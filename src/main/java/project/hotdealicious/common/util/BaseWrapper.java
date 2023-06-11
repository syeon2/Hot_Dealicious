package project.hotdealicious.common.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseWrapper<T> {

	private final Boolean success;
	private final T data;
	private final Integer code;
	private final String errorMessage;

	@Builder
	private BaseWrapper(Boolean success, T data, Integer code, String errorMessage) {
		this.success = success;
		this.data = data;
		this.code = code;
		this.errorMessage = errorMessage;
	}

	public static <T> BaseWrapper<T> onSuccess(Integer code, T data) {
		return BaseWrapper.<T>builder()
			.code(code)
			.success(true)
			.data(data)
			.build();
	}

	public static <T> BaseWrapper<T> onFailure(Integer code, String errorMessage) {
		return BaseWrapper.<T>builder()
			.code(code)
			.success(false)
			.errorMessage(errorMessage)
			.build();
	}
}
