package project.hotdealicious.common.error;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public ErrorMessage handlerEmptyResultDataAccessException(NoSuchElementException error) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), error.getLocalizedMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorMessage handlerMethodArgumentNotValidException(MethodArgumentNotValidException error) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), error.getFieldError().getDefaultMessage());
	}
}
