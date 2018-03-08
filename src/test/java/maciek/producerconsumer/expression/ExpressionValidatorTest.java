package maciek.producerconsumer.expression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import maciek.producerconsumer.expression.ExpressionValidator;

public class ExpressionValidatorTest {
	
	@Test
	public void testValidation() {
		ExpressionValidator validator = new ExpressionValidator();
		
		assertTrue(validator.validate("2"));
		assertTrue(validator.validate("+2"));
		assertTrue(validator.validate("-2"));
		assertTrue(validator.validate("2+2"));
		assertTrue(validator.validate("2/2"));
		assertTrue(validator.validate("323/65323 * 24323/2343 + 2234-2233"));
		
		assertFalse(validator.validate("2-"));
		assertFalse(validator.validate("2/"));
		assertFalse(validator.validate("2*"));
		assertFalse(validator.validate("1++2"));
		assertFalse(validator.validate("1+*2"));
		assertFalse(validator.validate("1//2"));
		
	}
	
}
