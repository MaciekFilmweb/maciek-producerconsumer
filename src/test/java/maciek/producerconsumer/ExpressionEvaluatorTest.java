package maciek.producerconsumer;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.junit.Test;

import com.google.common.collect.ImmutableMap;

import groovy.lang.GroovyShell;

public class ExpressionEvaluatorTest {

	private ExpressionEvaluator evaluator = ExpressionEvaluator.builder()
	        .shell(new GroovyShell())
	        .build();

	@Test
	public void test() throws Exception {
		
		ImmutableMap<String, String> testCases = ImmutableMap.<String, String>builder()
				.put("2", "2")
				.put("9", "5+4")
				.put("1.0513311803", "323/65323*24323/2343+2234-2233")
				.put("51274980140191482402133918", "508*32361341+51274980140191483150198174-7762549+889*7+26")
				.build();
		
		for (Entry<String, String> testCase : testCases.entrySet()) {
			assertEquals(new BigDecimal(testCase.getKey()), evaluator.evaluate(testCase.getValue()));
		}
		
	}

}
