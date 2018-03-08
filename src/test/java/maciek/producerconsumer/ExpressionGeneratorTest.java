package maciek.producerconsumer;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class ExpressionGeneratorTest {
	
	@Test
	public void testExpressionCreation() {
		
		ExpressionGenerator factory = ExpressionGenerator.builder()
				.random(new Random(1))
				.expressionValidator(new ExpressionValidator())
				.build();
		
		assertEquals("86-351984", factory.generateExpression());
		assertEquals("74706", factory.generateExpression());
		assertEquals("88247*7327673000865+777804935+2856434", factory.generateExpression());
	}
	
}
