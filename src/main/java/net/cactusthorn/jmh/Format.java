package net.cactusthorn.jmh;

import java.text.DecimalFormat;
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
public class Format {

	public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Format.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	private static DecimalFormat fcdf = new DecimalFormat("#0.00");
	private static ThreadLocal<DecimalFormat> tldf = ThreadLocal.withInitial(() -> new DecimalFormat("#0.00"));
	 
	@Benchmark
	public String everyTimeCreate()
	{
	    DecimalFormat df = new DecimalFormat("#0.00");
	    return df.format(20.20f);
	}
	 
	@Benchmark
	public String useGlobal()
	{
		DecimalFormat df = tldf.get();
	    return df.format(20.20f);
	}
	
	@Benchmark
	public String useClone()
	{
		DecimalFormat df = (DecimalFormat)fcdf.clone();
	    return df.format(20.20f);
	}
}
