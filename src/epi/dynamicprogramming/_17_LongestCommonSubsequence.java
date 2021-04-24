package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;


public class _17_LongestCommonSubsequence{
	@Test
	public void lcs(){
		Assert.assertEquals(4, lcs1("abcdeh", "abedfh"));
		Assert.assertEquals(3, lcs1("ABCDGH", "AEDFHR"));
		
	}
	/**
		Problem: Given 2 String, find the longest common subsequence length. Example: 
		String1="ABCDGH" String2="AEDFHR", then LCS = 3
		String1="AGGTAB" String2="GXTXAYB", then LCS = 4
		
		Solution logic:
		When we go via the Top-down approach, i.e. in a Recursion tree when we pick the last index of both String and keep moving towards the 0th index, then at every Recursion stack level we have a choice to make like below:
		IF String1 current index = String2 current index:
			Call the next Recursion level by reducing the index of both string, to check the next indexes
			Whatver value comes back from this above call, Add +1 to it, since in current level 1 character matched.
		ELSE:
			Call the next Recursion level by Reducing only the Index of String1
			Call the next Recursion level by Reducing only the Index of String2
			Take the max returned by above 2 calls, and return it to the previous Recursion stack level.
	
		Asymptotic analysis:
			Time complexity: O(2^ (m+n)) without Memoization i.e. If we don't use the Cache Array to store the intermediate results. Why 2^(m+n)? If you draw the Recursion tree with Pen and Paper, for a worst case scenario in which none of the characters of 2 Strings match eg. str1="ABC", str2="XYZ". In this case the Depth of the Tree will be m+n, where m=str1 length and n=str2 length. Now that we have understood that the Depth of the Tree=m+n, you will also notice that at any level in the Recursion Tree, we have 2 choices to make. If you do the Math it comes to 2^(m+n), therefore Time complexity = O(2 ^ (m+n))
			
			Time complexity: O(m x n) with Memoization
			
			Space complexity: O(m + n) without Memoization, due to the level/depth of Recursion tree is max (m+n).
			Space complexity: O(m x n) with Memoization, since the Array size is [m rows x n columns]
			
		Note: There is a scope to reduce the Space complexity from current O(m x n) to simple O(n), by changing the 2 Dimensional Cache Array to a Single Dimensional array.
	**/
	public int lcs1(String str1, String str2){
		if(str1==null || str2==null || str1.isEmpty() || str2.isEmpty()){
			return 0;
		}
		int[][] cache=new int[str1.length()][str2.length()];
		int sum= _lcs1(str1, str2, str1.length(), str2.length(), cache);
		
		System.out.println("\n\n");
		for(int i=0; i<str1.length(); i++){
			System.out.println(Arrays.toString(cache[i]));
		}
		return sum;
	}
	private int _lcs1(String str1, String str2, int str1Idx, int str2Idx, int[][] cache){
		if(str1Idx==0 || str2Idx==0){
			return 0;
		}
		if(cache[str1Idx-1][str2Idx-1]!=0){
			return cache[str1Idx-1][str2Idx-1];
		}
		
		int sum=0;
		if(str1.charAt(str1Idx-1)==str2.charAt(str2Idx-1)){
			sum= 1 + _lcs1(str1, str2, str1Idx-1, str2Idx-1, cache);
		}else{
			int sum1 = _lcs1(str1, str2, str1Idx-1, str2Idx, cache);	// Reduce str1
			int sum2 = _lcs1(str1, str2, str1Idx, str2Idx-1, cache);	// Reduce str2
			sum= Math.max(sum1, sum2);
		}
		cache[str1Idx-1][str2Idx-1]=sum;
		return sum;
	}
	
	
}