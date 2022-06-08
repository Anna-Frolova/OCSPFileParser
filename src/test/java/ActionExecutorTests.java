import actions.exceptions.InvalidOptionActionTypeException;
import actions.models.ActionData;
import actions.models.ParseFileActionResult;
import actions.models.ReadFileActionResult;
import actions.service.ActionExecutor;
import actions.service.impls.ActionExecutorImpl;
import actions.strategies.impls.ParseFileAction;
import actions.strategies.impls.ReadFileAction;
import cli.options.impls.ApplicationOptionsImpl;
import filereader.impls.FileReaderImpl;
import filereader.models.FileLocation;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import sun.security.provider.certpath.OCSPResponse;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static com.github.stefanbirkner.fishbowl.Fishbowl.exceptionThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ActionExecutorTests {

	private ActionExecutor executor;

	@Before
	public void before() {
		val fileReader = new FileReaderImpl();
		this.executor = new ActionExecutorImpl(
				new ParseFileAction(fileReader),
				new ReadFileAction(fileReader)
		);
	}

	@Test
	public void invalidOptionActionTypeExceptionTest() {
		val exception = exceptionThrownBy(() -> {
			executor.execute(
					new ActionData("invalidOptionActionType", new FileLocation("", ""))
			);
		});

		assertEquals(InvalidOptionActionTypeException.class, exception.getClass());
	}

	@Test
	public void parseActionTest() throws URISyntaxException, IOException, InvalidOptionActionTypeException {
		val fileName = "document.txt";
		val resource = ActionExecutorTests.class.getClassLoader().getResource(fileName);
		val file = new File(resource.toURI());

		val parseOptionActionType = ApplicationOptionsImpl.OPTION_PARSE.getLongOpt();

		// since we know actual type we can safely cast to concrete result type
		val executionResult = (ParseFileActionResult) executor.execute(
				new ActionData(parseOptionActionType, new FileLocation(file.getName(), file.getParent()))
		);

		assertNotNull(executionResult.getResponse());
		assertEquals(1, executionResult.getResponse().getCertIds().size());
		assertEquals(OCSPResponse.ResponseStatus.SUCCESSFUL, executionResult.getResponse().getResponseStatus());
	}

	@Test
	public void parseInvalidFileActionTest() throws URISyntaxException {
		val fileName = "shortDocument.txt";
		val resource = ActionExecutorTests.class.getClassLoader().getResource(fileName);
		val file = new File(resource.toURI());

		val parseOptionActionType = ApplicationOptionsImpl.OPTION_PARSE.getLongOpt();

		val exception = exceptionThrownBy(() -> {
			executor.execute(
					new ActionData(parseOptionActionType, new FileLocation(file.getName(), file.getParent()))
			);
		});

		assertEquals(IOException.class, exception.getClass());
		assertEquals("extra data given to DerValue constructor", exception.getMessage());
	}

	@Test
	public void readActionTest() throws URISyntaxException, IOException, InvalidOptionActionTypeException {
		val expectedData = "[48, -126, 3, -71, 10, 1, 0, -96, -126, 3, -78, 48, -126, 3, -82, 6, 9, 43, 6, 1, 5, 5, 7, 48]";
		val fileName = "shortDocument.txt";
		val resource = ActionExecutorTests.class.getClassLoader().getResource(fileName);
		val file = new File(resource.toURI());

		val readOptionActionType = ApplicationOptionsImpl.OPTION_READ.getLongOpt();

		// since we know actual type we can safely cast to concrete result type
		val executionResult = (ReadFileActionResult) executor.execute(
				new ActionData(readOptionActionType, new FileLocation(file.getName(), file.getParent()))
		);

		assertNotNull(executionResult.getData());
		assertEquals(expectedData, executionResult.getData());
	}
}
