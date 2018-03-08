package maciek.producerconsumer.expression;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import groovy.lang.GroovyShell;
import maciek.producerconsumer.expression.ExpressionEvaluator;
import maciek.producerconsumer.expression.ExpressionValidator;

public class ExpressionEvaluatorTest {

	private ExpressionEvaluator evaluator = ExpressionEvaluator.builder()
	        .shell(new GroovyShell())
	        .expressionValidator(new ExpressionValidator())
	        .build();

	@Test
	public void test() throws Exception {
		assertEquals(BigDecimal.valueOf(2), evaluator.evaluate("2"));
		assertEquals(BigDecimal.valueOf(9), evaluator.evaluate("5 + 4"));
		assertEquals(BigDecimal.valueOf(1.0513311803), evaluator.evaluate("323/65323 * 24323/2343 + 2234-2233"));
	}

}
