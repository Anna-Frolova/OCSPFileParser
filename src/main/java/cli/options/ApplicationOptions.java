package cli.options;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.Map;

public interface ApplicationOptions {
	Map<String, Option> getOptionsMap();

	Options getOptions();

	int getValidArgumentsAmount();

	boolean hasOption(Option option);

	int getValidProcessedOptionsAmount();
}
