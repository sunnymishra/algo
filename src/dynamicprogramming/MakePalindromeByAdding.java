package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;

public class MakePalindromeByAdding{
	@Test
	public void makePalindrome(){
		Assert.assertEquals(0, makePalindrome("ABA"));
		Assert.assertEquals(1, makePalindrome("ABB"));
		Assert.assertEquals(2, makePalindrome("ABC"));
		Assert.assertEquals(2, makePalindrome("ABZKRBA"));
		Assert.assertEquals(2, makePalindrome("ABCDA"));
	}
	
	@Test
	public void makePalindrome2(){
		Assert.assertEquals(0, makePalindrome2("ABA"));
		Assert.assertEquals(1, makePalindrome2("ABB"));
		Assert.assertEquals(2, makePalindrome2("ABC"));
		Assert.assertEquals(2, makePalindrome2("ABZKRBA"));
		Assert.assertEquals(2, makePalindrome2("ABCDA"));
	}
	
	@Test
	public void makePalindrome3(){
		Assert.assertEquals(0, makePalindrome3("ABA"));
		Assert.assertEquals(1, makePalindrome3("ABB"));
		Assert.assertEquals(2, makePalindrome3("ABC"));
		Assert.assertEquals(2, makePalindrome3("ABZKRBA"));
		Assert.assertEquals(2, makePalindrome3("ABCDA"));
	}
	
	
	/**
		Problem:
		Given a String, find out how many characters need to be added to make it a Palindrome.
		Example1: Input="AA" Palindrome="AA", Result=0
		Example2: Input="AB" Palindrome="BAB", Result=1  <- "B" got added on left most side
		Example3: Input="ABCA" Palindrome="ACBCA", Result=1 <- "C" got added in between
		
		Solution logic:
		Basically we need to find how many charaters on the left hand of the String don't have a matching character on the right hand. At every recursion stack level we have 2 choices:
		IF: Character on left most index matched character on right most index, we will simply call the next recursion level by reducing the left most index and increasing the right most index, in the hope that the next recursion level may find some non-matching characters on both end of indexes. 
		ELSE: Character on lefft most index didn't match character on right most index, and we will call the next recursion level and whatever value we get from that recursion call, we will increment the result by +1 because in the current stack level we found 1 character which didn't have a matching character on the right index. Also while calling the next recursion level we will have 2 choices: 1.) We can increase the left hand index and call the next recursion level in the hope that the next recursion level will have some non-matching character, or 2.) We can decrease the right hand index and call the next recursion level in the hope that the next recursion level will have some non-matching character.
		
		Reference : https://www.geeksforgeeks.org/minimum-insertions-to-form-a-palindrome-dp-28/
		
		Asymptotic analysis:
		Time complexity: O(2 ^ n) without Memoization, since at every step in the Recursion stack we get 2 options of calling further below Level
		Time complexity: O(n^2) with Memoization
		Space complexity: O(n) without Memoization, due to the level/depth of Recursion tree is max (m+n).
		Space complexity: O(n^2) with Memoization, since the Array size is [n rows x n columns]
			
		NOTE: This makePalindrome1() method is approach 1. For approach 2 refer to method makePalindrome2()
	*/
	public int makePalindrome(String str){
		if(str==null) return 0;
		
		int[][] cache=new int[str.length()][str.length()];
		return _makePalindrome1(str, 0, str.length()-1, cache);
	}
	
	private int _makePalindrome1(String str, int leftIdx, int rightIdx, int[][] cache){
		if(leftIdx>=rightIdx) return 0;
		
		if(cache[leftIdx][rightIdx]!=0){
			return cache[leftIdx][rightIdx];
		}
		int edits=0;
		if(str.charAt(leftIdx)==str.charAt(rightIdx)){
			edits=_makePalindrome1(str, leftIdx+1, rightIdx-1, cache);
		}else{
			int edits1=_makePalindrome1(str, leftIdx+1, rightIdx, cache);
			int edits2=_makePalindrome1(str, leftIdx, rightIdx-1, cache);
			edits=Math.min(edits1, edits2) + 1;
		}
		cache[leftIdx][rightIdx]=edits;
		return edits;
	}
	
	
	
