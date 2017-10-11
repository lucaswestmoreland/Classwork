public class HashTable {
	private int tableSize;
	private int loadFactor;
	public int duplicates = 0;
	public int probes[];
	public int numProbes = 0;
	public int numInserted = 0;
	private HashObject<?>[] hashTable;
	private int probeType = 0;

	HashTable(int tableSizeParam, int probeTypeParam, double loadFactorParam) { 
		if (loadFactorParam < 0)
		{
			System.out.println("Your load factor was under 0, so it has been set to 0.");
			loadFactorParam = 0;
		}

		if (loadFactorParam > 1)
		{
			System.out.println("Your load factor was under 1, so it has been set to 1.");
			loadFactorParam = 1;
		}

		tableSize = tableSizeParam;
		hashTable = new HashObject[tableSize];

		loadFactor = (int) (tableSizeParam * loadFactorParam);
		probeType = probeTypeParam;

		probes = new int[tableSize];
	}

	private int getHash(long keyValue, int n) {
		if (probeType == 0) //if linear
			return ((int) ((keyValue % tableSize) + n) % tableSize);
		else
			return ((int) (((keyValue % tableSize) + n * (1 + (keyValue % (tableSize - 2)))) % tableSize));
	}


	public HashObject<?> getObject(int idx) {
		if (idx < 0)
			return null;
		else
			return hashTable[idx];
	}

	public int search(HashObject<?> object, long n) {
		int i = 0;
		for (i = getHash(n, i); hashTable[i] != null && i <= tableSize; i++) {
			if (hashTable[i].compareTo(object.getHashObject()) == 0 && !hashTable[i].isDeleted())
				return i;
		}
		return -1;
	}

	public double getAverage() {
		return (double) numProbes / (double) numInserted;
	}


	public int insert(HashObject<?> object, long n) {
		while (numInserted <= loadFactor) {
			for (int i = 0; i < tableSize; i++) {
				int idx = getHash(n, i);
				if (hashTable[idx] == null || hashTable[idx].isDeleted()) {
					hashTable[idx] = object;
					probes[idx] = i + 1;
					numProbes += (i + 1);
					numInserted++;
					return idx;
				}         

				else if (hashTable[idx].compareTo(object.getHashObject()) == 0) {
					hashTable[idx].incrementFrequency();
					duplicates++;
					return idx;
				}
			}
		}
		return -1;

	}

	public int delete(HashObject<?> object, long n) {
		int i = search(object, n);
		if (i >= 0)
			hashTable[i].delete();
		return i;
	}

	public String toString(boolean table) {
		StringBuffer string = new StringBuffer("\n");
		for (int i = 0; i < tableSize; i++) {
			if (hashTable[i] != null) {
				string.append("table[" + i + "]: ");
				string.append(hashTable[i].toString());
				if (table)
					string.append(" Probes: " + probes[i]);
				string.append("\n");
			}
		}
		return string.toString();
	}
}
