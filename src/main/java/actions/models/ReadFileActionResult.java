package actions.models;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class ReadFileActionResult extends ExecutionResult {
	String data;
}
