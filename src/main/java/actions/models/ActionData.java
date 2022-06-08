package actions.models;

import filereader.models.FileLocation;
import lombok.Value;

@Value
public class ActionData {
	String optionActionType;
	FileLocation fileLocation;
}
