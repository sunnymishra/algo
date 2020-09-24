package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class CoinSelection{
	@Test
	public void coinSelection1(){
		Assert.assertEquals(26, coinSelection1(new int[]{5,25,10,1}));
		Assert.assertEquals(31, coinSelection1(new int[]{10,25,5,1,10,5}));
	}
	
	/**
		Problem:
		Given a line of coins represented by an Array of numbers and 2 Players - player1 and player2, both players are allowed to pick 1 coin at a time from any of the 2 ends of the List i.e from the Left end or from the Right End. Also both players have to take turns in picking 1 coin. That means if player1 have picked his coin then next coin is picked by player2, and vice-versa. If you are Player1 and you always get to pick the 1st coin, then write an algorithm to return the max coin sum that Player1 can gain from this coinSelection process.
		Example1: Input [5,25,10,1]. Here if Player1 picks "1", Player2 picks "10", Player1 picks "25", Player2 picks "5". Hence Player1 total=26 which is max.
		Example2: Input [5,25,10,1]. Here if Player1 becomes greedy then, Player1 picks "5", Player2 picks "25", Player1 picks "10", Player2 picks "1". Hence Player1 total=15 which is not max.
		
		Solution logic:
		Here, 1.) Pick the mininum of the 2 possible moves when Player2 can make more profit, if Player1 chooses left coin.
		2.) Pick the mininum of the 2 possible moves when Player2 can make more profit, if Player1 chooses right coin.
		Then, pick maximum of the above 2 values. That is our solution.
		We will use Memoization by storing the intermediate results in a Cache array, since a lot of steps are repeat again and again.
		
		Asymptotic analysis:
		Time complexity: O(n^2)
		Space complexity: O(n^2) since the size of Cache array is n x n
	*/
	public int coinSelection1(int[] coins){
		if(coins==null || coins.length==0 || (coins.length%2)!=0) return -1;
		
		int[][]cache=new int[coins.length][coins.length];
		int sum= _coinSelection1(coins, 0, coins.length-1, cache);
		
		System.out.println("\nInput: "+Arrays.toString(coins));
		for(int i=0; i<cache.length; i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		return sum;
	}
	
	private int _coinSelection1(int[] coins, int leftIdx, int rightIdx, int[][]cache){
		if(leftIdx>rightIdx){
			return 0;
		}
		if(cache[leftIdx][rightIdx]!=0) return cache[leftIdx][rightIdx];

		int leftSum1= coins[leftIdx] + _coinSelection1(coins, leftIdx+2, rightIdx, cache);
		int leftSum2= coins[leftIdx] + _coinSelection1(coins, leftIdx+1, rightIdx-1, cache);
		
		int rightSum1= coins[rightIdx] + _coinSelection1(coins, leftIdx+1, rightIdx-1, cache);
		int rightSum2= coins[rightIdx] + _coinSelection1(coins, leftIdx, rightIdx-2, cache);
		
		int sum1 = Integer.min(leftSum1, leftSum2);
		int sum2 = Integer.min(rightSum1, rightSum2);
		
		cache[leftIdx][rightIdx]= Integer.max(sum1, sum2);
		return cache[leftIdx][rightIdx];
	}
	

	
	
	
	
	
	
	
}