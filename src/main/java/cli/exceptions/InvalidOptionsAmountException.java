package cli.exceptions;

public class InvalidOptionsAmountException extends Exception {
	public InvalidOptionsAmountException(int expectedOptionsAmount, int actualOptionsAmount) {
		super(String.format("Expected %s options, but got %s", expectedOptionsAmount, actualOptionsAmount), null);
	}
}
