package actions.exceptions;

public class InvalidOptionActionTypeException extends Exception {
	public InvalidOptionActionTypeException(String actualOptionActionType) {
		super(String.format("%s option action type is not supported", actualOptionActionType), null);
	}
}
