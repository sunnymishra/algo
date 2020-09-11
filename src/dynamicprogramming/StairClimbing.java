package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class StairClimbing{
	@Test
	public void stairClimbing1(){
		Assert.assertEquals(24, stairClimbing1(6, 3));
		
		Assert.assertEquals(5, stairClimbing1(4, 2));
	}
	
	/**
		Problem:
		You are climbing stairs. You can advance 1 to k steps at a time. Your
		destination is exactly n steps up. Write a program which takes as inputs n and k and
		returns the number of ways in which you can get to your destination.
		
		Solution logic:
		This is again a variation of Zero One knapsack problem, very similar to Coin change problem.
		Below code is a Top-down Recursion approach. I have added the Memoization(to store intermediate steps) in a Cache array to add optimization in the Space and time complexity.
		
		Time complexity = O(n x k), where n is the totalSteps and k is the steps allowed
		Space complexity = O(n),  where n is the totalSteps. How? If you draw the recursion tree with paper and pen, the max depth of the tree is at max the totalSteps in the worst case when reduced by a factor of '1' at each level i.e. if at each level '1' step is taken. Also, the Caching in Array is also equal to size n. Thus Space complexity=O(n)
		
		NOTE: If in below code I remove the Memoization step i.e. the storing of intermediate results in the Cache array, then the Time complexity will significantly increase from current O(k x n) to O(k ^ n)
	*/
	public int stairClimbing1(int totalSteps, int steps){
		System.out.println("\n");
		if(steps<=0 || totalSteps<=0){
			return 0;
		}
		int[] cache=new int[totalSteps];
		
		int totCombination = _stairClimbing1(steps, totalSteps, cache);
		
		System.out.println(Arrays.toString(cache)+"\n\nResult: "+ totCombination);
		
		return totCombination;
	}
	
	private int _stairClimbing1(int allowedSteps, int totalSteps, int[] cache){
		if(totalSteps==0) return 1;
		if(totalSteps<0) return 0;

		if(cache[totalSteps-1]!=0){
			return cache[totalSteps-1];
		}

		int ways=0;
		for(int i=1; i<=allowedSteps; i++){
			ways+=_stairClimbing1(allowedSteps, (totalSteps-i), cache);
		}
		cache[totalSteps-1]=ways;
		return ways;
	}
	
}