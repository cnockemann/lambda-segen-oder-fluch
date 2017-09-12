package de.viadee.lambda.codebeispiele.lambdaVsInnerClass;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaVsInnerClass2_Lambda {

	public static void main(String[] args) {

		Stream<Integer> filteredByLambda = Stream.of(2, 5, 10, 20).filter(x -> x >= 10);

		String lambda = filteredByLambda.map(String::valueOf).collect(Collectors.joining(","));

		// @formatter:off
		//	ByteCode dazu
		//	34: invokestatic  #22                 // InterfaceMethod java/util/stream/Stream.of:([Ljava/lang/Object;)Ljava/util/stream/Stream;
		//	37: invokedynamic #31,  0             // InvokeDynamic #0:test:()Ljava/util/function/Predicate;
		//	42: invokeinterface #32,  2           // InterfaceMethod java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
		// @formatter:on

		System.out.println("Lambda: " + lambda);
	}

}
