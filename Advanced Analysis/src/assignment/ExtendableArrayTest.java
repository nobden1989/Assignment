package assignment;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ExtendableArrayTest {

	private static final int INT_INITLEN = 10;
	private static final int INT_ELEMTSIZE = 1000000;
	private static final int[] INTS_CS = new int[] { 10, 50, 100, 200, 500, 1000, 2000, 5000,
			10000 };

	public static void main(String[] args) {

		for (int k = 0; k < 51; k++) {
			for (int i : INTS_CS) {
				RunTest(i);
			}
			// System.out.println("O");
			// for (double i = 1.2; i <= 4.1; i += 0.1) {
			// RunTest(i);
			// }

			// System.out.println("P");
			// for (double i = 1.2; i <= 4.1; i += 0.1) {
			// RunObjectTest(i);
			// }
			System.out.println();
		}

	}

	private static void RunTest(int inc) {

		int[] testArray = new int[INT_INITLEN];
		long totalTime = 0;
		for (int i = 0; i < INT_ELEMTSIZE; i++) {
			int orgSize = testArray.length;
			long startTime = System.nanoTime();
			if (testArray.length <= i) {
				testArray = ReallocateArray(testArray, orgSize + inc);
			} else {
				testArray[i] = i;
			}
			totalTime += System.nanoTime() - startTime;
		}

		System.out.print(TimeUnit.NANOSECONDS.toMillis(totalTime) + ",");
	}

	private static void RunTest(double inc) {

		int[] testArray = new int[INT_INITLEN];
		long totalTime = 0;
		for (int i = 0; i < INT_ELEMTSIZE; i++) {
			int orgSize = testArray.length;
			long startTime = System.nanoTime();
			if (testArray.length <= i) {
				testArray = ReallocateArray(testArray, (int) (orgSize * inc));
			} else {
				testArray[i] = i;
			}
			totalTime += System.nanoTime() - startTime;
		}

		System.out.print(totalTime + ",");
	}

	private static void RunObjectTest(double inc) {

		Integer[] testArray = new Integer[INT_INITLEN];
		long totalTime = 0;
		for (int i = 0; i < INT_ELEMTSIZE; i++) {
			int orgSize = testArray.length;
			long startTime = System.nanoTime();
			if (testArray.length <= i) {
				testArray = ReallocateArray(testArray, (int) (orgSize * inc));
			} else {
				testArray[i] = i;
			}
			totalTime += System.nanoTime() - startTime;
		}

		System.out.print(totalTime + ",");
	}

	private static int[] ReallocateArray(int[] orgArray, int newSize) {

		int[] newArray = new int[newSize];

		int token = 0;

		for (int item : orgArray) {

			newArray[token] = item;
			token++;
		}

		return newArray;

	}

	private static Integer[] ReallocateArray(Integer[] orgArray, Integer newSize) {

		Integer[] newArray = new Integer[newSize];

		int token = 0;

		for (Integer item : orgArray) {

			newArray[token] = item;
			token++;
		}

		return newArray;

	}

}
