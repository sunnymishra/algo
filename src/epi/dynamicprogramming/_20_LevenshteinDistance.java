package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;

public class _20_LevenshteinDistance{
	@Test
	public void levenshteinDistance(){
		Assert.assertEquals(4, levenshteinDistance("Saturday", "Sundays"));
		Assert.assertEquals(8, levenshteinDistance("Orchestra", "Carthorse"));
		Assert.assertEquals(1, levenshteinDistance("Sat", "Sut"));
	}
	
	/**
		Problem: 
		In 1965, Vladimir Levenshtein defined the distance between two words as the minimum number of "edits" it would take to transform the 1 word into another word. Here a single edit is the insertion, deletion, or replacement of a single character. 
		Example: 
		Input str1= "Saturday" str2="Sundays"
		Levenshtein distance= 4
		Why 4? In str1, Delete the first 'a' and 't', substitute 'r' by 'n' and insert the trailing 's'.
		
		Solution logic:
		When we go via the Top-down approach, i.e. in a Recursion tree when we pick the last index of both the input String i.e. str1 and str2, and we keep moving towards the 0th index, then at every Recursion stack level we have a choice to make like below:
		IF String1 current index = String2 current index:
			Since the character matched, we are interested in the Index where characters didn't match, so Call the next Recursion level by reducing the index of both string
		ELSE:
			1. Call the next Recursion level by Reducing only the Index of String1
			2. Call the next Recursion level by Reducing only the Index of String2
			Take the MIN of the distances returned by above 2 calls, add +1 this distance(since current level characters didn't match so 1 'edit' is required) and return it to the previous Recursion stack level.
	
		Asymptotic analysis:
			Time complexity: O(2^ (m+n)) without Memoization i.e. If we don't use the Cache Array to store the intermediate results. Why 2^(m+n)? If you draw the Recursion tree with Pen and Paper, for a worst case scenario in which none of the characters of 2 Strings match eg. str1="ABC", str2="XYZ". In this case the Depth of the Tree will be m+n, where m=str1 length and n=str2 length. Now that we have understood that the Depth of the Tree=m+n, you will also notice that at any level in the Recursion Tree, we have 2 choices to make. If you do the Math it comes to 2^(m+n), therefore Time complexity = O(2 ^ (m+n))
			
			Time complexity: O(m x n) with Memoization
			
			Space complexity: O(m + n) without Memoization, due to the level/depth of Recursion tree is max (m+n).
			Space complexity: O(m x n) with Memoization, since the Array size is [m rows x n columns]
			
		Note: There is a scope to reduce the Space complexity from current O(m x n) to simple O(n), by changing the 2 Dimensional Cache Array to a Single Dimensional array.
	**/
	public int levenshteinDistance(String str1, String str2){
		if(str1==null || str2==null) return 0;
		int[][] cache=new int[str1.length()][str2.length()];
		int distance= _levenshteinDistance(str1, str2, str1.length(), str2.length(), cache);
		print2DArray(cache);
		return distance;
	}
	
	private int _levenshteinDistance(String str1, String str2, int str1Idx, int str2Idx, int[][] cache){
		if(str1Idx==0) {
			// Since str1 is 0, and str2 length is lets say X. Then it will take X steps for str1 to become str2. Therefore the Levenshtein distance between str1 & str2=str2's length in this IF condition.
			return str2Idx;
		}
		if(str2Idx==0) {
			// Since str2 is 0, and str2 length is lets say X. Then it will take X steps for str2 to become str1. Therefore the Levenshtein distance between str1 & str2=str1's length in this IF condition.
			return str1Idx;
		}
		if(cache[str1Idx-1][str2Idx-1]!=0){
			return cache[str1Idx-1][str2Idx-1];
		}
		
		int distance=0;
		if(str1.charAt(str1Idx-1)==str2.charAt(str2Idx-1)){
			distance= _levenshteinDistance(str1, str2, str1Idx-1, str2Idx-1, cache);
		}else{
			int distance1=_levenshteinDistance(str1, str2, str1Idx-1, str2Idx, cache); // Remove 1 char
			int distance2=_levenshteinDistance(str1, str2, str1Idx, str2Idx-1, cache); // Add 1 char
			int distance3=_levenshteinDistance(str1, str2, str1Idx-1, str2Idx-1, cache); // Replace 1 char
			distance= Math.min(Math.min(distance1, distance2), distance3) + 1;
		}
		cache[str1Idx-1][str2Idx-1]=distance;
		return distance;
	}
	
	private void print2DArray(int[][] arr){
		System.out.println("\n\n");
		for(int i=0; i<arr.length; i++){
			System.out.println(Arrays.toString(arr[i]));
		}
	}
	
}