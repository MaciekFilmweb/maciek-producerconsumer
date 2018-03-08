package maciek.producerconsumer;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import groovy.lang.GroovyShell;

@Configuration
public class AppConfiguration {

	@Bean
	public TaskQueue taskQueue() {
		return TaskQueueImpl.create(100);
	}

	@Bean
	@Scope("prototype")
	public TaskProducer taskProducer() {
		return TaskProducer.builder()
		        .queue(taskQueue())
		        .taskFactory(taskFactory())
		        .build();
	}

	@Bean
	@Scope("prototype")
	public TaskConsumer taskConsumer() {
		return TaskConsumer.builder()
		        .queue(taskQueue())
		        .evaluator(expressionEvaluator())
		        .println(System.out::println)
		        .build();
	}

	@Bean
	public TaskFactory taskFactory() {
		return TaskFactoryImpl.builder()
		        .expressionGenerator(expressionGenerator())
		        .build();
	}

	@Bean
	public ExpressionValidator expressionValidator() {
		return new ExpressionValidator();
	}

	@Bean
	public ExpressionGenerator expressionGenerator() {
		return ExpressionGenerator.builder()
		        .expressionValidator(expressionValidator())
		        .random(random())
		        .build();
	}

	@Bean
	public ExpressionEvaluator expressionEvaluator() {
		return ExpressionEvaluator.builder()
		        .shell(groovyShell())
		        .build();
	}

	@Bean
	public GroovyShell groovyShell() {
		return new GroovyShell();
	}

	@Bean
	public Random random() {
		return new Random();
	}

}
