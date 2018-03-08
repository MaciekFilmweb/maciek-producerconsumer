package maciek.producerconsumer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ExpressionValidatorTest {
	
	@Test
	public void testValidation() {
		ExpressionValidator validator = new ExpressionValidator();
		
		assertTrue(validator.validate("2"));
		assertTrue(validator.validate("+2"));
		assertTrue(validator.validate("-2"));
		assertTrue(validator.validate("2+2"));
		assertTrue(validator.validate("2/2"));
		assertTrue(validator.validate("323/65323*24323/2343+2234-2233"));
		
		assertFalse(validator.validate("2-"));
		assertFalse(validator.validate("2/"));
		assertFalse(validator.validate("2*"));
		assertFalse(validator.validate("1++2"));
		assertFalse(validator.validate("1+*2"));
		assertFalse(validator.validate("1//2"));
		assertFalse(validator.validate("3+02"));
		assertFalse(validator.validate("1/0"));
		assertFalse(validator.validate(""));
		
	}
	
}
