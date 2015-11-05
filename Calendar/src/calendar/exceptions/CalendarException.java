package calendar.exceptions;

import calendar.statics.ErrorCode;

public class CalendarException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9021504646854142579L;

	private int errorCode;
	private String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public CalendarException(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public CalendarException(int errorCode) {
		this(errorCode, ErrorCode.getErrorMessage(errorCode));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.errorCode).append(":").append(this.errorMessage);

		return sb.toString();
	}

}
