package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

public class _30_CoinSelectionGame{
	@Test
	public void coinSelection1(){
		Assert.assertEquals(26, coinSelection1(new int[]{5,25,10,1}));
		Assert.assertEquals(31, coinSelection1(new int[]{10,25,5,1,10,5}));
	}
	
	@Test
	public void coinSelection2(){
		Assert.assertEquals(26, coinSelection2(new int[]{5,25,10,1}));
		Assert.assertEquals(31, coinSelection2(new int[]{10,25,5,1,10,5}));
	}


	
	/**
		Problem:
		Given a line of coins represented by an Array of numbers and 2 Players - player1 and player2, both players are allowed to pick 1 coin at a time from any of the 2 ends of the List i.e from the Left end or from the Right End. Also both players have to take turns in picking 1 coin. That means if player1 have picked his coin then next coin is picked by player2, and vice-versa. If you are Player1 and you always get to pick the 1st coin, then write an algorithm to return the max coin sum that Player1 can gain from this coinSelection process.
		Example1: Input [5,25,10,1]. Here if Player1 picks "1", Player2 picks "10", Player1 picks "25", Player2 picks "5". Hence Player1 total=26 which is max.
		Example2: Input [5,25,10,1]. Here if Player1 becomes greedy then, Player1 picks "5", Player2 picks "25", Player1 picks "10", Player2 picks "1". Hence Player1 total=15 which is not max.
		
		Reference: https://www.youtube.com/watch?v=ww4V7vRIzSk
		Reference: https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
		
		Solution logic:
		Step 1. Let Player1 chooses left coin. Now Player2 is left with coins Array (i+1, j). Now Player2 has 2 options, he can either pick left coin, in which case Player1 will be left with coins Array (i+2, j), or Player2 can pick right coin, in which case Player1 will be left with coins Array (i+1, j-1). Now among these 2 possibilities left with Player1 i.e. coins Array (i+2, j) & coins Array (i+1, j-1), Player1 will go into 2 recursion stacks, but we can be sure that  Player1 will get the MIN() of the output value of these 2 Recursion stack results. Reason: Player2 will ensure that you will make minimum profit after his choice is made.
		
		Step 2. Repeat the same step as above Step #1 but this time Player1 chooses right coin. Now Player2 is left with coins Array (i+1, j), etc....
		
		Step 3. Whatever is the output value of Step1 and Step2, take the max of those 2 values and add it to the current coin picked, and return that final value to the previous Recursion stack as result. Remember important point: "When it is my turn and I have the power of choice I will always use MAX() among the possible values, but when my opponent has the power of choice, then I will get the MIN() among the possible outcomes. This is because both players are playing greedy."
		
		We will use Memoization by storing the intermediate results in a Cache array, since a lot of steps are repeated in each Recursion stack level. NOTE: A detailed explanation is given in the below Recursion function in the comments section, at each line of code.
		
		Asymptotic analysis:
		Time complexity: O(n^2)
		Space complexity: O(n^2) since the size of Cache array is n x n
	*/
	public int coinSelection1(int[] coins){
		if(coins==null || coins.length==0 || (coins.length%2)!=0) return -1;
		
		int[][]cache=new int[coins.length][coins.length];
		int sum= _coinSelection1(coins, 0, coins.length-1, cache);
		
		System.out.println("\nInput: "+Arrays.toString(coins));
		System.out.println("\nOutput: ");
		for(int i=0; i<cache.length; i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		return sum;
	}
	
	/**
		The algorithm has been explained in the below code comments section.
	*/
	private int _coinSelection1(int[] coins, int leftIdx, int rightIdx, int[][]cache){
		if(leftIdx>rightIdx){
			return 0;
		}
		if(cache[leftIdx][rightIdx]!=0) return cache[leftIdx][rightIdx];
		
		// If I pick the left coin, and opponent pick left again, then I will get remaining Array size=(leftIdx+2, rightIdx)
		int leftSum1= coins[leftIdx] + _coinSelection1(coins, leftIdx+2, rightIdx, cache);
		// If I pick the left coin, and opponent pick right, then I will get remaining Array size=(leftIdx+1, rightIdx-1)
		int leftSum2= coins[leftIdx] + _coinSelection1(coins, leftIdx+1, rightIdx-1, cache);
		
		// If I pick the right coin, and opponent pick left coin, then I will get remaining Array size=(leftIdx+1, rightIdx-1)
		int rightSum1= coins[rightIdx] + _coinSelection1(coins, leftIdx+1, rightIdx-1, cache);
		// If I pick the right coin, and opponent pick right coin again, then I will get remaining Array size=(leftIdx, rightIdx-2)
		int rightSum2= coins[rightIdx] + _coinSelection1(coins, leftIdx, rightIdx-2, cache);
		
		// Why min() here? Since I have played my chance in the current Recursion Stack, so the Opponent will ensure that I will get Minimum of the available 2 options in the next Recursion  Stack i.e. one where he picked left coin and one where he picked right coin
		int sum1 = Integer.min(leftSum1, leftSum2);
		
		// Why min() here? Since I have played my chance in the current Recursion Stack, so the Opponent will ensure that I will get Minimum of the available 2 options in the next Recursion  Stack i.e. one where he picked left coin and one where he picked right coin
		int sum2 = Integer.min(rightSum1, rightSum2);
		
		// Why max() here? Since I am in the current stack, I had option to choose either left coin or right coin, so I want to maximize my returns, so I will pick the max among those 2 values. REMEMBER: When I have the power of choice I will always use MAX() among the possible values, but when my opponent has the power of choice, then I will get the MIN() among the possible outcomes. This is because both players are playing greedy.
		cache[leftIdx][rightIdx]= Integer.max(sum1, sum2);
		return cache[leftIdx][rightIdx];
	}
	

	private int coinSelection2(int[] coins){
		if(coins==null || coins.length==0 || (coins.length%2)!=0) return -1;
		
		int[][]cache=new int[coins.length][coins.length];
				
		for(int leftIdx=0; leftIdx<coins.length; leftIdx++){
			for(int rightIdx=0; rightIdx<coins.length; rightIdx++){
				int leftSum1=0;
				int leftSum2=0;
				int rightSum1=0;
				int rightSum2=0;
				
				if(leftIdx<coins.length-2){
					leftSum1=cache[leftIdx+2][rightIdx];
				}
				if(leftIdx<coins.length-1 && rightIdx>0){
					leftSum2=cache[leftIdx+1][rightIdx-1];
				}
				if(leftIdx<coins.length-1 && rightIdx>0){
					rightSum1=cache[leftIdx+1][rightIdx-1];
				}
				if(rightIdx>1){
					rightSum2=cache[leftIdx][rightIdx-2];
				}
				if(leftIdx==0 && rightIdx==1){
					System.out.println("leftSum1="+leftSum1+" leftSum2="+leftSum2+" rightSum1="+rightSum1+" rightSum2="+rightSum2);
				}
				
				int sum1 = Integer.min(coins[leftIdx]+leftSum1, coins[leftIdx]+leftSum2);
				
				int sum2 = Integer.min(coins[rightIdx]+rightSum1, coins[rightIdx]+rightSum2);
				
				cache[leftIdx][rightIdx]= Integer.max(sum1, sum2);
			}
		}
		
		System.out.println("\nInput: "+Arrays.toString(coins));
		System.out.println("\nOutput: ");
		for(int i=0; i<cache.length; i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		return cache[0][coins.length-1];
	}
	
	
	
	
	
}