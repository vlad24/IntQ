package com.appricots.intq.wrappers;

public class DBAccessReport {

	private Long successId;
	private String errorMessage;
	
	public DBAccessReport() {
		super();
	}

	public DBAccessReport(Long successId, String errorMessage) {
		super();
		this.successId = successId;
		this.errorMessage = errorMessage;
	}

	public Long getSuccessId() {
		return successId;
	}

	public void setSuccessId(Long successId) {
		this.successId = successId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean areErrorsPresent() {
		return this.errorMessage != null;
	}
	
	
}
