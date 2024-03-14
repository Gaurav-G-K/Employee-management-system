package com.ems.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorDetails> handlesResourceNotFoundException(NotFoundException notFoundException , WebRequest web){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), notFoundException.getMessage(), web.getDescription(false));
		
		return new ResponseEntity<>(errorDetails , HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> handlesGlobalException(RuntimeException runtimeException , WebRequest web){
		
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), runtimeException.getMessage(), web.getDescription(false));
		
		return new ResponseEntity<>(errorDetails , HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
