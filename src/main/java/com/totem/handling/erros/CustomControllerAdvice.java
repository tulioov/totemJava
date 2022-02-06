package com.totem.handling.erros;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.totem.exception.CustomDataNotFoundException;
import com.totem.exception.CustomErrorException;
import com.totem.exception.CustomParameterConstraintException;
import com.totem.util.ResponseEntityUtil;
import com.totem.util.RetornoDTO;

@ControllerAdvice
class CustomControllerAdvice {
	@ExceptionHandler(CustomDataNotFoundException.class)
	public ResponseEntity<RetornoDTO> handleCustomDataNotFoundExceptions(Exception e) {
		HttpStatus status = HttpStatus.NOT_FOUND; // 404

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();
		ErrorResponse erro = new ErrorResponse(status, e.getMessage(), stackTrace);
		return ResponseEntityUtil.defaultResponse(new RetornoDTO(status, erro));
	}

	@ExceptionHandler(CustomParameterConstraintException.class)
	public ResponseEntity<RetornoDTO> handleCustomParameterConstraintExceptions(Exception e) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		ErrorResponse erro = new ErrorResponse(status, e.getMessage());
		return ResponseEntityUtil.defaultResponse(new RetornoDTO(status, erro));
	}

	@ExceptionHandler(CustomErrorException.class)
	public ResponseEntity<RetornoDTO> handleCustomErrorExceptions(Exception e) {

		CustomErrorException customErrorException = (CustomErrorException) e;

		HttpStatus status = customErrorException.getStatus();

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		customErrorException.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();

		ErrorResponse erro = new ErrorResponse(status, customErrorException.getMessage(), stackTrace);
		return ResponseEntityUtil.defaultResponse(new RetornoDTO(status, erro));

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RetornoDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntityUtil.defaultResponse(new RetornoDTO(HttpStatus.BAD_REQUEST, errors));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RetornoDTO> handleExceptions(Exception e) {

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();

		ErrorResponse erro = new ErrorResponse(status, e.getMessage(), stackTrace);

		return ResponseEntityUtil.defaultResponse(new RetornoDTO(status, erro));

	}
}
