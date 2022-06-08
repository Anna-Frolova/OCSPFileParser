package print.executionresult.impls;

import actions.models.ExecutionResult;
import lombok.val;
import print.executionresult.ExecutionResultPrinter;

import java.io.OutputStream;
import java.io.PrintWriter;

public class ConsoleExecutionResultPrinter implements ExecutionResultPrinter {

	private final OutputStream outputStream;

	public ConsoleExecutionResultPrinter(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void print(ExecutionResult result) {
		try (val printWriter = new PrintWriter(outputStream)) {
			printWriter.println(result);
		}
	}
}
