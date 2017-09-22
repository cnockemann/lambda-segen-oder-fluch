package de.viadee.lambda.codeexamples.performance;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Streams {

	public static void main(String[] args) {
		List<Integer> ints = new Random()
				.ints(100000, 0, Integer.MAX_VALUE)
				.boxed()
				.collect(Collectors.toList());
		long startForEach = System.currentTimeMillis();
		repeatForEach(ints, 1000);
		long endForEach = System.currentTimeMillis();

		long startStream = System.currentTimeMillis();
		repeatStream(ints, 1000);
		long endStream = System.currentTimeMillis();

		System.out.println("Runtime ForEach: " + (endForEach - startForEach) + "ms");
		System.out.println("Runtime Stream: " + (endStream - startStream) + "ms");
	}

	private static int forEachLoopMaxInteger(List<Integer> ints) {
		int max = Integer.MIN_VALUE;
		for (Integer n : ints) {
			max = Integer.max(max, n);
		}
		return max;
	}

	private static int lambdaMaxInteger(List<Integer> ints) {
		return ints.stream().reduce(Integer.MIN_VALUE, (a, b) -> Integer.max(a, b));
	}

	private static void repeatForEach(List<Integer> ints, int times) {
		for (int i = 0; i <= times; i++) {
			forEachLoopMaxInteger(ints);
		}
	}

	private static void repeatStream(List<Integer> ints, int times) {
		for (int i = 0; i <= times; i++) {
			lambdaMaxInteger(ints);
		}
	}

}
