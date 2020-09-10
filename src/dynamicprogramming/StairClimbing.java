package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class StairClimbing{
	@Test
	public void stairClimbing1(){
		Assert.assertEquals(7, stairClimbing1(6, 3));
		Assert.assertEquals(5, stairClimbing1(5, 3));
		Assert.assertEquals(5, stairClimbing1(4, 2));
	}
	
	/**
		Problem:
		You are climbing stairs. You can advance 1 to k steps at a time. Your
		destination is exactly n steps up. Write a program which takes as inputs n and k and
		returns the number of ways in which you can get to your destination.
		
		Solution logic:
		This is again a variation of Zero One knapsack problem, very similar to Coin change problem.
		Below code is a Top-down Recursion approach. Next I will also add the Bottom-Up iterative approach with 
		Memoization(to store intermediate steps) to add optimization in the Space and time complexity.
		
		Time complexity = O(2^n), which is an exponential solution.
		Space complexity = O(n)
	*/
	public int stairClimbing1(int totalSteps, int steps){
		if(steps<=0 || totalSteps<=0){
			return 0;
		}
		int totCombination = _stairClimbing1(totalSteps, steps);

		return totCombination;
	}
	
	private int _stairClimbing1(int totalSteps, int stepsIdx){
		if(totalSteps==0) return 1;
		if(totalSteps<0) return 0;
		if(stepsIdx<1) return 0;
		
		int sumInclude=_stairClimbing1((totalSteps-stepsIdx), stepsIdx);
		int sumExclude=_stairClimbing1((totalSteps), stepsIdx-1);
		
		return sumInclude+sumExclude;
	}
	
}