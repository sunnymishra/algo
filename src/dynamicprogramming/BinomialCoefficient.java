package dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class BinomialCoefficient{
	@Test
	public void binominalCoefficient(){
		//Assert.assertEquals(4, binominalCoefficient(4,3));
		Assert.assertEquals(10, binominalCoefficient(5,3));
	}
	
	/**
		Using Pascal's triangle, we can calculate the Binomial co-efficient for B(i,j) = B(i-1, j)+B(i,j-1)
		
		If you want to deep-dive into Binomial theorem, Binomial co-efficient, Pascal's Triangle, reference link: https://www.mathsisfun.com/algebra/binomial-theorem.html
		
		Basically nCk = Combination of ways to choose a k-element subset from an n-element larger set. And the Math formula for nCk = n!/ (k! x (n-k)!). Even though this formula is fairly straightForward to calculate and takes only O(1) constant space and O(n) time, the problem is that n! or Factorial of n can grow to a very-very big number due to continuous multiplication. For example: 5!=5x4x3x2x1. The integer size = 2^32 which is fixed. If the question gives n as a very big no. then n! will get a Integer overflow.
		
		There is thankfully a different approach to solve nC2. 
		nCk = n!/ (k! x (n-k)!), but 
		nCk is also equal to = (n-1)C(k) + (n-1)C(k-1). This is definitely a Recurrence relation and a simple Recursive function can solve this. If you refer to above mentioned link, This recurrence relation forms a beautiful Pascal Triangle.
		
		Below I have used the same Recurrence relation and instead written an Iterative algorithm, instead of Recursive algorithm.
		
		Asymptotic analysis:
		Time complexity: O(n x k), since I am using an Array of size nxk. NOTE: There is scope of optimization where we can use a 1D array instead of 2D array, thus reducing Time complexity to O(k)
		Space complexity: O(n x k), since I am using an Array of size nxk.
		
		More coding reference: https://www.geeksforgeeks.org/binomial-coefficient-dp-9/
	**/
	public int binominalCoefficient(int n, int k){
		System.out.println("\n");
		int[][] cache=new int[n+1][k+1];
		
		for(int i=0;i<n+1;i++){
			int column=Math.min(i, k);
			for(int j=0; j<=column ; j++){
				if(j==0 || j==column){
					// This is the Base condition. In the Pascal's triangle, for every row the 0th Index and ith Index is always = 1
					cache[i][j]=1;
				}else{
					// This is the recurrence relation which can be used to write an Iterative or Recusive algo.
					cache[i][j]=cache[i-1][j-1]+cache[i-1][j];
				}
			}
		}
		int result=cache[n][k-1];
		
		// Printing Result below
		for(int i=0;i<n+1;i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		System.out.println("("+n+" C "+k+")="+ result + "\n\n");
		return result;
	}
	
	
	
	
	
}