package org.jsp.cms.exceptionHandler;

import org.jsp.cms.exceptionClasses.InvalidDepartmentIdException;
import org.jsp.cms.exceptionClasses.NoDepartmentFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DepartmentExceptionHandler {

	@ExceptionHandler(NoDepartmentFoundException.class)
	public ResponseEntity<?> NoDepartmentFoundException(NoDepartmentFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("No Department found").body(e.getMessage()).build());
	}
	@ExceptionHandler(InvalidDepartmentIdException.class)
	public ResponseEntity<?> InvalidDepartmentIdException(InvalidDepartmentIdException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("No Department found").body(e.getMessage()).build());
	}

}
