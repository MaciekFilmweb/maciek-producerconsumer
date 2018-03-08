package maciek.producerconsumer;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class ExpressionValidator {
	
	public static final List<String> INVALID_CHARACTER_COMBINATIONS_REGEXES = ImmutableList.<String>builder()
	        .add("^[\\*/0]")
	        .add("[\\+\\-\\*/]$")
	        .add("[\\+\\-\\*/]{2}")
	        .add("[\\+\\-\\*/]0")
	        .build();
	
	public boolean validate(String expression) {
		if (expression.isEmpty()) {
			return false;
		}
		for (String regex : INVALID_CHARACTER_COMBINATIONS_REGEXES) {
			if (expression.matches(".*" + regex + ".*")) {
				return false;
			}
		}
		return true;
	}

}
