package actions.strategies.impls;

import actions.models.ReadFileActionResult;
import actions.strategies.ActionStrategy;
import cli.options.impls.ApplicationOptionsImpl;
import filereader.models.FileLocation;
import filereader.FileReader;
import lombok.val;

import java.io.IOException;

public class ReadFileAction implements ActionStrategy<ReadFileActionResult> {

	private final FileReader reader;

	public ReadFileAction(FileReader reader) {
		this.reader = reader;
	}

	@Override
	public String getOptionAction() {
		return ApplicationOptionsImpl.OPTION_READ.getLongOpt();
	}

	@Override
	public ReadFileActionResult process(FileLocation fileLocation) throws IOException {
		val bytes = reader.readBytes(fileLocation);
		return new ReadFileActionResult(bytes.toString());
	}
}
