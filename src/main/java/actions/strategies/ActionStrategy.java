package actions.strategies;

import actions.models.ExecutionResult;
import filereader.models.FileLocation;

import java.io.IOException;

public interface ActionStrategy<T extends ExecutionResult> {
	String getOptionAction();

	T process(FileLocation fileLocation) throws IOException;
}
