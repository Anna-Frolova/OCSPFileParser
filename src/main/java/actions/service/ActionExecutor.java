package actions.service;

import actions.models.ActionData;
import actions.models.ExecutionResult;
import actions.exceptions.InvalidOptionActionTypeException;

import java.io.IOException;

public interface ActionExecutor {
	ExecutionResult execute(ActionData actionData) throws IOException, InvalidOptionActionTypeException;
}
