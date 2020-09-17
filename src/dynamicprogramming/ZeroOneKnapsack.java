package dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class ZeroOneKnapsack{
	@Test
	public void knapsack(){
		int[] valArray1 = { 60, 100, 120 }; 
		int[] weightArray1 = { 10, 20, 30 }; 
		int maxWeight1 = 50;
		Assert.assertEquals(220, knapsack(valArray1, weightArray1, maxWeight1));
		
		int[] valArray2 = { 20, 10, 30 }; 
		int[] weightArray2 = { 1, 1, 1 }; 
		int maxWeight2 = 2;
		Assert.assertEquals(50, knapsack(valArray2, weightArray2, maxWeight2));
		
		int[] valArray3 = { 24,	18, 18,	10 }; 
		int[] weightArray3 = { 24, 10, 10, 7 }; 
		int maxWeight3 = 25;
		Assert.assertEquals(36, knapsack(valArray3, weightArray3, maxWeight3));
		
	}
	/**
		Problem:
		This is the famous DP problem of Knapsack. Reference: https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
		
		Solution logic:
		At any recursion stack level, call 2 Recursion functions for following 2 scenarios: 
		1) Include weight and value at current index 2.) Exclude weight and value at current index. 
		In both scenarios keep reducing the Array index by -1. When Array index reaches 0, then Terminate the Recursion function. This is called Base condition without which any Recursion function can throw StackOverflow error.
		Only 1 special use-case need to be additionally taken care of: If current index weight is greater than the Weight left in the Knapsack, then exclude current Recursion level and instead call next recursion stack level by reducting Index by -1.
		
		Asymptotic analysis:
		Time Complexity (without Memoization): O(2^n) since at every Recursion tree level we have 2 branches.
		Auxiliary Space (without Memoization): O(n) since total Recursion tree depth = n
		
		Time Complexity (without Memoization): O(n x W) 
		Auxiliary Space (without Memoization): O(n x W) , since Cache array dimension = n x w
	*/
	public int knapsack(int[] valArray, int[] weightArray, int maxWeight){
		if(valArray==null || valArray.length==0 || weightArray==null || weightArray.length==0 || maxWeight<=0) 	
			return 0;
		int[][]cache=new int[valArray.length][maxWeight];
		return _knapsack(valArray, weightArray, maxWeight, valArray.length-1, cache);
	}
	
	private int _knapsack(int[] valArray, int[] weightArray, int weight, int arrayIndex, int[][]cache){
		if(arrayIndex<0 || weight<=0){
			return 0;
		}
		if(cache[arrayIndex][weight-1]!=0){
			return cache[arrayIndex][weight-1];
		}
		if(weightArray[arrayIndex] > weight){
			// Ignore current Level, if the weight in weightArray Index > current weight
			cache[arrayIndex][weight]=_knapsack(valArray, weightArray, weight, arrayIndex-1, cache);
			return cache[arrayIndex][weight];
		}
		int valIncludeCurrentItem = 
		 _knapsack(valArray, weightArray, weight-weightArray[arrayIndex], arrayIndex-1, cache) + valArray[arrayIndex];
		int valExcludeCurrentItem = _knapsack(valArray, weightArray, weight, arrayIndex-1, cache);
		
		cache[arrayIndex][weight-1]= Math.max(valIncludeCurrentItem, valExcludeCurrentItem);
		return cache[arrayIndex][weight-1];

	}
}