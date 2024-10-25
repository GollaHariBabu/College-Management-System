package org.jsp.cms.exceptionHandler;

import org.jsp.cms.exceptionClasses.InvalidOTPException;
import org.jsp.cms.exceptionClasses.InvalidUserIdException;
import org.jsp.cms.exceptionClasses.NoUserFoundException;
import org.jsp.cms.responseStructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<?> NoUserFoundException(NoUserFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("No User Found").body(e.getMessage()).build());
	}

	@ExceptionHandler(InvalidUserIdException.class)
	public ResponseEntity<?> InvalidUserIdException(InvalidUserIdException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder()
				.status(HttpStatus.NOT_FOUND.value()).message("Invalid User Id").body(e.getMessage()).build());
	}
	
	@ExceptionHandler(InvalidOTPException.class)
	public ResponseEntity<?> InvalidOTPException(InvalidOTPException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder()
				.status(HttpStatus.BAD_REQUEST.value()).message("Invalid OTP...").body(e.getMessage()).build());
	}
	
}
