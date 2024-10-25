package org.jsp.cms.exceptionClasses;

import lombok.Builder;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
public class NoStudentFoundException extends RuntimeException {

private String message;
	
	public NoStudentFoundException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}