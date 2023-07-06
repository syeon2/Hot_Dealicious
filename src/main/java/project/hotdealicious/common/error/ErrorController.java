package project.hotdealicious.common.error;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import project.hotdealicious.common.error.exception.LoginException;
import project.hotdealicious.common.util.basewrapper.ErrorApiResult;

@RestControllerAdvice
public class ErrorController {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({NoSuchElementException.class, LoginException.class})
	public ErrorApiResult handlerEmptyResultDataAccessException(NoSuchElementException error) {
		return ErrorApiResult.onError(HttpStatus.NOT_FOUND.value(), error.getLocalizedMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorApiResult handlerMethodArgumentNotValidException(MethodArgumentNotValidException error) {
		String validationErrors = error.getBindingResult().getFieldErrors().stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.collect(Collectors.joining(", "));

		return ErrorApiResult.onError(HttpStatus.BAD_REQUEST.value(), validationErrors);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ErrorApiResult handlerMethodArgumentNotValidException(
		HttpRequestMethodNotSupportedException error) {
		return ErrorApiResult.onError(HttpStatus.BAD_REQUEST.value(), error.getLocalizedMessage());
	}
}
