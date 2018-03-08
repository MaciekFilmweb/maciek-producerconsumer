package maciek.producerconsumer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {
	
	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(Main.class);
	}
	
	@PostConstruct
	public void run() {
		
		new Thread(context.getBean(TaskConsumer.class)).start();
		new Thread(context.getBean(TaskConsumer.class)).start();
		
		new Thread(context.getBean(TaskProducer.class)).start();
		new Thread(context.getBean(TaskProducer.class)).start();
		new Thread(context.getBean(TaskProducer.class)).start();
		new Thread(context.getBean(TaskProducer.class)).start();
		
	}

}
