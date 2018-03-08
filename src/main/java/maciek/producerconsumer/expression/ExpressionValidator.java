package maciek.producerconsumer.expression;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class ExpressionValidator {
	
	public static final List<String> INVALID_CHARACTER_COMBINATIONS_REGEXES = ImmutableList.<String>builder()
	        .add("^[\\*/]")
	        .add("[\\+\\-\\*/]$")
	        .add("[\\+\\-\\*/]{2}")
	        .build();
	
	public boolean validate(String expression) {
		for (String regex : INVALID_CHARACTER_COMBINATIONS_REGEXES) {
			if (expression.matches(".*" + regex + ".*")) {
				return false;
			}
		}
		return true;
	}

}
