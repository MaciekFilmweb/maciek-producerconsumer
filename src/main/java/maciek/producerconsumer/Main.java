package maciek.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import groovy.lang.GroovyShell;
import maciek.producerconsumer.expression.ExpressionEvaluator;
import maciek.producerconsumer.expression.ExpressionFactory;
import maciek.producerconsumer.expression.ExpressionValidator;

public class Main {

	public static void main(String[] args) {

		final TaskQueue queue = TaskQueue.builder()
		        .maximumCapacity(100)
		        .queue(new ArrayBlockingQueue<Task>(100))
		        .build();

		final ExpressionValidator expressionValidator = new ExpressionValidator();

		final ExpressionFactory expressionFactory = ExpressionFactory.builder()
		        .expressionValidator(expressionValidator)
		        .random(new Random())
		        .build();

		final ExpressionEvaluator expressionEvaluator = ExpressionEvaluator.builder()
		        .shell(new GroovyShell())
		        .expressionValidator(expressionValidator)
		        .build();

		final TaskFactory taskFactory = TaskFactory.builder()
		        .expressionFactory(expressionFactory)
		        .build();

		final ExecutorService executorService = Executors.newFixedThreadPool(6);

		for (int i = 0; i < 2; i++) {
			executorService.submit(new Runnable() {

				public void run() {
					TaskProducer producer = TaskProducer.builder()
					        .queue(queue)
					        .taskFactory(taskFactory)
					        .build();

					producer.start();
				}
			});
		}

		for (int i = 0; i < 4; i++) {
			executorService.submit(new Runnable() {

				public void run() {
					TaskConsumer consumer = TaskConsumer.builder()
							.queue(queue)
					        .evaluator(expressionEvaluator)
					        .println(System.out::println)
					        .build();

					consumer.start();
				}
			});
		}
	}

}
