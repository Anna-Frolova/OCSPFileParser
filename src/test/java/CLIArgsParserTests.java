import actions.models.ActionData;
import cli.exceptions.InvalidArgumentsAmountException;
import cli.exceptions.InvalidOptionsAmountException;
import cli.options.impls.ApplicationOptionsImpl;
import cli.parser.CLIArgsParser;
import cli.parser.impls.CLIArgsParserImpl;
import filereader.models.FileLocation;
import lombok.val;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.junit.Before;
import org.junit.Test;

import static com.github.stefanbirkner.fishbowl.Fishbowl.exceptionThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CLIArgsParserTests {

	private CLIArgsParser cliArgsParser;

	@Before
	public void before() {
		this.cliArgsParser = new CLIArgsParserImpl(
				new DefaultParser(),
				new ApplicationOptionsImpl()
		);
	}

	@Test
	public void validInputReadTest() throws ParseException, InvalidOptionsAmountException, InvalidArgumentsAmountException {
		val expectedActionData = new ActionData("read", new FileLocation("document.txt", "\\path\\to"));

		val actualActionData = cliArgsParser.parseArgs(new String[]{
				"\\path\\to", "document.txt", "--read"
		});

		assertEquals(expectedActionData, actualActionData);
	}

	@Test
	public void validInputParseTest() throws ParseException, InvalidOptionsAmountException, InvalidArgumentsAmountException {
		val expectedActionData = new ActionData("parse", new FileLocation("document.txt", "\\path\\to"));

		val actualActionData = cliArgsParser.parseArgs(new String[]{
				"\\path\\to", "document.txt", "--parse"
		});

		assertEquals(expectedActionData, actualActionData);
	}

	@Test
	public void invalidFileNameInputTest() throws ParseException, InvalidOptionsAmountException, InvalidArgumentsAmountException {
		val expectedActionData = new ActionData("read", new FileLocation("document.txt", "\\path\\to"));

		val actualActionData = cliArgsParser.parseArgs(new String[]{
				"\\path\\to", "notExistingFile.txt", "--read"
		});

		assertNotEquals(expectedActionData, actualActionData);
	}

	@Test
	public void invalidOptionsAmountExceptionTest() {
		val exception = exceptionThrownBy(() -> {
			cliArgsParser.parseArgs(new String[]{
					"\\path\\to", "notExistingFile.txt", "--read", "--parse"
			});
		});
		assertEquals(InvalidOptionsAmountException.class, exception.getClass());

	}

	@Test
	public void invalidArgumentsAmountExceptionTest() {
		val exception = exceptionThrownBy(() -> {
			cliArgsParser.parseArgs(new String[]{
					"\\path\\to", "document.txt", "document2.txt", "--read"
			});
		});

		assertEquals(InvalidArgumentsAmountException.class, exception.getClass());
	}
}
