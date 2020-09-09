package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class MaxSubArraySum{
	@Test
	public void maxSubArraySum1(){
		Assert.assertEquals(18, maxSubArraySum1(new int[]{7,6,5,-3,-2,1,-4}));
		Assert.assertEquals(24, maxSubArraySum1(new int[]{7,6,5,-3,-2,11,-4}));
	}
		@Test
	public void maxSubArraySum2(){
		Assert.assertEquals(18, maxSubArraySum2(new int[]{7,6,5,-3,-2,1,-4}));
		Assert.assertEquals(24, maxSubArraySum2(new int[]{7,6,5,-3,-2,11,-4}));
		
		Assert.assertEquals(6, maxSubArraySum2(new int[]{1,2,-3,2,-4,6,-3,2}));
	}
	
	/**
		Problem: Given an input array of integers contains Duplicates and Negative numbers as well, find a sub-array whose Sum of elements is the max among all the Sub-arrays possible.
		
		Solution logic:
		Prepare the cache array by visiting all the input array elements and let the cache contain this: cache[i] = Array[0] ... Array[i] i.e. Sum of all elements from 0th index to ith Index.
		
		Later simply Loop through original array, pick a subArray range i and j and search this range i and j in the cache array with following formula -> sum of Range i - j = cache[j+1]-cache[i]
		
		Asymptotic analysis:
		Time complexity= O(n^2), since O(n) time to prepare the Cache array and n^2 time due to the 2 FOR loops to get all possible Sub Array ranges. Then lookup in the Cache array is in O(1) constant time. So total Time=T(n + n^2 ) , therefore O(n^2)
		
		Space complexity=O(n) since we are storing intermediate Sums in Cache array of size n
	*/
	public int maxSubArraySum1(int[] arr){
		if(arr==null || arr.length==0){
			return 0;
		}
		int[] cache=new int[arr.length + 1];
		
		// Initialize cache which is a sumArray i.e. every element i is a sum of original array's 0 to ith index
		cache[0]=0;
		for(int i=0; i<arr.length; i++){
			cache[i+1]=cache[i]+arr[i];		// Memoization step: Store intermediate result in a cache
		}
		
		// Visit all possible subArrays using 2 nested FOR loops
		// In each subArray find the sum of all its elements by using the cache.
		int maxSum=0;
		for(int i=0; i<arr.length-1; i++){
			for(int j=i; j<arr.length; j++){
				maxSum = Math.max(maxSum, cache[j+1]-cache[i]);
			}
		}
		return maxSum; 
	}
	
	/**
		This is an optimization of the above solution in maxSubArraySum1() 
		
		Asymptotic analysis:
		Time complexity= O(n)
		Space complexity=O(1), here we are not creating a separate Cache array of size n. Instead just using 3 fixed variables to get the result.
	*/
	public int maxSubArraySum2(int[] arr){
		int minSum = 0, sum = 0, maxSum = 0;
		for (int i = 0; i < arr.length; ++i) {
			sum += arr[i];
			if (sum < minSum) {
				minSum = sum;
			}
			if (sum - minSum > maxSum) {
				maxSum = sum - minSum;
			}
		}
		return maxSum;
	}


}