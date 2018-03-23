package net.cactusthorn.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Measurement(batchSize = 10000, iterations = 10)
@Warmup(batchSize = 10000, iterations = 10)
public class StringBufferVS {

	public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringBufferVS.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	@Benchmark
	public String stringBuffer() {
	    StringBuffer s = new StringBuffer();
	    s.append("whghighreghghpghrpghwrpnhpnwrpbnwrbnrpbnrbnrbnrpbnreponbrepobnreobnreb");
	    s.append("whghighreghghpghrpghwrpnhpnwrpbnwrbnrpbnrbnrbnrpbnreponbrepobnreobn34575reb");
	    s.append("whghighreghghpghrpghwrpnhpnwrpbnwrbnrpbnrbnrbnrpbnreponbrepobnr6666eobnreb");
	    return s.toString();
	}
	
	@Benchmark
	public String stringBuilder() {
		StringBuilder s = new StringBuilder();
		s.append("whghighreghghpghrpghwrpnhpnwrpbnwrbnrpbnrbnrbnrpbnreponbrepobnreobnreb");
		s.append("whghighreghghpghrpghwrpnhpnwrpbnwrbnrpbnrbnrbnrpbnreponbrepobnreobn34575reb");
		s.append("whghighreghghpghrpghwrpnhpnwrpbnwrbnrpbnrbnrbnrpbnreponbrepobnr6666eobnreb");
	return s.toString();
	}
	
	@Benchmark
	public String stringSimple() {
		String s = 
				"whghighreghghpghrpghwrpnhpnwrpbnwrbnrpbnrbnrbnrpbnreponbrepobnreobnreb"
				+ "whghighreghghpghrpghwrpnhpnwrpbnwrbnrpbnrbnrbnrpbnreponbrepobnreobn34575reb"
				+ "whghighreghghpghrpghwrpnhpnwrpbnwrbnrpbnrbnrbnrpbnreponbrepobnreobn34575reb";
		
		return s;
	}
}
