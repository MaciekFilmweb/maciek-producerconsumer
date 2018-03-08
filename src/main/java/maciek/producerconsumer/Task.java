package maciek.producerconsumer;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName="withExpression")
public class Task {
	
	private final String expression;

}
