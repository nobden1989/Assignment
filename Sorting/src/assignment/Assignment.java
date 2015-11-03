package assignment;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import sorting.RadixSort;
import sorting.Sort;
import util.StringHelper;

public class Assignment {

	private static final int SEED = 256;
	private static final int INT_DATASIZE = 100000;
	private static final int INT_LOOP_COUNT = 10;
	private static final int INT_SORT_COUNT = 100;

	public static void main(String[] args) {
		// Assignment2();
		Assignment3();
	}

	private static void Assignment2() {

		long totalTime = 0;
		long startTime = 0;

		// Run all methods before test in order to instantiate Sort Class
		Long[] initData = new Long[] { 3L, 2L, 1L, 0L };
		Sort.mergeSort(initData);
		Sort.heapsort(initData);
		Sort.quicksort(initData);
		Arrays.sort(initData);

		long[] testDataForDualQuick;

		Long[] testData;
		for (int i = 0; i < INT_SORT_COUNT; i++) {
			testData = PrepareLongData(INT_DATASIZE, i);
			startTime = System.nanoTime();
			Sort.mergeSort(testData);
			totalTime += System.nanoTime() - startTime;
		}

		System.out.println("Merge Sort: "
				+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_SORT_COUNT + " ms");

		totalTime = 0;
		for (int i = 0; i < INT_SORT_COUNT; i++) {
			testData = PrepareLongData(INT_DATASIZE, i);
			startTime = System.nanoTime();
			Sort.heapsort(testData);
			totalTime += System.nanoTime() - startTime;
		}

		System.out.println("Heap Sort: "
				+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_SORT_COUNT + " ms");
		totalTime = 0;
		for (int i = 0; i < INT_SORT_COUNT; i++) {
			testData = PrepareLongData(INT_DATASIZE, i);
			startTime = System.nanoTime();
			Sort.quicksort(testData);
			totalTime += System.nanoTime() - startTime;
		}

		System.out.println("Quick Sort: "
				+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_SORT_COUNT + " ms");
		totalTime = 0;
		for (int i = 0; i < INT_SORT_COUNT; i++) {
			testDataForDualQuick = PreparelongData(INT_DATASIZE, i);
			startTime = System.nanoTime();
			Arrays.sort(testDataForDualQuick);
			totalTime += System.nanoTime() - startTime;
		}

		System.out.println("Dual-quick Sort: "
				+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_SORT_COUNT + " ms");
		totalTime = 0;
		for (int i = 0; i < INT_SORT_COUNT; i++) {
			testData = PrepareLongData(INT_DATASIZE, i);
			startTime = System.nanoTime();
			Arrays.sort(testData);
			totalTime += System.nanoTime() - startTime;
		}

		System.out.println("Dual-quick Sort2: "
				+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_SORT_COUNT + " ms");
	}

	private static void Assignment3() {

		long totalTime = 0;
		long startTime = 0;
		String[] testData;
		boolean second = true;
		for (int i = 2; i <= 5; i++) {
			if (second && i == 3) {
				second = false;
				i--;
			}
			int strLen = i * 2;
			totalTime = 0;
			System.out.println("String Length: " + strLen);
			for (int j = 1; j <= INT_LOOP_COUNT; j++) {

				testData = PrepareStringData(INT_DATASIZE, strLen, j);
				startTime = System.nanoTime();
				Sort.mergeSort(testData);
				totalTime += System.nanoTime() - startTime;
			}

			System.out.println("Merge Sort: "
					+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_LOOP_COUNT + " ms");
			totalTime = 0;
			for (int j = 1; j <= INT_LOOP_COUNT; j++) {
				testData = PrepareStringData(INT_DATASIZE, strLen, j);
				startTime = System.nanoTime();
				Sort.heapsort(testData);
				totalTime += System.nanoTime() - startTime;
			}

			System.out.println("Heap Sort: "
					+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_LOOP_COUNT + " ms");
			totalTime = 0;
			for (int j = 1; j <= INT_LOOP_COUNT; j++) {
				testData = PrepareStringData(INT_DATASIZE, strLen, j);
				startTime = System.nanoTime();
				Sort.quicksort(testData);
				totalTime += System.nanoTime() - startTime;
			}

			System.out.println("Quick Sort: "
					+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_LOOP_COUNT + " ms");
			totalTime = 0;
			for (int j = 1; j <= INT_LOOP_COUNT; j++) {
				testData = PrepareStringData(INT_DATASIZE, strLen, j);
				startTime = System.nanoTime();
				Arrays.sort(testData);
				totalTime += System.nanoTime() - startTime;
			}

			System.out.println("Dual-quick Sort: "
					+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_LOOP_COUNT + " ms");

			totalTime = 0;
			for (int j = 1; j <= INT_LOOP_COUNT; j++) {
				testData = PrepareStringData(INT_DATASIZE, strLen, j);
				startTime = System.nanoTime();
				RadixSort.radixSortA(testData, strLen);
				totalTime += System.nanoTime() - startTime;
			}

			System.out.println("Radix Sort: "
					+ (double) TimeUnit.NANOSECONDS.toMillis(totalTime) / INT_LOOP_COUNT + " ms");
		}
	}

	private static void Assignment5() {

		long time = 0;

		int size = 100000;
		int testCount = 5;
		int testRepeat = 10;

		for (int i = 1; i <= testCount; i++) {

			int strLeng = i * 2;

			for (int k = 0; k < testRepeat; k++) {

				String[] testData = new String[size];

				for (int j = 0; j < size; j++) {
					testData[j] = StringHelper.getRandomString(strLeng, j);
				}

				long startTime = System.nanoTime();
				RadixSort.radixSortA(testData, strLeng);
				time = System.nanoTime() - startTime;
			}

			System.out.println("RadixSort " + strLeng + " :" + ((double) time / testRepeat / size));
		}
	}

	public static Long[] PrepareLongData(int dataSize, int incseed) {

		Long[] testData = new Long[dataSize];

		Random rd = new Random(SEED + incseed);

		for (int i = 0; i < dataSize; i++) {
			testData[i] = rd.nextLong();

		}

		return testData;

	}

	public static long[] PreparelongData(int dataSize, int incseed) {

		long[] testData = new long[dataSize];

		Random rd = new Random(SEED + incseed);

		for (int i = 0; i < dataSize; i++) {
			testData[i] = rd.nextLong();
		}
		return testData;
	}

	public static String[] PrepareStringData(int dataSize, int strLen, int incseed) {

		String[] testData = new String[dataSize];

		for (int i = 0; i < dataSize; i++) {
			testData[i] = StringHelper.getRandomString(strLen, i);

		}
		return testData;

	}

}
