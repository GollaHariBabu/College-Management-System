package org.jsp.cms.exceptionHandler;

import org.jsp.cms.exceptionClasses.InvalidAdministratorIdException;
import org.jsp.cms.exceptionClasses.NoAdministratorFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdministratorExceptionHandler {

	@ExceptionHandler(NoAdministratorFoundException.class)
	public ResponseEntity<?> NoAdministratorFoundException(NoAdministratorFoundException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder()
				.status(HttpStatus.BAD_REQUEST.value()).message("Bad Request").body(e.getMessage()).build());
	}

	@ExceptionHandler(InvalidAdministratorIdException.class)
	public ResponseEntity<?> InvalidAdministratorIdException(InvalidAdministratorIdException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder()
				.status(HttpStatus.BAD_REQUEST.value()).message("Bad Request").body(e.getMessage()).build());
	}

}
