package maciek.producerconsumer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import lombok.Value;
import maciek.producerconsumer.util.Wait;

public class TaskProducerTest {

	@Test
	public void test() throws Exception {
		
		Task task = Task.withExpression("2+2");

		TaskFactory taskFactory = () -> task;

		TestTaskQueue queue = new TestTaskQueue();

		TaskProducer producer = TaskProducer.builder()
		        .queue(queue)
		        .taskFactory(taskFactory)
		        .build();

		new Thread(producer).start();

		for (int i = 0; i < 10; i++) {
			assertEquals(new Offer(task, 1.0), queue.getLastOffer(true));
		}
		
		assertEquals(new Offer(task, 1.0), queue.getLastOffer(false));

		for (int i = 0; i < 10; i++) {
			assertEquals(new Offer(task, 0.5), queue.getLastOffer(false));
		}
		
		assertEquals(new Offer(task, 0.5), queue.getLastOffer(true));

		for (int i = 0; i < 10; i++) {
			assertEquals(new Offer(task, 1.0), queue.getLastOffer(true));
		}

		producer.stop();

	}

	static class TestTaskQueue implements TaskQueue {
		
		private volatile Offer lastOffer;

		private volatile boolean accept = true;

		@Override
		public synchronized boolean offer(Task task, double maxFillRate) {
			lastOffer = new Offer(task, maxFillRate);
			notifyLastOfferGetter();
			return accept;
		}

		public synchronized Offer getLastOffer(boolean acceptNext) {
			try {
				waitOnProducer();
				this.accept = acceptNext;
				return lastOffer;
			} finally {
				lastOffer = null;
				notify();
			}
		}
		
		private void notifyLastOfferGetter() {
			notify();
			Wait.wait(this);
		}

		private void waitOnProducer() {
			while (lastOffer == null) {
				Wait.wait(this);
			}
		}

		@Override
		public Task take() {
			throw new UnsupportedOperationException();
		}

	}

	@Value
	public static class Offer {

		Task task;

		double maxFillRate;

	}

}
