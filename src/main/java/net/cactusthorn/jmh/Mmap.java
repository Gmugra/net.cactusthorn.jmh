package net.cactusthorn.jmh;

import org.openjdk.jmh.annotations.*;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

// https://stackoverflow.com/questions/40311990/run-jmh-benchmark-under-eclipse
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Measurement(batchSize = 10000)
@Warmup(batchSize = 10000)
public class Mmap {
	 
	public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Mmap.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
	
	//int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	int[] arr = new int[] {1,2,3,4,5};
	 
	@Benchmark
	@Warmup(iterations = 5)
	@Measurement(iterations = 10)
	public void testDefault()
	{
	    Map<Integer,Integer> mm = new HashMap<>();
	    for (int i : arr) {
	    	mm.put(i, i);
	    }
	}
	 
	@Benchmark
	@Warmup(iterations = 5)
	@Measurement(iterations = 10)
	public void testSet()
	{
		Map<Integer,Integer> mm = new HashMap<>(arr.length, 0.95f);
	    for (int i : arr) {
	    	mm.put(i, i);
	    }
	}
	
	@Benchmark
	@Warmup(iterations = 5)
	@Measurement(iterations = 10)
	public void testSet1()
	{
		Map<Integer,Integer> mm = new HashMap<>(arr.length, 1f);
	    for (int i : arr) {
	    	mm.put(i, i);
	    }
	}
	
	@Benchmark
	@Warmup(iterations = 5)
	@Measurement(iterations = 10)
	public void testSetPlus1()
	{
		Map<Integer,Integer> mm = new HashMap<>(arr.length+1, 1f);
	    for (int i : arr) {
	    	mm.put(i, i);
	    }
	}
}
