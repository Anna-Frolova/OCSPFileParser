package actions.service.impls;

import actions.service.ActionExecutor;
import actions.models.ActionData;
import actions.strategies.ActionStrategy;
import actions.models.ExecutionResult;
import actions.exceptions.InvalidOptionActionTypeException;
import lombok.val;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ActionExecutorImpl implements ActionExecutor {

	private final Map<String, ActionStrategy> map;

	public ActionExecutorImpl(ActionStrategy... strategies) {
		this.map = new HashMap<>();
		for (val strategy : strategies) {
			this.map.put(strategy.getOptionAction(), strategy);
		}
	}

	@Override
	public ExecutionResult execute(ActionData actionData) throws IOException, InvalidOptionActionTypeException {
		val actionType = actionData.getOptionActionType();
		val strategy = map.get(actionType);
		if (strategy == null) {
			throw new InvalidOptionActionTypeException(actionType);
		}
		return strategy.process(actionData.getFileLocation());
	}
}
