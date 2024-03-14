package com.ems.exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {

	private final LocalDateTime localDateTime;
	private final String message;
	private final String description;
	public ErrorDetails(LocalDateTime localDateTime, String message, String description) {
		super();
		this.localDateTime = localDateTime;
		this.message = message;
		this.description = description;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public String getMessage() {
		return message;
	}
	public String getDescription() {
		return description;
	}
	
	
}
