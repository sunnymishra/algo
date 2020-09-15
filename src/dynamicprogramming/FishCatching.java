package dynamicprogramming;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

public class FishCatching{
	@Test
	public void fishCatching1(){
		System.out.println("\n");
		int[][] arr1 = {{1,1,3},{2,0,2},{3,0,2}};
		Assert.assertEquals(9, fishCatching1(arr1));
		
		int[][] arr2 = {{2,1,3},{0,4,4},{3,0,1}};
		Assert.assertEquals(12, fishCatching1(arr2));
	}
	@Test
	public void fishCatching2(){
		System.out.println("\n");
		int[][] arr1 = {{1,1,3},{2,0,2},{3,0,2}};
		Assert.assertEquals(9, fishCatching2(arr1));
		
		int[][] arr2 = {{2,1,3},{0,4,4},{3,0,1}};
		Assert.assertEquals(12, fishCatching2(arr2));
	}
	@Test
	public void fishCatching3(){
		System.out.println("\n");
		int[][] arr1 = {{1,1,3},{2,0,2},{3,0,2}};
		Assert.assertEquals(9, fishCatching3(arr1));
		
		int[][] arr2 = {{2,1,3},{0,4,4},{3,0,1}};
		Assert.assertEquals(12, fishCatching3(arr2));
	}
	
	/**
		Problem:
		A fisherman is in a rectangular sea which can be charted using a 2D array of Dimension m x n. The fisherman should begin at the top left of a 2D array and end at bottom right. Each array cell (i,j) has a value for the Fish. Fisherman is allowed to move in either right or bottom direction. Find the max fish value that the fisherman can catch with this above mentioned constraints, once he reaches bottom right.
		
		Asymptotic analysis:
		Time complexity without Memoization(Storing intermediate result in a cache array): O(2^ (m+n)), where m,n are the dimensions of the 2D Input Array . Reason: In the recursion tree (draw using Pen and Paper to visualize this), at any level there are 2 child Nodes created at max. The depth of the Tree is max=m+n, although using Base condition we are bounding it by pruning the branch any further as soon as either m or n =0;
		Time complexity with Memoization: O(m x n)
		
		Space complexity without Memoization: O(m+n)
		Space complexity with Memoization: O(m x n), Reason: we are storing intermediate results in an extra cache array of dimension m,n
		
		NOTE: For iterative solution refer to below function fishCatching2()
	*/
	public int fishCatching1(int[][]arr){
		if(arr==null || arr.length==0){
			return 0;
		}
		System.out.println("Recursive approach:\nInput Array:");
		for(int i=0; i<arr.length; i++){
			System.out.println(Arrays.toString(arr[i]));
		}
		int[][]cache=new int[arr.length][arr[0].length];
		int maxFishValue = _fishCatching1(arr, arr.length-1, arr[0].length-1, cache);
		
		System.out.println("Output Cache:");
		for(int i=0; i<cache.length; i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		System.out.println("Recursive Result: "+maxFishValue+"\n\n");
		return maxFishValue;
	}
	
	private int _fishCatching1(int[][] arr, int i, int j, int[][]cache){
		if(cache[i][j]!=0) return cache[i][j];
		int leftFishValue=0;
		int downFishValue=0;
		if(i>0){
			leftFishValue=_fishCatching1(arr, i-1, j, cache);
		}
		if(j>0){
			downFishValue=_fishCatching1(arr, i, j-1, cache);
		}
		int maxFishValue= arr[i][j] + Math.max(leftFishValue , downFishValue);
		cache[i][j]=maxFishValue;
		return maxFishValue;
	}
	
	/**
		Problem:
		Given a 2D array, a fisherman should begin at the top left of a 2D array and end at bottom right. Each cell has a value for the Fish. Fisherman is allowed to move in either right or bottom direction. Find the max fish value that the fisherman can catch with this above mentioned constraints.
		
		Asymptotic analysis:
		Time complexity with Memoization: O(m x n) Reason: We are using 2 Nested FOR loops of size m and n
		Space complexity with Memoization: O(m x n), Reason: we are storing intermediate results in an extra cache array of dimension m,n
		
		NOTE: For recursive solution refer to above function fishCatching1()
	*/
	public int fishCatching2(int[][] arr){
		if(arr==null || arr.length==0){
			return 0;
		}
		int[][]cache=new int[arr.length][arr[0].length];
		
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[i].length; j++){
				int leftFishValue=0;
				int downFishValue=0;
				if(i>0){
					leftFishValue=cache[i-1][j];
				}
				if(j>0){
					downFishValue=cache[i][j-1];
				}
				cache[i][j]=arr[i][j] + Math.max(leftFishValue, downFishValue);
			}
		}
		int maxFishValue=cache[cache.length-1][cache[cache.length-1].length-1];
		
		System.out.println("Iterative approach:\nInput Array: ");
		for(int k=0; k<arr.length; k++){
			System.out.println(Arrays.toString(arr[k]));
		}
		System.out.println("Output Cache: ");
		for(int k=0; k<cache.length; k++){
			System.out.println(Arrays.toString(cache[k]));
		}
		System.out.println("Iterative Result: "+ maxFishValue+"\n");
		return maxFishValue;
	}
	
	/**
		This approach is an optimization of the Iterative approach in the above function fishCatching2()
		In the above function the Space complexity=O(m x n), but in this below function fishCatching3() the Space complexity=O(m), because the Cache Array Dimension = (m x 2). Here 2 is a constant therefore not considered in Space complexity.
		Time complexity= O(m x n) since the 2 nested FOR loops m x n times.
	*/
	public int fishCatching3(int[][] arr){
		if(arr==null || arr.length==0){
			return 0;
		}
		int[][]cache=new int[2][arr[0].length];
		
		for(int i=0; i<arr.length; i++){
			for(int j=0; j<arr[i].length; j++){
				
				int leftFishValue=0;
				int downFishValue=0;
				if(i==0 && j>0){
					leftFishValue=cache[0][j-1];
				}
				if(i>0){
					leftFishValue=cache[0][j];
				}
				if(j>0){
					downFishValue=cache[1][j-1];
				}
				int currentVal=arr[i][j] + Math.max(leftFishValue, downFishValue);
				if(i>0){
					cache[1][j]=currentVal;
				}
				cache[0][j]=currentVal;
			}
		}
		int maxFishValue=cache[1][cache[0].length-1];
		
		System.out.println("Iterative Space efficient approach:\nInput Array: ");
		for(int k=0; k<arr.length; k++){
			System.out.println(Arrays.toString(arr[k]));
		}
		System.out.println("Output Cache: ");
		for(int k=0; k<cache.length; k++){
			System.out.println(Arrays.toString(cache[k]));
		}
		System.out.println("Iterative Space efficient Result: "+ maxFishValue+"\n");
		return maxFishValue;
	}
}