import filereader.FileReader;
import filereader.impls.FileReaderImpl;
import filereader.models.FileLocation;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;

import static com.github.stefanbirkner.fishbowl.Fishbowl.exceptionThrownBy;
import static org.junit.Assert.assertEquals;

public class FileReaderTests {

	private FileReader fileReader;

	@Before
	public void before() {
		this.fileReader = new FileReaderImpl();
	}

	@Test
	public void validFileLocationTest() throws IOException, URISyntaxException {
		val fileName = "shortDocument.txt";
		val resource = FileReaderTests.class.getClassLoader().getResource(fileName);
		val file = new File(resource.toURI());

		val expectedByteList = Arrays.asList(
				48, -126, 3, -71, 10, 1, 0, -96, -126, 3, -78,
				48, -126, 3, -82, 6, 9, 43, 6, 1, 5, 5, 7, 48
		);
		val actualByteList = fileReader.readBytes(new FileLocation(file.getName(), file.getParent()));

		assertEquals(expectedByteList.size(), actualByteList.size());
		for (int i = 0; i < expectedByteList.size(); i++) {
			byte expectedByte = Byte.parseByte(expectedByteList.get(i).toString());
			byte actualByte = actualByteList.get(i);
			assertEquals(expectedByte, actualByte);
		}
	}

	@Test
	public void invalidFileTest() throws URISyntaxException {
		val fileName = "invalidDocument.txt";
		val resource = FileReaderTests.class.getClassLoader().getResource(fileName);
		val file = new File(resource.toURI());

		val exception = exceptionThrownBy(() -> {
			fileReader.readBytes(new FileLocation(file.getName(), file.getParent()));
		});

		assertEquals(NumberFormatException.class, exception.getClass());
	}

	@Test
	public void invalidFileLocationTest() {
		val exception = exceptionThrownBy(() -> {
			fileReader.readBytes(new FileLocation("document.txt", "\\invalid\\path\\to"));
		});

		assertEquals(NoSuchFileException.class, exception.getClass());
	}
}
