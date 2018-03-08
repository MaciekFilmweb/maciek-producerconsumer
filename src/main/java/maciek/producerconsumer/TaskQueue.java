package maciek.producerconsumer;

public interface TaskQueue {

	default boolean offer(Task task) {
		return offer(task, 1.0);
	}

	boolean offer(Task task, double maxFillRate);

	Task take();

}
