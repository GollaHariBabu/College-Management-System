package org.jsp.cms.exceptionHandler;

import org.jsp.cms.exceptionClasses.InvalidStudentIdException;
import org.jsp.cms.exceptionClasses.NoStudentFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler {

	@ExceptionHandler(InvalidStudentIdException.class)
	public ResponseEntity<?> InvalidStudentIdException(InvalidStudentIdException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("Invalid Faculty Id").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(NoStudentFoundException.class)
	public ResponseEntity<?> NoStudentFoundException(NoStudentFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("No Faculty Found").body(e.getMessage()).build());
	}
}
