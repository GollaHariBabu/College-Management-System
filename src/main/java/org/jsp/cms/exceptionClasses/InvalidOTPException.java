package org.jsp.cms.exceptionClasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class InvalidOTPException extends RuntimeException {

	private String message;
	
	public InvalidOTPException(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
