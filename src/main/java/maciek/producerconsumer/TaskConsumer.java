package maciek.producerconsumer;

import java.util.function.Consumer;

import lombok.Builder;
import maciek.producerconsumer.expression.ExpressionEvaluator;

@Builder
public class TaskConsumer {

	private final TaskQueue queue;

	private final ExpressionEvaluator evaluator;

	private final Consumer<Object> println;

	public void start() {
		while (true) {
			executeNextTask();
		}
	}

	public void executeNextTask() {
		println.accept(evaluator.evaluate(queue.take().getExpression()));
	}

}
