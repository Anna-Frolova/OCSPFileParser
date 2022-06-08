package print.helpinfo.impls;

import lombok.val;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import print.helpinfo.HelpInfoPrinter;

import java.io.OutputStream;
import java.io.PrintWriter;

public class HelpInfoPrinterImpl implements HelpInfoPrinter {

	private final HelpFormatter formatter = new HelpFormatter();
	private final OutputStream outputStream;
	private final String applicationName;
	private final String commandLineSyntax;
	private final Options options;

	public HelpInfoPrinterImpl(
			OutputStream outputStream,
			String applicationName,
			String commandLineSyntax,
			Options options
	) {
		this.outputStream = outputStream;
		this.applicationName = applicationName;
		this.commandLineSyntax = commandLineSyntax;
		this.options = options;
	}

	public void printHelpInfo() {
		try (val printWriter = new PrintWriter(outputStream)) {
			printWriter.println(applicationName);
			formatter.printUsage(printWriter, 100, commandLineSyntax);
			formatter.printOptions(printWriter, 100, options, 2, 5);
		}
	}
}
