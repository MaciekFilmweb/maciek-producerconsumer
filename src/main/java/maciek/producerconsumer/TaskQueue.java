package maciek.producerconsumer;

import java.util.concurrent.BlockingQueue;

import lombok.Builder;

@Builder
public class TaskQueue {

	private final int maximumCapacity;

	private final BlockingQueue<Task> queue;

	public double getFillRate() {
		return 1.0 * (maximumCapacity - queue.remainingCapacity()) / maximumCapacity;
	}

	public boolean offer(Task task) {
		return queue.offer(task);
	}

	public Task take() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
