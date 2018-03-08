package maciek.producerconsumer;

import java.math.BigDecimal;
import java.math.BigInteger;

import groovy.lang.GroovyShell;
import lombok.Builder;

@Builder
public class ExpressionEvaluator {

	private final GroovyShell shell;

	public BigDecimal evaluate(String expression) {
		
		Object result = shell.evaluate(expression);
		
		if (result instanceof Integer) 
			return BigDecimal.valueOf((Integer) result);
		if (result instanceof Long) 
			return BigDecimal.valueOf((Long) result);
		if (result instanceof BigInteger) 
			return new BigDecimal((BigInteger) result);
		
		return (BigDecimal) shell.evaluate(expression);
	}

}
