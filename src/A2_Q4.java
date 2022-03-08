import java.math.BigInteger;
import java.util.Scanner;
import java.util.*;

public class A2_Q4 {
	public static ArrayList<BigInteger> fib_global = new ArrayList<BigInteger>();

	public static String mod_fibonacci(int N, BigInteger K) {
		ArrayList<BigInteger> fibonacci = new ArrayList<BigInteger>();

		fib_global = fib(N);


		int value = K.intValue();
		return rec_fib(N, K);
	}

	public static ArrayList<BigInteger> fib(int n) {
		//create fib function
		ArrayList<BigInteger> fibonacci = new ArrayList<BigInteger>();
		size_list(fibonacci, n+2);
		fibonacci.set(0, BigInteger.valueOf(0));
		fibonacci.set(1, BigInteger.valueOf(1));
		for (int i = 2; i <= n; i++) {
			fibonacci.set(i, fibonacci.get(i-1).add(fibonacci.get(i-2)));
		}
		return fibonacci;
	}
	public static String rec_fib(int n, BigInteger K) {
		if (n == 1) {
			return "X";
		} else if (n == 2) {
			return "Y";
		}
		if (K.compareTo(fib_global.get(n-2)) <= 0) {
			return rec_fib(n-2, K);
		} //x,y,xy,yxy,xyyxy
		else {
			return rec_fib(n - 1, K.subtract(fib_global.get(n-2)));
		}
	}
	public static void size_list(ArrayList<BigInteger> list, int n) {
		for (int i = 0; i <= n ; i++) {
			list.add(BigInteger.valueOf(-1));
		}
	}



	public static void main(String[] args) {

	}

}
