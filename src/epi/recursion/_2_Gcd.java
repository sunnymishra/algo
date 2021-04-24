package recursion;

import org.junit.Test;
import org.junit.Assert;
import java.lang.Math;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
	Problem:
	GCD is Greatest common divisor. In the school math we studied this. Eg. :
		GCD for 12 & 4 = 4 because 4 can divide both 12 and 4 And No number greater than 2 can do that.
		GCD for 10 & 4 = 2 because 2 can divide both 10 and 4. And No number greater than 2 can do that.
		GCD for 7 & 11 = 1 because 1 can divide both 7 and 11. And No number greater than 1 can do that.
		
	Solution:
	Here below I have written 5 variations to solve GCD. 
		gcd1() <- Euclidean RECURSION algorithm. Uses Subtraction a-b to reduce the argument until it becomes 0
		gcd2() <- Euclidean RECURSION algorithm. Uses  Modulus a%b to reduce the argument until it becomes 0
		gcd3() <- Euclidean RECURSION algorithm. Uses  Modulus a%b to reduce the argument until it becomes 0
		gcd4() <- Euclidean ITERATIVE algorithm. Uses  Modulus a%b to reduce the argument until it becomes 0
		
		gcd5() <- Binary RECURSION algorithm.
	
	Asymptotic analysis:
	Reference: https://stackoverflow.com/a/59264770/1316967
	Time complexity= O(log n) where n is the upper limit of a and b
	Space complexity= O(1) in case of Iterative solution
	Space complexity=O(log n) in case of Recursive solution where n is the upper limit of a and b. This is because in recursion tree, each stack of recursion occupies some space in memory. In Iterative approach this is not required.
	
		
*/
public class _2_Gcd{ 

	@Test
	public void testGcd(){
		System.out.println("\nstarting testGcd()");
		
		Assert.assertEquals(6l, gcd(12l, 30l));	// In Java convert int to long by adding 'l' in the end. long var = 6l;
		Assert.assertEquals(6l, gcd(30l, 12l));
		
		Assert.assertEquals(12l, gcd(12l, 60l));
		Assert.assertEquals(5l, gcd(50l, 15l));
		Assert.assertEquals(1l, gcd(21l, 13l));	// 21 & 13 don't have any common GCD so the answer = 1
	}
	
	private long gcd(long a, long b){
		long gcd = gcd5(a, b);
		System.out.println("GCD of "+ a + " & " + b + " = " + gcd);
		return gcd;
	}
	
	/**
	  Euclidean RECURSION algorithm. Uses Subtraction a-b to reduce the argument until it becomes 0
	*/
	private long gcd1(long a, long b){
		long gcd=0;
		if(a==0)
			gcd= b;
		else {
			if(a>b){
				gcd=gcd1(a-b, b);
			}else{
				gcd=gcd1(b-a, a);
			}
		}
		return gcd;
	}
	
	/**
	  Euclidean RECURSION algorithm. Uses Modulus a%b to reduce the argument until it becomes 0
	*/
	private long gcd2(long a, long b){
		long gcd=0;
		if(a==0)
			gcd= b;
		else {
			if(a>b){
				gcd=gcd2(a%b, b);
			}else{
				gcd=gcd2(b%a, a);
			}
		}
		return gcd;
	}
	
	/**
	  Euclidean RECURSION algorithm. Same as gcd2() only a short version
	*/
	private long gcd3(long a, long b){
		return a==0 ? b : gcd3(b % a , a);	
	}
	
	/**
		Euclidean ITERATIVE algorithm. Uses  Modulus a%b to reduce the argument until it becomes 0.
		Since this method doesn't use Recursion, therefore the Space complexity is reduced to O(1) i.e. constant space
		Time complexity will continue to be O(k) where k is the no. of digits in the input min(a, b). or O(log n)
	*/
	private long gcd4(long a, long b){
		while(a!=0l && b!=0l){
			long temp=0l;

			if(a<b){
				temp=a;
				a=b;
				b=temp;
			}
			a=a%b;
		}
		if(a==0){
			return b;
		}else{
			return a;
		}
	}
	
	/**
		Binary RECURSION algorithm. Algorithm created by Josef Stein in 1967. 
		https://en.wikipedia.org/wiki/Binary_GCD_algorithm
		Time complexity same O(log n) but 60% more efficient than Euclidean GCD algorithm.
		Optimization possible: Convert this Algorithm from Recursive to Iterative to get Space complexity of O(1)
	*/
	private long gcd5(long a, long b){
		if(a==b)	return a;
		if(a==0)	return b;
		if(b==0)	return a;
		if(isEven(a) && isEven(b)){
			return gcd5((a>>1) , (b>>1)) << 1;		// Dividing a / 2, b / 2 and multiplying gcd result x 2
		}
		if(isEven(a) && isOdd(b)){
			return gcd5((a>>1) , b);	// Dividing a / 2
		}
		if(isOdd(a) && isEven(b)){
			return gcd5(a , (b>>1));	// Dividing b / 2
		}
		//Here both a & b are Odd
		if (a > b)
			return gcd5((a-b), b);	// Reducing the 1st argument by making it a-b since a>b
		else
			return gcd5((b-a), a);	// Reducing the 1st argument by making it a-b since a>b

	}
	private boolean isEven(long x){
		return (x & 1)==0;
	}
	private boolean isOdd(long x){
		return (x & 1)==1;
	}
	
}










