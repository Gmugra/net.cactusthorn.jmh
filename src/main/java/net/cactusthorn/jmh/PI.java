package net.cactusthorn.jmh;

import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

// https://stackoverflow.com/questions/40311990/run-jmh-benchmark-under-eclipse
@State(Scope.Benchmark)
public class PI {
	 
	public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(PI.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	private double x = Math.PI;
	 
	@Benchmark
	@Warmup(iterations = 5)
	@Measurement(iterations = 10)
	public double testWrong()
	{
	    return Math.sin( Math.PI );
	}
	 
	@Benchmark
	@Warmup(iterations = 5)
	@Measurement(iterations = 10)
	public double testRight()
	{
	    return Math.sin( x );
	}
}
