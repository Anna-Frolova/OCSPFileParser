package filereader;

import filereader.models.FileLocation;

import java.io.IOException;
import java.util.List;

public interface FileReader {
	List<Byte> readBytes(FileLocation location) throws IOException;
}
