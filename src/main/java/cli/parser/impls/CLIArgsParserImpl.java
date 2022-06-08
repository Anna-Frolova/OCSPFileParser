package cli.parser.impls;

import actions.models.ActionData;
import cli.exceptions.InvalidArgumentsAmountException;
import cli.exceptions.InvalidOptionsAmountException;
import cli.options.ApplicationOptions;
import cli.parser.CLIArgsParser;
import filereader.models.FileLocation;
import lombok.val;
import org.apache.commons.cli.*;

public class CLIArgsParserImpl implements CLIArgsParser {

	private final CommandLineParser clp;
	private final ApplicationOptions applicationOptions;

	public CLIArgsParserImpl(
			CommandLineParser clp,
			ApplicationOptions applicationOptions
	) {
		this.clp = clp;
		this.applicationOptions = applicationOptions;
	}

	@Override
	public ActionData parseArgs(String[] args) throws ParseException, InvalidArgumentsAmountException, InvalidOptionsAmountException {
		val commandLine = clp.parse(applicationOptions.getOptions(), args);
		val argList = commandLine.getArgList();

		if (argList.size() != applicationOptions.getValidArgumentsAmount()) {
			throw new InvalidArgumentsAmountException(applicationOptions.getValidArgumentsAmount(), argList.size());
		}

		val processedOptions = commandLine.getOptions();
		if (processedOptions.length != applicationOptions.getValidProcessedOptionsAmount()) {
			throw new InvalidOptionsAmountException(applicationOptions.getValidProcessedOptionsAmount(), processedOptions.length);
		}

		//reads args from cli
		val filePath = commandLine.getArgList().get(0);
		val fileName = commandLine.getArgList().get(1);

		//todo:: need to process more than one options
		val processedOption = processedOptions[0];

		return new ActionData(processedOption.getLongOpt(), new FileLocation(fileName, filePath));
	}
}
