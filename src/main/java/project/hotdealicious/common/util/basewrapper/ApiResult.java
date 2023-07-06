package project.hotdealicious.common.util.basewrapper;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResult<T> {

	private final T data;

	@Builder
	private ApiResult(T data) {
		this.data = data;
	}

	public static <T> ApiResult<T> onSuccess(T data) {
		return ApiResult.<T>builder()
			.data(data)
			.build();
	}
}
