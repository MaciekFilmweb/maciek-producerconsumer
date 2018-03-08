package maciek.producerconsumer;

import lombok.Builder;
import maciek.producerconsumer.util.Sleep;

@Builder
public class TaskProducer implements Runnable {

	private final TaskFactory taskFactory;

	private final TaskQueue queue;

	private Runnable state;

	private volatile boolean running;

	@Override
	public void run() {
		init();

		while (running) {
			state.run();
		}
	}
	
	public void stop() {
		running = false;
	}

	private void init() {
		running = true;
		state = producing;
	}

	private final Runnable producing = new Runnable() {

		@Override
		public void run() {
			while (running) {
				if (!queue.offer(taskFactory.createTask())) {
					break;
				}
			}
			state = waiting;
		}

	};

	private final Runnable waiting = new Runnable() {

		@Override
		public void run() {
			while (running && !queue.offer(taskFactory.createTask(), 0.5)) {
				Sleep.sleep(500);
			}
			state = producing;
		}

	};
	
}
