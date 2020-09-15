package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class TraverseArray{
	@Test
	public void traverseArray1(){
		System.out.println("\n");
		Assert.assertEquals(10, traverseArray1(new int[3][4]));
		Assert.assertEquals(2, traverseArray1(new int[2][2]));
		Assert.assertEquals(3, traverseArray1(new int[3][2]));
	}
	
	@Test
	public void traverseArray2(){
		System.out.println("\n");
		Assert.assertEquals(10, traverseArray2(new int[3][4]));
		Assert.assertEquals(2, traverseArray2(new int[2][2]));
		Assert.assertEquals(3, traverseArray2(new int[3][2]));
	}
	
	@Test
	public void traverseArray3(){
		System.out.println("\n");
		Assert.assertEquals(10, traverseArray3(new int[3][4]));
		Assert.assertEquals(2, traverseArray3(new int[2][2]));
		Assert.assertEquals(3, traverseArray3(new int[3][2]));
	}
	
	/**
		Problem:
		Given a 2D array of i x j dimention, identify all possible ways in which you can traverse from top-left cell to the bottom-right cell. The only moves you are allowed are Going down or Going right.
		
		Solution approach:
		At any cell I will call 2 Recursive function: 1 will go down (reduce the row count), 1 will go right (reduce the column count). Then add the result of both those recursive functions and return the result.
		All recursive function must have a BASE condition (some IF condition) in the start, else the program will throw a Stackoverflow error. Here the BASE condtion = When either row==0 or column==0. In that case we will return =1, because if we reach row=0 or column=0, then there is only 1 way to reach the bottom-right cell. In a Recursion tree Base condition is reached when we arrive at a leaf node, where we can't go any further down the Tree. Draw a Recursion tree on Pen and paper to visualize this.
		
		Asymptotic analysis:
		Time complexity without Memoization(Storing intermediate result in a cache array): O(2^ (m+n)), where m,n are the dimensions of the 2D Input Array . Reason: In the recursion tree (draw using Pen and Paper to visualize this), at any level there are 2 child Nodes created at max. The depth of the Tree is max=m+n, although using Base condition we are bounding it by pruning the branch any further as soon as either m or n =0;
		Time complexity with Memoization: O(m x n)
		
		Space complexity without Memoization: O(m+n)
		Space complexity with Memoization: O(m x n), Reason: we are storing intermediate results in an extra cache array of dimension m,n
	*/
	public int traverseArray1(int[][]arr){
		if(arr==null || arr.length==0){
			return 0;
		}
		int[][]cache=new int[arr.length][arr[0].length];
		int ways = _traverseArray1(arr, arr.length-1, arr[0].length-1, cache);
		
		for(int i=0; i<cache.length;i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		System.out.println("Recursive Result: "+ways+"\n\n");
		return ways;
	}
	
	private int _traverseArray1(int[][] arr, int i, int j, int[][]cache){
		if(i==0 || j==0){
			return 1;
		}

		if(cache[i][j]!=0) return cache[i][j];
		int ways= _traverseArray1(arr, i-1, j, cache) + 
					_traverseArray1(arr, i, j-1, cache);
		cache[i][j]=ways;
		return ways;
	}
	
	/**
		This is the Iterative approach or Bottom-up approach of solving the same problem as mentioned above in traverseArray1()
		
		Solution approach:
		We take a 2D cache array and keep moving to each cell in the Array using 2 Nested FOR loops. Here is the Recurrence relation -> 
		At any cell i,j the value= SUM (Previous cell reducing row (i-1,j) + Previous cell reducing column (i,j-1))
		This recurrence relation is ditto the same as had used in the previous Recursive approach solution in the function _traverseArray1() above.
		
		Asymptotic analysis:
		Time complexity with Memoization: O(m x n) Reason: We are using 2 Nested FOR loops of size m and n
		Space complexity with Memoization: O(m x n), Reason: we are storing intermediate results in an extra cache array of dimension m,n
	*/
	public int traverseArray2(int[][]arr){
		if(arr==null || arr.length==0){
			return 0;
		}
		
		int ways = _traverseArray2(arr);
		
		System.out.println("Iterative Result: "+ways+"\n\n");
		return ways;
	}
	
	private int _traverseArray2(int[][] arr){
		int rows=arr.length;
		int columns=arr[0].length;
		
		int[][]cache=new int[rows][columns];
		// Filling all 0'th row cells = 1. This is to replicate the 1st IF condition in the Recursive function _traverseArray1()
		Arrays.fill(cache[0], 1); 
		for(int i=0;i<rows; i++){
			// Filling all 0'th column cells = 1. This is to replicate the 1st IF condition in the Recursive function _traverseArray1()
			cache[i][0]=1;
		}
		
		for(int i=1;i<rows; i++){
			for(int j=1;j<columns; j++){
				cache[i][j]=cache[i-1][j] + cache[i][j-1];
			}
		}
		int result = cache[rows-1][columns-1];
		
		for(int i=0; i<cache.length;i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		return result;
	}
	
	/**
		This is the 3rd approach of solving the same problem. People doing Competitive programming try to find shortcuts to get the result. Given n rows and m columns, at max there are (n C m) combinations possible. So in 1 single computation we can get the result of (n C m) without using any complex Recursion or Iteration algorithms as mentioned in above 2 Functions traverseArray1() and traverseArray2()
		
		Asymptotic analysis:
		Time complexity with Memoization: O(1)
		Space complexity with Memoization: O(1)
	**/
	public int traverseArray3(int[][]arr){
		if(arr==null || arr.length==0){
			return 0;
		}
		
		// Why "-1" below, since 0th row(Iterative solution above) or final row (Recursive solution above) won't be considered as its value will always be 1.
		int rows=arr.length-1;
		// Why "-1" below, since 0th column(Iterative solution above) or final column (Recursive solution above) won't be considered as its value will always be 1.
		int columns=arr[0].length-1;
		
		//Given rows and columns, at max there are (rows C columns) possible using Combinatorics Math.
		// i.e. No of combination = (rows + columns)! / (rows! x columns!)
		int ways = factorial(rows + columns) / (factorial(rows) * factorial(columns));
		
		System.out.println("Factorial Result: "+ways+"\n\n");
		return ways;
	}
	
	private int factorial(int num){
		int fact=1;
		while(num>0) fact*=num--;
		return fact;
	}
	
}