package recursion;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class _8_PalindromePartitioning{
	@Test
	public void testPalindromePartitioning(){
		String input = "0204451881";
		System.out.println("Input: "+ input + " \n\nOutput:");
		palindromePartitioning(input);
		
	}
	
	/**
		Problem: 
		Given a String, partition it into smaller strings such that each string is a palindrome. Print all such possible combinations.
		Example:
		Input: "0204451881"
		Output:
		Combination 1: [020, 4, 4, 5, 1, 88, 1]
		Combination 1: [020, 4, 4, 5, 1881]
		etc.
		
		Solution logic: 
		Here I am using the normal backtracking approach of Recursion where we exhaustively do following:
		Step : Pick a range of consecutive elements in the input Array, check if they form a Palindrome by calling isPalindrome(), put that String range in an intermediateList, go to the next level of Recursion stack where this same process will be repeated, come back from the Recursion stack and Delete that element from the intermediateList.
		Step: Do this above step for every element in the input element
		Step: Don't forget to check whether a smaller string range (of consecutive elements) isPalindrome() or not, only then add it in intermediateList
		Step: In each recursion stack level, you must check whether the end of string is reached. At that point you have to print the intermediateList and return from that recursion stack level. This is called "Base condition" in the Recursion algorithm.
		
		Asymptotic analysis:
		Time complexity: O(n x 2^n). Why 2^n ? Okay in Math whenever we have to choose all combinations of smaller Subset from any Bigger Set, then we go to each element and then we can either choose it or ignore it. This means we have 2 options (0 for ignoring or 1 for selecting that element) for each element. So if 3 elements are there as input, then we have 2 x 2 x 2 possible choices to make. Therefore If we have n input elements, then we have 2^n choices to make.
		Now that we are clear on why 2^n, therefore on top of that in below code we notice that in each Recursion stack level, we are spending n operations also, thus our final Time complexity = O(n x 2^n).
		Do refer to my other code in this same Github repository for more explanation on Time complexity here -> "PowerSet.java"
		Space complexity: O(n) i.e. the no. of levels of Recursion stack levels. 
		Use Paper and Pen to Visualize a Tree to see this in action.
		
	*/
	public void palindromePartitioning(String input){
		if(input==null || input.length()==0){
			return;
		}
		_palindromePartitioning(input, 0, new ArrayList<String>());
	}
	
	public void _palindromePartitioning(String input, int idx, List<String> intermediateList){
		if(idx==input.length()){
			System.out.println(intermediateList);
			return;
		}
		for(int i=idx; i<input.length(); i++){
			String intermediateStr = input.substring(idx, i+1);
			if(!isPalindrome(intermediateStr)){
				continue;
			}
			intermediateList.add(intermediateStr);
			_palindromePartitioning(input, i+1, intermediateList);
			intermediateList.remove(intermediateList.size()-1);
		}
		
	}

	private boolean isPalindrome(String prefix) {
		for (int i = 0, j = prefix.length() - 1; i < j; ++i , --j) {
			if (prefix.charAt (i) != prefix.charAt(j)) {
				return false ;
			}
		}
		return true;
	}
}