package de.viadee.lambda.codebeispiele.lambdaVsInnerClass;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaVsInnerClass2_InnerClass {

	public static void main(String[] args) {

		class InnerClass implements Predicate<Integer> {

			private Integer y;

			public InnerClass(Integer y) {
				this.y = y;
			}

			@Override
			public boolean test(Integer x) {
				return x >= y;
			}

		}

		Stream<Integer> filterdByInnerClass = Stream.of(2, 5, 10, 20).filter(new InnerClass(10));
		
		String innerClass = filterdByInnerClass.map(String::valueOf).collect(Collectors.joining(","));

		// @formatter:off
		// 	ByteCode dazu
		//	34: invokestatic  #22                 // InterfaceMethod java/util/stream/Stream.of:([Ljava/lang/Object;)Ljava/util/stream/Stream;
		//	37: new           #28                 // class de/viadee/lambda/codebeispiele/lambdaVsInnerClass/LambdaVsInnerClass2$1InnerClass
		//	40: dup
		//	41: bipush        10
		//	43: invokestatic  #18                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
		//	46: invokespecial #30                 // Method de/viadee/lambda/codebeispiele/lambdaVsInnerClass/LambdaVsInnerClass2$1InnerClass."<init>":(Ljava/lang/Integer;)V
		//	49: invokeinterface #33,  2           // InterfaceMethod java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
		// @formatter:on

		System.out.println("InnerClass: " + innerClass);
		
	}

}
