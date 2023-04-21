package mypack.controller.exception;

public class CommonRuntimeException extends RuntimeException {
	private static final long serialVersionUID = -4999305655483313284L;
	
	public CommonRuntimeException(String message) {
		super(message);
	}
}