package project.hotdealicious.common.util.basewrapper;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorApiResult {

	private final Integer code;
	private final String message;

	@Builder
	private ErrorApiResult(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public static ErrorApiResult onError(Integer code, String message) {
		return ErrorApiResult.builder()
			.code(code)
			.message(message)
			.build();
	}
}
