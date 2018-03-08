package maciek.producerconsumer;

import java.util.function.Consumer;

import lombok.Builder;

@Builder
public class TaskConsumer implements Runnable {

	private final TaskQueue queue;

	private final ExpressionEvaluator evaluator;

	private final Consumer<String> println;

	private volatile boolean running;

	@Override
	public void run() {
		running = true;

		while (running) {
			println.accept(evaluator.evaluate(queue.take().getExpression()).toString());
		}
	}

	public void stop() {
		running = false;
	}

}
