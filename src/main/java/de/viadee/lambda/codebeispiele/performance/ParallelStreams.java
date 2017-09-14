package de.viadee.lambda.codebeispiele.performance;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class ParallelStreams {

	public static void main(String[] args) throws InterruptedException {
		Integer[] zehnLaeufe = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toArray(Integer[]::new);

		final long startSequentiell = System.currentTimeMillis();
		Stream.of(zehnLaeufe).forEach(warten(startSequentiell, "sequentiell"));

		System.out
				.println("==========================================================================================");

		final long startParallel = System.currentTimeMillis();
		Stream.of(zehnLaeufe).parallel().forEach(warten(startParallel, "parallel"));
	}

	private static Consumer<? super Integer> warten(final long startSeq, String typ) {
		return x -> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// Nichts tun
			}
			System.out.println("Dauer " + typ + " " + x + ": " + Thread.currentThread().getName() + " "
					+ (System.currentTimeMillis() - startSeq) + "ms");
		};
	}

}
