package org.jsp.cms.exceptionHandler;

import org.jsp.cms.exceptionClasses.InvalidCourseIdException;
import org.jsp.cms.exceptionClasses.NoCourseFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CourseExceptionHandler {

	@ExceptionHandler(InvalidCourseIdException.class)
	public ResponseEntity<?> InvalidCourseIdException(InvalidCourseIdException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder()
				.status(HttpStatus.BAD_REQUEST.value()).message("No Course Found...").body(e.getMessage()).build());
	}

	@ExceptionHandler(NoCourseFoundException.class)
	public ResponseEntity<?> NoCourseFoundException(NoCourseFoundException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder()
				.status(HttpStatus.BAD_REQUEST.value()).message("No Course Found...").body(e.getMessage()).build());
	}

}
