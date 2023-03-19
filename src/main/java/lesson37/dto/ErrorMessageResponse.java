package lesson37.dto;

/**
 * @author spasko
 */
public class ErrorMessageResponse {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorMessageResponse(String message) {
		this.message = message;
	}

	public ErrorMessageResponse() {
	}

}
