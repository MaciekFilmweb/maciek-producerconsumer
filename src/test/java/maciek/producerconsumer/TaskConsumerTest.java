package maciek.producerconsumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import groovy.lang.GroovyShell;

public class TaskConsumerTest {

	@Test
	public void test() throws Exception {
		
		ExpressionEvaluator expressionEvaluator = ExpressionEvaluator.builder()
		        .shell(new GroovyShell())
		        .build();
		
		TaskQueue queue = TaskQueueImpl.create(4);
		
		queue.offer(Task.withExpression("2+3"));
		queue.offer(Task.withExpression("3+4"));
		queue.offer(Task.withExpression("4+5"));
		queue.offer(Task.withExpression("5+6"));
		
		List<String> printedLines = Collections.synchronizedList(new LinkedList<>());
		
		TaskConsumer consumer1 = TaskConsumer.builder()
				.evaluator(expressionEvaluator)
				.queue(queue)
				.println(printedLines::add)
				.build();
		TaskConsumer consumer2 = TaskConsumer.builder()
				.evaluator(expressionEvaluator)
				.queue(queue)
				.println(printedLines::add)
				.build();

		new Thread(consumer1).start();
		new Thread(consumer2).start();
		
		Thread.sleep(2000);

		consumer1.stop();
		consumer2.stop();
		
		System.err.println(printedLines);
		
		assertEquals(4, printedLines.size());
		
		assertTrue(printedLines.contains("5"));
		assertTrue(printedLines.contains("7"));
		assertTrue(printedLines.contains("9"));
		assertTrue(printedLines.contains("11"));
	}

}
