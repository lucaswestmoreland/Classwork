public class HashObject<T> {

	private T hashObject;
	private int frequency;
	private long key;
	private boolean removed;

	HashObject(T object) {
		hashObject = object;
		frequency = 0;
		removed = false;
		key = hash(object);
	}
	private long hash(T object) {
		long value = 0;
		if (object instanceof Integer | object instanceof Long) //if object is int or long
			value = Math.abs((Long) object);

		if (object instanceof String) //if object is string
			value = Math.abs(object.hashCode());

		return value;
	}

	public int compareTo(Object object) {
		if (object.equals(hashObject))
			return 0;
		else
			return 1;
	}

	public void incrementFrequency() {
		this.frequency++;
	}

	public int delete() {
		this.removed = true;
		return 0;
	}

	public boolean isDeleted() {
		return this.removed;
	}

	public void setHashObject(T object) {
		this.hashObject = object;
	}
	public T getHashObject() {
		return hashObject;
	}

	public Long getKey() {
		return this.key;
	}

	public int getFrequency() {
		return this.frequency;
	}



	@Override
	public String toString() {
		String text = " " + hashObject.toString() + " " + "Frequency:" + frequency;
		return text;
	}

}