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
		
		// @formatter:off
		// ByteCode dazu
		//		invokedynamic #31,  0             // InvokeDynamic #0:test:()Ljava/util/function/Predicate;
		//		invokeinterface #32,  2           // InterfaceMethod java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
		//		invokedynamic #39,  0             // InvokeDynamic #1:apply:()Ljava/util/function/Function;
		//		invokeinterface #40,  2           // InterfaceMethod java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
		//		ldc           #44                 // String ,
		//		invokestatic  #46                 // Method java/util/stream/Collectors.joining:(Ljava/lang/CharSequence;)Ljava/util/stream/Collector;
		// 		invokeinterface #52, 2            // InterfaceMethod java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
		// @formatter:on
		
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
		
		// @formatter:off
		// ByteCode dazu
		//		invokestatic  #18                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
		//		invokespecial #86                 // Method de/viadee/lambda/codebeispiele/sprachAenderungen/LambdaVsInnerClass2$1InnerClass."<init>":(Ljava/lang/Integer;)V
		//		invokeinterface #32,  2           // InterfaceMethod java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
		//		invokedynamic #89,  0             // InvokeDynamic #2:apply:()Ljava/util/function/Function;
		//		invokeinterface #40,  2           // InterfaceMethod java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
		//		ldc           #44                 // String ,
		//		invokestatic  #46                 // Method java/util/stream/Collectors.joining:(Ljava/lang/CharSequence;)Ljava/util/stream/Collector;
		//		invokeinterface #52,  2           // InterfaceMethod java/util/stream/Stream.collect:(Ljava/util/stream/Collector;)Ljava/lang/Object;
		// @formatter:on
		
		System.out.println("InnerClass: " + innerClass);

	}

}
