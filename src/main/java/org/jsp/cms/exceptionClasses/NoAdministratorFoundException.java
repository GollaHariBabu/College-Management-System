package org.jsp.cms.exceptionClasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoAdministratorFoundException extends RuntimeException {

private String message;
	
	public NoAdministratorFoundException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}

