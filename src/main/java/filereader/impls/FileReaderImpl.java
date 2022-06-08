package filereader.impls;

import filereader.FileReader;
import filereader.models.FileLocation;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderImpl implements FileReader {

	@Override
	public List<Byte> readBytes(FileLocation location) throws IOException {
		val directory = Paths.get(location.getDirectory());
		val filePath = directory.resolve(location.getFileName());

		val lines = Files.readAllLines(filePath);

		val sb = new StringBuilder();
		lines.forEach(sb::append);

		val numberStrings = sb.toString()
				.trim()
				.split(",");

		val bytes = new ArrayList<Byte>();
		for (String numberString : numberStrings) {
			bytes.add((byte) Double.parseDouble(numberString));
		}

		return bytes;
	}
}
