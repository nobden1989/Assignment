package Data;

public class Pair<Key, Value extends Comparable<Value>> implements Comparable<Pair<Key, Value>> {

	public static final int CMP_MIN = 0;
	public static final int CMP_MAX = 1;
	public static final int CMP_KEY = 2;

	private Key key;
	private int orderType = CMP_MIN;
	private Value value;

	public Key getKey() {
		return this.key;
	}

	public void setKey(Key k) {
		this.key = k;
	}

	public Value getValue() {
		return this.value;
	}

	public void setValue(Value v) {
		this.value = v;
	}

	public Pair(Key k, Value v, int orderType) {
		this.key = k;
		this.value = v;
		this.orderType = orderType;
	}

	public Pair(Key k, Value v) {
		this.key = k;
		this.value = v;
	}

	@Override
	public int compareTo(Pair<Key, Value> o) {
		if (orderType == CMP_MIN) {
			return value.compareTo(o.getValue());
		} else if (orderType == CMP_MAX) {
			return o.getValue().compareTo(value);
		} else if (orderType == CMP_KEY) {
			if (o.getKey().equals(key)) {
				return 0;
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return String.valueOf(this.key) + ":" + String.valueOf(this.value);

	}

}