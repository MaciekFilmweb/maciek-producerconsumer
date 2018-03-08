package maciek.producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TaskQueueImpl implements TaskQueue {

	private final int maximumCapacity;

	private final BlockingQueue<Task> queue;

	public static TaskQueueImpl create(int capacity) {
		return new TaskQueueImpl(capacity, new ArrayBlockingQueue<>(capacity));
	}

	public double getFillRate() {
		return 1.0 * (maximumCapacity - queue.remainingCapacity()) / maximumCapacity;
	}

	@Override
	public synchronized boolean offer(Task task, double maxFillRate) {
		if (getFillRate() <= maxFillRate) {
			return queue.offer(task);
		}
		return false;
	}

	@Override
	public Task take() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
