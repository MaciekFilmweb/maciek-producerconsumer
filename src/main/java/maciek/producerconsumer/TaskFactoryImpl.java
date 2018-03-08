package maciek.producerconsumer;

import lombok.Builder;

@Builder
public class TaskFactoryImpl implements TaskFactory {
	
	private final ExpressionGenerator expressionGenerator;

	@Override
	public Task createTask() {
		return Task.withExpression(expressionGenerator.generateExpression());
	}

}
