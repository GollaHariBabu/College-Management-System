package org.jsp.cms.exceptionClasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class InvalidFacultyIdException extends RuntimeException {

private String message;
	
	public InvalidFacultyIdException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
