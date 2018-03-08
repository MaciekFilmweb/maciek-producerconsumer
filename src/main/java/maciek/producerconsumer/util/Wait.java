package maciek.producerconsumer.util;

public class Wait {
	
	public static void wait(Object monitor) {
		try {
			monitor.wait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
