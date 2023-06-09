package project.hotdealicious.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorMessage {

	private Boolean success = false;
	private Object data = null;
	private final Integer errorCode;
	private final String errorMessage;
}
