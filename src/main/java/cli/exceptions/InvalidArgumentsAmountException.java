package cli.exceptions;

public class InvalidArgumentsAmountException extends Exception {
	public InvalidArgumentsAmountException(int expectedArgumentsAmount, int actualArgumentsAmount) {
		super(String.format("Expected %s arguments, but got %s", expectedArgumentsAmount, actualArgumentsAmount), null);
	}
}
