package maciek.producerconsumer;

import lombok.Builder;

@Builder
public class TaskProducer {

	private final TaskFactory taskFactory;

	private final TaskQueue queue;

	private Task nextTask;

	public void start() {
		while (true) {
			fillTheQueue();
			waitTillQueueIsHalfEmpty();
		}
	}

	/**
	 * @return {@code true} if task was send to the queue, {@code false} if the queue is full.
	 */
	public boolean produceNextTask() {
		if (nextTask == null) {
			nextTask = taskFactory.createTask();
		}
		if (!queue.offer(nextTask)) {
			return false;
		}
		nextTask = taskFactory.createTask();
		return true;
	}
	
	private void fillTheQueue() {
		while (true) {
			if (!produceNextTask()) {
				return;
			}
		}
	}

	private void waitTillQueueIsHalfEmpty() {
		while (queue.getFillRate() > 0.5) {
			sleep(1000);
		}
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
