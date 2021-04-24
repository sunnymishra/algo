package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class _15_CoinChange{
	@Test
	public void coinChange1(){
		Assert.assertEquals(4, coinChange1(12, new int[]{2,3,7}));
		Assert.assertEquals(5, coinChange1(5, new int[]{2,3,1}));
	}
	
	@Test
	public void coinChange2(){
		Assert.assertEquals(4, coinChange2(12, new int[]{2,3,7}));
		Assert.assertEquals(5, coinChange2(5, new int[]{2,3,1}));
		Assert.assertEquals(9, coinChange2(15, new int[]{2,3,10,5}));
	}
	/**
		Problem:
		Coin change problem is one of the 1st problems anybody solves when introduced to the Dynamic programming. It is one of the variance of 0-1 Knapsack pattern of DP problems. In this problem you are given an array of coins of different demoninations eg. Rs 2, Rs 3, Rs 7. Now if you are allowed to use infinite no. of these coins, then using any possible combinations, how many total no. of ways can you make a Total amount of say Rs 12. The answer is 4 ways -> [[2,2,2,2,2,2], [3,3,3,3], [2,3,7], [2,2,2,3,3]]
		Reference: https://www.geeksforgeeks.org/coin-change-dp-7/
		
		Solution logic:
		This is a Top-down solution using Recursive approach. In the code below you will notice I am calling the Recursive function 2 times, 1 when I am including a coin, and 1 when I am excluding the coin. The solution will be sum of those 2 Recursive calls.
		
		Asymptotic analysis:
		Time complexity = O(2^n), which is an exponential solution. Not good because if you draw the recursion tree using Pen and paper you will notice several Nodes are repeated again and again. See next function below where I have optimized this solution further using Memoization (using Cache Array to store intermediate results).
		Space complexity = O(n) where n is the sum total of coins
	*/
	public int coinChange1(int total, int[] coins){
		if(coins==null || coins.length==0 || total<=0){
			return 0;
		}
		Map<Integer, Integer> result=new HashMap<>();
		for(int i=0; i<coins.length; i++){
			result.put(coins[i],0);
		}
		int totCombination = _coinChange1(total, coins, coins.length-1);

		return totCombination;
	}
	
	private int _coinChange1(int total, int[] coins, int currIdx){
		if(total==0) return 1;
		if(total<0) return 0;
		if(currIdx<0) return 0;
		
		int sumInclude=_coinChange1((total-coins[currIdx]), coins, currIdx);
		int sumExclude=_coinChange1((total), coins, currIdx-1);
		
		return sumInclude+sumExclude;
	}
	
	/**
		In this function I am going to add some Dynamic programming optimization by using a Caching array to store the intermediate results. Also previous function was a Recursion solution i.e. a Top-Down approach, whereas this function is going to use Iterative solution of FOR loops therefore a Bottom-Up approach.
		Reference: https://www.youtube.com/watch?v=L27_JpN6Z1Q
		Time complexity: O(mxn), where m=no. of coins, n=Value of coin sum. How? Since we are using 2 FOR loops. The outer FOR loop runs for m times and the inner FOR loop runs for n times, so Time=m x n
		Space complexity: O(mxn), how? Since we are using a Caching array of size m rows and n columns, so Space=m x n
	**/
	public int coinChange2(int total, int[] coins){
		int combinations=0;
		if(coins==null || coins.length==0 || total<=0){
			return combinations;
		}
		Arrays.sort(coins);
		int[][] cache = new int[coins.length+1][total+1];
		
		// Step 1: Filling 1st columns of all the rows as 1 i.e. 0th Index in Array. Since if there are 0 coin i hand, then the no. of ways to reach the Total = 1 i.e. no way :)
		for(int i=0; i<coins.length+1; i++){
			cache[i][0]=1;
		}
		// Step 2: Filling 1st row all columns as 0. This is just for buffer and will help when I am processing the below Rows logics. Without adding this extra 0th row also, I can do the algorithm, but this is convenient for me.
		for(int j=0; j<total+1; j++){
			cache[0][j]=0;
		}
		System.out.println("\n\n"+Arrays.toString(cache[0]));
		
		// Step 3: Actual algorithm logic here. Remember this step has a IF condition and a ELSE condition: 
		// IF CONDITION: if current coin demonination (can be fetched using current row index) is greater than the current total expected (can be fetched using current Column index), then simply ignore current Coin denomination and instead use the previous coin demonination already processed (can be fetched using previous row index already processed). Why? Let's say current row coin denomination =5 (can be fetched using current row index) and the current expected Total=3 (current column index), then there is no way a coin of denomination 5 can be used to give me a total value of 3, so ignore Current coin denomination of 5(i.e. ignore row index 5) and instead consider the value in the Cache array for the previous row (i.e. row=4).
		// ELSE CONDITION: else if current row index (i.e. coin demonination) is smaller than or equal to the current column index (i.e. current expected total), then simply do an addition of previous row's value (previous coin demonination) for this column index  + current row's current column index minus current row's coin demonination, and what ever value you get after the minus, go to that column and fetch the value in the current row.
		for(int i=1; i<coins.length+1; i++){
			for(int j=1; j<total+1; j++){
				int coinDenomination=coins[i-1];
				if(coinDenomination > j){
					cache[i][j]=cache[i-1][j];
				}else{
					int excludeCurrentCoin=cache[i-1][j];
					int includeCurrentCoin=cache[i][j-coinDenomination];
					cache[i][j]=excludeCurrentCoin + includeCurrentCoin;
				}
			}
			System.out.println(Arrays.toString(cache[i]));
		}
		combinations=cache[coins.length][total];
		return combinations;
	}
	
}