	/**
		Problem:
		Given a String, find out how many characters need to be added to make it a Palindrome.
		Example1: Input="AA" Palindrome="AA", Result=0
		Example2: Input="AB" Palindrome="BAB", Result=1  <- "B" got added on left most side
		Example3: Input="ABCA" Palindrome="ACBCA", Result=1 <- "C" got added in between
		
		Solution logic:
		It is a 2 step solution. Step 1: Find the Longest common subsequence (LCS). Step 2: Minus the LCS value from the Original String length to get the answer.
		
		IF String1 current index = String2 current index:
			Call the next Recursion level by reducing the index of both string, to check the next indexes
			Whatver value comes back from this above call, Add +1 to it, since in current level 1 character matched.
		ELSE:
			Call the next Recursion level by Reducing only the Index of String1
			Call the next Recursion level by Reducing only the Index of String2
			Take the max returned by above 2 calls, and return it to the previous Recursion stack level.
		
		Reference : https://www.geeksforgeeks.org/minimum-insertions-to-form-a-palindrome-dp-28/
		
		Find the Longest common subsequence comparing original string and its reverese string. This longest common subsequence will tell us how many characters are there in the Palindrome inside the Original string.
		Example: Original String = "ABCDBA", Reverse String = "ABDCBA", 
		Longest Common Subsequence="ABBA" = 4 length. Therefore the non-matching characters=6-4=2
		So our answer=2, meaning we need to add 2 more characters into the original string "ABCDBA" to make it a Palindrome.
		
		Refer to LongestCommonSubsequence.java file in this Repository project for proper Solution logic as well as Time and Space complexity of LCS algorithm.
	*/
	public int makePalindrome2(String str){
		if(str==null) return 0;
		
		String reverseStr = reverseString(str);
		int[][] cache=new int[str.length()][str.length()];
		int lcs = _lcs1(str, reverseStr, str.length()-1, reverseStr.length()-1, cache);
		int edits=str.length()-lcs;
		return edits;
	}
	private int _lcs1(String str1, String str2, int str1Idx, int str2Idx, int[][] cache){
		if(str1Idx<0 || str2Idx<0) return 0;
		
		int lcs=0;
		if(cache[str1Idx][str2Idx]!=0){
			return cache[str1Idx][str2Idx];
		}
		
		if(str1.charAt(str1Idx)==str2.charAt(str2Idx)){
			
			lcs= 1+ _lcs1(str1, str2, str1Idx-1, str2Idx-1, cache);
		}else{
			int lcs1=_lcs1(str1, str2, str1Idx-1, str2Idx, cache);
			int lcs2=_lcs1(str1, str2, str1Idx, str2Idx-1, cache);
			lcs=Math.max(lcs1, lcs2);
		}
		cache[str1Idx][str2Idx]=lcs;
		return lcs;
	}
	private String reverseString(String str){
		StringBuffer sb=new StringBuffer();
		for(int i=str.length()-1; i>=0; i--)
			sb.append(str.charAt(i));
		return sb.toString();
	}
	
	/**
		Below Algorithm is a Time complexity optimization compared to previous method makePalindrome2(). The difference is that makePalindrome2() is Recursive algorithm or Top-down approach & makePalindrome3() is an Iterative algorithm or Bottom-up approach.
		
		In below method makePalindrome3() Iterative approach the Recurrence relation for finding the LCS (Longest common subsequence) is ditto same as makePalindrome2 LCS.
		IF String1 current index = String2 current index:
			IF current level has (i, j), then get the LCS value for the previous level from the cache Array for (i-1, j-1) Add +1 to it, since in current level 1 character matched. This is exactly same as the Recursive approach.
		ELSE IF String character didn't match, THEN:
			GET LCS value for previous Level from the Cache array where (i-1, j) i.e. decrease the index for 1st string
			GET LCS value for previous Level from the Cache array where (i, j-1) i.e. decrease the index for 2nd string
			Take the max returned by above cache array fetch. This is the LCS value for current level (i, j)  where i and j are indexes of str1 and str2. Add this LCS value to the Cache array at i,j index.
			
		Asymptotic analysis:
		Time complexity: O(N^2), since we are running 2 Nested FOR loops for length n
		Auxiliary Space: O(N^2), since we are storing a 2D array of since n x n.
	
	*/
	public int makePalindrome3(String str){
		if(str==null) return 0;
		
		String reverseStr = reverseString(str);
		
		int lcs = _lcs2(str, reverseStr);
		int edits=str.length()-lcs;
		return edits;
	}
	public int _lcs2(String str1, String str2){
		int[][] cache=new int[str1.length()+1][str2.length()+1];
		
		for(int i=1; i<str1.length()+1; i++){
			for(int j=1; j<str2.length()+1; j++){
				if(str1.charAt(i-1)==str2.charAt(j-1)){
					cache[i][j]=cache[i-1][j-1] + 1;
				}else{
					int lcs1=cache[i-1][j];
					int lcs2=cache[i][j-1];
					int lcs=Math.max(lcs1, lcs2);
					cache[i][j]=lcs;
				}
			}
		}
		return cache[str1.length()][str2.length()];
	}
}
