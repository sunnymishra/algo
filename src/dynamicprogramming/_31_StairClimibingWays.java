package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class _31_StairClimibingWays {
	@Test
	public void stairClimbWays1(){
		Assert.assertEquals(5, stairClimbWays1(4, 2));
	}
	
	@Test
	public void stairClimbWays2(){
		Assert.assertEquals(5, stairClimbWays2(4, 2));
	}
	
	/**
		Problem:
		Write a program which takes as inputs stairCount(No. of Stairs to climb) and stepCount(If stepCount=2, then you are allowed to take 1 or 2 steps. if stepCount=3, then you are allowed to take 1 or 2 or 3 steps) and returns the number of ways in which you can get to your destination. 
		Example, if stairCount = 4 and stepCount = 2, there are five ways in which to get to the destination: 1way=1,1,1,1 , 2way=1,1,2 , 3way=1,2,1 , 4way=2,1,1 , 5way=2,2
		
		Solution:
		At any given level I can take 1 to stepCount no. of steps, and call the next Recursion stack. But for every value of stepCount, the value of stairCount will reduce to stairCount-stepCount. This means that as we keep going deeper in the Recursion stack, at some point stairCount=0. This means we have reached to the destination i.e. top of the stairs, thus we have found "1" way of reaching to the destination. We will return "1" from that recursion stack level. Now we have to also notice that since at any given Recursion stack level, we will be calling multiple times Recursion function for each value of 1 > i > stepCount, and each of these REcursion calls will return some value to us. So we keep adding them together to get the no. of ways to reach destination. If we draw recursion tree, we can visualize how simple this solution looks, where root node => f(stairCount, stepCount), then 1st level will be f(stairCount-1, stepCount), f(stairCount-2, stepCount), if stepCount=2. And so on until the leaf node = f(1, stepCount) or f(0, stepCount). Since we draw Recursion tree on Pen and paper, we notice there are many nodes repeated, so to optimize we can use a Cache array to store intermediate results.
		
		Asymptotic analysis:
		Time complexity=O(k ^ n), where k=no. of steps allowed, n=no. of stairs. Reason: if we draw Recursion tree on Pen and paper, depth of tree=n, and at each level the breadth=k, so total Nodes=k^n is Time complexity.
		
		Space complexity=o(n), Reason: The max depth of Recursion tree is where Root=f(stairCount, stepCount) and Leaf node=f(0, stepCount). This means max depth=stairCount. Thus Space complexity is stairCount which is stored in Recusion stack memory.
		
		Time complexity with Memoization=O(k ^ n). Reason: Since we are storing intermediate results, several branches of the Recursion tree will be pruned.
		
		Space complexity with Memoization=O(n). Reason: The size of Cache array=n.
	**/
	public int stairClimbWays1(int stairCount, int stepCount){
		int[] cache = new int[stairCount+1];
		int tot = _stairClimbWays1(stairCount, stepCount, cache);
		System.out.println("Cache: "+Arrays.toString(cache));
		return tot;
	}
	public int _stairClimbWays1(int stairCount, int stepCount, int[] cache){
		if(stairCount==1) return 1;
		if(stairCount==0) return 1;
		if(stairCount<=0) return 0;
		if(stepCount<=0) return 0;
		
		if(cache[stairCount]>0) return cache[stairCount];
		
		int tot=0;
		for(int i=1; i<=stepCount; i++){
			tot+= _stairClimbWays1(stairCount-i , stepCount, cache);
		}
		cache[stairCount]=tot;
		return tot;
	}
	
	public int stairClimbWays2(int stairCount, int stepCount){
		int[] cache = new int[stairCount+1];
		cache[0]=1;
		cache[1]=1;
		if(stairCount<=0) return 0;
		if(stepCount<=0) return 0;
		if(stairCount<2) return cache[stairCount];
		
		for(int i=2; i<=stairCount; i++){
			int tot=0;
			for(int j=1; j<=stepCount; j++){
				tot+= cache[i-j];
			}
			cache[i]=tot;
		}
		System.out.println("Cache: "+Arrays.toString(cache));
		return cache[stairCount];
	}
	
}