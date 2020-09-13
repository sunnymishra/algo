package dynamicprogramming;

import org.junit.Test;
import org.junit.Assert;
import java.util.Arrays;


public class MakePalindromeByDeleting{
	@Test
	public void makePalindrome1(){
		Assert.assertEquals(1, makePalindrome1("BCBK"));
		Assert.assertEquals(1, makePalindrome1("BCDCBK"));
		Assert.assertEquals(2, makePalindrome1("ZKBCB"));
		Assert.assertEquals(3, makePalindrome1("BRWCZB"));
		Assert.assertEquals(2, makePalindrome1("BRWZB"));
		Assert.assertEquals(1, makePalindrome1("BCZB"));
	}
	@Test
	public void makePalindrome2(){
		Assert.assertEquals(1, makePalindrome2("BCBK"));
		Assert.assertEquals(1, makePalindrome2("BCDCBK"));
		Assert.assertEquals(2, makePalindrome2("ZKBCB"));
		Assert.assertEquals(3, makePalindrome2("BRWCZB"));
		Assert.assertEquals(2, makePalindrome2("BRWZB"));
		Assert.assertEquals(1, makePalindrome2("BCZB"));
	}
	
	/**
		Problem:
		Given a string str, compute the minimum number of characters you need to delete from str to make the resulting string a palindrome. A palindrome is a string which read the same from both ends eg. ABA
		Example: Input str="BCDCBK", Output=1, since only 1 character "K" needs to be deleted to make the string a Palindrome.
		
		Solution logic:
		In Below logic we are going to use the Longest common subsequence pattern of DP problems. Keep reducing the string length for every Recursion stack level.
		If the startIdx and endIdx match then return 0, if they don't match then it means we have to remove 1 Element. We don't know if removing 1 element from 
		Left Index is better or Removing 1 Element from Right Index is better. So we call the same Recursion function 2 times: 
		1 time with reducing left index by -1 and 1 time with reducing the right index by -1.
		
		Reference: https://www.techiedelight.com/find-minimum-number-deletions-convert-string-into-palindrome/
		NOTE: For Appraoch2 refer to below 1 more function: makePalindrome2()
	**/
	public int makePalindrome1(String str){
		if(str==null || str.isEmpty()) return 0;
		int[][]cache=new int[str.length()][str.length()];
		int deleteSteps=_makePalindrome1(str, 0, str.length(), cache);
		return deleteSteps;
		
	}
	private int _makePalindrome1(String str, int leftIdx, int rightIdx, int[][]cache){
		if (leftIdx >= rightIdx-1)
			return 0;

		int deleteSteps=0;
		if(cache[leftIdx][rightIdx-1]!=0) {
			deleteSteps=cache[leftIdx][rightIdx-1];
		}
		
		if (str.charAt(leftIdx) == str.charAt(rightIdx-1)) {
			deleteSteps=_makePalindrome1(str, leftIdx + 1, rightIdx - 1, cache);
		}else {
			deleteSteps=1 + Math.min(_makePalindrome1(str, leftIdx, rightIdx - 1, cache), 
							_makePalindrome1(str, leftIdx + 1, rightIdx, cache));
		}
		cache[leftIdx][rightIdx-1]=deleteSteps;
		return deleteSteps;
	}

	/**
		Problem:
		Given a string str, compute the minimum number of characters you need to delete from str to make the resulting string a palindrome. A palindrome is a string which read the same from both ends eg. ABA
		Example: Input str="BCDCBK", Output=1, since only 1 character "K" needs to be deleted to make the string a Palindrome.
		
		Solution logic:
		In Below logic we are going to use the Longest common subsequence pattern of DP problems with a variation. 1st of all we will find out the longest Palindrome subsequence.
		Then we will reduce this value from the String length. THis will be our answer i.e. Min no. of Delete required to achieve a palindrome.
		
		To find the Longest Palindrome subsequence we will do the usual Longest common subsquence approach:
		Keep reducing the string length for every Recursion stack level.
		If the startIdx and endIdx match then return 0, if they don't match then it means we have to remove 1 Element. We don't know if removing 1 element from 
		Left Index is better or Removing 1 Element from Right Index is better. So we call the same Recursion function 2 times: 
		1 time with reducing left index by -1 and 1 time with reducing the right index by -1.
		
		NOTE: In previous method makePalindrome1() Math.min() was used and this below makePalindrome2() Math.max() is used.
	**/
	public int makePalindrome2(String str){
		if(str==null || str.isEmpty()) return 0;
		int[][]cache=new int[str.length()][str.length()];
		int deleteSteps=str.length() - _longestPalindromeSubsequence(str, 0, str.length(), cache);
		return deleteSteps;
		
	}
	private int _longestPalindromeSubsequence(String str, int leftIdx, int rightIdx, int[][]cache){
		if(leftIdx > rightIdx-1){
			return 0;
		}
		if(leftIdx==rightIdx-1) {
			return 1;
		}
		if(cache[leftIdx][rightIdx-1]!=0) {
			return cache[leftIdx][rightIdx-1];
		}
		int palindromeLen=0;
		if(str.charAt(leftIdx)==str.charAt(rightIdx-1)){
			palindromeLen=2+ _longestPalindromeSubsequence(str, leftIdx+1, rightIdx-1, cache);
		}else{
			int palindromeLen1=_longestPalindromeSubsequence(str, leftIdx+1, rightIdx, cache);
			int palindromeLen2=_longestPalindromeSubsequence(str, leftIdx, rightIdx-1, cache);
			
			palindromeLen = Math.max(palindromeLen1, palindromeLen2);
		}
		cache[leftIdx][rightIdx-1]=palindromeLen;
		return palindromeLen;
	}
	
	
}