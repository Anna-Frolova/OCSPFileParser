package cli.options.models;

import lombok.Value;
import org.apache.commons.cli.Option;

@Value
public class CLIOption {
	String longOpt;
	Option option;
}
