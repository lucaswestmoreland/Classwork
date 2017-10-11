public class Prime {

	public static boolean isPrime(long n) {
		if (n < 2 || n % 2 == 0|| n % 3 == 0)
			return false;
		if (n == 2 || n == 3)
			return true;

		long sqrt = (long) (Math.sqrt(n) + 1);
		for (long i = 6; i <= sqrt; i += 6)
		{
			if (n % (i - 1) == 0 || n % (i + 1) == 0) {
				return false;
			}
		}

		return true;
	}
	public static long findPrime(long x, long y) {

		for (; x <= y; x++)
			if (isPrime(x) && isPrime(x - 2))
				return x;
		return -1;
	}
}
