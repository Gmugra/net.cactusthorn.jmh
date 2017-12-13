package net.cactusthorn.jmh;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Measurement(batchSize = 10000, iterations = 10)
@Warmup(batchSize = 10000, iterations = 10)
public class Replace {

	public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Replace.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	private static final String PS = "{{";
	private static final String PE = "}}";
	private static final int PSL = PS.length();
	private static final int PEL = PE.length();
	
	private static Map<String,String> PARAMS;
	static {
		PARAMS = new HashMap<>();
		PARAMS.put("param1", "AAA");
		PARAMS.put("param2", "BBB");
	}
	 
	private static String replace(String message, final Map<String, String> params) {
		
		StringBuilder result = new StringBuilder();
		int endIndex = -1 * PEL;
		int beginIndex = message.indexOf(PS);
		while (beginIndex != -1 ) {
	
			result.append(message.substring(endIndex + PEL, beginIndex ));
			
			endIndex = message.indexOf(PE, beginIndex + PSL);
			if (endIndex == -1) {
				break;
			}
			
			String parameter = message.substring(beginIndex + PSL, endIndex );
			if (params.containsKey(parameter ) ) {
				result.append(params.get(parameter));
			} else {
				result.append(message.substring(beginIndex, endIndex + PEL ));
			}
			
			beginIndex = message.indexOf(PS, endIndex + PEL);
		}
		if (endIndex != -1 ) {
			result.append(message.substring(endIndex+2));
		} else if (beginIndex != -1 ) {
			result.append(message.substring(beginIndex));
		}
		
		return result.toString();
	}
	
	private static String replaceStr(String message, final Map<String, String> params) {
		
		String result = message;
		
		for (Map.Entry<String,String> e : PARAMS.entrySet() ) {
			result = result.replace(PS + e.getKey() + PE, e.getValue()); 
		}
		
		return result;
	}
	
	@Benchmark
	public String everyStringReplace() {
		
		return replaceStr("default {{param1}} message {{param2}} XYZ", PARAMS);
	}
	 
	@Benchmark
	public String useManualReplace()
	{
		return replace("default {{param1}} message {{param2}} XYZ", PARAMS);
	}
	
}
