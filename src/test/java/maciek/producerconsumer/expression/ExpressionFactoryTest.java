package maciek.producerconsumer.expression;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import maciek.producerconsumer.expression.ExpressionFactory;
import maciek.producerconsumer.expression.ExpressionValidator;

public class ExpressionFactoryTest {
	
	@Test
	public void testExpressionCreation() {
		
		ExpressionFactory factory = ExpressionFactory.builder()
				.random(new Random(1))
				.expressionValidator(new ExpressionValidator())
				.build();
		
		assertEquals("86-351984", factory.createExpression());
		assertEquals("74706", factory.createExpression());
		assertEquals("88247*7327673000865+777804935+2856434", factory.createExpression());
	}
	
}
