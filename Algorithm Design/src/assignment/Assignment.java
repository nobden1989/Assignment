package assignment;

import java.util.ArrayList;
import java.util.List;

import algorithmDesign.Sequences;
import util.StringHelper;

public class Assignment {

	private static final int[] TEST_LENGTH = { 10, 20, 50, 100 };

	private static final double STR_LENGTH = 1000;

	public static void main(String[] args) {

		for (int i : TEST_LENGTH) {
			printResult(i, RunTest1(i));
		}

	}

	private static String RunTest1(int strLen) {
		List<StringPair> data = new ArrayList<StringPair>();
		int distance = 0;
		for (int i = 0; i < STR_LENGTH; i++) {
			data.add(new StringPair(StringHelper.getRandomString(strLen),
					StringHelper.getRandomString(strLen)));
		}
		long startTime;
		long totalTIme = 0;
		for (StringPair sp : data) {
			startTime = System.nanoTime();
			distance = Math.max(distance, Sequences.editDistance(sp.getFirst(), sp.getSecond()));
			totalTIme += System.nanoTime() - startTime;

		}
		return String.valueOf(" Max Edit Distance: " + distance + " Average Time: "
				+ totalTIme / STR_LENGTH + "ns");
	}

	private static void printResult(int dataLen, String data) {
		System.out.print("Data Length: " + dataLen);
		System.out.println(data);
	}

}
