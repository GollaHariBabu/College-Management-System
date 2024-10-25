package org.jsp.cms.exceptionHandler;

import org.jsp.cms.exceptionClasses.InvalidFacultyIdException;
import org.jsp.cms.exceptionClasses.NoFacultyFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class FacultyExceptionHandler {

	@ExceptionHandler(InvalidFacultyIdException.class)
	public ResponseEntity<?> InvalidFacultyId(InvalidFacultyIdException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("Invalid Faculty Id").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(NoFacultyFoundException.class)
	public ResponseEntity<?> NoFacultyFoundException(NoFacultyFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("No Faculty Found").body(e.getMessage()).build());
	}
}
