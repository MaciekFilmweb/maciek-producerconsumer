package maciek.producerconsumer;

import lombok.Builder;
import maciek.producerconsumer.expression.ExpressionFactory;

@Builder
public class TaskFactory {
	
	private final ExpressionFactory expressionFactory;

	public Task createTask() {
		return Task.withExpression(expressionFactory.createExpression());
	}

}
