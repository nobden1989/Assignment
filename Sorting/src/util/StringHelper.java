package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringHelper {

	public static final short HEX_LOWER = 0x1;
	public static final short HEX_CAPITAL = 0x10;
	public static final short HEX_NUMBER = 0x100;

	private static final char CHA_LOWER = 'a';
	private static final char CHA_CAPITAL = 'A';
	private static final char CHA_NUMBER = '0';
	private static final char CHA_NON = '\n';

	private static int SEED = 233;

	public static String getRandomString(int strLen) {
		Random rd = new Random();
		SEED = rd.nextInt();
		return getRandomString(strLen, HEX_LOWER + HEX_NUMBER + HEX_CAPITAL, rd.nextInt());
	}

	public static String getRandomString(int strLen, int incseed) {
		return getRandomString(strLen, HEX_LOWER + HEX_NUMBER + HEX_CAPITAL, incseed);
	}

	public static String getRandomString(int strLen, int strType, int incseed) {
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < strLen; i++) {
			strBuilder.append(getChar(strType, incseed + i));
		}
		return strBuilder.toString();
	}

	public static char getChar(int type, int incseed) {

		Random rd = new Random(SEED + incseed);
		List<Integer> charTypes = new ArrayList<Integer>();

		if ((type & HEX_LOWER) != 0)
			charTypes.add(Integer.valueOf(HEX_LOWER));
		if ((type & HEX_CAPITAL) != 0)
			charTypes.add(Integer.valueOf(HEX_CAPITAL));
		if ((type & HEX_NUMBER) != 0)
			charTypes.add(Integer.valueOf(HEX_NUMBER));

		int target = charTypes.get(rd.nextInt(charTypes.size()));

		switch (target) {

		case HEX_LOWER:
			return (char) (CHA_LOWER + rd.nextInt(26));
		case HEX_CAPITAL:
			return (char) (CHA_CAPITAL + rd.nextInt(26));
		case HEX_NUMBER:
			return (char) (CHA_NUMBER + rd.nextInt(10));
		default:
			return CHA_NON;
		}

	}

	public static void main(String[] args) {
		System.out.println(getRandomString(20));
	}
}
