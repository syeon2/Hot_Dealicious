package project.hotdealicious.common.util;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorBaseWrapper {

	private final Integer code;
	private final String message;

	@Builder
	private ErrorBaseWrapper(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ErrorBaseWrapper onError(Integer code, String message) {
		return ErrorBaseWrapper.builder()
			.code(code)
			.message(message)
			.build();
	}
}
