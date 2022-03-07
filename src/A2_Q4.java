import java.math.BigInteger;
import java.util.Scanner;
import java.util.*;

public class A2_Q4 {


	public static String mod_fibonacci(int N, BigInteger K) {
		ArrayList<BigInteger> fibonacci = new ArrayList<BigInteger>(fib(N-1));


		//compare it to <= fib(n-2) then check n-2
		//fib(N);
		int value = K.intValue();
		//String return_val = fibonacci.get(value);
		return null;
	}

	public static ArrayList<BigInteger> fib(int n) {
		//create fib function
		ArrayList<BigInteger> fibonacci = new ArrayList<BigInteger>(n+2);
		fibonacci.set(0, BigInteger.valueOf(0));
		fibonacci.set(1, BigInteger.valueOf(1));
		for (int i = 2; i <= n; i++) {
			fibonacci.set(i, fibonacci.get(i-1).add(fibonacci.get(i-2)));
		}
		return fibonacci;
	}



	public static void main(String[] args) {

	}

}
