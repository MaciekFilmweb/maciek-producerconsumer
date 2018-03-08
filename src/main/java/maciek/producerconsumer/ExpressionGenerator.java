package maciek.producerconsumer;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;

import lombok.Builder;

@Builder
public class ExpressionGenerator {

	private static final int MAXIMUM_EXPRESSION_LENGTH = 100;

	private final Random random;
	
	private final ExpressionValidator expressionValidator;

	public String generateExpression() {
		String expression = generateRandomString();
		while (!expressionValidator.validate(expression)) {
			expression = generateRandomString();
		}
		return expression.toString();
	}

	private String generateRandomString() {
		StringBuilder expression = new StringBuilder();

		int length = random.nextInt(MAXIMUM_EXPRESSION_LENGTH);
		for (int i = 0; i < length; i++) {
			expression.append(generateDigitOrOperator());
		}
		return expression.toString();
	}

	private static final Map<Integer, String> OPERATORS = ImmutableMap.<Integer, String>builder()
	        .put(10, "+")
	        .put(11, "-")
	        .put(12, "*")
	        .put(13, "/")
	        .build();

	private String generateDigitOrOperator() {
		int i = random.nextInt(13);
		if (i > 9) {
			return OPERATORS.get(i);
		}
		return String.valueOf(i);
	}

}
