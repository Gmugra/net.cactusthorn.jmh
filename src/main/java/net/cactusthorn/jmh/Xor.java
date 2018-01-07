package net.cactusthorn.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Measurement(batchSize = 10000, iterations = 10)
@Warmup(batchSize = 10000, iterations = 10)
public class Xor {

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(Xor.class.getSimpleName())
				.forks(1)
				.build();

		new Runner(opt).run();
	}
	
	public static final boolean BOOL = true;
	
	@Benchmark
	public static boolean iff() {
		
		int r = (int)(Math.random() * 100);
		
		boolean b = r > 50 ? true : false;
		
		return b ? !BOOL : BOOL;
	}
	
	@Benchmark
	public static boolean xor() {
		
		int r = (int)(Math.random() * 100);
		
		boolean b = r > 50 ? true : false;
		
		return b ^ BOOL;
	}
}
