package de.viadee.lambda.codebeispiele.lambdaVsInnerClass;

import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaVsInnerClass2 {

	public static void main(String[] args) {

		// Lambda

		String lambda = IntStream.of(2, 5, 10, 20)
				.filter(x -> x >= 10)
				.mapToObj(Integer::toString)
				.collect(Collectors.joining(","));

		System.out.println("Lambda: " + lambda);

		// Inner Class

		class InnerClass implements IntPredicate {

			private Integer y;

			public InnerClass(Integer y) {
				this.y = y;
			}


			@Override
			public boolean test(int x) {
				return x >= y;
			}

		}

		String innerClass = IntStream.of(2, 5, 10, 20)
				.filter(new InnerClass(10))
				.mapToObj(Integer::toString)
				.collect(Collectors.joining(","));

		System.out.println("InnerClass: " + innerClass);

	}

}
