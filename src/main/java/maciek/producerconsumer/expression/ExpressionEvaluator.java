package maciek.producerconsumer.expression;

import java.math.BigDecimal;

import groovy.lang.GroovyShell;
import lombok.Builder;

@Builder
public class ExpressionEvaluator {

	private final GroovyShell shell;

	private final ExpressionValidator expressionValidator;

	public BigDecimal evaluate(String expression) {
		if (!expressionValidator.validate(expression)) {
			throw new IllegalArgumentException("Invalid expression [" + expression + "].");
		}
		Object result = shell.evaluate(expression);
		if (result instanceof Integer) {
			return BigDecimal.valueOf((Integer) result);
		}
		return (BigDecimal) shell.evaluate(expression);
	}

}
