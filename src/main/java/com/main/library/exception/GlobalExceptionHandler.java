package com.main.library.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ExceptionPojo> handleCustomException(CustomException ex) {
		// Log the exception (optional)
		ExceptionPojo exPojo = new ExceptionPojo(Arrays.asList(ex.getMessage()), HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.badRequest().body(exPojo);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ExceptionPojo> handleGenericException(Exception ex) {
		// Log the exception (optional)
		ExceptionPojo exPojo = new ExceptionPojo(Arrays.asList("An unexpected error occurred"),
				HttpStatus.INTERNAL_SERVER_ERROR.value());
		return ResponseEntity.internalServerError().body(exPojo);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ExceptionPojo> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.add(fieldName + " : " + errorMessage);
		});
		ExceptionPojo exPojo = new ExceptionPojo(errors, HttpStatus.BAD_REQUEST.value());
		return ResponseEntity.badRequest().body(exPojo);
	}
}
