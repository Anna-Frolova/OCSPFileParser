package actions.strategies.impls;

import actions.models.ParseFileActionResult;
import actions.strategies.ActionStrategy;
import cli.options.impls.ApplicationOptionsImpl;
import filereader.models.FileLocation;
import filereader.FileReader;
import lombok.val;
import sun.security.provider.certpath.OCSPResponse;

import java.io.IOException;
import java.util.stream.IntStream;

public class ParseFileAction implements ActionStrategy<ParseFileActionResult> {

	private final FileReader reader;

	public ParseFileAction(FileReader reader) {
		this.reader = reader;
	}

	@Override
	public String getOptionAction() {
		return ApplicationOptionsImpl.OPTION_PARSE.getLongOpt();
	}

	@Override
	public ParseFileActionResult process(FileLocation fileLocation) throws IOException {
		val byteList = reader.readBytes(fileLocation);
		val bytes = new byte[byteList.size()];

		IntStream.range(0, byteList.size()).forEach(i -> bytes[i] = byteList.get(i));

		return new ParseFileActionResult(
				new OCSPResponse(bytes)
		);
	}
}
