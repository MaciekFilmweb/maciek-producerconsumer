package maciek.producerconsumer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.function.Supplier;

import org.junit.Test;

public class TaskQueueTest {

	@Test
	public void testFillingQueue() throws Exception {

		Supplier<Task> createTask = () -> Task.withExpression("2+2");

		TaskQueueImpl queue = TaskQueueImpl.create(4);

		assertTrue(queue.offer(createTask.get()));
		assertTrue(queue.offer(createTask.get()));
		assertTrue(queue.offer(createTask.get()));
		assertTrue(queue.offer(createTask.get()));

		assertFalse(queue.offer(createTask.get()));

		queue.take();

		assertTrue(queue.offer(createTask.get()));
	}

	@Test
	public void testMaxFillRate() throws Exception {

		Supplier<Task> createTask = () -> Task.withExpression("2+2");

		TaskQueueImpl queue = TaskQueueImpl.create(4);

		assertTrue(queue.offer(createTask.get(), 0.5));
		assertTrue(queue.offer(createTask.get(), 0.5));
		assertTrue(queue.offer(createTask.get(), 0.5));

		assertFalse(queue.offer(createTask.get(), 0.5));

		queue.take();

		assertTrue(queue.offer(createTask.get(), 0.5));
	}

}
