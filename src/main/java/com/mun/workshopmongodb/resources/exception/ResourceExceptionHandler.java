package com.mun.workshopmongodb.resources.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mun.workshopmongodb.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException exception, HttpServletRequest httpServletRequest){
		String error = "Object not found";
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		StandardError standardError = new StandardError(
												Instant.now(), 
												HttpStatus.NOT_FOUND.value(), 
												error, 
												exception.getMessage(), 
												httpServletRequest.getRequestURI());
		return ResponseEntity.status(httpStatus).body(standardError);
	}
	

}	
