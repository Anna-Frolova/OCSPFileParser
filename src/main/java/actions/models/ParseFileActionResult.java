package actions.models;

import lombok.EqualsAndHashCode;
import lombok.Value;
import sun.security.provider.certpath.OCSPResponse;

@Value
@EqualsAndHashCode(callSuper = true)
public class ParseFileActionResult extends ExecutionResult {
	OCSPResponse response;
}
