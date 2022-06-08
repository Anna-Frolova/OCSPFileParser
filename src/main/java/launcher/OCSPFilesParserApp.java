package launcher;

import actions.exceptions.InvalidOptionActionTypeException;
import actions.service.ActionExecutor;
import actions.service.impls.ActionExecutorImpl;
import actions.strategies.impls.ParseFileAction;
import actions.strategies.impls.ReadFileAction;
import cli.exceptions.InvalidArgumentsAmountException;
import cli.exceptions.InvalidOptionsAmountException;
import cli.options.ApplicationOptions;
import cli.options.impls.ApplicationOptionsImpl;
import cli.parser.CLIArgsParser;
import cli.parser.impls.CLIArgsParserImpl;
import filereader.FileReader;
import filereader.impls.FileReaderImpl;
import lombok.val;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import print.executionresult.ExecutionResultPrinter;
import print.executionresult.impls.ConsoleExecutionResultPrinter;
import print.helpinfo.HelpInfoPrinter;
import print.helpinfo.impls.HelpInfoPrinterImpl;
import resource.Resources;

import java.io.IOException;

public class OCSPFilesParserApp {

	private final HelpInfoPrinter helpInfoPrinter;
	private final CLIArgsParser cliArgsParser;
	private final ApplicationOptions applicationOptions;
	private final FileReader fileReader;
	private final ActionExecutor actionExecutor;
	private final ExecutionResultPrinter executionResultPrinter;

	public OCSPFilesParserApp() {
		this.applicationOptions = new ApplicationOptionsImpl();
		this.cliArgsParser = new CLIArgsParserImpl(
				new DefaultParser(),
				applicationOptions
		);
		this.helpInfoPrinter = new HelpInfoPrinterImpl(
				System.out,
				Resources.APP_NAME,
				Resources.USAGE_SYNTAX,
				applicationOptions.getOptions()
		);
		this.fileReader = new FileReaderImpl();
		this.actionExecutor = new ActionExecutorImpl(
				new ParseFileAction(fileReader),
				new ReadFileAction(fileReader)
		);
		this.executionResultPrinter = new ConsoleExecutionResultPrinter(
				System.out
		);
	}

	public void run(String[] args) {
		try {
			val actionData = cliArgsParser.parseArgs(args);
			val executionResult = actionExecutor.execute(actionData);
			executionResultPrinter.print(executionResult);
		} catch (ParseException | InvalidArgumentsAmountException | InvalidOptionsAmountException | IOException | InvalidOptionActionTypeException e) {
			e.printStackTrace();
			helpInfoPrinter.printHelpInfo();
		}
	}
}
