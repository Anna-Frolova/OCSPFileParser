package cli.options.impls;

import cli.options.ApplicationOptions;
import lombok.val;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.HashMap;
import java.util.Map;

public class ApplicationOptionsImpl implements ApplicationOptions {

	private final static int VALID_ARGUMENTS_AMOUNT = 2;
	private final static int VALID_PROCESSED_OPTIONS_AMOUNT = 1;

	public final static Option OPTION_PARSE = new Option(
			"p", "parse", false,
			"Prints information from an OCSP response");

	public final static Option OPTION_READ = new Option(
			"r", "read", false,
			"Reads a text file");

	public final Map<String, Option> optionsMap = createOptionsMap();
	public final Options options = createOptions();

	@Override
	public Map<String, Option> getOptionsMap() {
		return optionsMap;
	}

	@Override
	public Options getOptions() {
		return options;
	}

	@Override
	public int getValidArgumentsAmount() {
		return VALID_ARGUMENTS_AMOUNT;
	}

	@Override
	public boolean hasOption(Option option) {
		return options.hasOption(option.getLongOpt());
	}

	@Override
	public int getValidProcessedOptionsAmount() {
		return VALID_PROCESSED_OPTIONS_AMOUNT;
	}

	private Options createOptions() {
		val options = new Options();
		optionsMap.forEach((longOpt, option) -> options.addOption(option));
		return options;
	}

	private Map<String, Option> createOptionsMap() {
		val optionsMap = new HashMap<String, Option>();
		optionsMap.put(OPTION_PARSE.getLongOpt(), OPTION_PARSE);
		optionsMap.put(OPTION_READ.getLongOpt(), OPTION_READ);
		return optionsMap;
	}
}
