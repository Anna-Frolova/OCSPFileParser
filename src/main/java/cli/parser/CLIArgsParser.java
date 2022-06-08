package cli.parser;

import actions.models.ActionData;
import cli.exceptions.InvalidArgumentsAmountException;
import cli.exceptions.InvalidOptionsAmountException;
import org.apache.commons.cli.ParseException;

public interface CLIArgsParser {
	ActionData parseArgs(String[] args) throws ParseException, InvalidArgumentsAmountException, InvalidOptionsAmountException;
}
